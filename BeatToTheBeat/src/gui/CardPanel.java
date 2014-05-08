package gui;

import java.awt.CardLayout;
import java.util.List;

import javax.swing.JPanel;

import musichandler.Song;

public class CardPanel extends JPanel {
	
	Options options;
	MainMenu menu;
	SongSelection songPresenter;
	
	public CardPanel(List<Song> songList) {
		
		this.setLayout(new CardLayout());
		options = new Options();
		menu = new MainMenu();
		songPresenter = new SongSelection(songList);
		songPresenter.setVisible(true);
		menu.setVisible(true);
		options.setVisible(true);
		this.add(menu);
		this.add(options);
		this.add(songPresenter);
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
		songPresenter.presentSongList(0);
	}

}
