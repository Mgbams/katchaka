package fr.orsys.kingsley.katchaka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.orsys.kingsley.katchaka.business.VieCommune;
import fr.orsys.kingsley.katchaka.dao.ConnexionBdd;
import fr.orsys.kingsley.katchaka.dao.InvitationDao;
import fr.orsys.kingsley.katchaka.dao.Requetes;
import fr.orsys.kingsley.katchaka.dao.VieCommuneDao;

public class VieCommuneDaoImpl implements VieCommuneDao {
	private Connection connection;
	private InvitationDao invitationDao;
	// private MessageDao messageDao;

	public VieCommuneDaoImpl() {
		try {
			connection = ConnexionBdd.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		// invitationDao = new InvitationDaoImpl(); // CAUSES INFINITE LOOP

		// messageDao = new MessageDaoImpl();
	}

	@Override
	public VieCommune create(VieCommune vieCommune) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.AJOUT_VIECOMMUNE, Statement.RETURN_GENERATED_KEYS);
		ps.setDate(1, java.sql.Date.valueOf(java.time.LocalDate.from(vieCommune.getDateDebut())));
		if (vieCommune.getDateFin() != null) {
			ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.from(vieCommune.getDateFin())));
		} else {
			ps.setDate(2, null);
		}
		ps.setInt(3, vieCommune.getNbCredits());
		ps.setLong(4, vieCommune.getInvitation().getId());

		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();

		if (rs.next()) {
			vieCommune.setId(rs.getLong(1));
		}

		return vieCommune;
	}

	@Override
	public List<VieCommune> findAll() throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.RECUPERATION_VIECOMMUNES);
		ResultSet rs = ps.executeQuery();

		List<VieCommune> vieCommunes = new ArrayList<>();

		while (rs.next()) {
			Long id = rs.getLong("id");
			LocalDate dateDebut = rs.getDate("dateEnvoi").toLocalDate();
			LocalDate dateFin = rs.getDate("dateLecture").toLocalDate();
			int nbCredits = rs.getInt("nbCredits");
			vieCommunes.add(new VieCommune(id, dateDebut, dateFin, nbCredits));
		}

		return vieCommunes;
	}

	@Override
	public VieCommune findOne(Long id) throws SQLException {
		String query = Requetes.RECUPERATION_VIECOMMUNE_PAR_ID;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();

		VieCommune vieCommune = null;

		if (rs.next()) {
			vieCommune = new VieCommune();

			vieCommune.setId(rs.getLong("id"));
			vieCommune.setDateDebut(rs.getDate("dateDebut").toLocalDate());
			vieCommune.setDateFin(rs.getDate("dateFin").toLocalDate());
			vieCommune.setNbCredits(rs.getInt("nbCredits"));
			vieCommune.setInvitation(invitationDao.findOne(rs.getLong("invitation_id")));
		}
		return vieCommune;
	}

	@Override
	public VieCommune update(VieCommune vieCommune) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.UPDATE_VIECOMMUNE);

		ps.setDate(1, java.sql.Date.valueOf(java.time.LocalDate.from(vieCommune.getDateDebut())));
		ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.from(vieCommune.getDateFin())));
		ps.setInt(3, vieCommune.getNbCredits());
		ps.setLong(4, vieCommune.getInvitation().getId());
		ps.setLong(5, vieCommune.getId());

		ps.executeUpdate();
		return vieCommune;
	}

	@Override
	public boolean delete(VieCommune vieCommune) throws SQLException {
		boolean estPresent = false;

		if (findOne(vieCommune.getId()) != null) {

			PreparedStatement ps = connection.prepareStatement(Requetes.DELETE_VIECOMMUNE);
			ps.setLong(1, vieCommune.getId());
			ps.executeUpdate();
			estPresent = true;
		}

		return estPresent;
	}

}
