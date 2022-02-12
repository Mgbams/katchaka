package fr.orsys.kingsley.katchaka.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.orsys.kingsley.katchaka.business.Genre;
import fr.orsys.kingsley.katchaka.business.Interet;
import fr.orsys.kingsley.katchaka.business.Personne;
import fr.orsys.kingsley.katchaka.business.Statut;
import fr.orsys.kingsley.katchaka.business.Ville;
import fr.orsys.kingsley.katchaka.dao.ConnexionBdd;
import fr.orsys.kingsley.katchaka.dao.GenreDao;
import fr.orsys.kingsley.katchaka.dao.PersonneDao;
import fr.orsys.kingsley.katchaka.dao.Requetes;
import fr.orsys.kingsley.katchaka.dao.StatutDao;
import fr.orsys.kingsley.katchaka.dao.VilleDao;

public class PersonneDaoImpl implements PersonneDao {
	private Connection connection;
	private VilleDao villeDao;
	private StatutDao statutDao;
	private GenreDao genreDao;
	private GenreDao genreRechercheDao;

	public PersonneDaoImpl() {
		try {
			connection = ConnexionBdd.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		villeDao = new VilleDaoImpl();
		statutDao = new StatutDaoImpl();
		genreDao = new GenreDaoImpl();
		genreRechercheDao = new GenreDaoImpl();
	}

	@Override
	public Personne create(Personne personne) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.AJOUT_PERSONNE, Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, personne.getBio());
		ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.from(personne.getDateDeNaissance())));
		ps.setString(3, personne.getEmail());
		ps.setBoolean(4, personne.getEstFumeur());
		ps.setString(5, personne.getMotDePasse());
		ps.setInt(6, personne.getNbCredits());
		ps.setString(7, personne.getPseudo());
		ps.setLong(8, personne.getGenre().getId());
		ps.setLong(9, personne.getGenreRecherche().getId());
		ps.setLong(10, personne.getStatut().getId());
		ps.setLong(11, personne.getVille().getId());

		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();

		if (rs.next()) {
			personne.setId(rs.getLong(1));

			// inSérer dans la table personne_interets
			PreparedStatement pis = connection.prepareStatement(Requetes.AJOUT_PERSONNE_INTERETS);

			for (int i = 0; i < personne.getInterets().size(); i++) {
				pis.setLong(1, rs.getLong(1));
				pis.setLong(2, personne.getInterets().get(i).getId());
				pis.executeUpdate();
			}

			// inSérer dans la table ville_personnes
			PreparedStatement pvs = connection.prepareStatement(Requetes.AJOUT_VILLE_PERSONNES);

			pvs.setLong(1, rs.getLong(1));
			pvs.setLong(2, personne.getVille().getId());
			pvs.executeUpdate();

		}

		return personne;
	}

	@Override
	public List<Personne> findAll() throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.RECUPERATION_PERSONNES);
		ResultSet rs = ps.executeQuery();

		List<Personne> personnes = new ArrayList<>();

		while (rs.next()) {
			Long id = rs.getLong("id");
			String bio = rs.getString("bio");
			LocalDate dateDeNaissance = rs.getDate("dateDeNaissance").toLocalDate();
			String email = rs.getString("email");
			Boolean estFumeur = rs.getBoolean("estFumeur");
			String motDePasse = rs.getString("motDePasse");
			int nbCredits = rs.getInt("nbCredits");
			String pseudo = rs.getString("pseudo");
			Genre genre = genreDao.findOne(rs.getLong("genre_id"));
			Statut statut = statutDao.findOne(rs.getLong("statut_id"));
			Genre genreRecherche = genreRechercheDao.findOne(rs.getLong("genreRecherche_id"));
			Ville ville = villeDao.findOne(rs.getLong("ville_id"));

			personnes.add(new Personne(id, pseudo, motDePasse, email, dateDeNaissance, bio, nbCredits, estFumeur, ville,
					statut, genreRecherche, genre));
		}
		return personnes;
	}

	@Override
	public Personne findOne(Long id) throws SQLException {
		String query = Requetes.RECUPERATION_PERSONNE_PAR_ID;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();

		Personne personne = null;

		if (rs.next()) {
			personne = new Personne();

			personne.setId(rs.getLong("id"));
			personne.setBio(rs.getString("bio"));
			personne.setDateDeNaissance(rs.getDate("dateDeNaissance").toLocalDate());
			personne.setEmail(rs.getString("email"));
			personne.setEstFumeur(rs.getBoolean("estFumeur"));
			personne.setMotDePasse(rs.getString("motDePasse"));
			personne.setNbCredits(rs.getInt("nbCredits"));
			personne.setPseudo(rs.getString("pseudo"));
			personne.setGenre(genreDao.findOne(rs.getLong("genre_id")));
			personne.setGenreRecherche(genreRechercheDao.findOne(rs.getLong("genreRecherche_id")));
			personne.setStatut(statutDao.findOne(rs.getLong("statut_id")));
			personne.setVille(villeDao.findOne(rs.getLong("ville_id")));

		}
		return personne;
	}

	@Override
	public Personne findByEmailAndMotDePasse(String email, String motDePasse) throws SQLException {
		String query = Requetes.RECUPERATION_PERSONNE_PAR_EMAIL_ET_MOT_DE_PASSE;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setString(1, email);
		ps.setString(2, motDePasse);
		ResultSet rs = ps.executeQuery();

		// List<Personne> personnes = new ArrayList<>();
		Personne personne = null;

		if (rs.next()) {
			personne = new Personne();

			personne.setId(rs.getLong("id"));
			personne.setBio(rs.getString("bio"));
			personne.setDateDeNaissance(rs.getDate("dateDeNaissance").toLocalDate());
			personne.setEmail(rs.getString("email"));
			personne.setEstFumeur(rs.getBoolean("estFumeur"));
			personne.setMotDePasse(rs.getString("motDePasse"));
			personne.setNbCredits(rs.getInt("nbCredits"));
			personne.setPseudo(rs.getString("pseudo"));
			personne.setGenre(genreDao.findOne(rs.getLong("genre_id")));
			personne.setGenreRecherche(genreRechercheDao.findOne(rs.getLong("genreRecherche_id")));
			personne.setStatut(statutDao.findOne(rs.getLong("statut_id")));
			personne.setVille(villeDao.findOne(rs.getLong("ville_id")));
		}
		return personne;
	}

	@Override
	public Personne update(Personne personne) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(Requetes.UPDATE_PERSONNE);

		ps.setString(1, personne.getBio());
		ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.from(personne.getDateDeNaissance())));
		ps.setString(3, personne.getEmail());
		ps.setBoolean(4, personne.getEstFumeur());
		ps.setString(5, personne.getMotDePasse());
		ps.setInt(6, personne.getNbCredits());
		ps.setString(7, personne.getPseudo());
		ps.setLong(8, personne.getGenre().getId());
		ps.setLong(9, personne.getGenreRecherche().getId());
		ps.setLong(10, personne.getStatut().getId());
		ps.setLong(11, personne.getVille().getId());
		ps.setLong(5, personne.getId());

		ps.executeUpdate();
		return personne;
	}

	@Override
	public List<Personne> recupererPersonnesParGenreRecherches(Long genreRecherche_id) throws SQLException {
		String query = Requetes.RECUPERATION_PERSONNE_PAR_GENRE_RECHERCHE;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1, genreRecherche_id);
		ResultSet rs = ps.executeQuery();

		List<Personne> personnes = new ArrayList<>();

		while (rs.next()) {
			Long id = rs.getLong("id");
			String bio = rs.getString("bio");
			LocalDate dateDeNaissance = rs.getDate("dateDeNaissance").toLocalDate();
			String email = rs.getString("email");
			Boolean estFumeur = rs.getBoolean("estFumeur");
			String motDePasse = rs.getString("motDePasse");
			int nbCredits = rs.getInt("nbCredits");
			String pseudo = rs.getString("pseudo");
			Genre genre = genreDao.findOne(rs.getLong("genre_id"));
			Statut statut = statutDao.findOne(rs.getLong("statut_id"));
			Genre genreRecherche = genreRechercheDao.findOne(rs.getLong("genreRecherche_id"));
			Ville ville = villeDao.findOne(rs.getLong("ville_id"));

			personnes.add(new Personne(id, pseudo, motDePasse, email, dateDeNaissance, bio, nbCredits, estFumeur, ville,
					statut, genreRecherche, genre));
		}

		return personnes;
	}

	@Override
	public boolean delete(Personne personne) throws SQLException {
		boolean estPresent = false;

		if (findOne(personne.getId()) != null) {

			PreparedStatement ps = connection.prepareStatement(Requetes.DELETE_PERSONNE);
			ps.setLong(1, personne.getId());
			ps.executeUpdate();
			estPresent = true;
		}

		return estPresent;
	}

	@Override
	public Personne findInteretsParPersonneId(Long id) throws SQLException {
		String query = Requetes.RECUPERATION_INTERETS_PAR_PERSONNE_ID;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();

		Personne personne = null;
		List<Interet> interets = new ArrayList<>();
		Interet interet = null;

		while (rs.next()) {
			personne = new Personne();
			interet = new Interet();

			personne.setId(rs.getLong("id"));
			personne.setBio(rs.getString("bio"));
			personne.setDateDeNaissance(rs.getDate("dateDeNaissance").toLocalDate());
			personne.setEmail(rs.getString("email"));
			personne.setEstFumeur(rs.getBoolean("estFumeur"));
			personne.setMotDePasse(rs.getString("motDePasse"));
			personne.setNbCredits(rs.getInt("nbCredits"));
			personne.setPseudo(rs.getString("pseudo"));
			personne.setGenre(genreDao.findOne(rs.getLong("genre_id")));
			personne.setGenreRecherche(genreRechercheDao.findOne(rs.getLong("genreRecherche_id")));
			personne.setStatut(statutDao.findOne(rs.getLong("statut_id")));
			personne.setVille(villeDao.findOne(rs.getLong("ville_id")));

			interet.setId(rs.getLong("interet_id"));
			interet.setNom(rs.getString("interet"));
			interets.add(interet);
			personne.setInterets(interets);

		}
		return personne;
	}

	@Override
	public List<Personne> recupererPersonnesParGenreRecherchesAvecLimit(Long genreRecherche_id) throws SQLException {
		String query = Requetes.RECUPERATION_PERSONNE_PAR_GENRE_RECHERCHE_AVEC_LIMIT;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1, genreRecherche_id);
		ResultSet rs = ps.executeQuery();

		List<Personne> personnes = new ArrayList<>();

		while (rs.next()) {
			Long id = rs.getLong("id");
			String bio = rs.getString("bio");
			LocalDate dateDeNaissance = rs.getDate("dateDeNaissance").toLocalDate();
			String email = rs.getString("email");
			Boolean estFumeur = rs.getBoolean("estFumeur");
			String motDePasse = rs.getString("motDePasse");
			int nbCredits = rs.getInt("nbCredits");
			String pseudo = rs.getString("pseudo");
			Genre genre = genreDao.findOne(rs.getLong("genre_id"));
			Statut statut = statutDao.findOne(rs.getLong("statut_id"));
			Genre genreRecherche = genreRechercheDao.findOne(rs.getLong("genreRecherche_id"));
			Ville ville = villeDao.findOne(rs.getLong("ville_id"));

			personnes.add(new Personne(id, pseudo, motDePasse, email, dateDeNaissance, bio, nbCredits, estFumeur, ville,
					statut, genreRecherche, genre));
		}

		return personnes;
	}

	@Override
	public List<Personne> recupererPersonnesParGenreRecherchesEtSansInvitationAvecLimit5(Long genreRecherche_id,
			Long personne_id) throws SQLException {
		String query = Requetes.RECUPERATION_PERSONNE_PAR_GENRE_RECHERCHE_ET_SANS_INVITATION_AVEC_LIMIT;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1, genreRecherche_id);
		ps.setLong(2, personne_id);
		ResultSet rs = ps.executeQuery();

		List<Personne> personnes = new ArrayList<>();

		while (rs.next()) {
			Long id = rs.getLong("id");
			String bio = rs.getString("bio");
			LocalDate dateDeNaissance = rs.getDate("dateDeNaissance").toLocalDate();
			String email = rs.getString("email");
			Boolean estFumeur = rs.getBoolean("estFumeur");
			String motDePasse = rs.getString("motDePasse");
			int nbCredits = rs.getInt("nbCredits");
			String pseudo = rs.getString("pseudo");
			Genre genre = genreDao.findOne(rs.getLong("genre_id"));
			Statut statut = statutDao.findOne(rs.getLong("statut_id"));
			Genre genreRecherche = genreRechercheDao.findOne(rs.getLong("genreRecherche_id"));
			Ville ville = villeDao.findOne(rs.getLong("ville_id"));

			personnes.add(new Personne(id, pseudo, motDePasse, email, dateDeNaissance, bio, nbCredits, estFumeur, ville,
					statut, genreRecherche, genre));
		}

		return personnes;
	}

	@Override
	public List<Personne> filtrer_personnes_par_interet(Long genreRecherche_id, Long expediteur_id, Long interetId)
			throws SQLException {
		String query = Requetes.FILTRER_PERSONNES_PAR_INTERET;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1, genreRecherche_id);
		ps.setLong(2, expediteur_id);
		ps.setLong(3, interetId);
		ResultSet rs = ps.executeQuery();

		List<Personne> personnes = new ArrayList<>();

		while (rs.next()) {
			Long id = rs.getLong("id");
			String bio = rs.getString("bio");
			LocalDate dateDeNaissance = rs.getDate("dateDeNaissance").toLocalDate();
			String email = rs.getString("email");
			Boolean estFumeur = rs.getBoolean("estFumeur");
			String motDePasse = rs.getString("motDePasse");
			int nbCredits = rs.getInt("nbCredits");
			String pseudo = rs.getString("pseudo");
			Genre genre = genreDao.findOne(rs.getLong("genre_id"));
			Statut statut = statutDao.findOne(rs.getLong("statut_id"));
			Genre genreRecherche = genreRechercheDao.findOne(rs.getLong("genreRecherche_id"));
			Ville ville = villeDao.findOne(rs.getLong("ville_id"));

			personnes.add(new Personne(id, pseudo, motDePasse, email, dateDeNaissance, bio, nbCredits, estFumeur, ville,
					statut, genreRecherche, genre));
		}

		return personnes;
	}

	@Override
	public List<Personne> filtrer_personnes_par_ville(Long genreRecherche_id, Long expediteur_id, Long villeId)
			throws SQLException {
		String query = Requetes.FILTRER_PERSONNES_PAR_VILLE;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1, genreRecherche_id);
		ps.setLong(2, expediteur_id);
		ps.setLong(3, villeId);
		ResultSet rs = ps.executeQuery();

		List<Personne> personnes = new ArrayList<>();

		while (rs.next()) {
			Long id = rs.getLong("id");
			String bio = rs.getString("bio");
			LocalDate dateDeNaissance = rs.getDate("dateDeNaissance").toLocalDate();
			String email = rs.getString("email");
			Boolean estFumeur = rs.getBoolean("estFumeur");
			String motDePasse = rs.getString("motDePasse");
			int nbCredits = rs.getInt("nbCredits");
			String pseudo = rs.getString("pseudo");
			Genre genre = genreDao.findOne(rs.getLong("genre_id"));
			Statut statut = statutDao.findOne(rs.getLong("statut_id"));
			Genre genreRecherche = genreRechercheDao.findOne(rs.getLong("genreRecherche_id"));
			Ville ville = villeDao.findOne(rs.getLong("ville_id"));

			personnes.add(new Personne(id, pseudo, motDePasse, email, dateDeNaissance, bio, nbCredits, estFumeur, ville,
					statut, genreRecherche, genre));
		}

		return personnes;
	}

	@Override
	public List<Personne> filtrer_personnes_par_ville_et_interet(Long genreRecherche_id, Long expediteur_id,
			Long villeId, Long interetId) throws SQLException {
		String query = Requetes.FILTRER_PERSONNES_PAR_VILLE_ET_INTERET;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1, genreRecherche_id);
		ps.setLong(2, expediteur_id);
		ps.setLong(3, villeId);
		ps.setLong(4, interetId);
		ResultSet rs = ps.executeQuery();

		List<Personne> personnes = new ArrayList<>();

		while (rs.next()) {
			Long id = rs.getLong("id");
			String bio = rs.getString("bio");
			LocalDate dateDeNaissance = rs.getDate("dateDeNaissance").toLocalDate();
			String email = rs.getString("email");
			Boolean estFumeur = rs.getBoolean("estFumeur");
			String motDePasse = rs.getString("motDePasse");
			int nbCredits = rs.getInt("nbCredits");
			String pseudo = rs.getString("pseudo");
			Genre genre = genreDao.findOne(rs.getLong("genre_id"));
			Statut statut = statutDao.findOne(rs.getLong("statut_id"));
			Genre genreRecherche = genreRechercheDao.findOne(rs.getLong("genreRecherche_id"));
			Ville ville = villeDao.findOne(rs.getLong("ville_id"));

			personnes.add(new Personne(id, pseudo, motDePasse, email, dateDeNaissance, bio, nbCredits, estFumeur, ville,
					statut, genreRecherche, genre));
		}

		return personnes;
	}

	@Override
	public List<Personne> recuperer_personne_et_pagination(Long genreRecherche_id, Long expediteur_id, int limit,
			int offset) throws SQLException {
		String query = Requetes.RECUPERATION_PERSONNE_PAR_GENRE_RECHERCHE_ET_SANS_INVITATION_AVEC_LIMIT_ET_OFFSET_POUR_PAGINATION;
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1, genreRecherche_id);
		ps.setLong(2, expediteur_id);
		ps.setInt(3, limit);
		ps.setInt(4, offset);
		ResultSet rs = ps.executeQuery();

		List<Personne> personnes = new ArrayList<>();

		while (rs.next()) {
			Long id = rs.getLong("id");
			String bio = rs.getString("bio");
			LocalDate dateDeNaissance = rs.getDate("dateDeNaissance").toLocalDate();
			String email = rs.getString("email");
			Boolean estFumeur = rs.getBoolean("estFumeur");
			String motDePasse = rs.getString("motDePasse");
			int nbCredits = rs.getInt("nbCredits");
			String pseudo = rs.getString("pseudo");
			Genre genre = genreDao.findOne(rs.getLong("genre_id"));
			Statut statut = statutDao.findOne(rs.getLong("statut_id"));
			Genre genreRecherche = genreRechercheDao.findOne(rs.getLong("genreRecherche_id"));
			Ville ville = villeDao.findOne(rs.getLong("ville_id"));

			personnes.add(new Personne(id, pseudo, motDePasse, email, dateDeNaissance, bio, nbCredits, estFumeur, ville,
					statut, genreRecherche, genre));
		}

		return personnes;
	}

}
