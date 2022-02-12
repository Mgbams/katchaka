package fr.orsys.kingsley.katchaka.service;

import java.util.List;

import fr.orsys.kingsley.katchaka.business.Statut;

public interface StatutService {
	Statut create(Statut statut);

	List<Statut> findAll();

	Statut findOne(Long id);

	Statut update(Statut statut);

	boolean delete(Statut statut);

}
