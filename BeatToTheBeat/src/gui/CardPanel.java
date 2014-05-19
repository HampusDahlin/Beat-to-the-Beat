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
	private ScorePanel scorePanel;
	private static final String OPTIONSPANEL = "Panel with Options";
	private static final String MENUPANEL = "Panel with MainMenu";
	private static final String SONGSELECTIONPANEL = "Panel with Song selection";
	private static final String GAMEPANEL = "Panel with the game";
	private static final String SCOREPANEL = "Panel with Score";
	private int activePanel;
	
	public CardPanel(List<Song> songList) {
		
		this.setLayout(new CardLayout());
		options = new Options();
		menu = new MainMenu();
		gamePanel = new GamePanel();
		scorePanel = new ScorePanel();
		songPresenter = new SongSelection(songList);
		songPresenter.setVisible(true);
		menu.setVisible(true);
		options.setVisible(true);
		this.add(menu, MENUPANEL);
		this.add(options, OPTIONSPANEL);
		this.add(songPresenter, SONGSELECTIONPANEL);
		this.add(gamePanel, GAMEPANEL);
		this.add(scorePanel, SCOREPANEL);
		
	}
	
	public void exit() {
		System.exit(0);
	}
	
	public void back() {
		((CardLayout)this.getLayout()).show(this, MENUPANEL);
		activePanel = 0;
	}
	
	public void goToOptions() {
		((CardLayout)this.getLayout()).show(this, OPTIONSPANEL);
		activePanel = 1;
	}
	
	public void playSong() {
		((CardLayout)this.getLayout()).show(this, SONGSELECTIONPANEL);
		activePanel = 2;
		songPresenter.presentSongList(0);
	}
	
	public void goToGame() {
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
