package gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class CardPanel extends JPanel {
	
	public CardPanel() {
		this.setLayout(new CardLayout());
		//Options o = new Options();
		MainMenu m = new MainMenu(this);
		m.setVisible(true);
		//o.setVisible(false);
		this.add(m);
		//this.add(o);
	}
	
	public void exit() {
		System.exit(0);
	}
	
	public void goToOptions() {
		
	}
	
	public void playSong() {
		((CardLayout)this.getLayout()).next(this);
	}

}
