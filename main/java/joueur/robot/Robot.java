package main.java.joueur.robot;

import main.java.hex.IRobot;

/**
 * Représentation d'un joueur Robot de bas-niveau/naïf
 * @author Wania
 * @author Iyad
 * @author Mahydine
 * @since 01/01/2023
 *
 */

public class Robot implements IRobot{

	private final int BORNE_MAX;
	private final int BORNE_MIN;
	private final IConvertisseur CONVERT;
	
	private static int compteur = 0;
	
	private int numero;

	/**
	 * Crée un joueur Robot qui respectera des bornes données, et convertira ses résultats
	 * @param min - borne basse
	 * @param max - borne haute
	 * @param c - convertisseur
	 * @throws IllegalArgumentException - si le minimum est supérieur ou égal ou maximum
	 */
	public Robot(int min, int max, IConvertisseur c) throws IllegalArgumentException {
		if(min >= max) {
			throw new IllegalArgumentException("Le minimum est supérieur ou égal ou maximum");
		}
		BORNE_MIN = min;
		BORNE_MAX = max;
		CONVERT = c;
		numero = ++compteur;
	}

	/**
	 * Permet d'obtenir un coup joué par le robot.
	 * @return une chaine de caractère correspondant au coordonnées du coup joué par le Robot
	 */
	@Override
	public String nvCoup() {
		int lig = (int)(Math.random() * BORNE_MAX) + BORNE_MIN;
		int col = (int)(Math.random() * BORNE_MAX) + BORNE_MIN;
		String s = CONVERT.conversion(col)+lig;
		System.out.println(s);
		return s;
	}

	/**
	 * Permet de connaître le nom du robot
	 * @return le nom du Robot
	 */
	@Override
	public String getNom() {
		return this.getClass().getSimpleName() + " " + numero;
	}

	/**
	 * Permet de connaître la borne max
	 * @return la borne maximal des coups du robot
	 */
	@Override
	public int getMax() {
		return BORNE_MAX;
	}

	/**
	 * Permet de connaître la borne min
	 * @return la borne minimale des coups du robot
	 */
	@Override
	public int getMin() {
		return BORNE_MIN;
	}

	/**
	 * Permet de connaître le convertisseur utilisé.
	 * @return le convertisseur
	 */
	@Override
	public IConvertisseur getConvertisseur() {
		return CONVERT;
	}

}
