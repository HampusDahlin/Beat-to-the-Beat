package musichandler;

import java.io.Serializable;

public class Song implements Serializable {
	
	private final String fileName;
	private final String artist;
	private final String songName;
	private final Genre genre;
	
	public Song(String fileName, String songName, String artist, Genre genre) {
		this.fileName = "songs\\" + fileName;
		this.songName = songName;
		this.artist = artist;
		this.genre = genre;
	}
	
	public String getFilename() {
		return fileName;
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
