package test;

import javax.swing.JFrame;

import controller.HeadControl;

/**
 * 
 * @author Malin
 * @version 0.0.1
 * @grupp 14
 */
public class TestGame extends JFrame {
	
	public TestGame(){
		setSize(200,200);
		setFocusable(true);
		setVisible(true);
		
	}
	
	public static void main(String[]args){
		
		new HeadControl(new TestGame());
	}
 
}
