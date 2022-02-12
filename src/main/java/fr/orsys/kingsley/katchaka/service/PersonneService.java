package fr.orsys.kingsley.katchaka.service;

import java.util.List;

import fr.orsys.kingsley.katchaka.business.Personne;

public interface PersonneService {
	Personne create(Personne personne);

	List<Personne> findAll();

	Personne findOne(Long id);

	Personne findByEmailAndMotDePasse(String email, String motDePasse);

	Personne update(Personne personne);

	boolean delete(Personne personne);

	List<Personne> recupererPersonnesParGenreRecherches(Long genreRecherche_id);

	List<Personne> recupererPersonnesParGenreRecherchesAvecLimit(Long genreRecherche_id);

	List<Personne> recupererPersonnesParGenreRecherchesEtSansInvitationAvecLimit5(Long genreRecherche_id,
			Long personne_id);

	Personne findInteretsParPersonneId(Long id);

	List<Personne> filtrer_personnes_par_interet(Long genreRecherche_id, Long expediteur_id, Long interetId);

	List<Personne> filtrer_personnes_par_ville(Long genreRecherche_id, Long expediteur_id, Long villeId);

	List<Personne> filtrer_personnes_par_ville_et_interet(Long genreRecherche_id, Long expediteur_id, Long villeId, Long interetId);
	
	List<Personne> recuperer_personne_et_pagination(Long genreRecherche_id, Long expediteur_id, int limit, int offset);
}
