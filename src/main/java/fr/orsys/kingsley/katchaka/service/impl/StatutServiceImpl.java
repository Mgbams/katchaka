package fr.orsys.kingsley.katchaka.service.impl;

import java.sql.SQLException;
import java.util.List;

import fr.orsys.kingsley.katchaka.business.Statut;
import fr.orsys.kingsley.katchaka.dao.StatutDao;
import fr.orsys.kingsley.katchaka.dao.impl.StatutDaoImpl;
import fr.orsys.kingsley.katchaka.service.StatutService;

public class StatutServiceImpl implements StatutService {
	private StatutDao statutDao = new StatutDaoImpl();

	@Override
	public Statut create(Statut statut) {
		try {
			return statutDao.create(statut);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Statut> findAll() {
		try {
			return statutDao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Statut findOne(Long id) {
		try {
			return statutDao.findOne(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Statut update(Statut statut) {
		try {
			return statutDao.update(statut);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(Statut statut) {
		try {
			return statutDao.delete(statut);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
