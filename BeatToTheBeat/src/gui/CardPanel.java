package gui;

import java.awt.CardLayout;
import java.util.List;

import javax.swing.JPanel;

import model.music.Genre;
import model.music.Song;

@SuppressWarnings("serial")
public class CardPanel extends JPanel {
	
	private boolean tutorialEnabled = true;
	private Options options;
	private MainMenu menu;
	private SongSelection songPresenter;
	private GamePanel gamePanel;
	private ScorePanel scorePanel;
	private SongUploadPanel songUpload;
	private TutorialPanel tutorialPanel;
	private static final String OPTIONSPANEL = "Panel with Options";
	private static final String MENUPANEL = "Panel with MainMenu";
	private static final String SONGSELECTIONPANEL = "Panel with Song selection";
	private static final String GAMEPANEL = "Panel with the game";
	private static final String SCOREPANEL = "Panel with Score";
	private static final String SONGUPLOADPANEL = "Panel with Song upload";
	private static final String TUTORIALPANEL = "Panel with tutorial";
	private int activePanel;
	
	/**
	 * @param songList
	 * @param genreList
	 * creates a panel holding all the other panels compromissing the Beat to the Beat GUI
	 */
	public CardPanel(List<Song> songList, Genre[] genreList, model.BeatToTheBeatModel model) {
		this.setLayout(new CardLayout());
		options = new Options();
		menu = new MainMenu();
		gamePanel = new GamePanel(model);
		scorePanel = new ScorePanel();
		songPresenter = new SongSelection(songList);
		songUpload = new SongUploadPanel();
		tutorialPanel = new TutorialPanel();
		this.add(menu, MENUPANEL);
		this.add(options, OPTIONSPANEL);
		this.add(songPresenter, SONGSELECTIONPANEL);
		this.add(gamePanel, GAMEPANEL);
		this.add(scorePanel, SCOREPANEL);
		this.add(songUpload, SONGUPLOADPANEL);
		this.add(tutorialPanel, TUTORIALPANEL);
	}
	
	/**
	 * switches to the SongUploadPanel
	 */
	public void goToSongUpload() {
		((CardLayout)this.getLayout()).show(this, SONGUPLOADPANEL);
		activePanel = 5;
	}
	
	public boolean tutorialEnabled() {
		return tutorialEnabled;
	}
	
	public void setTutorialEnabled(boolean enabled) {
		tutorialEnabled = enabled;
	}
	
	/**
	 * Goes back to the mainmenu
	 */
	public void back() {
		((CardLayout)this.getLayout()).show(this, MENUPANEL);
		activePanel = 0;
	}
	
	public void goToTutorial() {
		((CardLayout)this.getLayout()).show(this, TUTORIALPANEL);
		activePanel = 6;
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
	
	public JPanel getTutorialPanel() {
		return tutorialPanel;
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
		if (activePanel != 3 && activePanel != 6) {
			((ZoomablePanel) this.getComponent(activePanel)).zoom();
		}
	}

}
