package main.java.hex;

/**
 * IPlateau.java
 * Une interface de la classe Plateau
 * @author Iyad
 * @author Wania
 * @author Mahydine
 * @since 01/01/2023
 */

public interface IPlateau {

	/**
	 * Permet de connaître le numéro du joueur gagnant.
	 * Commence à 0.
	 * @return le numéro du joueur gagnant
	 * @throws IllegalStateException - Aucun joueur n'a gagné la partie
	 */
	int getGagnant() throws IllegalStateException;

	/**
	 * Permet de connaître le numéro du joueur dont c'est le tour.
	 * Commence à 0.
	 * @return le numéro du joueur dont c'est le tour.
	 */
	int getJoueur();

	/**
	 * Permet de savoir si la partie est gagné par l'un des joueurs
	 * @return l'état de la partie (vrai si gagné et faux sinon)
	 */
	boolean estPartieGagnee();

	/**
	 * Fait jouer un pion au joueur actuel sur une case.
	 * @param coord - la case jouée
	 * @throws IndexOutOfBoundsException - La coordonnée est en dehors du plateau
	 * @throws IllegalArgumentException - La case coord contient déjà un Pion
	 */
	void jouer(String coord) throws IndexOutOfBoundsException, IllegalArgumentException;

	/**
	 * Permet de savoir si la case est valide
	 * @param coord - la case
	 * @return vrai si la coordonnée est valide, si elle est cohérente par rapport au plateau et faux sinon
	 */
	boolean estValide(String coord);

	/**
	 * Permet de connaître la taille du plateau
	 * @return la taille du plateau
	 */
	int taille();

	/**
	 * Construit une chaine de caractère representant l'état actuelle du plateau
	 * @return la chaine de caractère représentant l'état actuelle du plateau
	 */
	String toString();

}
