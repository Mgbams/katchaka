package fr.orsys.kingsley.katchaka.dao;

import java.sql.SQLException;
import java.util.List;

import fr.orsys.kingsley.katchaka.business.Statut;

public interface StatutDao {
	Statut create(Statut statut) throws SQLException;

	List<Statut> findAll() throws SQLException;

	Statut findOne(Long id) throws SQLException;

	Statut update(Statut statut) throws SQLException;

	boolean delete(Statut statut) throws SQLException;

}
