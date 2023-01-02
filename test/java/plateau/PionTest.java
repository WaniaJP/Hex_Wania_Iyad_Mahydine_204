package test.java.plateau;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.plateau.Pion;

class PionTest {

	@Test
	void test() {
		assertEquals("X",Pion.Croix.toString());
		assertEquals("O",Pion.Rond.toString());
		assertEquals(".",Pion.Vide.toString());
		
		assertTrue(Pion.Croix == Pion.get('X'));
		assertTrue(Pion.Rond == Pion.get('O'));
		assertTrue(Pion.Vide == Pion.get('.'));
		
		assertThrows(IllegalArgumentException.class,
				() -> {
					Pion.get('*');
				}
				);
	}

}
