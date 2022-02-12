package fr.orsys.kingsley.katchaka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.orsys.kingsley.katchaka.business.Message;
import fr.orsys.kingsley.katchaka.business.Personne;
import fr.orsys.kingsley.katchaka.business.VieCommune;
import fr.orsys.kingsley.katchaka.dao.ConnexionBdd;
import fr.orsys.kingsley.katchaka.dao.MessageDao;
import fr.orsys.kingsley.katchaka.dao.PersonneDao;
import fr.orsys.kingsley.katchaka.dao.Requetes;
import fr.orsys.kingsley.katchaka.dao.VieCommuneDao;

public class MessageDaoImpl implements MessageDao {
	private Connection connection;
	private VieCommuneDao vieCommuneDao;
	private PersonneDao destinataireDao;
	private PersonneDao expediteurDao;

	public MessageDaoImpl() {
		try {
			connection = ConnexionBdd.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		vieCommuneDao = new VieCommuneDaoImpl();
		destinataireDao = new PersonneDaoImpl();
		expediteurDao = new PersonneDaoImpl();
	}

	@Override
	public Message create(Message message) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.AJOUT_MESSAGE, Statement.RETURN_GENERATED_KEYS);

		ps.setString(1, message.getContenu());
		ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.from(message.getDateEnvoi())));
		ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.from(message.getDateLecture())));
		ps.setLong(4, message.getDestinataire().getId());
		ps.setLong(5, message.getExpediteur().getId());
		ps.setLong(6, message.getVieCommune().getId());

		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();

		if (rs.next()) {
			message.setId(rs.getLong(1));
		}

		return message;
	}

	@Override
	public List<Message> findAll() throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.RECUPERATION_MESSAGES);
		ResultSet rs = ps.executeQuery();

		List<Message> messages = new ArrayList<>();

		while (rs.next()) {
			Long id = rs.getLong("id");
			String contenu = rs.getString("contenu");
			LocalDate dateEnvoi = rs.getDate("dateEnvoi").toLocalDate();
			LocalDate dateLecture = rs.getDate("dateLecture").toLocalDate();
			Personne destinataire = destinataireDao.findOne(rs.getLong("destinataire_id"));
			Personne expediteur = expediteurDao.findOne(rs.getLong("expediteur_id"));
			VieCommune vieCommune = vieCommuneDao.findOne(rs.getLong("vieCommune_id"));

			messages.add(new Message(id, contenu, dateEnvoi, dateLecture, vieCommune, expediteur, destinataire));
		}
		return messages;
	}

	@Override
	public Message findOne(Long id) throws SQLException {
		String query = Requetes.RECUPERATION_MESSAGE_PAR_ID;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();

		Message message = null;

		if (rs.next()) {
			message = new Message();

			message.setId(rs.getLong("id"));
			message.setContenu(rs.getString("contenu"));
			message.setDateEnvoi(rs.getDate("dateEnvoi").toLocalDate());
			message.setDateLecture(rs.getDate("dateEnvoi").toLocalDate());
			message.setDestinataire(destinataireDao.findOne(rs.getLong("destinataire_id")));
			message.setExpediteur(expediteurDao.findOne(rs.getLong("expediteur_id")));
			message.setVieCommune(vieCommuneDao.findOne(rs.getLong("vieCommune_id")));
		}
		return message;
	}

	@Override
	public Message update(Message message) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.UPDATE_MESSAGE);

		ps.setString(1, message.getContenu());
		ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.from(message.getDateEnvoi())));
		ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.from(message.getDateLecture())));
		ps.setLong(4, message.getDestinataire().getId());
		ps.setLong(5, message.getExpediteur().getId());
		ps.setLong(6, message.getVieCommune().getId());
		ps.setLong(5, message.getId());

		ps.executeUpdate();
		return message;
	}

	@Override
	public boolean delete(Message message) throws SQLException {
		boolean estPresent = false;

		if (findOne(message.getId()) != null) {

			PreparedStatement ps = connection.prepareStatement(Requetes.DELETE_MESSAGE);
			ps.setLong(1, message.getId());
			ps.executeUpdate();
			estPresent = true;
		}

		return estPresent;
	}

}
