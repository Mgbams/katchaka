package fr.orsys.kingsley.katchaka.dao;

import java.sql.SQLException;
import java.util.List;

import fr.orsys.kingsley.katchaka.business.Ville;

public interface VilleDao {
	Ville create(Ville ville) throws SQLException;

	List<Ville> findAll() throws SQLException;

	Ville findOne(Long id) throws SQLException;

	Ville update(Ville ville) throws SQLException;

	boolean delete(Ville ville) throws SQLException;

}
