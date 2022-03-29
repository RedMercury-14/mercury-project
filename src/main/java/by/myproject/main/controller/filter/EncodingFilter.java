package by.myproject.main.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//отключен! блокирует css
@WebFilter("/EncodingFilter")
public class EncodingFilter implements Filter {

	private String encoding = "UTF-8";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String encodingParam = filterConfig.getInitParameter("encoding");
		if (encodingParam != null && !encodingParam.isEmpty()) {
			encoding = encodingParam;			
		}
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {		
		servletRequest.setCharacterEncoding(encoding);
		servletResponse.setContentType("text/html;charset=" + encoding);	
		filterChain.doFilter(servletRequest, servletResponse);
	}

}
