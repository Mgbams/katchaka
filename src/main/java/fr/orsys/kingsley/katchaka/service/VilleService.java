package fr.orsys.kingsley.katchaka.service;

import java.util.List;

import fr.orsys.kingsley.katchaka.business.Ville;

public interface VilleService {
	Ville create(Ville ville);

	List<Ville> findAll();

	Ville findOne(Long id);

	Ville update(Ville ville);

	boolean delete(Ville ville);
}
