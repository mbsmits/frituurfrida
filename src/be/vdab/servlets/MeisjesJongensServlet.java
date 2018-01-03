package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/meisjesjongens.htm")
public class MeisjesJongensServlet extends HttpServlet {
	
	private static final long	serialVersionUID	= 1L;
	private static final String	VIEW				= "/WEB-INF/JSP/meisjesjongens.jsp";
	
	private static final String REDIRECT_URL = "/meisjesjongens.htm";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String value = request.getParameter("meisjesjongens");
		Cookie cookie = new Cookie("meisjesjongens", value);
		cookie.setMaxAge(30 * 60);
		response.addCookie(cookie);
		response.sendRedirect(request.getContextPath() + REDIRECT_URL);
	}
	
}
