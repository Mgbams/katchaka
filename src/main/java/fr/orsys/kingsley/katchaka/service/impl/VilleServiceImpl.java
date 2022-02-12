package fr.orsys.kingsley.katchaka.service.impl;

import java.sql.SQLException;
import java.util.List;

import fr.orsys.kingsley.katchaka.business.Ville;
import fr.orsys.kingsley.katchaka.dao.VilleDao;
import fr.orsys.kingsley.katchaka.dao.impl.VilleDaoImpl;
import fr.orsys.kingsley.katchaka.service.VilleService;

public class VilleServiceImpl implements VilleService {
	private VilleDao villeDao = new VilleDaoImpl();

	@Override
	public Ville create(Ville ville) {
		try {
			return villeDao.create(ville);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Ville> findAll() {
		try {
			return villeDao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Ville findOne(Long id) {
		try {
			return villeDao.findOne(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Ville update(Ville ville) {
		try {
			return villeDao.update(ville);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(Ville ville) {
		try {
			return villeDao.delete(ville);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
