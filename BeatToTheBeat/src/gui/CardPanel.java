package gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class CardPanel extends JPanel {
	
	public CardPanel() {
		this.setLayout(new CardLayout());
		Options options = new Options(this);
		MainMenu menu = new MainMenu(this);
		SongSelection song = new SongSelection(this);
		song.setVisible(true);
		menu.setVisible(true);
		options.setVisible(true);
		this.add(menu);
		this.add(options);
		this.add(song);
	}
	
	public void exit() {
		System.exit(0);
	}
	
	public void back() {
		((CardLayout)this.getLayout()).first(this);
	}
	
	public void goToOptions() {
		((CardLayout)this.getLayout()).next(this);
	}
	
	public void playSong() {
		((CardLayout)this.getLayout()).last(this);
	}

}
