package main.java.convertisseur;

import main.java.joueur.robot.IConvertisseur;

/**
 * Convertis un entier en une lettre
 * @author Wania
 * @author Iyad
 * @author Mahydine
 * @version 1.1
 * @since 1.0
 */
public class ConvertisseurIntToLetter implements IConvertisseur {

	/**
	 * Convertis un entier en une chaine de caractère
	 * @param i
	 * @return l'entier i sous forme de chaine de caractère
	 */
	public String conversion(int i) {
		return i > -1 && i < 26 ? String.valueOf((char)(i + 65)) : null;
	}

}
