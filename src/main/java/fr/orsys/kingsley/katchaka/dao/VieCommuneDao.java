package fr.orsys.kingsley.katchaka.dao;

import java.sql.SQLException;
import java.util.List;

import fr.orsys.kingsley.katchaka.business.VieCommune;

public interface VieCommuneDao {
	VieCommune create(VieCommune vieCommune) throws SQLException;

	List<VieCommune> findAll() throws SQLException;

	VieCommune findOne(Long id) throws SQLException;

	VieCommune update(VieCommune vieCommune) throws SQLException;

	boolean delete(VieCommune vieCommune) throws SQLException;

}
