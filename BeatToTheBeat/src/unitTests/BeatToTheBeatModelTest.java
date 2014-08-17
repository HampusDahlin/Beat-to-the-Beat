package unitTests;

import model.BeatToTheBeatModel;
import static org.junit.Assert.*;

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
}
