package fr.orsys.kingsley.katchaka.dao;

import java.sql.SQLException;
import java.util.List;

import fr.orsys.kingsley.katchaka.business.Genre;

public interface GenreDao {
	Genre create(Genre genre) throws SQLException;

	List<Genre> findAll() throws SQLException;

	Genre findOne(Long id) throws SQLException;

	Genre update(Genre genre) throws SQLException;

	boolean delete(Genre genre) throws SQLException;

}
