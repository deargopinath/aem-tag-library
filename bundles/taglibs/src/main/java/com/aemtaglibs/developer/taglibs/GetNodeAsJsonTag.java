package com.aemtaglibs.developer.taglibs;

import javax.jcr.Node;
import javax.jcr.Value;
import javax.jcr.RepositoryException;
import javax.jcr.PathNotFoundException;
import javax.jcr.ValueFormatException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.StringWriter;
import com.squeakysand.sling.taglib.SlingSimpleTagSupport;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.commons.json.jcr.*;

public class GetNodeAsJsonTag extends SlingSimpleTagSupport {
	
	private String var;
	private String name;
	private String resourcePath;
	private String page;
	
	public void setVar(String var) {
		this.var = var;
	}
	
	public void setPage(String page) {
		this.page = page;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setResourcePath(String resourcePath) {
		
		String currentPagePath = this.page;
		
		if(resourcePath == ".")
		{
			this.resourcePath = currentPagePath; //page root node
		}
		else if(resourcePath.startsWith(".") && resourcePath.length() > 1)
		{			
			this.resourcePath = currentPagePath + resourcePath.substring(1); //relative short path
			this.resourcePath = this.resourcePath.replace("//", "/");
		}
		else
		{
			this.resourcePath = resourcePath; //full path
		}	
	}
	
    @Override
    public void doTag() throws JspException, IOException {
    	PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();
        
        String jsx = "";
        try {
            
        	//System.out.println("New Resource path: " + resourcePath);
        	
        	Resource valuesResource = resolveResource(resourcePath);
    
    		if (valuesResource != null) {
        		Node valuesNode = valuesResource.adaptTo(Node.class);
        		
        		if (valuesNode != null)
        		{        		
        			StringWriter stringWriter = new StringWriter();
            		JsonItemWriter jsonWriter = new JsonItemWriter(null);
            		jsonWriter.dump(valuesNode, stringWriter, -1, true);
            		jsx = stringWriter.toString();            		
        		}
    		}
        }
       
        catch (ValueFormatException vfe) {
        	vfe.printStackTrace();
        }
        catch (PathNotFoundException pnfe) {
        	pnfe.printStackTrace();
        }
        catch (RepositoryException re) {
        	re.printStackTrace();
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
    	
    	getSlingRequest().setAttribute(var, jsx);
    }
}