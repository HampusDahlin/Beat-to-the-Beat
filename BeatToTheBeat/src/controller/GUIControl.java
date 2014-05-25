package controller;

import enviroment.BarBackground;
import enviroment.MatrixBackground;
import enviroment.SinWaveBackground;
import enviroment.WaveBackground;
import gui.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import musichandler.Genre;
import musichandler.Song;
import services.HomogeneousFileHandler;

public class GUIControl implements PropertyChangeListener {
	private CardPanel mainPanel;
	private SongUploadFunctionality uploadFunc;
	private ScoreFunctionality scoreFunc;
	private int intensity;
	
	public GUIControl(List<Song> songList, Genre[] genreList) {
		mainPanel = new CardPanel(songList, genreList);
		this.intensity = (int)new HomogeneousFileHandler().load("options.conf").get(0);
		setBackground();
		setPCL();
		uploadFunc = new SongUploadFunctionality((SongUploadPanel)mainPanel.getSongUpload(), songList, genreList);
		scoreFunc = new ScoreFunctionality((ScorePanel)mainPanel.getScorePanel());
	}
	
	public void setPCL() {
		((Options)(mainPanel.getOptionsPanel())).pcs.addPropertyChangeListener(this);
		((ScorePanel)(mainPanel.getScorePanel())).pcs.addPropertyChangeListener(this);
		((SongSelection)(mainPanel.getSongSelection())).pcs.addPropertyChangeListener(this);
		((SongUploadPanel)(mainPanel.getSongUpload())).pcs.addPropertyChangeListener(this);
		((MainMenu)(mainPanel.getMainMenu())).pcs.addPropertyChangeListener(this);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("back")) {
			mainPanel.back();
		} else if(evt.getPropertyName().equals("upload")) {
			uploadFunc.clearFields();
			mainPanel.goToSongUpload();
		} else if(evt.getPropertyName().equals("options")) {
			mainPanel.goToOptions();
		} else if(evt.getPropertyName().equals("selection")) {
			mainPanel.playSong();
		} else if(evt.getPropertyName().equals("browse")) {
			uploadFunc.browse();
		} else if(evt.getPropertyName().equals("load")) {
			uploadFunc.loadingSequence();
		} else if(evt.getPropertyName().equals("procced")) {
			scoreFunc.procced();
			mainPanel.playSong();
		} else if (evt.getPropertyName().equals("bgSlider")){
			this.intensity = (int)evt.getNewValue();
			setBackground();
		}
	}
	
	
	/**
	 * Determines what to paint depending on the backgrounds intensity.
	 * @param g2d
	 * @param intensity
	 */
	public void setBackground(){
		switch(intensity){
		case 0:
			((GamePanel)(mainPanel.getGamePanel())).getBackgroundWaves().clear();
			((GamePanel)(mainPanel.getGamePanel())).addBackgroundWave(new BarBackground());
			break;
		case 1:
			((GamePanel)(mainPanel.getGamePanel())).getBackgroundWaves().clear();
			((GamePanel)(mainPanel.getGamePanel())).addBackgroundWave(new WaveBackground());
			System.out.println("1");
			break;
		case 2:
			((GamePanel)(mainPanel.getGamePanel())).getBackgroundWaves().clear();
			((GamePanel)(mainPanel.getGamePanel())).addBackgroundWave(new SinWaveBackground());
			break;
		case 3:
			((GamePanel)(mainPanel.getGamePanel())).getBackgroundWaves().clear();
			((GamePanel)(mainPanel.getGamePanel())).addBackgroundWave(new WaveBackground());
			((GamePanel)(mainPanel.getGamePanel())).addBackgroundWave(new SinWaveBackground());
			break;
		case 4:
			((GamePanel)(mainPanel.getGamePanel())).getBackgroundWaves().clear();
			((GamePanel)(mainPanel.getGamePanel())).addBackgroundWave(new MatrixBackground());
			((GamePanel)(mainPanel.getGamePanel())).addBackgroundWave(new WaveBackground());
			((GamePanel)(mainPanel.getGamePanel())).addBackgroundWave(new SinWaveBackground());
			break;
		}
	}
	
	public void goToScore(int score, Song song) {
		scoreFunc.presentScore(score, song);
		mainPanel.goToScore();
	}
	
	public CardPanel getMainPanel() {
		return mainPanel;
	}
}
