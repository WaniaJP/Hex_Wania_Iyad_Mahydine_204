package main.java.ihm;

import java.util.Scanner;

import main.java.hex.FabriqueJoueur;
import main.java.hex.FabriquePlateau;
import main.java.hex.IJoueur;
import main.java.hex.IPlateau;

public class Hex {
	private static final int NB_JOUEURS = 2;

	public static String lancement() {
		System.out.println("Bienvenue dans le jeu de HEX");
		System.out.println(
				"Choisissez entre les différentes options :\r\n"
						+ "1 -> Lancer une nouvelle partie\r\n"
						+ "2 -> Les règles du jeu\r\n"
						//+ "2 -> Le tutoriel"
						+ "3 -> Quitter\r\n");
		Scanner sc = new Scanner(System.in);
		String s = "";

		do {
			System.out.print(choix("[1-3]") +"\n -> ");
			if(sc.hasNext())
				s = sc.nextLine();
		}while(!s.matches("[1-3]"));

		System.out.print("\n");
		return s;
	}

	public static String choix(String pattern){
		return "Faites votre choix "+pattern;
	}

	public static String regle() {
		System.out.println("Hex est un jeu à 2 joueurs dont les parties se déroulent sur un terrain dont les cases sont hexagonales.\r\n"
				+ "Chaque joueur joue à tour de rôle. Le terrain est initialement vide. A son tour de jeu un joueur place un des\r\n"
				+ "ses pions sur une des cases encore libres dans le but de relier les deux bords du terrains qui lui font face: les\r\n"
				+ "bords SUD et NORD alors que sont adversaire essaye de relier les bords EST et OUEST. Le premier joueur\r\n"
				+ "a relier les deux bords qui lui font face par une ligne continue de ses propres pions a gagne la partie.\r\n");

		System.out.println(
				"Choisissez entre les différentes options :\r\n"
				//+ "1 -> Suivre le tutoriel\r\n"
				+ "1 -> Lancer une nouvelle partie\r\n"
				+ "2 -> Quitter\r\n");

		Scanner sc = new Scanner(System.in);
		String s = "";

		do {
			System.out.print(choix("[1-2]") +"\n -> ");
			s = sc.nextLine();
		}while(!s.matches("[1-2]"));


		return s;
	}

	public static IJoueur getJoueurs(int max) {
		Scanner sc = new Scanner(System.in);
		String s = "";
		s = sc.nextLine();
		return FabriqueJoueur.creerJoueur(s, 0, max); 
	}

	public static IJoueur[] creerJoueurs(int max) {
		System.out.println("Entrez le nom des joueurs, entrez seulement un 'R' si le joueur est un ordinateur :");
		IJoueur[] j = new IJoueur[NB_JOUEURS];
		for(int i = 0; i < NB_JOUEURS; i++) {
			System.out.print("Joueur "+(i+1)+" ->");
			j[i] = getJoueurs(max);
			System.out.println("");
		}
		return j;
	}

	public static IPlateau initialisationPartie() {
		System.out.println("Définissez la taille du plateau :\n (exemple: 4 donne une grille 4*4 donc de 16 cases).\n");
		Scanner sc = new Scanner(System.in);
		String s = "";

		do {
			System.out.print(choix("[1-26]") +"\n -> ");
			s = sc.nextLine();
		}while(!s.matches("\\b([1-9]|[12][0-6])\\b"));

		IPlateau p = FabriquePlateau.creerPlateau(Integer.parseInt(s));
		System.out.print("\n");
		return p;
	}

	public static void quitter() {
		System.out.println("J'espère que vous avez passé un bon moment sur HEX !");
		System.exit(0);
	}

	public static void main(String[] args) {
		String code = "";
		boolean plateauCreer = false;
		IPlateau p = null;
		code = lancement();
		while(!plateauCreer) {
			if(code.equals("1")) {
				p = initialisationPartie();
				plateauCreer = true;
				break;
			}
			if(code.equals("2")) {
				code = regle();
				break;
			}
			if(code.equals("3")) {
				quitter();
				break;
			}
		}

		if(!plateauCreer) {
			while(!plateauCreer) {
				if(code.equals("1")) {
					p = initialisationPartie();
					plateauCreer = true;
					break;
				}
				if(code.equals("2")) {
					quitter();
					break;
				}
			}
		}

		IJoueur[] joueurs = creerJoueurs(p.taille());
		System.out.println("Lancement de votre partie amusez-vous bien !");
		String coord = "";
		do {
			System.out.println(p.toString());
			System.out.println("Au tour du joueur " + joueurs[p.getJoueur()].getNom() + " de jouer.");

			do {
				System.out.println(joueurs[p.getJoueur()].getNom() + " veuillez entrer des coordonnées.");
				System.out.print(joueurs[p.getJoueur()].getNom() + " -> ");
				coord = joueurs[p.getJoueur()].nvCoup();
				try {
					p.jouer(coord);
				} catch (Exception e) {
					System.out.println("Votre coup est invalide.");
				}
			}while(!p.estValide(coord));

		} while(!p.estPartieGagnee());
		
		System.out.println(p.toString());
		int gagnant = 0;
		try {
			gagnant = p.getGagnant();
		} catch (Exception e) {
			System.out.println("Aucun gagnant.");
		}

		System.out.println(joueurs[gagnant].getNom() + " vous avez gagné !");
		System.out.println("Fin de partie");
		quitter();
	}

}
