package gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class CardPanel extends JPanel {
	
	public CardPanel() {
		this.setLayout(new CardLayout());
		Options o = new Options(this);
		MainMenu m = new MainMenu(this);
		m.setVisible(true);
		o.setVisible(true);
		this.add(m);
		this.add(o);
	}
	
	public void exit() {
		System.exit(0);
	}
	
	public void back() {
		((CardLayout)this.getLayout()).previous(this);
	}
	
	public void goToOptions() {
		((CardLayout)this.getLayout()).next(this);
	}
	
	public void playSong() {
		
	}

}
