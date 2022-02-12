package fr.orsys.kingsley.katchaka.dao;

import java.sql.SQLException;
import java.util.List;

import fr.orsys.kingsley.katchaka.business.Message;

public interface MessageDao {
	Message create(Message message) throws SQLException;

	List<Message> findAll() throws SQLException;

	Message findOne(Long id) throws SQLException;

	Message update(Message message) throws SQLException;

	boolean delete(Message message) throws SQLException;

}
