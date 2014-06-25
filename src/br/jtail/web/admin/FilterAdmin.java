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

import br.jtail.core.Constants;
import br.jtail.model.AdminUser;

public class FilterAdmin implements Filter {

    @Override
    public void destroy() {
	// TODO Auto-generated method stub

    }

    /**
     * validate user in session to proceed
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
	    FilterChain chain) throws IOException, ServletException {

	HttpServletRequest httpReq = (HttpServletRequest) req;
	HttpSession session = (HttpSession) httpReq.getSession(false);
	AdminUser usr = null;

	if (session != null) {
	    usr = (AdminUser) session
		    .getAttribute(Constants.Admin.SESSION_USER);
	}

	if (usr != null) {
	    // ok
	} else {
	    // usuario nao autenticado
	}

    }

    @Override
    public void init(FilterConfig cfg) throws ServletException {

    }

}
