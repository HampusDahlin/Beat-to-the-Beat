package tests;

import java.awt.Color;

import enviroment.WaveForm;
public class WaveFormTest {

	public static void main(String[] args){
		new WaveFormTest();
	}
	
	private WaveForm wave, wavetwo;
	
	public WaveFormTest(){
		wave = new WaveForm(null, false, new Color(252, 0, 0));
		wavetwo = new WaveForm(null, false, new Color(252, 0, 0));
		
		if (testAge()) {
			System.out.println("testAge successful!");
		} else {
			System.out.println("testAge failed.");
		}
	}
	
	
	private boolean testAge(){
		boolean test = true;
		
		wave.age();
		
		if((wave.getColor().getAlpha() == wavetwo.getColor().getAlpha()/2 && wave.getWidth() == wavetwo.getWidth()-1)){
			System.out.println("Age test successful");
		} else {
			System.out.println("Age test failed");
			test = false;
		}
		
		if(testMinimumWidth()){
			System.out.println("Minimum Width test successful");
		} else {
			System.out.println("Minimum Width test failed");
			test = false;
		}
		
		return test;
		
	}


	private boolean testMinimumWidth() {
		
		wave.age();
		wave.age();
		wave.age();
		wave.age();
		
		return (wave.getWidth() == 1);
	}
}
