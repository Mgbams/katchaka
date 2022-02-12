package fr.orsys.kingsley.katchaka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.orsys.kingsley.katchaka.business.Invitation;
import fr.orsys.kingsley.katchaka.business.Personne;
import fr.orsys.kingsley.katchaka.business.VieCommune;
import fr.orsys.kingsley.katchaka.dao.ConnexionBdd;
import fr.orsys.kingsley.katchaka.dao.InvitationDao;
import fr.orsys.kingsley.katchaka.dao.PersonneDao;
import fr.orsys.kingsley.katchaka.dao.Requetes;
import fr.orsys.kingsley.katchaka.dao.VieCommuneDao;

public class InvitationDaoImpl implements InvitationDao {
	private Connection connection;
	private VieCommuneDao vieCommuneDao;
	private PersonneDao destinataireDao;
	private PersonneDao expediteurDao;

	public InvitationDaoImpl() {
		try {
			connection = ConnexionBdd.getConnection();
			connection.setAutoCommit(true);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		vieCommuneDao = new VieCommuneDaoImpl();
		destinataireDao = new PersonneDaoImpl();
		expediteurDao = new PersonneDaoImpl();
	}

	@Override
	public Invitation create(Invitation invitation) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.AJOUT_INVITATION, Statement.RETURN_GENERATED_KEYS);
		ps.setTimestamp(1, java.sql.Timestamp.valueOf(invitation.getDateEnvoi()));
		ps.setTimestamp(2, null); // assumer d'étre null pendant l'envoi d'un invitation
		ps.setBoolean(3, invitation.getEstAccepte());
		ps.setLong(4, invitation.getDestinataire().getId());
		ps.setLong(5, invitation.getExpediteur().getId());
		ps.setNull(6, java.sql.Types.BIGINT);
		// ps.setLong (6, 0); //assumer d'étre null pendant l'envoi d'un invitation

		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();

		if (rs.next()) {
			invitation.setId(rs.getLong(1));
		}

		return invitation;

	}

	@Override
	public List<Invitation> findAll() throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.RECUPERATION_INVITATIONS);
		ResultSet rs = ps.executeQuery();

		List<Invitation> invitations = new ArrayList<>();

		while (rs.next()) {
			Long id = rs.getLong("id");
			LocalDateTime dateEnvoi = rs.getTimestamp("dateEnvoi").toLocalDateTime();
			LocalDateTime dateLecture = rs.getTimestamp("dateLecture").toLocalDateTime();
			Boolean estAccepte = rs.getBoolean("estAccepte");
			VieCommune vieCommune = vieCommuneDao.findOne(rs.getLong("vieCommune_id"));
			Personne destinataire = destinataireDao.findOne(rs.getLong("destinataire_id"));
			Personne expediteur = expediteurDao.findOne(rs.getLong("expediteur_id"));

			invitations
					.add(new Invitation(id, dateEnvoi, dateLecture, estAccepte, vieCommune, destinataire, expediteur));
		}
		return invitations;
	}

	@Override
	public Invitation findOne(Long id) throws SQLException {
		String query = Requetes.RECUPERATION_INVITATION_PAR_ID;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();

		Invitation invitation = null;

		if (rs.next()) {
			invitation = new Invitation();

			invitation.setId(rs.getLong("id"));
			invitation.setDateEnvoi(rs.getTimestamp("dateEnvoi").toLocalDateTime());
			invitation.setDateLecture(rs.getTimestamp("dateEnvoi").toLocalDateTime());
			invitation.setDestinataire(destinataireDao.findOne(rs.getLong("destinataire_id")));
			invitation.setEstAccepte(rs.getBoolean("estAccepte"));
			invitation.setExpediteur(expediteurDao.findOne(rs.getLong("expediteur_id")));
			invitation.setVieCommune(vieCommuneDao.findOne(rs.getLong("vieCommune_id")));
		}
		return invitation;
	}

	@Override
	public Invitation update(Invitation invitation) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.UPDATE_INVITATION);

		ps.setTimestamp(1, java.sql.Timestamp.valueOf(invitation.getDateEnvoi()));
		ps.setTimestamp(2, java.sql.Timestamp.valueOf(invitation.getDateLecture()));
		ps.setBoolean(3, invitation.getEstAccepte());
		ps.setLong(4, invitation.getDestinataire().getId());
		ps.setLong(5, invitation.getExpediteur().getId());
		ps.setLong(6, invitation.getVieCommune().getId());
		ps.setLong(5, invitation.getId());

		ps.executeUpdate();
		return invitation;
	}

	@Override
	public boolean delete(Invitation invitation) throws SQLException {
		boolean estPresent = false;

		if (findOne(invitation.getId()) != null) {

			PreparedStatement ps = connection.prepareStatement(Requetes.DELETE_INVITATION);
			ps.setLong(1, invitation.getId());
			ps.executeUpdate();
			estPresent = true;
		}

		return estPresent;
	}

	@Override
	public List<Invitation> findByExpediteurId(Long expediteur_id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.RECUPERATION_INVITATIONS_PAR_EXPEDITEUR);
		ps.setLong(1, expediteur_id);
		ResultSet rs = ps.executeQuery();

		List<Invitation> invitations = new ArrayList<>();

		while (rs.next()) {
			LocalDateTime dateLecture = null;
			VieCommune vieCommune = null;
			Long id = rs.getLong("id");
			LocalDateTime dateEnvoi = rs.getTimestamp("dateEnvoi").toLocalDateTime();

			if (rs.getTimestamp("dateLecture") == null) {
				dateLecture = null;
			} else {
				dateLecture = rs.getTimestamp("dateLecture").toLocalDateTime();
			}

			Boolean estAccepte = rs.getBoolean("estAccepte");

			if (rs.getTimestamp("dateLecture") == null) {
				vieCommune = null;
			} else {
				vieCommune = vieCommuneDao.findOne(rs.getLong("vieCommune_id"));
			}

			Personne destinataire = destinataireDao.findOne(rs.getLong("destinataire_id"));
			Personne expediteur = expediteurDao.findOne(rs.getLong("expediteur_id"));

			invitations
					.add(new Invitation(id, dateEnvoi, dateLecture, estAccepte, vieCommune, destinataire, expediteur));
		}
		return invitations;
	}

	@Override
	public List<Invitation> findByDestinataireId(Long destinataire_id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.RECUPERATION_INVITATIONS_PAR_DESTINATEUR);
		ps.setLong(1, destinataire_id);
		ResultSet rs = ps.executeQuery();

		List<Invitation> invitations = new ArrayList<>();

		while (rs.next()) {
			LocalDateTime dateLecture = null;
			VieCommune vieCommune = null;
			Long id = rs.getLong("id");
			LocalDateTime dateEnvoi = rs.getTimestamp("dateEnvoi").toLocalDateTime();

			if (rs.getTimestamp("dateLecture") == null) {
				dateLecture = null;
			} else {
				dateLecture = rs.getTimestamp("dateLecture").toLocalDateTime();
			}

			Boolean estAccepte = rs.getBoolean("estAccepte");

			if (rs.getTimestamp("dateLecture") == null) {
				vieCommune = null;
			} else {
				vieCommune = vieCommuneDao.findOne(rs.getLong("vieCommune_id"));
			}

			Personne destinataire = destinataireDao.findOne(rs.getLong("destinataire_id"));
			Personne expediteur = expediteurDao.findOne(rs.getLong("expediteur_id"));

			invitations
					.add(new Invitation(id, dateEnvoi, dateLecture, estAccepte, vieCommune, destinataire, expediteur));
		}
		return invitations;
	}

	@Override
	public Invitation update_invitation_pendant_acceptation_ou_declinant(Invitation invitation) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.UPDATE_INVITATION_PENDANT_ACCEPTATION_OU_DECLINANT);

		ps.setTimestamp(1, java.sql.Timestamp.valueOf(invitation.getDateLecture()));
		ps.setInt(2, invitation.getEstAccepte() ? 1 : 0);
		ps.setLong(3, invitation.getDestinataire().getId());
		ps.setLong(4, invitation.getExpediteur().getId());

		int executionCount = ps.executeUpdate();
		// update de table de vieCommunne
		if (executionCount > 0) {

			if (invitation.getEstAccepte()) {
				Invitation invitationEnvoye = findByExpediteurIdAndDestinataireId(invitation.getDestinataire().getId(), invitation.getExpediteur().getId());
				LocalDate dateDebut = LocalDate.now();
				VieCommune vieCommune = new VieCommune();
				vieCommune.setDateDebut(dateDebut);
				vieCommune.setDateFin(null);
				vieCommune.setNbCredits(VieCommune.getNbCreditsParDefault());
				vieCommune.setInvitation(invitationEnvoye);
				vieCommuneDao.create(vieCommune);
			}
		}

		return invitation;
	}

	@Override
	public Invitation findByExpediteurIdAndDestinataireId(Long expediteur_id, Long destinataire_id)
			throws SQLException {
		String query = Requetes.RECUPERATION_INVITATIONS_PAR_DESTINATEURID_ET_EXPEDITEURID;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1, expediteur_id);
		ps.setLong(2, destinataire_id);
		ResultSet rs = ps.executeQuery();

		Invitation invitation = null;

		if (rs.next()) {
			invitation = new Invitation();

			invitation.setId(rs.getLong("id"));
			invitation.setDateEnvoi(rs.getTimestamp("dateEnvoi").toLocalDateTime());
			invitation.setDateLecture(rs.getTimestamp("dateEnvoi").toLocalDateTime());
			invitation.setDestinataire(destinataireDao.findOne(rs.getLong("destinataire_id")));
			invitation.setEstAccepte(rs.getBoolean("estAccepte"));
			invitation.setExpediteur(expediteurDao.findOne(rs.getLong("expediteur_id")));
			invitation.setVieCommune(vieCommuneDao.findOne(rs.getLong("vieCommune_id")));
		}
		return invitation;
	}

}
