package test.java.joueur.humain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.hex.IJoueur;
import main.java.joueur.humain.Humain;


class HumainTest {
	
	private String nom1 = "John";
	private String nom2 = "Lucie";

	@Test
	public void testHumaingetNom() {
		IJoueur h = new Humain(nom1);
		assertEquals("John", h.getNom());
		assertFalse("John " == h.getNom());
		assertFalse(" John" == h.getNom());
		assertFalse(" John " == h.getNom());
	}
	
	@Test
	public void testnvCoup() {
		IJoueur h = new Humain(nom2);
		
		System.out.println("Ecrire B2");
		assertEquals("B2", h.nvCoup());
	}
	
}