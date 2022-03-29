package by.myproject.main.view;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class MainViewTag extends TagSupport{
	private static final long serialVersionUID = 1L;
	private String target;
	private String value;

	@Override
	public int doStartTag() throws JspException {
		
		if(target.equals(value)) {
			return EVAL_BODY_INCLUDE;
		}		
		return SKIP_BODY;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


	
}
