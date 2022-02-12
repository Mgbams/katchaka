package fr.orsys.kingsley.katchaka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.orsys.kingsley.katchaka.business.Interet;
import fr.orsys.kingsley.katchaka.dao.ConnexionBdd;
import fr.orsys.kingsley.katchaka.dao.InteretDao;
import fr.orsys.kingsley.katchaka.dao.Requetes;

public class InteretDaoImpl implements InteretDao {
	private Connection connection;

	public InteretDaoImpl() {
		try {
			connection = ConnexionBdd.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Interet create(Interet interet) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.AJOUT_INTERET, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, interet.getNom());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();

		if (rs.next()) {
			interet.setId(rs.getLong(1));
		}

		return interet;
	}

	@Override
	public List<Interet> findAll() throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.RECUPERATION_INTERETS);
		ResultSet rs = ps.executeQuery();

		List<Interet> interets = new ArrayList<>();

		while (rs.next()) {
			Long id = rs.getLong("id");
			String nom = rs.getString("nom");
			interets.add(new Interet(id, nom));
		}
		return interets;
	}

	@Override
	public Interet findOne(Long id) throws SQLException {
		String query = Requetes.RECUPERATION_INTERET_PAR_ID;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();
		
		Interet interet = null;

		if (rs.next()) {
			interet = new Interet();
			interet.setId(rs.getLong("id"));
			interet.setNom(rs.getString("nom"));
		}
		
		return interet;
	}

	@Override
	public Interet update(Interet interet) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.UPDATE_INTERET);

		ps.setString(1, interet.getNom());
		ps.setLong(5, interet.getId());

		ps.executeUpdate();
		return interet;
	}

	@Override
	public boolean delete(Interet interet) throws SQLException {
		boolean estPresent = false;

		if (findOne(interet.getId()) != null) {

			PreparedStatement ps = connection.prepareStatement(Requetes.DELETE_INTERET);
			ps.setLong(1, interet.getId());
			ps.executeUpdate();
			estPresent = true;
		}

		return estPresent;
	}

}
