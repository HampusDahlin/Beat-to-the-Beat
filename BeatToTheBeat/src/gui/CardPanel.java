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
	
	/**
	 * @param songList
	 * @param genreList
	 * creates a panel holding all the other panels compromissing the Beat to the Beat GUI
	 */
	public CardPanel(List<Song> songList, Genre[] genreList) {
		this.setLayout(new CardLayout());
		options = new Options();
		menu = new MainMenu();
		gamePanel = new GamePanel();
		scorePanel = new ScorePanel();
		songPresenter = new SongSelection(songList);
		songUpload = new SongUploadPanel();
		this.add(menu, MENUPANEL);
		this.add(options, OPTIONSPANEL);
		this.add(songPresenter, SONGSELECTIONPANEL);
		this.add(gamePanel, GAMEPANEL);
		this.add(scorePanel, SCOREPANEL);
		this.add(songUpload, SONGUPLOADPANEL);
	}
	
	/**
	 * switches to the SongUploadPanel
	 */
	public void goToSongUpload() {
		((CardLayout)this.getLayout()).show(this, SONGUPLOADPANEL);
		activePanel = 5;
	}
	
	/**
	 * Goes back to the mainmenu
	 */
	public void back() {
		((CardLayout)this.getLayout()).show(this, MENUPANEL);
		activePanel = 0;
	}
	
	/**
	 * switches to options
	 */
	public void goToOptions() {
		((CardLayout)this.getLayout()).show(this, OPTIONSPANEL);
		activePanel = 1;
	}
	
	/**
	 * switches to SongSelection
	 */
	public void playSong() {
		((CardLayout)this.getLayout()).show(this, SONGSELECTIONPANEL);
		activePanel = 2;
		songPresenter.presentSongList(0);
	}
	
	/**
	 * switches to GamePanel
	 */
	void goToGame() {
		((CardLayout)this.getLayout()).show(this, GAMEPANEL);
		activePanel = 3;
	}
	
	/**
	 * switches to scorepanel
	 */
	public void goToScore() {
		activePanel = 4;
		((CardLayout)this.getLayout()).show(this, SCOREPANEL);
	}
	
	public JPanel getOptionsPanel(){
		return options;
	}
	
	public JPanel getScorePanel() {
		return scorePanel;
	}
	
	public JPanel getMainMenu() {
		return menu;
	}
	
	public JPanel getGamePanel() {
		return gamePanel;
	}
	
	public JPanel getSongSelection() {
		return songPresenter;
	}
	
	public JPanel getSongUpload() {
		return songUpload;
	}
	
	public JPanel[] getSongPanels(){
		return songPresenter.getSongPanels();
	}
	
	/**
	 * uupdates the gamepanel
	 */
	public void update() {
		gamePanel.update();
	}
	
	public void beat() {
		if (activePanel != 3) {
			((ZoomablePanel) this.getComponent(activePanel)).zoom();
		}
	}

}
