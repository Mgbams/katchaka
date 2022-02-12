
package fr.orsys.kingsley.katchaka.business;

import java.util.ArrayList;
import java.util.List;

public class Interet {
	private Long id;
	private String nom;
	private List<Personne> personnes;

	public Interet() {
	}

	public Interet(String nom) {
		this.nom = nom;
	}

	public Interet(Long id, String nom) {
		this.id = id;
		this.nom = nom;
		this.personnes = new ArrayList<>();
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

	@Override
	public String toString() {
		return "Interet [id=" + id + ", nom=" + nom + ", personnes=" + personnes + "]";
	}

}
