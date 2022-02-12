package fr.orsys.kingsley.katchaka.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.orsys.kingsley.katchaka.business.Interet;
import fr.orsys.kingsley.katchaka.business.Personne;
import fr.orsys.kingsley.katchaka.business.Ville;
import fr.orsys.kingsley.katchaka.service.InteretService;
import fr.orsys.kingsley.katchaka.service.PersonneService;
import fr.orsys.kingsley.katchaka.service.VilleService;
import fr.orsys.kingsley.katchaka.service.impl.InteretServiceImpl;
import fr.orsys.kingsley.katchaka.service.impl.PersonneServiceImpl;
import fr.orsys.kingsley.katchaka.service.impl.VilleServiceImpl;
import fr.orsys.kingsley.katchaka.util.ComparateurDePersonnesSurPseudo;
import fr.orsys.kingsley.katchaka.util.ComparateurDePersonnesSurStatut;
import fr.orsys.kingsley.katchaka.util.ComparateurDePersonnesSurVille;

/**
 * Servlet implementation class ListePersonnes
 */
@WebServlet("/liste-personnes")
public class ListePersonnesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InteretService interetService;
	private VilleService villeService;
	private PersonneService personneService;
	private static final int RECORDS_PER_PAGE = 4;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListePersonnesServlet() {
		interetService = new InteretServiceImpl();
		villeService = new VilleServiceImpl();
		personneService = new PersonneServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Boolean defaultSelect = true;

		// rédiriger vers la page index si la session est expiré
		if (session == null) {
			response.sendRedirect("index");
		}

		Personne personne = (Personne) session.getAttribute("personne");

		List<Personne> listesDesPersonnes = new ArrayList<>();
		List<Personne> totalDePersonneDisponible = new ArrayList<>();

		Long genreRechercheId = personne.getGenreRecherche().getId();

		totalDePersonneDisponible = personneService.recupererPersonnesParGenreRecherches(genreRechercheId);

		/* Pagination avec Suivant, Précédent, Dernier et Premier */

		int page = 1;
		int recordsPerPage = RECORDS_PER_PAGE;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		listesDesPersonnes = personneService.recuperer_personne_et_pagination(genreRechercheId, personne.getId(),
				recordsPerPage, (page - 1) * recordsPerPage);
		int noOfRecords = totalDePersonneDisponible.size();
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		

		List<Ville> villes = villeService.findAll();
		List<Interet> interets = interetService.findAll();

		// Triéer listesDesPersonnes lorsque le bouton correspondant est cliqué
		if (request.getParameter("pseudoSort") != null) {
			Collections.sort(listesDesPersonnes, new ComparateurDePersonnesSurPseudo());
		}

		if (request.getParameter("statutSort") != null) {
			Collections.sort(listesDesPersonnes, new ComparateurDePersonnesSurStatut());
		}

		if (request.getParameter("villeSort") != null) {
			Collections.sort(listesDesPersonnes, new ComparateurDePersonnesSurVille());
		}

		/* FILTRER DES RESULTATS */
		if (request.getParameter("filtrer") != null) {
			defaultSelect = false;
			String villeFiltre = request.getParameter("ville");
			String interetsFiltre = request.getParameter("interet");
			Long expediteurId = personne.getId();

			Long interetId = null;
			Long villeId = null;

			if (interetsFiltre != null && villeFiltre != null) {
				interetId = Long.parseLong(interetsFiltre);
				villeId = Long.parseLong(villeFiltre);
				listesDesPersonnes = personneService.filtrer_personnes_par_ville_et_interet(genreRechercheId,
						expediteurId, villeId, interetId);
			} else if (villeFiltre != null) {
				villeId = Long.parseLong(villeFiltre);
				listesDesPersonnes = personneService.filtrer_personnes_par_ville(genreRechercheId, expediteurId,
						villeId);
			} else if (interetsFiltre != null) {
				interetId = Long.parseLong(interetsFiltre);
				listesDesPersonnes = personneService.filtrer_personnes_par_interet(genreRechercheId, expediteurId,
						interetId);
			}

		}

		request.setAttribute("villes", villes);
		// request.setAttribute("recordsPerPage", recordsPerPage);
		request.setAttribute("interets", interets);
		request.setAttribute("totalDePersonneDisponible", totalDePersonneDisponible.size());
		request.setAttribute("noOfPages", noOfPages);
		request.setAttribute("page", page);
		request.setAttribute("startNombreDeChaquePage", ( (page - 1) * recordsPerPage) + 1 );
		request.setAttribute("endNombreDeChaquePage", ( (page - 1) * recordsPerPage) + recordsPerPage);
		request.setAttribute("listesDesPersonnes", listesDesPersonnes);
		request.setAttribute("defaultSelect", defaultSelect);

		request.getRequestDispatcher("/WEB-INF/listes-personnes.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		// rédiriger vers la page index si la session est expiré
		if (session == null) {
			response.sendRedirect("index");
		}
	}

}
