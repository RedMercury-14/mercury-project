package by.myproject.main.view;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class EmptyTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.println("The tag works");
		} catch (IOException e) {
			throw new JspException(e);
		}
		
		return SKIP_BODY;
	}
	
	

}
