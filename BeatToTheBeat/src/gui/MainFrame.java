package gui;

import java.awt.CardLayout;

import javax.swing.JFrame;

import controller.HeadControl;


/**
 * 
 * @author Hampus Dahlin
 *
 */
public class MainFrame extends JFrame{
	
	public MainFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(914, 600);
		this.setLayout(new CardLayout());

	}
}
