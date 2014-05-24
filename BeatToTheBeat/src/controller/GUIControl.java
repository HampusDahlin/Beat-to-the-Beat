package controller;

import gui.*;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.List;


import musichandler.Genre;
import musichandler.Song;

public class GUIControl implements PropertyChangeListener {
	private CardPanel mainPanel;
	
	public GUIControl(List<Song> songList, Genre[] genreList) {
		mainPanel = new CardPanel(songList, genreList);
		setPCL();
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
			mainPanel.goToSongUpload();
		} else if(evt.getPropertyName().equals("options")) {
			mainPanel.goToOptions();
		} else if(evt.getPropertyName().equals("selection")) {
			mainPanel.playSong();
		} else if(evt.getPropertyName().equals("browse")) {
			
		}
	}
	
	public CardPanel getMainPanel() {
		return mainPanel;
	}
}
