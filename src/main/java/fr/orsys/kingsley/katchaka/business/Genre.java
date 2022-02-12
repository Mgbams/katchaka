package fr.orsys.kingsley.katchaka.business;

import java.util.ArrayList;
import java.util.List;

public class Genre {
	private Long id;
	private String nom;
	private List<Personne> personnes;
	private List<Personne> personnesRecherchant;

	public Genre() {
	}

	public Genre(String nom) {
		this.nom = nom;
	}

	public Genre(Long id, String nom) {
		this.id = id;
		this.nom = nom;
		this.personnes = new ArrayList<>();
		this.personnesRecherchant = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Personne> getPersonnes() {
		return personnes;
	}

	public void setPersonnes(List<Personne> personnes) {
		this.personnes = personnes;
	}

	public List<Personne> getPersonnesRecherchant() {
		return personnesRecherchant;
	}

	public void setPersonnesRecherchant(List<Personne> personnesRecherchant) {
		this.personnesRecherchant = personnesRecherchant;
	}

	@Override
	public String toString() {
		return "Genre [id=" + id + ", nom=" + nom + ", personnes=" + personnes + ", personnesRecherchant="
				+ personnesRecherchant + "]";
	}

}
