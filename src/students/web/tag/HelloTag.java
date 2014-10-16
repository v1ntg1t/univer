package students.web.tag;


import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;


public final class HelloTag extends TagSupport {


	private String name = null;
	
	
	public int doStartTag() throws JspException {
		try {
			if(name == null) {
				pageContext.getOut().write("'Hello, world!' from tag");
			} else {
				pageContext.getOut().write("'Hello, world! I'm " + name 
						+ "' from tag");
			}
		} catch(IOException e) {
			throw new JspTagException(e.getMessage());
		}
		return SKIP_BODY;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void release() {
		super.release();
		name = null;
	}
}