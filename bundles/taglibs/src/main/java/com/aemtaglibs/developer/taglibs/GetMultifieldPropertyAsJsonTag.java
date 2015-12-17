package com.aemtaglibs.developer.taglibs;

import javax.jcr.Node;
import javax.jcr.Value;
import javax.jcr.RepositoryException;
import javax.jcr.PathNotFoundException;
import javax.jcr.ValueFormatException;
import javax.servlet.jsp.JspException;

import java.io.IOException;
import com.squeakysand.sling.taglib.SlingSimpleTagSupport;

import org.apache.sling.api.resource.Resource;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetMultifieldPropertyAsJsonTag extends SlingSimpleTagSupport {
	
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
        JSONParser parser = new JSONParser();
        StringBuilder json = null;
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
            		
            		json = new StringBuilder();
            		json.append("[");		
                	
        			for(Value value : valuesArray){
        				json.append(value.getString());
        			}
        			
        			json.append("]");

        		}
    		}
    		
    		getSlingRequest().setAttribute(var, json == null ? null : parser.parse(json.toString()));
        }
        catch (ValueFormatException vfe) {
        	vfe.printStackTrace();
        }
        catch (PathNotFoundException pnfe) {
        	pnfe.printStackTrace();
        }
        catch (RepositoryException re) {
        	re.printStackTrace();
        } catch (ParseException pe) {
			pe.printStackTrace();
		}
    	
    	
    }
}