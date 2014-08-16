package unitTests;

import static org.junit.Assert.*;

import java.awt.Point;

import model.actors.PC;

import org.junit.Before;
import org.junit.Test;

public class PCTest {

	PC test;
	
	public void testClass(){
		try{
			setUp();
		}catch(Exception e){
			System.out.println("PC-class could not be instantiated");
		}
		
		testIncScore();
		
		testCooldown();
	}
	
	@Before
	public void setUp() throws Exception {
		test = new PC(new Point(0,0));
	}

	@Test
	public void testIncScore() {
		
		final int INCAMOUNT = 1;
		int prevScore = test.getScore();
		int newScore;
		
		test.incScore(INCAMOUNT);
		
		newScore = test.getScore();
		
		assertEquals("PC.incScore failed!\n Expected result: " + (prevScore + INCAMOUNT) + 
				"\n Actual result: " + newScore,prevScore + INCAMOUNT*test.getCombo(), newScore);
	}
	
	@Test
	public void testCooldown(){
		
		assertFalse("onCooldown should be false, returned true", test.onCooldown());
		
		test.startCooldown();
		assertTrue("PC.startCooldown failed!\n Expected result: onCooldown returns true\n Actual result: onCooldown returns false", test.onCooldown());
		
		test.resetCooldown();
		assertFalse("PC.resetCooldown failed!\n Expected result: onCooldown returns false\n Actual result: onCooldown returns true", test.onCooldown());
	}

}
