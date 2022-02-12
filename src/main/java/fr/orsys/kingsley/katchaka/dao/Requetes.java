package fr.orsys.kingsley.katchaka.dao;

public class Requetes {
	public static final String AJOUT_GENRE = "INSERT INTO genre (nom) VALUES (?)";
	public static final String RECUPERATION_GENRES = "SELECT id, nom from genre";
	public static final String RECUPERATION_GENRE_PAR_ID = "SELECT id, nom from genre WHERE id = ?";
	public static final String UPDATE_GENRE = "UPDATE genre SET nom = ?  WHERE id = ?";
	public static final String DELETE_GENRE = "DELETE from genre WHERE id = ?";

	public static final String AJOUT_INTERET = "INSERT INTO interet (nom) VALUES (?)";
	public static final String RECUPERATION_INTERETS = "SELECT id, nom from interet";
	public static final String RECUPERATION_INTERET_PAR_ID = "SELECT id, nom from interet WHERE id = ?";
	public static final String UPDATE_INTERET = "UPDATE interet SET nom = ?  WHERE id = ?";
	public static final String DELETE_INTERET = "DELETE from interet WHERE id = ?";

	public static final String AJOUT_INVITATION = "INSERT INTO invitation (dateEnvoi, dateLecture, estAccepte, destinataire_id, expediteur_id, vieCommune_id) VALUES (?, ?, ?, ?, ?, ?)";
	public static final String RECUPERATION_INVITATIONS = "SELECT id, dateEnvoi, dateLecture, estAccepte, destinataire_id, expediteur_id, vieCommune_id from invitation";
	public static final String RECUPERATION_INVITATIONS_PAR_EXPEDITEUR = "SELECT id, dateEnvoi, dateLecture, estAccepte, destinataire_id, expediteur_id, vieCommune_id from invitation WHERE expediteur_id = ?";
	public static final String RECUPERATION_INVITATIONS_PAR_DESTINATEUR = "SELECT id, dateEnvoi, dateLecture, estAccepte, destinataire_id, expediteur_id, vieCommune_id from invitation WHERE destinataire_id = ?";
	public static final String RECUPERATION_INVITATIONS_PAR_DESTINATEURID_ET_EXPEDITEURID = "SELECT id, dateEnvoi, dateLecture, estAccepte, destinataire_id, expediteur_id, vieCommune_id from invitation WHERE expediteur_id = ? AND destinataire_id = ?";
	public static final String RECUPERATION_INVITATION_PAR_ID = "SELECT id, dateEnvoi, dateLecture, estAccepte, destinataire_id, expediteur_id, vieCommune_id from invitation WHERE id = ?";
	public static final String UPDATE_INVITATION = "UPDATE invitation SET dateEnvoi = ?, dateLecture = ?, estAccepte = ?, destinataire_id = ?, expediteur_id = ?, vieCommune_id = ? WHERE id = ?";
	public static final String UPDATE_INVITATION_PENDANT_ACCEPTATION_OU_DECLINANT = "UPDATE invitation SET dateLecture=?, estAccepte=? WHERE expediteur_id=? AND destinataire_id=?";
	public static final String DELETE_INVITATION = "DELETE from invitation WHERE id = ?";

	public static final String AJOUT_MESSAGE = "INSERT INTO message (contenu, dateEnvoi, dateLecture, destinataire_id, expediteur_id, vieCommune_id) VALUES (?, ?, ?, ?, ?, ?)";
	public static final String RECUPERATION_MESSAGES = "SELECT id, contenu, dateEnvoi, dateLecture, destinataire_id, expediteur_id, vieCommune_id from message";
	public static final String RECUPERATION_MESSAGE_PAR_ID = "SELECT id, contenu, dateEnvoi, dateLecture, destinataire_id, expediteur_id, vieCommune_id from message WHERE id = ?";
	public static final String UPDATE_MESSAGE = "UPDATE message SET contenu = ?, dateEnvoi = ?, dateLecture = ?, destinataire_id = ?, expediteur_id = ?, vieCommune_id = ? WHERE id = ?";
	public static final String DELETE_MESSAGE = "DELETE from message WHERE id = ?";

	public static final String AJOUT_VILLE = "INSERT INTO ville (nom) VALUES (?)";
	public static final String RECUPERATION_VILLES = "SELECT id, nom from ville";
	public static final String RECUPERATION_VILLE_PAR_ID = "SELECT id, nom from ville WHERE id = ?";
	public static final String UPDATE_VILLE = "UPDATE ville SET nom = ?  WHERE id = ?";
	public static final String DELETE_VILLE = "DELETE from ville WHERE id = ?";

	public static final String AJOUT_VIECOMMUNE = "INSERT INTO viecommune (dateDebut, dateFin, nbCredits, invitation_id) VALUES (?, ?, ?, ?)";
	public static final String RECUPERATION_VIECOMMUNES = "SELECT id, dateDebut, dateFin, nbCredits, invitation_id from viecommune";
	public static final String RECUPERATION_VIECOMMUNE_PAR_ID = "SELECT id, dateDebut, dateFin, nbCredits, invitation_id from viecommune WHERE id = ?";
	public static final String UPDATE_VIECOMMUNE = "UPDATE viecommune SET dateDebut = ?, dateFin = ?, nbCredits = ?, invitation_id = ? WHERE id = ?";
	public static final String DELETE_VIECOMMUNE = "DELETE from viecommune WHERE id = ?";

	public static final String AJOUT_STATUT = "INSERT INTO statut (nom) VALUES (?)";
	public static final String RECUPERATION_STATUTS = "SELECT id, nom from statut";
	public static final String RECUPERATION_STATUT_PAR_ID = "SELECT id, nom from statut WHERE id = ?";
	public static final String UPDATE_STATUT = "UPDATE statut SET nom = ?  WHERE id = ?";
	public static final String DELETE_STATUT = "DELETE from statut WHERE id = ?";

	public static final String AJOUT_PERSONNE = "INSERT INTO personne (bio, dateDeNaissance, email, estFumeur, motDePasse, nbCredits, pseudo, genre_id, genreRecherche_id, statut_id, ville_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String RECUPERATION_PERSONNES = "SELECT id, bio, dateDeNaissance, email, estFumeur, motDePasse, nbCredits, pseudo, genre_id, genreRecherche_id, statut_id, ville_id from personne";
	public static final String RECUPERATION_PERSONNE_PAR_ID = "SELECT id, bio, dateDeNaissance, email, estFumeur, motDePasse, nbCredits, pseudo, genre_id, genreRecherche_id, statut_id, ville_id from personne WHERE id = ?";
	public static final String UPDATE_PERSONNE = "UPDATE personne SET bio = ?, dateDeNaissance = ?, email = ?, estFumeur = ?, motDePasse = ?, nbCredits = ?, pseudo = ?, genre_id = ?, genreRecherche_id = ?, statut_id = ?, ville_id = ? WHERE id = ?";
	public static final String DELETE_PERSONNE = "DELETE from personne WHERE id = ?";
	public static final String RECUPERATION_PERSONNE_PAR_EMAIL_ET_MOT_DE_PASSE = "SELECT id, bio, dateDeNaissance, email, estFumeur, motDePasse, nbCredits, pseudo, genre_id, genreRecherche_id, statut_id, ville_id from personne WHERE email=? AND motDePasse=?";
	public static final String RECUPERATION_PERSONNE_PAR_GENRE_RECHERCHE = "SELECT personne.id, bio, dateDeNaissance, email, estFumeur, motDePasse, nbCredits, pseudo, genre_id, genreRecherche_id, statut_id, ville_id from personne \r\n"
			+ "LEFT JOIN genre\r\n" + "ON personne.genre_id = genre.id\r\n" + "LEFT JOIN ville\r\n"
			+ "ON personne.ville_id = ville.id\r\n" + "LEFT JOIN statut\r\n" + "ON personne.statut_id = statut.id\r\n"
			+ "WHERE genreRecherche_id != ?";
	public static final String RECUPERATION_PERSONNE_PAR_GENRE_RECHERCHE_AVEC_LIMIT = "SELECT personne.id, bio, dateDeNaissance, email, estFumeur, motDePasse, nbCredits, pseudo, genre_id, genreRecherche_id, statut_id, ville_id from personne \r\n"
			+ "LEFT JOIN genre\r\n" + "ON personne.genre_id = genre.id\r\n" + "LEFT JOIN ville\r\n"
			+ "ON personne.ville_id = ville.id\r\n" + "LEFT JOIN statut\r\n" + "ON personne.statut_id = statut.id\r\n"
			+ "WHERE genreRecherche_id != ? LIMIT 5";
	public static final String RECUPERATION_INTERETS_PAR_PERSONNE_ID = "SELECT *, interet.id as interet_id, interet.nom as interet from personne \r\n"
			+ "LEFT JOIN genre\r\n" + "ON personne.genre_id = genre.id\r\n" + "LEFT JOIN ville\r\n"
			+ "ON personne.ville_id = ville.id\r\n" + "LEFT JOIN statut\r\n" + "ON personne.statut_id = statut.id\r\n"
			+ "LEFT JOIN personne_interets\r\n" + "ON personne.id = personnes_id\r\n" + "LEFT JOIN interet \r\n"
			+ "ON interet.id = personne_interets.interets_id\r\n" + "WHERE personne.id = ?";

	public static final String RECUPERATION_PERSONNE_PAR_GENRE_RECHERCHE_ET_SANS_INVITATION_AVEC_LIMIT = "SELECT distinct(personne.id), bio, dateDeNaissance, email, estFumeur, motDePasse, nbCredits, pseudo, genre_id, genreRecherche_id, statut_id, ville_id \r\n"
			+ "from personne\r\n" + "LEFT JOIN genre \r\n" + "ON personne.genre_id = genre.id \r\n"
			+ "LEFT JOIN ville\r\n" + "ON personne.ville_id = ville.id \r\n" + "LEFT JOIN statut \r\n"
			+ "ON personne.statut_id = statut.id\r\n" + "LEFT JOIN invitation as inv\r\n"
			+ "ON inv.expediteur_id = personne.id\r\n" + "WHERE genreRecherche_id != ? AND personne.id NOT IN (\r\n"
			+ "	SELECT destinataire_id FROM invitation \r\n" + "    WHERE expediteur_id = ?\r\n" + ") LIMIT 5";
	
	// Pagination
	public static final String RECUPERATION_PERSONNE_PAR_GENRE_RECHERCHE_ET_SANS_INVITATION_AVEC_LIMIT_ET_OFFSET_POUR_PAGINATION = "SELECT distinct(personne.id), bio, dateDeNaissance, email, estFumeur, motDePasse, nbCredits, pseudo, genre_id, genreRecherche_id, statut_id, ville_id \r\n"
			+ "from personne\r\n" + "LEFT JOIN genre \r\n" + "ON personne.genre_id = genre.id \r\n"
			+ "LEFT JOIN ville\r\n" + "ON personne.ville_id = ville.id \r\n" + "LEFT JOIN statut \r\n"
			+ "ON personne.statut_id = statut.id\r\n" + "LEFT JOIN invitation as inv\r\n"
			+ "ON inv.expediteur_id = personne.id\r\n" + "WHERE genreRecherche_id != ? AND personne.id NOT IN (\r\n"
			+ "	SELECT destinataire_id FROM invitation \r\n" + " WHERE expediteur_id = ?\r\n" + ") LIMIT ? OFFSET ?";

	// Filtrer personnes par ville
	public static final String FILTRER_PERSONNES_PAR_VILLE = "SELECT distinct(personne.id), bio, dateDeNaissance, email, estFumeur, motDePasse, nbCredits, pseudo, genre_id, genreRecherche_id, statut_id, ville_id\r\n"
			+ "FROM personne\r\n" + "LEFT JOIN genre\r\n" + "ON personne.genre_id = genre.id\r\n"
			+ "LEFT JOIN ville\r\n" + "ON personne.ville_id = ville.id\r\n" + "LEFT JOIN statut\r\n"
			+ "ON personne.statut_id = statut.id\r\n" + "LEFT JOIN invitation as inv\r\n"
			+ "ON inv.expediteur_id = personne.id\r\n" + "LEFT JOIN personne_interets\r\n"
			+ "ON personne.id = personne_interets.personnes_id\r\n" + "LEFT JOIN interet\r\n"
			+ "ON interet.id = personne_interets.interets_id\r\n"
			+ "WHERE genreRecherche_id != ? AND personne.id NOT IN (\r\n" + "SELECT destinataire_id FROM invitation\r\n"
			+ "WHERE expediteur_id = ? \r\n" + ") AND (ville_id = ?) LIMIT 5";

	// Filtrer personnes par interet
	public static final String FILTRER_PERSONNES_PAR_INTERET = "SELECT distinct(personne.id), bio, dateDeNaissance, email, estFumeur, motDePasse, nbCredits, pseudo, genre_id, genreRecherche_id, statut_id, ville_id\r\n"
			+ "FROM personne\r\n" + "LEFT JOIN genre\r\n" + "ON personne.genre_id = genre.id\r\n"
			+ "LEFT JOIN ville\r\n" + "ON personne.ville_id = ville.id\r\n" + "LEFT JOIN statut\r\n"
			+ "ON personne.statut_id = statut.id\r\n" + "LEFT JOIN invitation as inv\r\n"
			+ "ON inv.expediteur_id = personne.id\r\n" + "LEFT JOIN personne_interets\r\n"
			+ "ON personne.id = personne_interets.personnes_id\r\n" + "LEFT JOIN interet\r\n"
			+ "ON interet.id = personne_interets.interets_id\r\n"
			+ "WHERE genreRecherche_id != ? AND personne.id NOT IN (\r\n" + "SELECT destinataire_id FROM invitation\r\n"
			+ "WHERE expediteur_id = ? \r\n" + ") AND (interets_id = ?) LIMIT 5";

	// Filtrer personnes par interet et ville
	public static final String FILTRER_PERSONNES_PAR_VILLE_ET_INTERET = "SELECT distinct(personne.id), bio, dateDeNaissance, email, estFumeur, motDePasse, nbCredits, pseudo, genre_id, genreRecherche_id, statut_id, ville_id\r\n"
			+ "FROM personne\r\n" + "LEFT JOIN genre\r\n" + "ON personne.genre_id = genre.id\r\n"
			+ "LEFT JOIN ville\r\n" + "ON personne.ville_id = ville.id\r\n" + "LEFT JOIN statut\r\n"
			+ "ON personne.statut_id = statut.id\r\n" + "LEFT JOIN invitation as inv\r\n"
			+ "ON inv.expediteur_id = personne.id\r\n" + "LEFT JOIN personne_interets\r\n"
			+ "ON personne.id = personne_interets.personnes_id\r\n" + "LEFT JOIN interet\r\n"
			+ "ON interet.id = personne_interets.interets_id\r\n"
			+ "WHERE genreRecherche_id != ? AND personne.id NOT IN (\r\n" + "SELECT destinataire_id FROM invitation\r\n"
			+ "WHERE expediteur_id = ? \r\n" + ") AND (ville_id = ? AND interets_id = 2) LIMIT 5";

	// Requetes pour les deux tables LOOKUP
	public static final String AJOUT_PERSONNE_INTERETS = "INSERT INTO personne_interets (personnes_id, interets_id) VALUES (?, ?)";
	public static final String AJOUT_VILLE_PERSONNES = "INSERT INTO ville_personnes (ville_id, personnes_id) VALUES (?, ?)";
}
