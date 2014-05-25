package controller;

import gui.CardPanel;
import gui.MainMenu;
import gui.Options;
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
	
	public GUIControl(List<Song> songList, Genre[] genreList) {
		mainPanel = new CardPanel(songList, genreList);
		setPCL();
		uploadFunc = new SongUploadFunctionality((SongUploadPanel)mainPanel.getSongUpload(), songList, genreList);
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
		}
	}
	
	public CardPanel getMainPanel() {
		return mainPanel;
	}
}
