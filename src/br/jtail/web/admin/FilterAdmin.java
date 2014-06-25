package br.jtail.web.admin;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class FilterAdmin implements Filter {

    private static final long serialVersionUID = 1L;

    @Override
    public void destroy() {
	// TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
	    FilterChain chain) throws IOException, ServletException {
	HttpServletRequest httpReq = (HttpServletRequest) req;
	HttpSession session = (HttpSession) httpReq.getSession(false);
	if (session != null && session.getAttribute(arg0)) {

	}

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
	// TODO Auto-generated method stub

    }

}
