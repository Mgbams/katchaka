package fr.orsys.kingsley.katchaka.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VieCommune {
	private static final int NB_CREDITS_PAR_DEFAULT = 500;
	private Long id;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private int nbCredits;
	private List<Message> messages; // Redundant
	private Invitation invitation;
	
	public VieCommune() {
	}
	
	public VieCommune(LocalDate dateDebut, LocalDate dateFin, int nbCredits,
			Invitation invitation) {
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nbCredits = NB_CREDITS_PAR_DEFAULT;
		this.messages = new ArrayList<>();
		
		this.invitation = invitation;
	}

	public VieCommune(Long id, LocalDate dateDebut, LocalDate dateFin, int nbCredits,
			Invitation invitation) {
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nbCredits = nbCredits;
		this.messages = new ArrayList<>();
		this.invitation = invitation;
	}
	
	public VieCommune(Long id, LocalDate dateDebut, LocalDate dateFin, int nbCredits) {
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nbCredits = nbCredits;
		this.messages = new ArrayList<>();
		this.invitation = new Invitation();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public int getNbCredits() {
		return nbCredits;
	}

	public void setNbCredits(int nbCredits) {
		this.nbCredits = nbCredits;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Invitation getInvitation() {
		return invitation;
	}

	public void setInvitation(Invitation invitation) {
		this.invitation = invitation;
	}

	public static int getNbCreditsParDefault() {
		return NB_CREDITS_PAR_DEFAULT;
	}

	@Override
	public String toString() {
		return "VieCommune [id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", nbCredits=" + nbCredits
				+ ", messages=" + messages + ", invitation=" + invitation + "]";
	}

}
