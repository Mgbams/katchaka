package fr.orsys.kingsley.katchaka.util;

import java.util.Comparator;

import fr.orsys.kingsley.katchaka.business.Personne;

public class ComparateurDePersonnesSurPseudo implements Comparator<Personne> {

	@Override
	public int compare(Personne personne1, Personne personne2) {
		return personne1.getPseudo().compareTo(personne2.getPseudo());
	}

}
