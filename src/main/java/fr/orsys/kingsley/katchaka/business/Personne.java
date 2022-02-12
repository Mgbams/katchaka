package fr.orsys.kingsley.katchaka.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Personne {
	private static final int NB_CREDITS_INITIAL = 500;
	private Long id;
	private String pseudo;
	private String motDePasse;
	private String email;
	private LocalDate dateDeNaissance;
	private String bio;
	private int nbCredits;
	private Boolean estFumeur;
	private List<Message> messagesRecus;
	private List<Message> messagesEnvoyes;
	private Ville ville;
	private List<Interet> interets;
	private Statut statut;
	private Genre genreRecherche;
	private Genre genre;

	public Personne() {
	}
	
	public Personne(String pseudo, String motDePasse, String email, LocalDate dateDeNaissance, String bio,
			int nbCredits, Boolean estFumeur, Ville ville,
			Statut statut, Genre genreRecherche, Genre genre) {
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.email = email;
		this.dateDeNaissance = dateDeNaissance;
		this.bio = bio;
		this.nbCredits = nbCredits;
		this.estFumeur = estFumeur;
		this.messagesRecus = new ArrayList<>();
		this.messagesEnvoyes = new ArrayList<>();
		this.ville = ville;
		this.interets = new ArrayList<>();
		this.statut = statut;
		this.genreRecherche = genreRecherche;
		this.genre = genre;
	}

	public Personne(Long id, String pseudo, String motDePasse, String email, LocalDate dateDeNaissance, String bio,
			int nbCredits, Boolean estFumeur, Ville ville,
			Statut statut, Genre genreRecherche, Genre genre) {
		this.id = id;
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.email = email;
		this.dateDeNaissance = dateDeNaissance;
		this.bio = bio;
		this.nbCredits = nbCredits;
		this.estFumeur = estFumeur;
		this.messagesRecus = new ArrayList<>();
		this.messagesEnvoyes = new ArrayList<>();
		this.ville = ville;
		this.interets = new ArrayList<>();
		this.statut = statut;
		this.genreRecherche = genreRecherche;
		this.genre = genre;
	}
	

	public Personne(Long id, String pseudo, String motDePasse, String email, LocalDate dateDeNaissance, String bio,
			int nbCredits, Boolean estFumeur, Ville ville,
			Statut statut, Genre genreRecherche, Genre genre, List<Interet> interets) {
		super();
		this.id = id;
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.email = email;
		this.dateDeNaissance = dateDeNaissance;
		this.bio = bio;
		this.nbCredits = nbCredits;
		this.estFumeur = estFumeur;
		this.messagesRecus =  new ArrayList<>();
		this.messagesEnvoyes =  new ArrayList<>();
		this.ville = ville;
		this.interets = interets;
		this.statut = statut;
		this.genreRecherche = genreRecherche;
		this.genre = genre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(LocalDate dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public int getNbCredits() {
		return nbCredits;
	}

	public void setNbCredits(int nbCredits) {
		this.nbCredits = nbCredits;
	}

	public Boolean getEstFumeur() {
		return estFumeur;
	}

	public void setEstFumeur(Boolean estFumeur) {
		this.estFumeur = estFumeur;
	}

	public List<Message> getMessagesRecus() {
		return messagesRecus;
	}

	public void setMessagesRecus(List<Message> messagesRecus) {
		this.messagesRecus = messagesRecus;
	}

	public List<Message> getMessagesEnvoyes() {
		return messagesEnvoyes;
	}

	public void setMessagesEnvoyes(List<Message> messagesEnvoyes) {
		this.messagesEnvoyes = messagesEnvoyes;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	public List<Interet> getInterets() {
		return interets;
	}

	public void setInterets(List<Interet> interets) {
		this.interets = interets;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Genre getGenreRecherche() {
		return genreRecherche;
	}

	public void setGenreRecherche(Genre genreRecherche) {
		this.genreRecherche = genreRecherche;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public static int getNbCreditsInitial() {
		return NB_CREDITS_INITIAL;
	}

	@Override
	public String toString() {
		return "Personne [id=" + id + ", pseudo=" + pseudo + ", motDePasse=" + motDePasse + ", email=" + email
				+ ", dateDeNaissance=" + dateDeNaissance + ", bio=" + bio + ", nbCredits=" + nbCredits + ", estFumeur="
				+ estFumeur + ", messagesRecus=" + messagesRecus + ", messagesEnvoyes=" + messagesEnvoyes + ", ville="
				+ ville + ", interets=" + interets + ", statut=" + statut + ", genreRecherche=" + genreRecherche
				+ ", genre=" + genre + "]";
	}

}
