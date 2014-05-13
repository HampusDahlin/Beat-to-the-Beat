package gui;

import java.awt.CardLayout;
import java.util.List;

import javax.swing.JPanel;

import musichandler.Song;

public class CardPanel extends JPanel {
	
	private Options options;
	private MainMenu menu;
	private SongSelection songPresenter;
	private GamePanel gamePanel;
	private static final String OPTIONSPANEL = "Panel with Options";
	private static final String MENUPANEL = "Panel with MainMenu";
	private static final String SONGSELECTIONPANEL = "Panel with Song selection";
	private static final String GAMEPANEL = "Panel with the game";
	
	public CardPanel(List<Song> songList) {
		
		this.setLayout(new CardLayout());
		options = new Options();
		menu = new MainMenu();
		gamePanel = new GamePanel();
		songPresenter = new SongSelection(songList);
		songPresenter.setVisible(true);
		menu.setVisible(true);
		options.setVisible(true);
		this.add(menu, MENUPANEL);
		this.add(options, OPTIONSPANEL);
		this.add(songPresenter, SONGSELECTIONPANEL);
		this.add(gamePanel, GAMEPANEL);
	}
	
	public void exit() {
		System.exit(0);
	}
	
	public void back() {
		((CardLayout)this.getLayout()).show(this, MENUPANEL);
	}
	
	public void goToOptions() {
		((CardLayout)this.getLayout()).show(this, OPTIONSPANEL);
	}
	
	public void playSong() {
		((CardLayout)this.getLayout()).show(this, SONGSELECTIONPANEL);
		songPresenter.presentSongList(0);
	}
	
	public void goToGame() {
		((CardLayout)this.getLayout()).show(this, GAMEPANEL);
	}
	
	public void goToScore() {
		
	}
	
	public JPanel getGamePanel() {
		return gamePanel;
	}
	
	public JPanel[] getSongPanels(){
		return songPresenter.getSongPanels();
	}

}
