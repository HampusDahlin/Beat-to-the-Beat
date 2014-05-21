package gui;

import java.awt.CardLayout;
import java.util.List;

import javax.swing.JPanel;

import musichandler.Genre;
import musichandler.Song;

@SuppressWarnings("serial")
public class CardPanel extends JPanel {
	
	private Options options;
	private MainMenu menu;
	private SongSelection songPresenter;
	private GamePanel gamePanel;
	private ScorePanel scorePanel;
	private SongUploadPanel songUpload;
	private static final String OPTIONSPANEL = "Panel with Options";
	private static final String MENUPANEL = "Panel with MainMenu";
	private static final String SONGSELECTIONPANEL = "Panel with Song selection";
	private static final String GAMEPANEL = "Panel with the game";
	private static final String SCOREPANEL = "Panel with Score";
	private static final String SONGUPLOADPANEL = "Panel with Song upload";
	private int activePanel;
	
	public CardPanel(List<Song> songList, Genre[] genreList) {
		this.setLayout(new CardLayout());
		options = new Options();
		menu = new MainMenu();
		gamePanel = new GamePanel();
		scorePanel = new ScorePanel();
		songPresenter = new SongSelection(songList);
		songUpload = new SongUploadPanel(songList, genreList);
		this.add(menu, MENUPANEL);
		this.add(options, OPTIONSPANEL);
		this.add(songPresenter, SONGSELECTIONPANEL);
		this.add(gamePanel, GAMEPANEL);
		this.add(scorePanel, SCOREPANEL);
		this.add(songUpload, SONGUPLOADPANEL);
	}
	
	void goToSongUpload() {
		((CardLayout)this.getLayout()).show(this, SONGUPLOADPANEL);
		activePanel = 5;
	}
	
	void back() {
		((CardLayout)this.getLayout()).show(this, MENUPANEL);
		activePanel = 0;
	}
	
	void goToOptions() {
		((CardLayout)this.getLayout()).show(this, OPTIONSPANEL);
		activePanel = 1;
	}
	
	void playSong() {
		((CardLayout)this.getLayout()).show(this, SONGSELECTIONPANEL);
		activePanel = 2;
		songPresenter.presentSongList(0);
	}
	
	void goToGame() {
		((CardLayout)this.getLayout()).show(this, GAMEPANEL);
		activePanel = 3;
	}
	
	public void goToScore(int score) {
		scorePanel.presentScore(score);
		activePanel = 4;
		((CardLayout)this.getLayout()).show(this, SCOREPANEL);
	}
	
	public JPanel getOptionsPanel(){
		return options;
	}
	
	public JPanel getGamePanel() {
		return gamePanel;
	}
	
	public JPanel[] getSongPanels(){
		return songPresenter.getSongPanels();
	}
	
	public void update() {
		gamePanel.update();
	}
	
	public void beat() {
		if (activePanel != 3) {
			((ZoomablePanel) this.getComponent(activePanel)).zoom();
		}
	}

}
