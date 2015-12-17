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
import com.squeakysand.sling.taglib.SlingSimpleTagSupport;
import org.apache.sling.api.resource.Resource;

public class GetMultifieldPropertyTag extends SlingSimpleTagSupport {
	
	private String var;
	private String name;
	private String resourcePath;
	
	public void setVar(String var) {
		this.var = var;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

    @Override
    public void doTag() throws JspException, IOException {
    	PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();
        List<String> values = new ArrayList<String>();
        
        try {
            Resource valuesResource = resolveResource(resourcePath);
    
    		if (valuesResource != null) {
        		Node valuesNode = valuesResource.adaptTo(Node.class);
        		
        		if (valuesNode != null && valuesNode.hasProperty(name)) {
            		Value[] valuesArray = new Value[0];
            
            		if (valuesNode.getProperty(name).isMultiple())
                		valuesArray = valuesNode.getProperty(name).getValues();
            		else
                		valuesArray = new Value[] {valuesNode.getProperty(name).getValue()};
           
            		for (int i = 0; i < valuesArray.length; i++)
                		values.add(valuesArray[i].getString());
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
    	
    	getSlingRequest().setAttribute(var, values);
    }
}