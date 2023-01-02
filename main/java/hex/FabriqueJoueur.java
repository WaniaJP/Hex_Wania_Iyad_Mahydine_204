package main.java.hex;

import main.java.joueur.humain.Humain;

/**
 * Fabrique un joueur
 * @author Wania
 * @author Iyad
 * @author Mahydine
 * @version 1.1
 * @since 1.0
 */
public class FabriqueJoueur {

	private static final String CODE = "R";

	/**
	 * Crée un joueur Humain ou Robot selon la valeur de CODE
	 * @param code
	 * @param min
	 * @param max
	 * @return un joueur Robot si CODE == R et Humain sinon
	 */
	public static IJoueur creerJoueur(String code, int min, int max) {
		if(code.equals(CODE)) {
			return FabriqueRobot.creerRobot(min, max);
		}
		return new Humain(code);
	}
}
