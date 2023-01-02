package main.java.hex;

import main.java.joueur.robot.IConvertisseur;

/**
 * IRobot.java
 * Une interface de la classe Robot étendant l'interface IJoueur
 * @author Wania
 * @author Iyad
 * @author Mahydine
 * @since 01/01/2023
 *
 */

public interface IRobot extends IJoueur {

	/**
	 * Renvoie la borne maximal des coups du robot
	 * @return la borne maximal des coups du robot
	 */
	int getMax();

	/**
	 * Renvoie la borne minimale des coups du robot
	 * @return la borne minimale des coups du robot
	 */
	int getMin();

	/**
	 * Permet de connaître le convertisseur utilisé par le robot
	 * @return le convertisseur
	 */
	IConvertisseur getConvertisseur();
}
