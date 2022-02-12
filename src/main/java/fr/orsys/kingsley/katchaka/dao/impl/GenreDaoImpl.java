package fr.orsys.kingsley.katchaka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.orsys.kingsley.katchaka.business.Genre;
import fr.orsys.kingsley.katchaka.dao.ConnexionBdd;
import fr.orsys.kingsley.katchaka.dao.GenreDao;
import fr.orsys.kingsley.katchaka.dao.Requetes;

public class GenreDaoImpl implements GenreDao {

	private Connection connection;

	public GenreDaoImpl() {
		try {
			connection = ConnexionBdd.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Genre create(Genre genre) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.AJOUT_GENRE, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, genre.getNom());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();

		if (rs.next()) {
			genre.setId(rs.getLong(1));
		}

		return genre;
	}

	@Override
	public List<Genre> findAll() throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.RECUPERATION_GENRES);
		ResultSet rs = ps.executeQuery();

		List<Genre> genres = new ArrayList<>();

		while (rs.next()) {
			Long id = rs.getLong("id");
			String nom = rs.getString("nom");
			genres.add(new Genre(id, nom));
		}
		return genres;
	}

	@Override
	public Genre findOne(Long id) throws SQLException {
		String query = Requetes.RECUPERATION_GENRE_PAR_ID;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();

		Genre genre = null;

		if (rs.next()) {
			genre = new Genre();
			genre.setId(rs.getLong("id"));
			genre.setNom(rs.getString("nom"));
		}
		return genre;
	}

	@Override
	public Genre update(Genre genre) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.UPDATE_GENRE);

		ps.setString(1, genre.getNom());
		ps.setLong(5, genre.getId());

		ps.executeUpdate();
		return genre;
	}

	@Override
	public boolean delete(Genre genre) throws SQLException {
		boolean estPresent = false;

		if (findOne(genre.getId()) != null) {

			PreparedStatement ps = connection.prepareStatement(Requetes.DELETE_GENRE);
			ps.setLong(1, genre.getId());
			ps.executeUpdate();
			estPresent = true;
		}

		return estPresent;
	}

}
