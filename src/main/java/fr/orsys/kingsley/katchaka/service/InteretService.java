package fr.orsys.kingsley.katchaka.service;

import java.util.List;

import fr.orsys.kingsley.katchaka.business.Interet;

public interface InteretService {

	Interet create(Interet interet);

	List<Interet> findAll();

	Interet findOne(Long id);

	Interet update(Interet interet);

	boolean delete(Interet interet);

}
