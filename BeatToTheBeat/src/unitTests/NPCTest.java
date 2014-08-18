package unitTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Point;

import model.actors.NPC;

import org.junit.Before;
import org.junit.Test;

public class NPCTest {
	
	NPC test;
	
	@Test
	public void testClass(){
		try{
			setUp();
		}catch(Exception e){
			System.out.println("PC-class could not be instantiated");
		}

	}
	
	public void setUp() throws Exception {
		test = new NPC(new Point(0,0));
	}
	
	public void testIsDead() {
		assertFalse("isDead should be false, returned true", test.isDead());
		
		test.setHealth(0);
		assertTrue("Actor.isDead failed!\n Expected result: isDead returns true\n Actual result: isDead returns false", test.isDead());
		
		test.setHealth(1);
		assertFalse("Actor.isDead failed!\n Expected result: isDead returns false\n Actual result: isDead returns true", test.isDead());
	}

}
