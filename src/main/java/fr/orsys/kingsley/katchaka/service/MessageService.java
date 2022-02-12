package fr.orsys.kingsley.katchaka.service;

import java.util.List;

import fr.orsys.kingsley.katchaka.business.Message;

public interface MessageService {
	Message create(Message message);

	List<Message> findAll();

	Message findOne(Long id);

	Message update(Message message);

	boolean delete(Message message);
}
