package main.java.plateau;

import java.util.Arrays;
import java.util.LinkedList;

import main.java.hex.IPlateau;

/**
 * Crée un Plateau du jeu de Hex
 * @author Wania
 * @author Iyad
 * @author Mikal Ziane
 * @author Mahydine
 * @version 2.0
 * @since 1.0
 *
 */
public class Plateau implements IPlateau {
	private final static int TAILLE_MAX = 26;
	private final static int NB_JOUEURS = 2;
	private final static int PREMIERE_COLONNE = 'A';
	private final static int PREMIERE_LIGNE = 1;
	private final static int PREMIERE_LIGNE_ASCII = '1';

	// le premier joueur relie la premiere et la derniere ligne
	// le second joueur relie la premiere et la derniere colonne

	//format [ligne][colonne]
	private Pion[][] t;
	private int joueur = 0; // prochain à jouer
	private boolean estGagnee = false;


	/**
	 * Crée un tableau de tableau, chacun de taille "taille"
	 * @param taille - la taille du futur plateau
	 * @throws IllegalArgumentException - La taille est inférieur ou égal à 0 ou bien supérieur à 26 (nombre de lettre dans l'alphabet)
	 */
	public Plateau(int taille) throws IllegalArgumentException {
		if(taille > TAILLE_MAX)
			throw new IllegalArgumentException("Taille supérieur à " + TAILLE_MAX);

		if( taille <= 0)
			throw new IllegalArgumentException("Taille inférieur ou égale à 0");

		t = new Pion [taille][taille];

		for (int lig = 0; lig < taille(); ++lig)
			for (int col = 0; col < taille(); ++col)
				t[lig][col] = Pion.Vide;
	}

	/**
	 * Crée un tableau de tableau, chacun de taille "taille" qui sera composé des valeurs de pos
	 * @param taille - correspondant à pos
	 * @param pos - chaîne de caractère représentant un plateau
	 * @throws IllegalArgumentException -  La taille est inférieur ou égal à 0, si elle est supérieur à 26 (nombre de lettre dans l'alphabet) ou
	 * 										enfin si la taille des tableau est incohérente avec la taille de pos
	 */
	public Plateau(int taille, String pos) throws IllegalArgumentException {
		if(taille > TAILLE_MAX)
			throw new IllegalArgumentException("Taille supérieur à " + TAILLE_MAX);

		if( taille <= 0)
			throw new IllegalArgumentException("Taille inférieur ou égale à 0");

		String[] lignes = decouper(pos);

		if(lignes.length != taille) {
			throw new IllegalArgumentException("Plateau non valide");
		}

		t = new Pion [taille][taille];


		for (int lig = 0; lig < taille(); ++lig)
			for (int col = 0; col < taille(); ++col)
				t[lig][col] = 
				Pion.get(lignes[lig].charAt(col));

		if (getNb(Pion.Croix) != getNb(Pion.Rond) &&
				getNb(Pion.Croix) != (getNb(Pion.Rond)+1) &&
				getNb(Pion.Croix) != (getNb(Pion.Rond)-1))
			throw new IllegalArgumentException(
					"position non valide");
	}

	private void suivant() {
		if(!estGagnee)
			joueur = (joueur +1) % NB_JOUEURS;
	}


	/**
	 * Permet de connaître le numéro du joueur gagnant.
	 * Commence à 0.
	 * @return le numéro du joueur gagnant
	 * @throws IllegalStateException - Aucun joueur n'a gagné la partie
	 */
	@Override
	public int getGagnant() throws IllegalStateException {
		if(estGagnee)
			return joueur;
		throw new IllegalStateException("Pas de joueur gagnant");
	}

	/**
	 * Permet de savoir si la partie est gagné par l'un des joueurs.
	 * @return l'état de la partie (vrai si gagné et faux sinon)
	 */
	@Override
	public boolean estPartieGagnee() {
		return estGagnee;
	}

	/**
	 * Vérifie si la partie est gagnée en vérifiant tout les pions d'un joueur en partant de sa bodure
	 * @param lig
	 * @param col
	 * @throws IndexOutOfBoundsException - La valeur de la ligne et de la colonne est plus grande ou plus petite que la taille du plateau
	 */
	public void setPartieGagnee(int lig, int col) throws IndexOutOfBoundsException {
		if(!estValide(lig))
			throw new IndexOutOfBoundsException("Ligne en dehors du plateau");
		if(!estValide(col))
			throw new IndexOutOfBoundsException("Colonne en dehors du plateau");

		LinkedList<Integer> pile = new LinkedList<>();
		pile.push(lig);
		pile.push(col);

		boolean[][] visitees = new boolean [taille()][taille()];
		for(boolean[] b : visitees)
			Arrays.fill(b, false);

		int ligCourant, colCourant;

		boolean bordure1 = false, bordure2 = false;
		while(!pile.isEmpty()) {
			colCourant = pile.pop(); 
			ligCourant = pile.pop();

			visitees[ligCourant][colCourant] = true;

			//On vérifie si la case est sur une bordure 
			/**** Il faut trouver une solution pour vérifier les bordures correctement selon le joueur ****/
			if(!bordure1)
				bordure1 = UtilitaireBordure.premiereBordure(joueur+1, NB_JOUEURS, ligCourant+1, colCourant+1);
			if(!bordure2)
				bordure2 = UtilitaireBordure.secondeBordure(joueur+1, NB_JOUEURS, ligCourant+1, colCourant+1, taille(), taille());

			//Si on a une eu une case sur chaque bordure --> donc relié les bordures
			if(bordure1 && bordure2) {
				estGagnee = true;
			}

			//On empile les autres cases
			pile = pushVoisins(pile, ligCourant,colCourant, Pion.values()[joueur], visitees);

		}
	}

	private LinkedList<Integer> pushVoisins(LinkedList<Integer> pile, int lig, int col, Pion p, boolean[][] v) {
		int[] voisins = getVoisins(lig,col);
		for(int i = 0; i < (voisins.length); i+=2) {
			if(t[voisins[i]][voisins[i+1]] == p && !v[voisins[i]][voisins[i+1]]) {
				pile.push(voisins[i]); //On empile la ligne
				pile.push(voisins[i+1]); // On empile la colonne
			}
		}
		return pile;
	}

	private int[] getVoisins(int lig, int col) {

		/********* CAS Spécial : Les Coins ***********/

		//// 2 Voisins

		// Coin : haut-gauche [0][0] --> premiere case
		if(lig == 0 && col == 0) {
			/*  -> [lig][col+1]
			 *  -> [lig+1][col+1] */
			int[] voisins = {lig,col+1,lig+1,col};

			return voisins;
		}

		// Coin : bas-droite [taille-1][taille-1] --> derniere case
		if(lig == (taille()-1) && col== (taille()-1)) {
			/*  -> [lig][col-1]
			 *  -> [lig-1][col-1] */
			int[] voisins = {lig, col-1, lig-1, col};

			return voisins;
		}

		//// 3 Voisins

		// Coin : haut-droite [0][taille-1] --> derniere case de la premiere ligne
		if(lig == 0 && col == (taille()-1)) {
			/*  -> [lig][col-1]
			 *  -> [lig+1][col-1]
			 *  -> [lig+1][col] */
			int[] voisins = {lig, col-1, lig+1, col-1, lig+1, col};

			return voisins;
		}

		// Coin : bas-gauche [taille-1][0] --> premier case de la derniere ligne
		if(lig == (taille()-1) && col == 0) {
			/* droite -> [lig][col+1]
			 * haut-gauche -> [lig-1][col]
			 * haut-droite -> [lig-1][col+1] */
			int[] voisins = {lig, col+1, lig-1, col, lig-1, col+1};

			return voisins;
		}

		/********* CAS Spécial : Les Bordures ***********/

		////4 voisins

		// Bordure : gauche [x][0] --> bordure à gauche
		if(col == 0) {
			/* haut-gauche -> [lig-1][col]
			 * haut-droite -> [lig-1][col+1]
			 * droite -> [lig][col+1]
			 * bas-droite -> [lig+1][col+1] */
			int[] voisins = {lig-1,col,lig-1,col+1,lig,col+1,lig+1,col};

			return voisins;
		}

		// Bordure : droite [x][taille-1] --> bordure à droite
		if(col == (taille()-1)) {
			/* haut-gauche -> [lig-1][col-1]
			 * gauche -> [lig][col-1]
			 * bas-gauche -> [lig+1][col-1]
			 * bas-droite -> [lig+1][col] */
			int[] voisins = {lig-1,col,lig,col-1,lig+1,col-1,lig+1,col};

			return voisins;
		}

		// Bordure : haut [0][x] --> bordure du haut
		if(lig == 0) {
			/* gauche -> [lig][col-1]
			 * bas-gauche -> [lig+1][col-1]
			 * bas-droite -> [lig+1][col+1]
			 * droite -> [lig][col+1] */
			int[] voisins = {lig,col-1,lig+1,col-1,lig+1,col,lig,col+1};

			return voisins;
		}

		// Bordure : bas [taille-1][x] --> bordure du bas
		if(lig == (taille()-1)) {
			/* gauche -> [lig][col-1]
			 * haut-gauche -> [lig-1][col-1]
			 * haut-droite -> [lig-1][col+1]
			 * droite -> [lig][col+1] */
			int[] voisins = {lig,col-1,lig-1,col,lig-1,col+1,lig,col+1};

			return voisins;
		}

		/********* CAS DE BASE ***********/

		////6 voisins
		//Cas de base --> case non-coin et non-bordure

		/* haut-gauche -> [lig-1][col-1]
		 * haut-droit -> [lig-1][col+1]
		 * droit -> [lig][col+1]
		 * bas-droit -> [lig+1][col+1]
		 * bas-gauche -> [lig+1][col-1]
		 * gauche -> [lig][col-1]
		 */
		int[] voisins = {lig, col-1, lig+1, col-1, lig+1, col, lig, col+1, lig-1, col, lig-1, col+1};

		return voisins;
	}


	/**
	 * Fait jouer un pion au joueur actuelle sur la case coord
	 * @param coord
	 * @throws IndexOutOfBoundsException -- La coordonnée est en dehors du plateau
	 * @throws IllegalArgumentException -- La case coord contient déjà un Pion
	 */
	public void jouer(String coord) throws IndexOutOfBoundsException, IllegalArgumentException {
		if(!estValide(coord))
			throw new IndexOutOfBoundsException("Coordonnée en dehors du plateau");


		if(getCase(coord) != Pion.Vide)
			throw new IllegalArgumentException("Case déjà utilisée");


		Pion pion = Pion.values()[joueur];
		int col = getColonne (coord);
		int lig = getLigne(coord);
		t[lig][col] = pion;
		setPartieGagnee(lig, col);
		suivant();
	}

	/**
	 * Cette fonction vérifie si la chaine pos est égal à la taille "taille" du plateau
	 * @param pos
	 * @return la taille de la chaine pos
	 */
	public static int getTaille(String pos) {
		int taille = (int) Math.sqrt(pos.length());
		assert taille * taille == pos.length();
		return taille;
	}

	/**
	 * Cette fonction renvoie vrai si la coordonnée est valide, si elle est cohérente par rapport au plateau et faux sinon
	 * @param coord
	 * @return vrai si la coordonnée est valide, si elle est cohérente par rapport au plateau et faux sinon
	 */
	public boolean estValide(String coord) {
		if ( coord.length() != 3 && coord.length() != 2)
			return false;
		int col = getColonne (coord);
		int lig = getLigne(coord);
		if (col <0 || col >= taille())
			return false;
		if (lig <0 || lig >= taille())
			return false;
		return true;
	}

	/**
	 * Cette fonction renvoie vrai si x est une valeur valide et existante du tableau et faux sinon
	 * @param x
	 * @return vrai si x est une valeur valide et existante du tableau et faux sinon
	 */
	public boolean estValide(int x) {
		if (x <0 || x >= taille())
			return false;
		return true;
	}

	/** 
	 * Cette fonction renvoie vrai si lig et col sont des valeur valide du plateau et faux sinon
	 * @param lig
	 * @param col
	 * @return vrai si lig et col sont des valeur valide du plateau et faux sinon
	 */
	public boolean estValide(int lig, int col) {
		if (col <0 || col >= taille())
			return false;
		if (lig <0 || lig >= taille())
			return false;
		return true;
	}

	/**
	 * Cette fonction renvoie le Pion se situant sur la case (lig, col) du plateau
	 * @param coord
	 * @return le Pion se situant sur la case (lig, col) du plateau
	 * @throws IndexOutOfBoundsException - Les coordonnées sont en dehors du plateau
	 */
	public Pion getCase(String coord) throws IndexOutOfBoundsException {
		if(!estValide(coord))
			throw new IndexOutOfBoundsException("Coordonnée en dehors du plateau");

		int col = getColonne (coord);
		int lig = getLigne(coord);
		return t[lig][col];
	}

	private int getColonne(String coord) {
		return coord.charAt(0) - PREMIERE_COLONNE; // ex 'B' -'A' == 1
	}

	private int getLigne(String coord) {
		if(coord.length() > 2)
			return Integer.parseInt(coord.substring(1, 3)) - PREMIERE_LIGNE;
		else
			return coord.charAt(1) - PREMIERE_LIGNE_ASCII; // ex '2' - '1' == 1
	}


	/**
	 * Permet de connaître le nombre de Pion sur le plateau, pour un pion donné.
	 * @param pion - pion dont on souhaite connaître la quantité présente sur le plateau
	 * @return le nombre de Pion identiques à pion sur le plateau
	 */
	public int getNb(Pion pion) {
		int nb = 0;
		for (Pion [] ligne : t)
			for (Pion p : ligne)
				if (p == pion)
					++nb;
		return nb;
	}

	/**
	 * Permet de connaître la taille du plateau
	 * @return la taille du plateau
	 */
	public int taille() {
		return t.length;
	}

	private String espaces(int n) {
		String s = "";
		if(n >= 9) {
			n = n - 1;
		}
		for(int i = 0; i < n; ++i)
			s+= " ";
		return s;
	}

	/**
	 * Construit une chaine de caractère representant l'état actuelle du plateau
	 * @return la chaine de caractère représentant l'état actuelle du plateau
	 */
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < taille(); ++i)
			s+=" "+(char)( 'A' + i);
		s+='\n';
		for (int lig = 0; lig < taille(); ++lig) {
			s+= lig+1 + espaces (lig);
			for (int col = 0; col < taille(); ++col)
				s+= " "+ t[lig][col];
			s+='\n';
		}
		return s;
	}

	/**
	 * Découpe une chaine de caractères en plusieurs morceau de longueur "taille" en un tableau de taille "taille"
	 * @param pos - la chaîne de caractère à découper 
	 * @return un tableau contenant à chaque indices une valeur représentant une ligne du plateau
	 */
	public static String[] decouper(String pos) {
		int taille = getTaille(pos);
		String[] lignes = new String[taille];
		for (int i = 0; i <taille; ++i)
			lignes[i] = pos.substring(i*taille,
					(i+1)*taille);
		return lignes;
	}

	/**
	 * Permet de connaître le numéro du joueur actuel.
	 * @return le numéro du joueur actuelle
	 */
	@Override
	public int getJoueur() {
		return joueur;
	}

}
