package main.java.hex;

/**
 * IJoueur.java
 * Une interface de la classe Humain
 * @author Wania
 * @author Iyad
 * @author Mahydine
 * @since 01/01/2023
 *
 */
public interface IJoueur {

	/**
	 * Permet de connaître le prochain coup joué par le joueur
	 * @return une chaine de caractère correspondant au coordonnées du coup joué
	 */
	String nvCoup();

	/**
	 * Permet de connaître le nom du joueur.
	 * @return le nom du joueur
	 */
	String getNom();
}
