package fr.orsys.kingsley.katchaka.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.orsys.kingsley.katchaka.business.Invitation;
import fr.orsys.kingsley.katchaka.business.Personne;
import fr.orsys.kingsley.katchaka.business.VieCommune;
import fr.orsys.kingsley.katchaka.service.InvitationService;
import fr.orsys.kingsley.katchaka.service.PersonneService;
import fr.orsys.kingsley.katchaka.service.impl.InvitationServiceImpl;
import fr.orsys.kingsley.katchaka.service.impl.PersonneServiceImpl;

/**
 * Servlet implementation class ListesInvitationsServlet
 */
@WebServlet("/listes-invitations")
public class ListesInvitationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static InvitationService invitationService;
	private static PersonneService personneService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListesInvitationsServlet() {
		invitationService = new InvitationServiceImpl();
		personneService = new PersonneServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Long expediteur_id = ((Personne) session.getAttribute("personne")).getId();

		List<Invitation> invitationsEnvoye = invitationService.findByExpediteurId(expediteur_id);

		List<Invitation> invitationsRecu = invitationService.findByDestinataireId(expediteur_id);

		// Date format
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
		String caseStartDate = dateFormat.format(LocalDateTime.now());
		LocalDateTime dateAccepteOrDecliner = LocalDateTime.parse(caseStartDate, dateFormat);

		if (request.getParameter("accepterId") != null) {
			Long idDExpediteur = Long.parseLong(request.getParameter("accepterId"));
			Long idDestinateur = ((Personne) session.getAttribute("personne")).getId();
			
			Personne expediteur = personneService.findOne(idDestinateur);
			Personne destinateur = personneService.findOne(idDExpediteur);
			
			Invitation acceptOuDeclineInvitation = new Invitation(null, dateAccepteOrDecliner, true, null, destinateur,
					expediteur);
			invitationService.update_invitation_pendant_acceptation_ou_declinant(acceptOuDeclineInvitation);
			
		}

		if (request.getParameter("declinerId") != null) {
			Long idDExpediteur = Long.parseLong(request.getParameter("declinerId"));
			Long idDestinateur = ((Personne) session.getAttribute("personne")).getId();
			
			Personne expediteur = personneService.findOne(idDestinateur);
			Personne destinateur = personneService.findOne(idDExpediteur);
			
			Invitation acceptOuDeclineInvitation = new Invitation(null, dateAccepteOrDecliner, false, null, destinateur,
					expediteur);
			invitationService.update_invitation_pendant_acceptation_ou_declinant(acceptOuDeclineInvitation);
		}

		request.setAttribute("invitationsEnvoye", invitationsEnvoye);
		request.setAttribute("invitationsRecu", invitationsRecu);
		request.getRequestDispatcher("/WEB-INF/listes-invitations.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		Long expediteur_id = ((Personne) session.getAttribute("personne")).getId();
		Long destinateur_id = Long.parseLong(request.getParameter("inviterId"));

		Personne personne_expediteur = personneService.findOne(expediteur_id);
		Personne personne_destinateur = personneService.findOne(destinateur_id);
		VieCommune vieCommune = new VieCommune();

		LocalDateTime dateEnvoi = LocalDateTime.now();
		LocalDateTime dateLecture = null;
		Boolean estAccepte = false;

		Invitation invitation = new Invitation(dateEnvoi, dateLecture, estAccepte, vieCommune, personne_destinateur,
				personne_expediteur);
		invitationService.create(invitation);

		response.sendRedirect("liste-personnes");
	}

}
