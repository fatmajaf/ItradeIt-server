package tn.esprit.SLTS_server.presentation.mbeans;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebFilter;
@WebFilter("/pages/*")
public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("url ="+((HttpServletRequest)request).getRequestURL());
		HttpServletRequest  servletRequest=(HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		LoginBean loginBean=(LoginBean) servletRequest.getSession().getAttribute("loginBean");
		System.out.println("loginn "+servletRequest.getSession().getAttribute("loginBean"));
		
		if ((loginBean != null && loginBean.isLoggedIn()) || servletRequest.getRequestURL().toString().contains("login.jsf")){
		chain.doFilter(servletRequest, servletResponse);
		}
		else {
			servletResponse.sendRedirect(servletRequest.getContextPath()+"/login.jsf");
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
