package fr.orsys.kingsley.katchaka.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.orsys.kingsley.katchaka.business.Personne;
import fr.orsys.kingsley.katchaka.service.PersonneService;
import fr.orsys.kingsley.katchaka.service.impl.PersonneServiceImpl;

/**
 * Servlet implementation class PersonneServlet
 */
@WebServlet("/personne")
public class PersonneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PersonneService personneService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PersonneServlet() {
		personneService = new PersonneServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Long personneId = Long.parseLong(request.getParameter("personneId"));
		Personne personneInfo = personneService.findOne(personneId);
		Personne personneInterets = personneService.findInteretsParPersonneId(personneId);
		Period period = (personneInfo.getDateDeNaissance()).until(LocalDate.now());
		int yearsBetween = period.getYears();

		request.setAttribute("age", yearsBetween);
		request.setAttribute("personneInfo", personneInfo);
		request.setAttribute("personneInterets", personneInterets);

		request.getRequestDispatcher("/WEB-INF/personne.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
