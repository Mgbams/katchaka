package fr.orsys.kingsley.katchaka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.orsys.kingsley.katchaka.business.Ville;
import fr.orsys.kingsley.katchaka.dao.ConnexionBdd;
import fr.orsys.kingsley.katchaka.dao.Requetes;
import fr.orsys.kingsley.katchaka.dao.VilleDao;

public class VilleDaoImpl implements VilleDao {
	private Connection connection;
	

	public VilleDaoImpl() {
		try {
			connection = ConnexionBdd.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Ville create(Ville ville) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.AJOUT_VILLE, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, ville.getNom());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();

		if (rs.next()) {
			ville.setId(rs.getLong(1));
		}

		return ville;
	}

	@Override
	public List<Ville> findAll() throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.RECUPERATION_VILLES);
		ResultSet rs = ps.executeQuery();

		List<Ville> villes = new ArrayList<>();

		while (rs.next()) {
			Long id = rs.getLong("id");
			String nom = rs.getString("nom");
			villes.add(new Ville(id, nom));
		}
		return villes;
	}

	@Override
	public Ville findOne(Long id) throws SQLException {
		String query = Requetes.RECUPERATION_VILLE_PAR_ID;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();

		Ville ville = null;

		if (rs.next()) {
			ville = new Ville();
			ville.setId(rs.getLong("id"));
			ville.setNom(rs.getString("nom"));
		}
		return ville;
	}

	@Override
	public Ville update(Ville ville) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.UPDATE_VILLE);

		ps.setString(1, ville.getNom());
		ps.setLong(5, ville.getId());

		ps.executeUpdate();
		return ville;
	}

	@Override
	public boolean delete(Ville ville) throws SQLException {
		boolean estPresent = false;

		if (findOne(ville.getId()) != null) {

			PreparedStatement ps = connection.prepareStatement(Requetes.DELETE_VILLE);
			ps.setLong(1, ville.getId());
			ps.executeUpdate();
			estPresent = true;
		}

		return estPresent;
	}

}
