package gui;

import java.awt.Color;

import musichandler.HighScoreList;
import musichandler.Song;

public class ScoreFunctionality {
	private ScorePanel scorePanel;
	private int score;
	private Song song;
	private HighScoreList hsList;

	public ScoreFunctionality(ScorePanel scorePanel) {
		this.scorePanel = scorePanel;
	}

	public void procced() {
		if(hsList.isEligible(score) != -1) {
			hsList.add(shortenString(scorePanel.getNameField()[hsList.isEligible(score)].getText(), 10), score);
		}
		hsList.saveHighscoreList(song);
	}

	public void presentScore(int score, Song song) {
		this.score = score;
		this.song = song;
		try{
			hsList.loadHighscorelist(song);
		}catch(NullPointerException exc){
			hsList = song.getHighScoreList();
		}
		scorePanel.getScoreLabel().setText(score + " Points");
		presentHighScoreList(song);
	}

	void presentHighScoreList(Song song) {
		scorePanel.getCongratsLabel().setForeground(Color.white);
		disableEditable();
		int possibleIndex = hsList.isEligible(score);
		if(possibleIndex != -1) {
			scorePanel.getCongratsLabel().setForeground(Color.green);
			scorePanel.getNameField()[possibleIndex].setEditable(true);
			scorePanel.getScoreField()[possibleIndex].setText(score + "");
			scorePanel.getNameField()[possibleIndex].setText("");

			for(int i = 0; i < possibleIndex; i++) {
				scorePanel.getNameField()[i].setText(hsList.getNames()[i]);
				scorePanel.getScoreField()[i].setText(hsList.getScores()[i] + "");
			}

			for(int j = possibleIndex + 1; j < 5; j++) {
				scorePanel.getNameField()[j].setText(hsList.getNames()[j - 1]);
				scorePanel.getScoreField()[j].setText(hsList.getScores()[j - 1] + "");
			}
		} else {
			for(int i = 0; i < 5; i++) {
				scorePanel.getNameField()[i].setText(hsList.getNames()[i]);
				scorePanel.getScoreField()[i].setText(hsList.getScores()[i] + "");
			}
		}
		scorePanel.getSongLabel().setText(shortenString(song.getSongName(), 15));
		scorePanel.getArtistLabel().setText(shortenString(song.getArtist(), 15));
	}

	public String shortenString(String stringToShorten, int maxLength) {
		if(stringToShorten.length() >= maxLength) {
			stringToShorten = stringToShorten.substring(0,maxLength - 4) + "...";
		}
		return stringToShorten;
	}

	public void disableEditable() {
		for(int i = 0; i < 5; i++) {
			scorePanel.getScoreField()[i].setEditable(false);
			scorePanel.getNameField()[i].setEditable(false);
		}
	}
}
