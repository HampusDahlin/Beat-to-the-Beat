package unitTests;

import static org.junit.Assert.assertTrue;

import java.awt.Point;

import model.BeatToTheBeatModel;

import org.junit.Test;


public class BeatToTheBeatModelTest {
	BeatToTheBeatModel test;
	
	@Test
	public void testClass(){
		try{
			setUp();
		}catch(Exception e){
			System.out.println("BeatToTheBeatModel-class could not be instantiated");
		}
		
		testCreateNPC();
		testRemoveActor();
		testNPCAttack();
		testMove();
	}
	
	public void setUp() {
		test = new BeatToTheBeatModel();
	}
	
	public void testCreateNPC() {
		test.createNpc();
		assertTrue(test.getNpcList().size() == 1);
	}
	
	public void testRemoveActor() {
		test.removeActor();
		assertTrue(test.getNpcList().size() == 0);
	}
	
	public void testMove() {
		test.createNpc();
		int oldX = test.getFirstEnemy().getPosition().x;
		test.moveActors();
		//Tests from both spawnpoints as the move in different directions
		//depending on this point
		if(oldX > 0) {
			assertTrue("Usecase move!\n Expected result: NPCs position should have changed\n Actual result: position remained the same", test.getFirstEnemy().getPosition().x < oldX);	
		} else {
			assertTrue("Usecase move!\n Expected result: NPCs position should have changed\n Actual result: position remained the same", test.getFirstEnemy().getPosition().x > oldX);	
		}

	}
	
	public void testNPCAttack() {
		int hp = test.getPlayer().getHealth();
		test.createNpc();
		test.getFirstEnemy().setPosition(new Point(420, 0));
		test.moveActors();
		assertTrue("Usecase move & attack failed!\n Expected result: players max hp should have decreased\n Actual result: hp remained the same value", test.getPlayer().getHealth() != hp);
	}
}
