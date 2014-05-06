package musichandler;

import java.util.ArrayList;
import java.util.List;

/**
 * A class handling a list of songs.
 * 
 * @author Hampus Dahlin
 * @helpFrom Pontus "Bondi" Eriksson
 * @version 0.0.2
 */
public class MusicFacade {
	
	private List<Song> songList;
	private int currentSong;
	private List<Genre> genres;
	private SoundHandler sh;
	
	
	/**
	 * 
	 */
	public MusicFacade() {
		genres = new ArrayList<Genre>();
		songList = new ArrayList<Song>();
		currentSong = 0;
		
		genres.add(new Genre("Happy Hardcore", 0, 10, 5, 200));
		genres.add(new Genre("Rap", 0, 5, 3, 200));
		genres.add(new Genre("Rock", 0, 5, 3, 200));
	}
	
	
	/**
	 * 
	 * @param fileName
	 */
	public void addSong(String fileName, String songName, String artist, Genre genre) {
		songList.add(new Song(fileName, songName, artist, genre));
	}
	
	public void addSong(String fileName, String songName, String artist, String genre) {
		for (Genre g : genres) {
			if (g.getName().equalsIgnoreCase(genre)) {
				addSong(fileName, songName, artist, g.getName());
				return;
			}
		}
		System.out.println("Hittade ingen genre som heter: \"" + genre + "\"");
	}
	
	
	/**
	 * Plays the current song with analyzing.
	 */
	public void play(){
		sh = new SoundHandler(songList.get(currentSong), true);
		sh.start();
	}
	
	/**
	 * Plays the current song without analyzing.
	 */
	public void playOnly() {
		sh = new SoundHandler(songList.get(currentSong), false);
		sh.start();
	}
	
	/**
	 * Pauses the current song.
	 */
	public void pause(){
		if (!sh.equals(null)) {
			sh.pause();
		}
	}
	
	
	/**
	 * Changes the current song to the next in the list.
	 */
	public void next(){
		if(currentSong < songList.size()){
			currentSong++;
		}
	}
	
	
	/**
	 * Changes the current song to the previous in the list.
	 */
	public void previous(){
		if (currentSong > 0) {
			currentSong--;
		}
	}
	
	public boolean isBeat() {
		return sh.isBeat();
	}
	
	
	public void changeDist(){
		//TODO
	}
	
	
	public void changeSpeed(){
		//TODO
	}
	
}
