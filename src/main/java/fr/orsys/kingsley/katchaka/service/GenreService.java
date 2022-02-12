package fr.orsys.kingsley.katchaka.service;

import java.util.List;

import fr.orsys.kingsley.katchaka.business.Genre;

public interface GenreService {
	
	Genre create(Genre genre);

	List<Genre> findAll();

	Genre findOne(Long id);

	Genre update(Genre genre);

	boolean delete(Genre genre);
}
