package gui;

import java.awt.CardLayout;

import javax.swing.JFrame;

public class TestPanels extends JFrame {
	
	public static void main(String[] args) {
		TestPanels s = new TestPanels();
	}
	
	public TestPanels() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(914, 600);
		this.setResizable(false);
		MainMenu m = new MainMenu();
		this.add(m);
		this.revalidate();
		this.repaint();
	}

}
