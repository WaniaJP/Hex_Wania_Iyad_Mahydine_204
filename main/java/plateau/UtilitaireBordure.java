package main.java.plateau;

/**
 * IPlateau.java
 * Classe utilitaire qui permet de savoir si la bordure d'une grille est touchéee
 * @author Iyad
 * @author Wania
 * @author Mahydine
 * @since 01/01/2023
 */

public class UtilitaireBordure {

	/**
	 * Permet de savoir si la première bordure (bordure du haut ou bordure de gauche, selon le numéro de joueur) d'une grille est touchée.
	 * @param numeroJoueur
	 * @param nbJoueurs
	 * @param lig
	 * @param col
	 * @param size
	 * @return si la bordure est atteinte
	 * @throws IllegalArgumentException - Un des arguments donnés est invalide.
	 */
	public static boolean premiereBordure(int numeroJoueur, int nbJoueurs, int lig, int col) throws IllegalArgumentException {
		if(numeroJoueur <= 0)
			throw new IllegalArgumentException("Numero du joueur invalide -> inférieur ou égale à 0");
		if(nbJoueurs <= 0)
			throw new IllegalArgumentException("Nombre de joueurs invalide  -> inférieur ou égale à 0");

		if(numeroJoueur > nbJoueurs)
			throw new IllegalArgumentException("numeroJoueur invalide -> supérieur au nombre de joueurs");

		if((numeroJoueur % nbJoueurs) == 0)
			return bordureColumnFirst(col);
		return bordureLigneTop(lig);
	}

	/**
	 * Permet de savoir si la seconde bordure (bordure du bas ou bordure de droite, selon le numéro de joueur) d'une grille est touchée.
	 * @param numeroJoueur
	 * @param nbJoueurs
	 * @param lig
	 * @param col
	 * @param size
	 * @return si la bordure est atteinte
	 * @throws IllegalArgumentException - Un des arguments donnés est invalide.
	 */
	public static boolean secondeBordure(int numeroJoueur, int nbJoueurs, int lig, int col, int ligMax, int colMax) throws IllegalArgumentException{
		if(numeroJoueur <= 0)
			throw new IllegalArgumentException("Numero du joueur invalide -> inférieur ou égale à 0");
		if(nbJoueurs <= 0)
			throw new IllegalArgumentException("Nombre de joueurs invalide  -> inférieur ou égale à 0");

		if(numeroJoueur > nbJoueurs)
			throw new IllegalArgumentException("numeroJoueur invalide -> supérieur au nombre de joueurs");

		if((numeroJoueur % nbJoueurs) == 0)
			return bordureColumnLast(col, colMax);
		return bordureLigneBottom(lig,ligMax);
	}

	/**
	 * Permet de savoir si la bordure du haut d'une grille est touchée.
	 * @param lig
	 * @return si la bordure est atteinte
	 * @throws IllegalArgumentException - La valeur lig est inférieur ou égale à 0.
	 */
	private static boolean bordureLigneTop(int lig) throws IllegalArgumentException{
		if(lig <= 0)
			throw new IllegalArgumentException("Numero de ligne invalide -> inférieur ou égale à 0");

		return lig-1 == 0;
	}

	/**
	 * Permet de savoir si la bordure du bas d'une grille est touchée.
	 * @param lig
	 * @param size
	 * @return si la bordure est atteinte
	 * @throws IllegalArgumentException - La valeur de ligne/de la taille est inférieur ou égale à 0, ou la valeur de la ligne est supérieur à la taille.
	 */
	private static boolean bordureLigneBottom(int lig, int size) throws IllegalArgumentException{
		if(lig <= 0)
			throw new IllegalArgumentException("Numero de ligne invalide -> inférieur ou égale à 0");
		if(size <= 0)
			throw new IllegalArgumentException("Taille invalide -> inférieur ou égale à 0");

		if(lig > size)
			throw new IllegalArgumentException("Numero de ligne invalide -> supérieur à la taille");

		return lig == size;
	}

	/**
	 * Permet de savoir si la bordure de droite d'une grille est touchée.
	 * @param col
	 * @return si la bordure est atteinte
	 * @throws IllegalArgumentException - La valeur col est inférieur ou égale à 0.
	 */
	private static boolean bordureColumnFirst(int col) throws IllegalArgumentException{
		if(col <= 0)
			throw new IllegalArgumentException("Numero de colonne invalide -> inférieur ou égale à 0");

		return col-1 == 0;
	}

	/**
	 * Permet de savoir si la bordure de gauche d'une grille est touchée.
	 * @param col
	 * @param size
	 * @return si la bordure est atteinte
	 * @throws IllegalArgumentException - La valeur de colonne/de la taille est inférieur ou égale à 0, ou la valeur de la colonne est supérieur à la taille.
	 */
	private static boolean bordureColumnLast(int col, int size) throws IllegalArgumentException{
		if(col <= 0)
			throw new IllegalArgumentException("Numero de colonne invalide -> inférieur ou égale à 0");
		if(size <= 0)
			throw new IllegalArgumentException("Taille invalide -> inférieur ou égale à 0");

		if(col > size)
			throw new IllegalArgumentException("Numero de colonne invalide -> supérieur à la taille");

		return col == size;
	}
}
