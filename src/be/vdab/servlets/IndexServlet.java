package be.vdab.servlets;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Adres;
import be.vdab.entities.Gemeente;

@WebServlet(urlPatterns = "/index.htm", name = "indexservlet")
public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String VIEW = "/WEB-INF/JSP/index.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DayOfWeek dayOfWeek = LocalDateTime.now().getDayOfWeek();
		switch (dayOfWeek) {
		case MONDAY:
		case THURSDAY:
			request.setAttribute("openGesloten", "gesloten");
			break;
		default:
			request.setAttribute("openGesloten", "open");
			break;
		}
		Gemeente gemeente = new Gemeente("Deurne", 2100);
		Adres adres = new Adres("Loempialaan", "101", gemeente);
		request.setAttribute("adres", adres);
		request.setAttribute("telefoonnummerHelpdesk", getServletContext().getInitParameter("telefoonnummerHelpdesk"));
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
