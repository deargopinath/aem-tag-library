package com.aemtaglibs.developer.taglibs;

import javax.servlet.jsp.JspException;

import java.io.IOException;
import com.squeakysand.sling.taglib.SlingSimpleTagSupport;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GetStringAsJsonTag extends SlingSimpleTagSupport {
	
	private String var;
	private String string;
	
	public void setVar(String var) {
		this.var = var;
	}
	
	public void setString(String string) {
		this.string = string;
	}
	
    @Override
    public void doTag() throws JspException, IOException {
        JSONParser parser = new JSONParser();
        Object json;
        try {        		
			json = parser.parse(string);
			getSlingRequest().setAttribute(var, json);        		
        } catch (ParseException pe) {
			getSlingRequest().setAttribute(var, null);        		
		}    	    	
    }
}