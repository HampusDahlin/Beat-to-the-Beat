package musichandler;

import java.io.Serializable;
import java.util.ArrayList;

public class Song implements Serializable {
	
	private ArrayList<Long> beatList;
	private String fileName;
	private String difficulty;
	private int threshold;
	private String artist;
	private String songName;
	private Genre genre;
	
	public Song(String fileName, String songName, String artist, Genre genre) {
		this.fileName = "songs\\" + fileName;
		this.songName = songName;
		this.artist = artist;
		this.genre = genre;
	}

	public ArrayList<Long> getBeatList() {
		return beatList;
	}
	
	public void setBeatList(ArrayList<Long> beatList) {
		this.beatList = beatList;
	}
	
	public String getFilename() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getDifficulty() {
		return difficulty;
	}
	
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	
	public int getThreshold() {
		return threshold;
	}
	
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public boolean equals(Object other) {
		if(this == other) {
			return true;
		} else if(other == null) {
			return false;
		} else if(getClass() != other.getClass()) {
			return false;
		} 
		
		return true;
	}

	public Genre getGenre() {
		return genre;
	}
	
	public String getSongName() {
		return songName;
	}
	
	public String getArtist() {
		return artist;
	}
}
