package test.java.plateau;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.plateau.UtilitaireBordure;

class UtilitaireBordureTest {

	@Test
	void testPremiereBordure_1_1() {
		assertTrue(UtilitaireBordure.premiereBordure(1, 2, 1, 1));
		assertTrue(UtilitaireBordure.premiereBordure(2, 2, 1, 1));
	}
	
	@Test
	void testSecondeBordure_1_1() {
		assertTrue(UtilitaireBordure.secondeBordure(1, 2, 1, 1, 1,1));
		assertTrue(UtilitaireBordure.secondeBordure(2, 2, 1, 1, 1,1));
	}
	
	@Test
	void testPremiereBordure_3_1() {
		assertTrue(UtilitaireBordure.premiereBordure(1, 2, 1, 1));
		assertFalse(UtilitaireBordure.premiereBordure(1, 2, 3, 1));
		assertFalse(UtilitaireBordure.premiereBordure(1, 2, 2, 1));
		
		assertTrue(UtilitaireBordure.premiereBordure(2, 2, 1, 1));
		assertTrue(UtilitaireBordure.premiereBordure(2, 2, 3, 1));
		assertTrue(UtilitaireBordure.premiereBordure(2, 2, 2, 1));
	}
	
	@Test
	void testSecondeBordure_3_1() {
		assertFalse(UtilitaireBordure.secondeBordure(1, 2, 1, 1, 3, 1));
		assertTrue(UtilitaireBordure.secondeBordure(1, 2, 3, 1, 3, 1));
		assertFalse(UtilitaireBordure.secondeBordure(1, 2, 2, 1, 3, 1));
		
		assertTrue(UtilitaireBordure.secondeBordure(2, 2, 1, 1, 3, 1));
		assertTrue(UtilitaireBordure.secondeBordure(2, 2, 3, 1, 3, 1));
		assertTrue(UtilitaireBordure.secondeBordure(2, 2, 2, 1, 3, 1));
	}
	
	
	
	@Test
	void testBordure_LigneInvalide() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			UtilitaireBordure.premiereBordure(1, 2, 0, 1);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			UtilitaireBordure.secondeBordure(1, 2, 0, 1, 26, 26);
		});
		
		assertTrue(UtilitaireBordure.premiereBordure(2, 2, 0, 1));
		assertFalse(UtilitaireBordure.secondeBordure(2, 2, 0, 1,26,26));
	}
	
	@Test
	void testBordure_ColonneInvalide() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			UtilitaireBordure.premiereBordure(2, 2, 1, 0);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			UtilitaireBordure.secondeBordure(2, 2, 1, 0, 26, 26);
		});
		
		assertTrue(UtilitaireBordure.premiereBordure(1, 2, 1, 0));
		assertFalse(UtilitaireBordure.secondeBordure(1, 2, 1, 0,26,26));
	}
	
	
	

	@Test
	void testSecondeBordure_0() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			UtilitaireBordure.secondeBordure(1, 2, 1, 1, 0, 0);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			UtilitaireBordure.secondeBordure(2, 2, 1, 1, 0, 0);
		});
	}
	
	@Test
	void testBordure_numeroJoueur() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			UtilitaireBordure.premiereBordure(0, 2, 1, 1);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			UtilitaireBordure.premiereBordure(3, 2, 1, 1);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			UtilitaireBordure.secondeBordure(0, 2, 1, 1, 5, 1);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			UtilitaireBordure.secondeBordure(3, 2, 1, 1, 5, 1);
		});
	}
	
	@Test
	void testBordure_NBJoueur() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			UtilitaireBordure.premiereBordure(1, 0, 1, 1);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			UtilitaireBordure.premiereBordure(2, 0, 1, 1);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			UtilitaireBordure.secondeBordure(1, 0, 1, 1, 5, 1);
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			UtilitaireBordure.secondeBordure(2, 0, 1, 1, 5, 1);
		});
	}

}
