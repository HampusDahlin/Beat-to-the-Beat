package musichandler;

import java.io.Serializable;

@SuppressWarnings("serial")
public final class Song implements Serializable {
	
	private final String FILENAME;
	private final String ARTIST;
	private final String SONGNAME;
	private final Genre GENRE;
	
	public Song(String fileName, String songName, String artist, Genre genre) {
		this.FILENAME = (!fileName.substring(0, 5).equals("songs\\") ? "songs\\" : "") + fileName;
		this.SONGNAME = songName;
		this.ARTIST = artist;
		this.GENRE = genre;
	}
	
	public String getFilename() {
		return FILENAME;
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
		return GENRE;
	}
	
	public String getSongName() {
		return SONGNAME;
	}
	
	public String getArtist() {
		return ARTIST;
	}
	
	public String toString(){
		//we start from substring 6 to get rid of "song\" when writing to the songList.list file
		return FILENAME.substring(6)+"\n"+SONGNAME+"\n"+ARTIST+"\n"+GENRE.toString();
	}
}
