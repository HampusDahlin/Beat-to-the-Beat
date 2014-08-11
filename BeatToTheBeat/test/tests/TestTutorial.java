package tests;

import gui.TutorialPanel;

import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class TestTutorial extends JFrame {
	
	public static void main(String[] args) {
		TestTutorial tt = new TestTutorial();
	}
	
	public TestTutorial() {
		setVisible(true);
		add(new TutorialPanel());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(914,600));
		this.setResizable(false);
	}
}
