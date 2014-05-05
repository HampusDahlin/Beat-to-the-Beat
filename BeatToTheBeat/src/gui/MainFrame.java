package gui;

import javax.swing.JFrame;

import controller.HeadControl;


/**
 * 
 * @author Hampus Dahlin
 *
 */
public class MainFrame extends JFrame{
	
	private HeadControl hc;
	public static MainFrame mf;
	
	public MainFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(100, 100);
		hc = new HeadControl();
		
		
		this.setVisible(true);
	}
	
	public static void main(String[] args){
		mf = new MainFrame();
	}
	
	public HeadControl getHeadControl(){
		return hc;
	}
}
