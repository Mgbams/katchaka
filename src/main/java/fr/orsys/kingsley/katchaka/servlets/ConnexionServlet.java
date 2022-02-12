package fr.orsys.kingsley.katchaka.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.orsys.kingsley.katchaka.business.Personne;
import fr.orsys.kingsley.katchaka.service.PersonneService;
import fr.orsys.kingsley.katchaka.service.impl.PersonneServiceImpl;

/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/connexion")
public class ConnexionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HttpSession session;
	private static PersonneService personneService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConnexionServlet() {
		personneService = new PersonneServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String motDePasse = request.getParameter("motDePasse");
		String email = request.getParameter("email");
		String connexionButton = request.getParameter("connexionButton");
		Personne personne = personneService.findByEmailAndMotDePasse(email, motDePasse);
		session = request.getSession();

		if (personne != null) {
			session.setAttribute("personne", personne);

			if (session.getAttribute("loginError") != null) {
				session.removeAttribute("loginError");
			}
			response.sendRedirect("liste-personnes");
		} else {
			session.setAttribute("connexionButton", connexionButton);
			session.setAttribute("loginError", "Identifiants de connexion invalides, veuillez réessayer");
			response.sendRedirect("index");
		}

	}

}
