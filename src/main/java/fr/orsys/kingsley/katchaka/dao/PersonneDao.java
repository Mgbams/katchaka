package fr.orsys.kingsley.katchaka.dao;

import java.sql.SQLException;
import java.util.List;

import fr.orsys.kingsley.katchaka.business.Personne;

public interface PersonneDao {
	Personne create(Personne personne) throws SQLException;

	List<Personne> findAll() throws SQLException;

	Personne findOne(Long id) throws SQLException;

	Personne findByEmailAndMotDePasse(String email, String motDePasse) throws SQLException;

	Personne update(Personne personne) throws SQLException;

	boolean delete(Personne personne) throws SQLException;

	List<Personne> recupererPersonnesParGenreRecherches(Long genreRecherche_id) throws SQLException;

	List<Personne> recupererPersonnesParGenreRecherchesAvecLimit(Long genreRecherche_id) throws SQLException;

	List<Personne> recupererPersonnesParGenreRecherchesEtSansInvitationAvecLimit5(Long genreRecherche_id,
			Long personne_id) throws SQLException;

	Personne findInteretsParPersonneId(Long id) throws SQLException;

	List<Personne> filtrer_personnes_par_interet(Long genreRecherche_id, Long expediteur_id, Long interetId)
			throws SQLException;

	List<Personne> filtrer_personnes_par_ville(Long genreRecherche_id, Long expediteur_id, Long villeId)
			throws SQLException;

	List<Personne> filtrer_personnes_par_ville_et_interet(Long genreRecherche_id, Long expediteur_id, Long villeId,
			Long interetId) throws SQLException;

	List<Personne> recuperer_personne_et_pagination(Long genreRecherche_id, Long expediteur_id, int limit, int offset)
			throws SQLException;
}
