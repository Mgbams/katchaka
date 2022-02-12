package fr.orsys.kingsley.katchaka.service.impl;

import java.sql.SQLException;
import java.util.List;

import fr.orsys.kingsley.katchaka.business.Interet;
import fr.orsys.kingsley.katchaka.dao.InteretDao;
import fr.orsys.kingsley.katchaka.dao.impl.InteretDaoImpl;
import fr.orsys.kingsley.katchaka.service.InteretService;

public class InteretServiceImpl implements InteretService {
	private InteretDao interetDao = new InteretDaoImpl();

	@Override
	public Interet create(Interet interet) {
		try {
			return interetDao.create(interet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Interet> findAll() {
		try {
			return interetDao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Interet findOne(Long id) {
		try {
			return interetDao.findOne(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Interet update(Interet interet) {
		try {
			return interetDao.update(interet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(Interet interet) {
		try {
			return interetDao.delete(interet);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
