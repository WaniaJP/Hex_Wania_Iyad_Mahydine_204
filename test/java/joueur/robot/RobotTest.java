package test.java.joueur.robot;

import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import main.java.convertisseur.ConvertisseurIntToLetter;
import main.java.hex.IRobot;
import main.java.joueur.robot.Robot;

class RobotTest {

	@Test
	public void testRobot() {
	
		IRobot r = new Robot(0, 4, new ConvertisseurIntToLetter());
		assertEquals("Robot", r.getNom());
	}
	
	@Test
	public void testgetMin() {
		IRobot r = new Robot(0, 4, new ConvertisseurIntToLetter());
		assertEquals(0, r.getMin());
		
		assertFalse(1 == r.getMin());
		
		assertTrue(r.getMin() >= 0);
		
		assertTrue(r.getMin() < r.getMax());
	}

	public void testgetMax() {
		IRobot r = new Robot(0, 4, new ConvertisseurIntToLetter());
		assertEquals(4, r.getMax());
		
		assertFalse(5 == r.getMax());
		
		assertTrue(r.getMax() <= 10);
		
		assertTrue(r.getMax() > 0);
		
		assertTrue(r.getMin() < r.getMax());
	}
	
	
	@Test
	public void testnvCoup() {
		IRobot r = new Robot(0, 4, new ConvertisseurIntToLetter());
		String coup = r.nvCoup();
		
	    Pattern p = Pattern.compile("([A-D])[1-4]", Pattern.CASE_INSENSITIVE);
	    Matcher matcher = p.matcher(coup);
	    boolean matchFound = matcher.find();
	    
	    assertTrue(matchFound);
	    
		IRobot r1 = new Robot(0, 26, new ConvertisseurIntToLetter());
		String coup1 = r1.nvCoup();
		
	    Pattern p1 = Pattern.compile("([A-Z])[1-26]", Pattern.CASE_INSENSITIVE);
	    Matcher matcher1 = p1.matcher(coup1);
	    boolean matchFound1 = matcher1.find();
	    
	    assertTrue(matchFound1);
	}
}