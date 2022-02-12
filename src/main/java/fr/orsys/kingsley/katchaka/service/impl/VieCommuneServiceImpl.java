package fr.orsys.kingsley.katchaka.service.impl;

import java.sql.SQLException;
import java.util.List;

import fr.orsys.kingsley.katchaka.business.VieCommune;
import fr.orsys.kingsley.katchaka.dao.VieCommuneDao;
import fr.orsys.kingsley.katchaka.dao.impl.VieCommuneDaoImpl;
import fr.orsys.kingsley.katchaka.service.VieCommuneService;

public class VieCommuneServiceImpl implements VieCommuneService {
	private VieCommuneDao vieCommuneDao = new VieCommuneDaoImpl();

	@Override
	public VieCommune create(VieCommune vieCommune) {
		try {
			return vieCommuneDao.create(vieCommune);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<VieCommune> findAll() {
		try {
			return vieCommuneDao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public VieCommune findOne(Long id) {
		try {
			return vieCommuneDao.findOne(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public VieCommune update(VieCommune vieCommune) {
		try {
			return vieCommuneDao.update(vieCommune);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(VieCommune vieCommune) {
		try {
			return vieCommuneDao.delete(vieCommune);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
