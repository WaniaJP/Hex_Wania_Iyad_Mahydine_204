package test.java.plateau;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.plateau.Pion;
import main.java.plateau.Plateau;


class PlateauTest {
	
	@SuppressWarnings("unused")
	private String display0_2_rep = 
			" A B\n" + 
			"1 . .\n" + 
			"2  . .\n";
	
	private String pos0_2 = "....";
	
	private String display0_4_rep = 
					" A B C D\n" + 
					"1 . . . .\n" + 
					"2  . . . .\n" + 
					"3   . . . .\n" + 
					"4    . . . .\n";
	
	private String pos1 = ".X..XOXXOO.OX..."; 
	private String[] lignes1_rep = {
			".X..", 
			"XOXX",
			"OO.O",
			"X..."
	};
	private String display1_rep = 
			" A B C D\n" + 
			"1 . X . .\n" + 
			"2  X O X X\n" + 
			"3   O O . O\n" + 
			"4    X . . .\n";
	
	private String pos2 = 
			  "X..."
			+ "X..O"
			+ "X..O"
			+ "X..O";
	
	private String[] lignes2_rep = {
			"X...", 
			"X..O",
			"X..O",
			"X..O"
	};
	
	private String display2_rep = 
			" A B C D\n" + 
			"1 X . . .\n" + 
			"2  X . . O\n" + 
			"3   X . . O\n" + 
			"4    X . . O\n";
	
	
	private String pos4 = "XXXX....OOOOX.O.";
	private String pos9 = "XXXX..............OOOO.....X.O...................................................";
	private String pos12 = "................................................................................................................................................";
	private String pos27 = ".................................................................................................................................................................." +
			"............................................................................................................................................................................................." +
			"........................................................................................................................................................................................................................" +
			"..................................................................................................................................................................";
	
	
	private String display4_rep = 
			" A B C D\n" + 
			"1 X X X X\n" + 
			"2  . . . .\n" + 
			"3   O O O O\n" + 
			"4    X . O .\n";
	
	private String display9_rep = 
			" A B C D E F G H I\n" + 
			"1 X X X X . . . . .\n" + 
			"2  . . . . . . . . .\n" + 
			"3   O O O O . . . . .\n" + 
			"4    X . O . . . . . .\n" +
			"5     . . . . . . . . .\n" + 
			"6      . . . . . . . . .\n" + 
			"7       . . . . . . . . .\n" + 
			"8        . . . . . . . . .\n" + 
			"9         . . . . . . . . .\n";
	
	private String display12_rep = 
			" A B C D E F G H I J K L\n" + 
			"1 . . . . . . . . . . . .\n" + 
			"2  . . . . . . . . . . . .\n" + 
			"3   . . . . . . . . . . . .\n" + 
			"4    . . . . . . . . . . . .\n" +
			"5     . . . . . . . . . . . .\n" + 
			"6      . . . . . . . . . . . .\n" + 
			"7       . . . . . . . . . . . .\n" + 
			"8        . . . . . . . . . . . .\n" + 
			"9         . . . . . . . . . . . .\n" + 
			"10         . . . . . . . . . . . .\n" + 
			"11          . . . . . . . . . . . .\n" + 
			"12           . . . . . . . . . . . .\n";
	
	
	
	
	@Test
	public void testGagner() {
		testerPosition(pos2, lignes2_rep, display2_rep);
		Plateau p = new Plateau(4, pos2);
		//System.out.print(p);
		try {
			p.setPartieGagnee(3,0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(p.estPartieGagnee());
	}
	
	@Test
	public void testerPositions() {
		testerPosition(pos1, lignes1_rep, display1_rep);
	}
	
	private void testerPosition(String pos, String[] lignes_rep, String display_rep) {
		String[] lignes;
		lignes  = Plateau.decouper(pos);
		int taille = Plateau.getTaille(pos);
		
		for (int i = 0; i< taille; ++i)
			assertEquals(lignes_rep[i], lignes[i]);
		
		Plateau p = new Plateau(taille, pos);
		assertEquals(display_rep, p.toString());
	}
	
	
	@Test
	public void testsetPartieGagnee() {
		
		Plateau p = new Plateau(4, pos2);
		
		assertThrows(IndexOutOfBoundsException.class, () -> {
			p.setPartieGagnee(4, 4);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			p.setPartieGagnee(5, 4);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			p.setPartieGagnee(4, 5);
		});		
		
		assertThrows(IndexOutOfBoundsException.class, () -> {
			p.setPartieGagnee(-1, 5);
		});	
		
		assertThrows(IndexOutOfBoundsException.class, () -> {
			p.setPartieGagnee(4, -1);
		});	
	}
	
	
	@Test
	public void testJouer() {
		
		Plateau p = new Plateau(4, pos4);
		//System.out.println(p);
		assertThrows(IllegalArgumentException.class, () -> {
			p.jouer("B1");
		});
		
		
		assertThrows(IllegalArgumentException.class, () -> {
			p.jouer("C3");
		});
		
		assertThrows(IndexOutOfBoundsException.class, () -> {
			p.jouer("E3");
		});
		
		assertThrows(IndexOutOfBoundsException.class, () -> {
			p.jouer("B8");
		});
		
		try {
			p.jouer("B2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals(
				" A B C D\n" + 
				"1 X X X X\n" + 
				"2  . X . .\n" + 
				"3   O O O O\n" + 
				"4    X . O .\n", p.toString());
		
		assertThrows(IllegalArgumentException.class, () -> {
			p.jouer("B2");
		});
	}
	
	@Test
	public void testestValide() {
		
		Plateau p = new Plateau(4, pos4);
		
		assertFalse(p.estValide("BB"));
		assertFalse(p.estValide("B"));
		assertFalse(p.estValide(8));
		assertFalse(p.estValide(8, 8));
		
		assertFalse(p.estValide("B6"));
		assertFalse(p.estValide("B"));
		assertFalse(p.estValide("6"));
		assertFalse(p.estValide(1, 5));
		
		assertFalse(p.estValide("F2"));
		assertFalse(p.estValide("F"));
		assertFalse(p.estValide("2"));
		assertFalse(p.estValide(5, 1));

		assertFalse(p.estValide("F8"));
		assertFalse(p.estValide("F"));
		assertFalse(p.estValide("8"));
		assertFalse(p.estValide(5, 7));
		
		assertFalse(p.estValide(-1, 1));
		
		
		assertTrue(p.estValide("B2"));
		assertTrue(p.estValide(1, 1));
		assertTrue(p.estValide(1));
		
		assertTrue(p.estValide("D1"));
		assertTrue(p.estValide(0, 3));
		assertTrue(p.estValide(0));
		assertTrue(p.estValide(3));
		
	}
	
	
	@Test
	public void testgetCase() {
		
		Plateau p = new Plateau(4, pos4);
		try {
			assertEquals(Pion.Croix, p.getCase("A1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals(Pion.Vide, p.getCase("B2"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			assertEquals(Pion.Rond, p.getCase("C3"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testgetGagnant() {
		
		Plateau p = new Plateau(2, pos0_2);
		//System.out.println(p);
		
		try {
			p.jouer("A1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			p.jouer("B2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			p.jouer("A2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			assertTrue(p.getGagnant() == 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testgetTaille() {
		
		Plateau p = new Plateau(4, pos4);
		assertEquals(4, Plateau.getTaille(pos4));
		assertFalse(5 == Plateau.getTaille(pos4));
		assertFalse(Plateau.getTaille(pos4) < 0);	
		
	}
	
	@Test
	public void testtaille() {
		
		Plateau p = new Plateau(4, pos4);
		assertEquals(4, p.taille());
		assertFalse(5 == p.taille());	
		assertFalse(p.taille() <= 0);	
	}
	
	@Test
	public void testgetNB() {
		
		Plateau p = new Plateau(4, pos4);
		assertEquals(10, p.getNb(Pion.Croix) + p.getNb(Pion.Rond));
		assertEquals(5, p.getNb(Pion.Croix));
		assertEquals(5, p.getNb(Pion.Rond));
		assertEquals(6, p.getNb(Pion.Vide));
		
		Plateau p1 = new Plateau(5);
		assertEquals(0, p1.getNb(Pion.Croix));
		assertEquals(0, p1.getNb(Pion.Rond));
		assertEquals(25, p1.getNb(Pion.Vide));
	}
	
	@Test
	public void testPlateau1() {
		
		Plateau p = new Plateau(4);
		assertNotNull(p);
		assertEquals(4, p.taille());
		assertEquals(display0_4_rep, p.toString());
		
		Plateau p1 = new Plateau(12);
		assertNotNull(p1);
		assertEquals(12, p1.taille());		
		assertEquals(display12_rep, p1.toString());
		
	}
	
	@Test
	public void testPlateau2() {
		
		Plateau p = new Plateau(4, pos4);
		assertNotNull(p);
		assertEquals(4, p.taille());
		assertEquals(display4_rep, p.toString());
		
		Plateau p1 = new Plateau(9, pos9);
		assertNotNull(p1);
		assertEquals(9, p1.taille());
		assertEquals(display9_rep, p1.toString());
		
		Plateau p2 = new Plateau(12, pos12);
		assertNotNull(p2);
		assertEquals(12, p2.taille());
		assertEquals(display12_rep, p2.toString());
		
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testPlateauError() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			Plateau p = new Plateau(27);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			Plateau p = new Plateau(0);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			Plateau p = new Plateau(-2);
		});
		
		
		
		
		assertThrows(IllegalArgumentException.class, () -> {
			Plateau p = new Plateau(5, pos4);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			Plateau p = new Plateau(27, pos27);
		});
				
		assertThrows(IllegalArgumentException.class, () -> {
			Plateau p = new Plateau(-1, pos4);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			Plateau p = new Plateau(0, "");
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			Plateau p = new Plateau(4, "");
		});
				
	}
	
	
	@Test
	public void testgetJoueur() {
		
		Plateau p = new Plateau(4);
		
		assertTrue(p.getJoueur() == 0);
		
		//On vérifie que le joueur ne change pas s'il y a une erreur
		try {
			p.jouer("A1");
		} catch (Exception e) {
		}
		
		assertTrue(p.getJoueur() == 1);
		
		try {
			p.jouer("A1");
		} catch (Exception e) {
		}
		
		assertTrue(p.getJoueur() == 1);
		
		//////
		
		try {
			p.jouer("D4");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(p.getJoueur() == 0);
	}
}
