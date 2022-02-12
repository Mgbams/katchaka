package fr.orsys.kingsley.katchaka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.orsys.kingsley.katchaka.business.Statut;
import fr.orsys.kingsley.katchaka.dao.ConnexionBdd;
import fr.orsys.kingsley.katchaka.dao.Requetes;
import fr.orsys.kingsley.katchaka.dao.StatutDao;

public class StatutDaoImpl implements StatutDao {
	private Connection connection;

	public StatutDaoImpl() {
		try {
			connection = ConnexionBdd.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Statut create(Statut statut) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.AJOUT_STATUT, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, statut.getNom());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();

		if (rs.next()) {
			statut.setId(rs.getLong(1));
		}

		return statut;
	}

	@Override
	public List<Statut> findAll() throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.RECUPERATION_STATUTS);
		ResultSet rs = ps.executeQuery();

		List<Statut> statuts = new ArrayList<>();

		while (rs.next()) {
			Long id = rs.getLong("id");
			String nom = rs.getString("nom");
			statuts.add(new Statut(id, nom));
		}
		return statuts;
	}

	@Override
	public Statut findOne(Long id) throws SQLException {
		String query = Requetes.RECUPERATION_STATUT_PAR_ID;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();

		Statut statut = null;

		if (rs.next()) {
			statut = new Statut();
			statut.setId(rs.getLong("id"));
			statut.setNom(rs.getString("nom"));
		}
		return statut;
	}

	@Override
	public Statut update(Statut statut) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.UPDATE_STATUT);

		ps.setString(1, statut.getNom());
		ps.setLong(5, statut.getId());

		ps.executeUpdate();
		return statut;
	}

	@Override
	public boolean delete(Statut statut) throws SQLException {
		boolean estPresent = false;

		if (findOne(statut.getId()) != null) {

			PreparedStatement ps = connection.prepareStatement(Requetes.DELETE_STATUT);
			ps.setLong(1, statut.getId());
			ps.executeUpdate();
			estPresent = true;
		}

		return estPresent;
	}

}
