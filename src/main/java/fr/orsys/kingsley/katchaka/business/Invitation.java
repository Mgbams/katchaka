package fr.orsys.kingsley.katchaka.business;

import java.time.LocalDateTime;

public class Invitation {
	private Long id;
	private LocalDateTime dateEnvoi;
	private LocalDateTime dateLecture;
	private Boolean estAccepte;
	private VieCommune vieCommune;
	private Personne destinataire;
	private Personne expediteur;

	public Invitation() {
	}

	public Invitation(LocalDateTime dateEnvoi, LocalDateTime dateLecture, Boolean estAccepte, VieCommune vieCommune,
			Personne destinataire, Personne expediteur) {
		this.dateEnvoi = dateEnvoi;
		this.dateLecture = dateLecture;
		this.estAccepte = estAccepte;
		this.vieCommune = vieCommune;
		this.destinataire = destinataire;
		this.expediteur = expediteur;
	}

	public Invitation(Long id, LocalDateTime dateEnvoi, LocalDateTime dateLecture, Boolean estAccepte,
			VieCommune vieCommune, Personne destinataire, Personne expediteur) {
		this.id = id;
		this.dateEnvoi = dateEnvoi;
		this.dateLecture = dateLecture;
		this.estAccepte = estAccepte;
		this.vieCommune = vieCommune;
		this.destinataire = destinataire;
		this.expediteur = expediteur;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateEnvoi() {
		return dateEnvoi;
	}

	public void setDateEnvoi(LocalDateTime dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}

	public LocalDateTime getDateLecture() {
		return dateLecture;
	}

	public void setDateLecture(LocalDateTime dateLecture) {
		this.dateLecture = dateLecture;
	}

	public Boolean getEstAccepte() {
		return estAccepte;
	}

	public void setEstAccepte(Boolean estAccepte) {
		this.estAccepte = estAccepte;
	}

	public VieCommune getVieCommune() {
		return vieCommune;
	}

	public void setVieCommune(VieCommune vieCommune) {
		this.vieCommune = vieCommune;
	}

	public Personne getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(Personne destinataire) {
		this.destinataire = destinataire;
	}

	public Personne getExpediteur() {
		return expediteur;
	}

	public void setExpediteur(Personne expediteur) {
		this.expediteur = expediteur;
	}

	@Override
	public String toString() {
		return "Invitation [id=" + id + ", dateEnvoi=" + dateEnvoi + ", dateLecture=" + dateLecture + ", estAccepte="
				+ estAccepte + ", vieCommune=" + vieCommune + ", destinataire=" + destinataire + ", expediteur="
				+ expediteur + "]";
	}

}
