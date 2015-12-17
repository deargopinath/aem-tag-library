package com.aemtaglibs.developer.taglibs;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.sling.api.resource.ResourceResolver;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.text.Text;
import com.squeakysand.sling.taglib.SlingSimpleTagSupport;

public class GetUrlFromPathFieldTag extends SlingSimpleTagSupport {
    private enum URL_TYPE{
    	EXTERNAL, VANITY, DEFAULT, SHORT
    }
    private String var;
	private String path;
    private String type = URL_TYPE.DEFAULT.toString();
    private int rootLevel = 2;
    
    @Override
    public void doTag() throws JspException, IOException {
    	ResourceResolver resolver = getResourceResolver();
    	PageManager pageManager = resolver.adaptTo(PageManager.class);
    	JspWriter writer =  getJspContext().getOut();
    	Page page;
    	String url = null;
    	
    	try{
	    	switch(URL_TYPE.valueOf(type.toUpperCase())) {
				case VANITY:
					 page = pageManager.getPage(path);
					
					if(page != null){
						url = page.getVanityUrl();
					}				
					break;
				
				case DEFAULT : 
					url = path + ".html";
					break;
				
				case SHORT :										
					
					if(path != null && path.startsWith("/content")){
						String rootPath = Text.getAbsoluteParent(path, rootLevel);
						url = path.replace(rootPath, "").trim();
						
						if(!url.endsWith(".html")){url +=".html";}					
					}else{
						url=path;
					}
					break;
					
				case EXTERNAL:
					url=path;
					// Dont do anything. Leave url as is.
				default:
					break;
			}
    	}catch(IllegalArgumentException e){
    		url = "#";
    	}
    	getSlingRequest().setAttribute(var, url);
    }
    
    public void setVar(String var){
    	this.var = var;
    }
	public void setPath(String path) {
		this.path = path;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setRootLevel(int rootLevel) {
		this.rootLevel = rootLevel;
	}
}