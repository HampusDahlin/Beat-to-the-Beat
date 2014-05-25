package controller;

import gui.CardPanel;
import gui.MainMenu;
import gui.Options;
import gui.ScoreFunctionality;
import gui.ScorePanel;
import gui.SongSelection;
import gui.SongUploadFunctionality;
import gui.SongUploadPanel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import musichandler.Genre;
import musichandler.Song;

public class GUIControl implements PropertyChangeListener {
	private CardPanel mainPanel;
	private SongUploadFunctionality uploadFunc;
	private ScoreFunctionality scoreFunc;
	
	public GUIControl(List<Song> songList, Genre[] genreList) {
		mainPanel = new CardPanel(songList, genreList);
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
			uploadFunc.load();
		} else if(evt.getPropertyName().equals("procced")) {
			scoreFunc.procced();
			mainPanel.playSong();
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
