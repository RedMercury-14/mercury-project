package by.myproject.main.view;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class ViewTag extends TagSupport {
	// простой тег, который выводит текст в теле тега, если роль равна админу
	
	private static final long serialVersionUID = 1L;
	private String role;

	@Override
	public int doStartTag() throws JspException {
		
		if(role.equals("admin")) {
			return EVAL_BODY_INCLUDE;
		}		
		return SKIP_BODY;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


}
