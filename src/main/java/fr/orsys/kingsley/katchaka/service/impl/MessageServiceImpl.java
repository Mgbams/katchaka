package fr.orsys.kingsley.katchaka.service.impl;

import java.sql.SQLException;
import java.util.List;

import fr.orsys.kingsley.katchaka.business.Message;
import fr.orsys.kingsley.katchaka.dao.MessageDao;
import fr.orsys.kingsley.katchaka.dao.impl.MessageDaoImpl;
import fr.orsys.kingsley.katchaka.service.MessageService;

public class MessageServiceImpl implements MessageService {
	private MessageDao messageDao = new MessageDaoImpl();

	@Override
	public Message create(Message message) {
		try {
			return messageDao.create(message);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Message> findAll() {
		try {
			return messageDao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Message findOne(Long id) {
		try {
			return messageDao.findOne(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Message update(Message message) {
		try {
			return messageDao.update(message);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(Message message) {
		try {
			return messageDao.delete(message);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
