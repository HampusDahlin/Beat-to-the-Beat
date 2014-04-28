package musichandler;

import java.io.Serializable;
import java.util.ArrayList;

public class Song implements Serializable {
	
	private ArrayList<Long> beatList;
	private String filename;
	private String difficulty;
	private int threshold;
	
	public Song(String filename) {
		this.filename = filename;
	}
	
	public ArrayList<Long> getBeatList() {
		return beatList;
	}
	
	public void setBeatList(ArrayList<Long> beatList) {
		this.beatList = beatList;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
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
}
