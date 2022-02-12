package fr.orsys.kingsley.katchaka.service;

import java.util.List;

import fr.orsys.kingsley.katchaka.business.VieCommune;

public interface VieCommuneService {
	VieCommune create(VieCommune vieCommune);

	List<VieCommune> findAll();

	VieCommune findOne(Long id);

	VieCommune update(VieCommune vieCommune);

	boolean delete(VieCommune vieCommune);
}
