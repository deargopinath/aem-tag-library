package com.aemtaglibs.developer.taglibs;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import com.squeakysand.sling.taglib.SlingSimpleTagSupport;

public class PropertyWriteTag extends SlingSimpleTagSupport {
	
	private String value;
	private String defaultValue;
	private boolean editMode = true;
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

    @Override
    public void doTag() throws JspException, IOException {
    	PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();
        	
        if (editMode) {
        	if (value.isEmpty()) {
        		if (defaultValue != null)
        			out.print(defaultValue);
        	}
        	else
        		out.print(value);
        }
        else
        	out.print(value);
    }
}