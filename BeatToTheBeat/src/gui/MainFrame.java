package gui;

import javax.swing.JFrame;

import controller.HeadControl;

public class MainFrame extends JFrame{
	
	public HeadControl hc;
	
	public MainFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(100, 100);
		hc = new HeadControl();
		
		
		this.setVisible(true);
	}
}
