package fr.orsys.kingsley.katchaka.dao;

import java.sql.SQLException;
import java.util.List;

import fr.orsys.kingsley.katchaka.business.Interet;

public interface InteretDao {
	Interet create(Interet interet) throws SQLException;

	List<Interet> findAll() throws SQLException;

	Interet findOne(Long id) throws SQLException;

	Interet update(Interet interet) throws SQLException;

	boolean delete(Interet interet) throws SQLException;
}
