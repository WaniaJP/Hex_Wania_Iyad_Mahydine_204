package main.java.hex;

import main.java.plateau.Plateau;

/**
 * Fabrique un joueur
 * @author Wania
 * @author Iyad
 * @author Mahydine
 * @version 1.1
 * @since 1.0
 */

public class FabriquePlateau {

	/**
	 * Création d'un IPlateau
	 * @param taille - la taille du plateau
	 * @return le plateau créé
	 */
	public static IPlateau creerPlateau(int taille) {
		return new Plateau(taille);
	}
}
