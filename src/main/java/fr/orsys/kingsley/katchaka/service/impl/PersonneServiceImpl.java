package fr.orsys.kingsley.katchaka.service.impl;

import java.sql.SQLException;
import java.util.List;

import fr.orsys.kingsley.katchaka.business.Personne;
import fr.orsys.kingsley.katchaka.dao.PersonneDao;
import fr.orsys.kingsley.katchaka.dao.impl.PersonneDaoImpl;
import fr.orsys.kingsley.katchaka.service.PersonneService;

public class PersonneServiceImpl implements PersonneService {
	private PersonneDao personneDao = new PersonneDaoImpl();

	@Override
	public Personne create(Personne personne) {
		try {
			return personneDao.create(personne);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Personne> findAll() {
		try {
			return personneDao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Personne findOne(Long id) {
		try {
			return personneDao.findOne(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Personne findByEmailAndMotDePasse(String email, String motDePasse) {
		try {
			return personneDao.findByEmailAndMotDePasse(email, motDePasse);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Personne update(Personne personne) {
		try {
			return personneDao.update(personne);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(Personne personne) {
		try {
			return personneDao.delete(personne);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Personne> recupererPersonnesParGenreRecherches(Long genreRecherche_id) {
		try {
			return personneDao.recupererPersonnesParGenreRecherches(genreRecherche_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Personne findInteretsParPersonneId(Long id) {
		try {
			return personneDao.findInteretsParPersonneId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Personne> recupererPersonnesParGenreRecherchesAvecLimit(Long genreRecherche_id) {
		try {
			return personneDao.recupererPersonnesParGenreRecherchesAvecLimit(genreRecherche_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Personne> recupererPersonnesParGenreRecherchesEtSansInvitationAvecLimit5(Long genreRecherche_id,
			Long personne_id) {
		try {
			return personneDao.recupererPersonnesParGenreRecherchesEtSansInvitationAvecLimit5(genreRecherche_id, personne_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Personne> filtrer_personnes_par_interet(Long genreRecherche_id, Long expediteur_id, Long interetId) {
		try {
			return personneDao.filtrer_personnes_par_interet(genreRecherche_id, expediteur_id, interetId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Personne> filtrer_personnes_par_ville(Long genreRecherche_id, Long expediteur_id, Long villeId) {
		try {
			return personneDao.filtrer_personnes_par_ville(genreRecherche_id, expediteur_id, villeId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Personne> filtrer_personnes_par_ville_et_interet(Long genreRecherche_id, Long expediteur_id, Long villeId, Long interetId) {
		try {
			return personneDao.filtrer_personnes_par_ville_et_interet(genreRecherche_id, expediteur_id, villeId, interetId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Personne> recuperer_personne_et_pagination(Long genreRecherche_id, Long expediteur_id, int limit,
			int offset) {
		try {
			return personneDao.recuperer_personne_et_pagination(genreRecherche_id, expediteur_id, limit, offset);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
