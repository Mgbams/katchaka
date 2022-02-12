package fr.orsys.kingsley.katchaka.business;

import java.time.LocalDate;

public class Message {
	private Long id;
	private String contenu;
	private LocalDate dateEnvoi;
	private LocalDate dateLecture;
	private VieCommune vieCommune;
	private Personne expediteur;
	private Personne destinataire;
	
	public Message() {
	}
	
	public Message(String contenu, LocalDate dateEnvoi, LocalDate dateLecture, VieCommune vieCommune,
			Personne expediteur, Personne destinataire) {
		this.contenu = contenu;
		this.dateEnvoi = dateEnvoi;
		this.dateLecture = dateLecture;
		this.vieCommune = vieCommune;
		this.expediteur = expediteur;
		this.destinataire = destinataire;
	}


	public Message(Long id, String contenu, LocalDate dateEnvoi, LocalDate dateLecture, VieCommune vieCommune,
			Personne expediteur, Personne destinataire) {
		this.id = id;
		this.contenu = contenu;
		this.dateEnvoi = dateEnvoi;
		this.dateLecture = dateLecture;
		this.vieCommune = vieCommune;
		this.expediteur = expediteur;
		this.destinataire = destinataire;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public LocalDate getDateEnvoi() {
		return dateEnvoi;
	}

	public void setDateEnvoi(LocalDate dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}

	public LocalDate getDateLecture() {
		return dateLecture;
	}

	public void setDateLecture(LocalDate dateLecture) {
		this.dateLecture = dateLecture;
	}

	public VieCommune getVieCommune() {
		return vieCommune;
	}

	public void setVieCommune(VieCommune vieCommune) {
		this.vieCommune = vieCommune;
	}

	public Personne getExpediteur() {
		return expediteur;
	}

	public void setExpediteur(Personne expediteur) {
		this.expediteur = expediteur;
	}

	public Personne getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(Personne destinataire) {
		this.destinataire = destinataire;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", contenu=" + contenu + ", dateEnvoi=" + dateEnvoi + ", dateLecture="
				+ dateLecture + ", vieCommune=" + vieCommune + ", expediteur=" + expediteur + ", destinataire="
				+ destinataire + "]";
	}

}
