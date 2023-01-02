package main.java.joueur.humain;

import java.util.Scanner;

import main.java.hex.IJoueur;

/**
 * Représentation d'un joueur humain
 * @author Wania
 * @author Iyad
 * @author Mahydine
 * @version 1.2
 * @since 1.0
 *
 */

public class Humain implements IJoueur {

	private String nom;

	/**
	 * Crée un joueur humain avec son nom
	 * @param nom
	 */
	public Humain(String nom) {
		this.nom = nom;
	}

	/**
	 * Permet les coordonnées d'un coup joué par le joueur humain
	 * @return une chaine de caractère correspondant au coordonnées du coup joué
	 */
	@Override
	public String nvCoup() {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		//sc.close();
		return s;
	}

	/**
	 * Permet de connaître le nom du joueur
	 * @return le nom du joueur humain
	 */
	@Override
	public String getNom() {
		return nom;
	}


}
