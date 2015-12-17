package com.aemtaglibs.developer.taglibs;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.scripting.jsp.util.TagUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.WCMMode;

public class SetWCMModeTag extends TagSupport {
	private static final Logger log = LoggerFactory.getLogger(SetWCMModeTag.class);

	private static final long serialVersionUID = 1101206075216515795L;
	protected WCMMode originalMode;
	protected WCMMode newMode;
	protected String mode;
	protected SlingHttpServletRequest slingRequest;
	
	public int doStartTag() throws JspException {
		slingRequest = TagUtil.getRequest(pageContext);
		
		originalMode = WCMMode.fromRequest(slingRequest);
		if(originalMode != null) {
			if((originalMode.equals(WCMMode.EDIT) || originalMode.equals(WCMMode.READ_ONLY)) && newMode != null) {
				newMode.toRequest(slingRequest);
				return EVAL_BODY_INCLUDE;
			}else {
				return EVAL_BODY_INCLUDE;
			}
		}else {
			return SKIP_BODY;
		}
	}
	
	public int doEndTag() throws JspException {
		if(originalMode != null) {
			originalMode.toRequest(slingRequest);
		}
		return EVAL_PAGE;
	}
	
	public void setMode(String mode) {
		try {
			newMode = WCMMode.valueOf(mode);			
		} catch (Exception e) {
			log.error("Invalid WCMMode passed to SetWCMModeTag.");
			e.printStackTrace();
		}
	}
	
}