package org.lms.utils;

import java.io.IOException;
import java.util.List;

import javax.faces.application.ResourceHandler;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lms.dto.RoleDTO;

@WebFilter("/Secretary/*")
public class AutherisationSecretary implements Filter {
	private static final String AJAX_REDIRECT_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<partial-response><redirect url=\"%s\"></redirect></partial-response>";

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		String loginURL = request.getContextPath() + "/error-403.xhtml";
		boolean resourceRequest = request.getRequestURI()
				.startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/");
		boolean ajaxRequest = "partial/ajax".equals(request.getHeader("Faces-Request"));
		boolean isAdmin;
		int check = 0;
		List<RoleDTO> rolesOfThisUser = (List<RoleDTO>) session.getAttribute("roles");
		for (RoleDTO roleDTO : rolesOfThisUser) {
			if (roleDTO.getRoleName().equals("Secretary")) {
				check++;
			}
		}
		if (check == 0) {
			isAdmin = false;
		} else {
			isAdmin = true;
		}

		if (isAdmin) {
			if (!resourceRequest) { // Prevent browser from caching restricted resources. See also
									// https://stackoverflow.com/q/4194207/157882
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
				response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
				response.setDateHeader("Expires", 0); // Proxies.
			}

			chain.doFilter(request, response); // So, just continue request.
		} else if (ajaxRequest) {
			response.setContentType("text/xml");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().printf(AJAX_REDIRECT_XML, loginURL); // So, return special XML response instructing JSF
																		// ajax to send a redirect.
		} else {
			response.sendRedirect(loginURL); // So, just perform standard synchronous redirect.
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	// You need to override init() and destroy() as well, but they can be kept
	// empty.
}
