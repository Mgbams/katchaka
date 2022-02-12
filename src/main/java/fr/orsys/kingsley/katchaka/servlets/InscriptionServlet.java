package fr.orsys.kingsley.katchaka.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.orsys.kingsley.katchaka.business.Genre;
import fr.orsys.kingsley.katchaka.business.Interet;
import fr.orsys.kingsley.katchaka.business.Personne;
import fr.orsys.kingsley.katchaka.business.Statut;
import fr.orsys.kingsley.katchaka.business.Ville;
import fr.orsys.kingsley.katchaka.service.GenreService;
import fr.orsys.kingsley.katchaka.service.InteretService;
import fr.orsys.kingsley.katchaka.service.PersonneService;
import fr.orsys.kingsley.katchaka.service.StatutService;
import fr.orsys.kingsley.katchaka.service.VilleService;
import fr.orsys.kingsley.katchaka.service.impl.GenreServiceImpl;
import fr.orsys.kingsley.katchaka.service.impl.InteretServiceImpl;
import fr.orsys.kingsley.katchaka.service.impl.PersonneServiceImpl;
import fr.orsys.kingsley.katchaka.service.impl.StatutServiceImpl;
import fr.orsys.kingsley.katchaka.service.impl.VilleServiceImpl;

/**
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GenreService genreService;
	private InteretService interetService;
	private VilleService villeService;
	private StatutService statutService;
	private PersonneService personneService;
	private HashMap<String, String> errors = new HashMap<String, String>();
	private static HttpSession session;
	private Boolean defaultSelect = true;

	private static final int NOMBRES_DE_GENRES = 2;
	private static final int NOMBRES_DE_INTERETS = 4;
	private static final int NOMBRES_DE_VILLES = 2;
	private static final int NOMBRES_DE_STATUTS = 4;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InscriptionServlet() {
		genreService = new GenreServiceImpl();
		interetService = new InteretServiceImpl();
		villeService = new VilleServiceImpl();
		statutService = new StatutServiceImpl();
		personneService = new PersonneServiceImpl();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Effacer l'erreur sur la page de connexion s'il existe on cliquant sur s'inscrire
		session = request.getSession();
		if (session.getAttribute("loginError") != null) {
			session.removeAttribute("loginError");
		}

		// Insérer genres
		Genre homme = new Genre("homme");
		Genre femme = new Genre("femme");

		if (genreService.findAll().size() < NOMBRES_DE_GENRES) {
			genreService.create(homme);
			genreService.create(femme);
		}

		// Insérer intéret
		Interet danser = new Interet("danser");
		Interet voyager = new Interet("voyager");
		Interet exercise = new Interet("exercise");
		Interet sports = new Interet("sports");

		if (interetService.findAll().size() < NOMBRES_DE_INTERETS) {
			interetService.create(danser);
			interetService.create(voyager);
			interetService.create(exercise);
			interetService.create(sports);
		}

		// Insérer ville
		Ville lyon = new Ville("lyon");
		Ville grenoble = new Ville("grenoble");

		if (villeService.findAll().size() < NOMBRES_DE_VILLES) {
			villeService.create(lyon);
			villeService.create(grenoble);
		}

		// Insérer statut
		Statut celibataire = new Statut("célibataire");
		Statut separe = new Statut("séparé(e)");
		Statut divorce = new Statut("divorcé(e)");
		Statut veuve = new Statut("veuf/veuve");

		if (statutService.findAll().size() < NOMBRES_DE_STATUTS) {
			statutService.create(celibataire);
			statutService.create(separe);
			statutService.create(divorce);
			statutService.create(veuve);
		}

		// à envoyé au servlet
		List<Genre> genres = genreService.findAll();
		List<Genre> genresRecherche = genreService.findAll();
		List<Statut> statuts = statutService.findAll();
		List<Ville> villes = villeService.findAll();
		List<Interet> interets = interetService.findAll();

		request.setAttribute("genres", genres);
		request.setAttribute("genresRecherche", genresRecherche);
		request.setAttribute("statuts", statuts);
		request.setAttribute("villes", villes);
		request.setAttribute("interets", interets);
		request.setAttribute("defaultSelect", defaultSelect);

		request.getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		defaultSelect = false;
		session = request.getSession();
		if (session.getAttribute("errors") != null && session.getAttribute("inscrireButtonClicked") != null) {
			session.removeAttribute("errors");
			session.removeAttribute("inscrireButtonClicked");
		}

		String pseudo = "";
		String email = "";
		String motDePasse = "";
		String bio = "";
		Long ville = 0L;
		Long genre = 0L;
		Long genresRecherche = 0L;
		Long statut = 0L;
		String[] interets = null;
		boolean fumeur = false;
		LocalDate dateDeNaissance = null;

		if (!request.getParameter("inscrireButtonClicked").isEmpty()) {
			session.setAttribute("inscrireButtonClicked", false);
			if (isPseudoValid(request.getParameter("pseudo"))) {
				pseudo = request.getParameter("pseudo");
				if (errors.size() > 0) {
					session.setAttribute("pseudo", pseudo);
					errors.remove("pseudoError");
				}
			} else {
				errors.put("pseudoError", "Le pseudo est invalide");
				session.setAttribute("pseudo", pseudo);
			}

			if (isEmailValid(request.getParameter("email"))) {
				email = request.getParameter("email");
				if (errors.size() > 0) {
					session.setAttribute("email", email);
					errors.remove("emailError");
				}
			} else {
				errors.put("emailError", "Invalide email");
				session.setAttribute("email", email);
			}

			if (isMotdePasseValid(request.getParameter("motDePasse"))) {
				motDePasse = request.getParameter("motDePasse");
				if (errors.size() > 0) {
					session.setAttribute("motDePasse", motDePasse);
					errors.remove("motDePasseError");
				}
			} else {
				errors.put("motDePasseError", "Le mot de passe est invalide");
				session.setAttribute("motDePasse", motDePasse);
			}

			if (isBioValid(request.getParameter("bio"))) {
				bio = request.getParameter("bio");
				if (errors.size() > 0) {
					session.setAttribute("bio", bio);
					errors.remove("bioError");
				}
			} else {
				errors.put("bioError", "Le bio dois comporter au moins 20 caractères");
				session.setAttribute("bio", bio);
			}

			if (request.getParameter("ville") != null) {
				ville = Long.parseLong(request.getParameter("ville"));
				if (errors.size() > 0) {
					session.setAttribute("ville", ville);
					errors.remove("ville");
				}
			} else {
				errors.put("villeError", "Il faut choisir une ville");
				session.setAttribute("ville", ville);
			}

			if (request.getParameter("genre") != null) {
				genre = Long.parseLong(request.getParameter("genre"));
				if (errors.size() > 0) {
					session.setAttribute("genre", genre);
					errors.remove("genre");
				}
			} else {
				errors.put("genreError", "Il faut choisir une genre");
				session.setAttribute("genre", genre);
			}

			if (request.getParameter("genreRecherche") != null) {
				genresRecherche = Long.parseLong(request.getParameter("genreRecherche"));
				if (errors.size() > 0) {
					session.setAttribute("genresRecherche", genresRecherche);
					errors.remove("genresRechercheError");
				}
			} else {
				errors.put("genresRechercheError", "Il faut choisir une genresRecherche");
				session.setAttribute("genresRecherche", genresRecherche);
			}

			if (request.getParameter("statut") != null) {
				statut = Long.parseLong(request.getParameter("statut"));
				if (errors.size() > 0) {
					session.setAttribute("statut", statut);
					errors.remove("statutError");
				}
			} else {
				errors.put("statutError", "Il faut choisir un statut");
				session.setAttribute("statut", statut);
			}

			if (request.getParameterValues("interet") != null) {
				interets = request.getParameterValues("interet");
				if (errors.size() > 0) {
					session.setAttribute("interets", interets);
					errors.remove("interetsError");
				}
			} else {
				errors.put("interetsError", "Il faut choisir au moins un interet");
				session.setAttribute("interets", interets);
			}

			if (request.getParameter("fumeur") == "on") {
				fumeur = true;
				if (errors.size() > 0) {
					session.setAttribute("fumeur", fumeur);
				}
			} else {
				fumeur = false;
			}

			if (request.getParameter("dateDeNaissance") != "") {

				// LocalDateTime Format
				String strDate = request.getParameter("dateDeNaissance");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate dateDeNaissanceFormatte = LocalDate.parse(strDate, formatter);

				Period period = (dateDeNaissanceFormatte).until(LocalDate.now());
				int yearsBetween = period.getYears();

				if (yearsBetween >= 18) {
					dateDeNaissance = dateDeNaissanceFormatte;
					if (errors.size() > 0) {
						session.setAttribute("dateDeNaissance", dateDeNaissance);
						errors.remove("dateDeNaissanceError");
						errors.remove("horsDeRangeDateDeNaissanceError");
					}
				} else {
					errors.put("horsDeRangeDateDeNaissanceError", "Il faut avoir au moins 18 ans pour cette aventure!");
					session.setAttribute("dateDeNaissance", request.getParameter("dateDeNaissance"));
				}
			} else {
				errors.put("dateDeNaissanceError", "Il faut choisir une date de naissance");
			}
			// Ajouter error au session
			session.setAttribute("errors", errors);
			session.setAttribute("inscrireButtonClicked", true);

		}

		Interet interet = new Interet();
		// Personne personne = new Personne();
		List<Interet> interetLists = new ArrayList<>();

		if (interets != null && interets.length > 0) {
			for (int i = 0; i < interets.length; i++) {
				Long id = Long.parseLong(interets[i]);
				interet = interetService.findOne(id);
				interetLists.add(interet);
			}
		}

		if (errors.size() == 0) {
			// Ajouter des logis des soumissions ci-dessous
			Ville villeChoisit = villeService.findOne(ville);
			Statut statutChoisit = statutService.findOne(statut);
			Genre genreRechercheChoisit = genreService.findOne(genresRecherche);
			Genre genreChoisit = genreService.findOne(genre);

			System.out.println("Genre result given: " + genreChoisit);

			Personne nouveauUtilisateur = new Personne(pseudo, motDePasse, email, dateDeNaissance, bio,
					Personne.getNbCreditsInitial(), fumeur, villeChoisit, statutChoisit, genreRechercheChoisit,
					genreChoisit);

			nouveauUtilisateur.setInterets(interetLists);
			personneService.create(nouveauUtilisateur);

			// Effacer les contenus après la soumission des formulaires
			pseudo = "";
			email = "";
			motDePasse = "";
			bio = "";
			ville = 0L;
			genre = 0L;
			genresRecherche = 0L;
			statut = 0L;
			interets = null;
			fumeur = false;
			dateDeNaissance = null;
		}

		response.sendRedirect("inscription");
	}

	// Validation d'email utilisant Regexp
	public static boolean isEmailValid(String email) {
		// String regex =
		// "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[\\a-zA-Z]{2,6}";
		String regex = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return (email.trim() != null) && (!email.trim().equals("")) && matcher.find();
	}

	// Validation de pseudo
	public static boolean isPseudoValid(String str) {
		String regx = "^[\\p{L} .'-]+$";
		Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		return (str != null) && (!str.equals("")) && matcher.find();
	}

	// Validation de mot de passe
	public static boolean isMotdePasseValid(String str) {
		return (str.trim() != null) && (!str.trim().equals("")) && str.trim().length() >= 5;
	}

	// Validation de mot de passe
	public static boolean isBioValid(String str) {
		return (str.trim() != null) && (!str.trim().equals("")) && str.trim().length() >= 20;
	}

}
