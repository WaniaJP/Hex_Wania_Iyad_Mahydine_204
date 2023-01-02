package main.java.hex;

import main.java.convertisseur.ConvertisseurIntToLetter;
import main.java.joueur.robot.Robot;

/**
 * Fabrique un Robot
 * @author Wania
 * @author Iyad
 * @author Mahydine
 * @version 1.1
 * @since 1.0
 *
 */
public class FabriqueRobot {

	/**
	 * Crée un joueur Robot
	 * @param min
	 * @param max
	 * @return un robot qui respectera sera contraint de jouer des coups entre les bornes min et max
	 */
	public static IRobot creerRobot(int min, int max) {
		return new Robot(min , max, new ConvertisseurIntToLetter());
	}
}
