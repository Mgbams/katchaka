package fr.orsys.kingsley.katchaka.service.impl;

import java.sql.SQLException;
import java.util.List;

import fr.orsys.kingsley.katchaka.business.Genre;
import fr.orsys.kingsley.katchaka.dao.GenreDao;
import fr.orsys.kingsley.katchaka.dao.impl.GenreDaoImpl;
import fr.orsys.kingsley.katchaka.service.GenreService;

public class GenreServiceImpl implements GenreService {
	private GenreDao genreDao = new GenreDaoImpl();

	@Override
	public Genre create(Genre genre) {
		try {
			return genreDao.create(genre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Genre> findAll() {
		try {
			return genreDao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Genre findOne(Long id) {
		try {
			return genreDao.findOne(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Genre update(Genre genre) {
		try {
			return genreDao.update(genre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(Genre genre) {
		try {
			return genreDao.delete(genre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
