package main.java.plateau;

/**
 * Représente un pion du Plateau. Soit un 'X', un 'O' ou un '.'
 * @author Mikal Ziane
 * @author Iyad
 * @author Wania
 * @author Mahydine
 * @version 1.1
 * @since 1.0
 */

public enum Pion {

	/**
	 * Correspond au symbole 'X'
	 */
	Croix('X'), 

	/**
	 * Correspond au symbole 'O'
	 */
	Rond('O'), 

	/**
	 * Correspond au symbole '.'
	 */
	Vide('.');
	private char symbole;
	private Pion (char symbole) {
		this.symbole = symbole;
	}

	/**
	 * Construit une chaine de caractère correspondant au pion.
	 * @return le pion sous fourme de chaine de caractère
	 */
	public String toString() {
		return ""+symbole;
	}

	/**
	 * Prend un caractère et le transforme en Pion
	 * @param c
	 * @return un Pion
	 * @throws IllegalArgumentException - le caractère ne correspond à aucun des symboles possible entre 'X', 'O' et '.'
	 */
	public static Pion get(char c) throws IllegalArgumentException {
		for (Pion p : Pion.values())
			if (p.symbole == c)
				return p;
		throw new IllegalArgumentException(
				"symbole inconnu " + c);
	}
}
