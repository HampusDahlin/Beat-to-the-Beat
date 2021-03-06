package services.musichandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.music.Genre;
import model.music.Song;

/**
 * A class handling a list of songs.
 * 
 * @author Hampus Dahlin
 * @helpFrom Pontus "Bondi" Eriksson
 * @revisedBy Malin "Nilhet" Thelin
 * @version 0.0.3
 */
public class MusicFacade {
	
	private List<Song> songList;
	private int currentSong;
	private Genre[] genres;
	private SoundHandler sh;
	
	
	/**
	 * 
	 */
	public MusicFacade() {
		setGenres();	
		songList = new ArrayList<Song>();
		currentSong = 0;
		
		loadSonglist();
		saveSonglist();
		sh  = new SoundHandler(songList.get(0), false);
		
	}
	
	private void setGenres() {
		genres = new Genre[]{
			new Genre("Happy Hardcore", 1, 3, 2, 330),
			new Genre("Rap", 2, 4, 2, 330),
			new Genre("Rock", 2, 4, 2, 330),
			new Genre("House", 1, 3, 2, 330),
			new Genre("Ballade", 1, 4, 2, 400)
		};
	}
	
	/**
	 * 
	 * @param fileName
	 */
	public void addSong(String fileName, String songName, String artist, Genre genre) {
		songList.add(new Song(fileName, songName, artist, genre));
		saveSonglist();
	}
	
	private void addSong(String fileName, String songName, String artist, String genre) {
		for (Genre g : genres) {
			if (g.getName().equalsIgnoreCase(genre)) {
				addSong(fileName, songName, artist, g);
				return;
			}
		}
		System.out.println("Couldn't find any genre called: \"" + genre + "\"");
	}
	
	/**
	 * Plays the song and choose if to analyze it or not.
	 * @param playSong Song to be played.
	 * @param isDelay
	 */
	public void play(Song playSong, boolean isDelay) {
		sh = new SoundHandler(playSong, isDelay);
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
	
	public void resume(){
		if (!sh.equals(null)){
			sh.resume();
		}
	}
	
	public void analyze(){
		sh.analyze();
	}
	
	public Analyzer getAnalyzer(){
		return sh.getAnalyzer();
	}
	
	public int getIndexOfCurrentSong() {
		return currentSong;
	}
	
	public void setIndex(int newIndex) {
		if(newIndex < 0) {
			currentSong = 0;
		} else if(newIndex > songList.size()) {
			currentSong = 0;
		} else {
			currentSong = newIndex;
		}
	}
	
	public float[][] getWave() {
		return sh.getWave();
	}
	
	public List<Song> getSongList() {
		return songList;
	}
	
	private void loadSonglist() {
		Scanner in = null;
		try {
			in = new Scanner(new FileReader("songs\\songList.list"));
			while (in.hasNextLine()) {	
				//adds a song with: filename, songname, artist and genre
				addSong(in.nextLine(), in.nextLine(), in.nextLine(), in.nextLine());		
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			in.close();
		}
	}
	
	private void saveSonglist(){
		try {
	          BufferedWriter output = new BufferedWriter(new FileWriter(new File("songs\\songList.list")));
	          for(Song s: songList){
	        	  //toString() in Song and Genre are overridden to guarantee satisfaction.
		          output.write(s.toString());
		          output.write("\n");
	          }
	          output.close();
		} catch ( IOException e ) {
	           e.printStackTrace();
	        }
}
	
	public int getSongCount() {
		return songList.size();
	}
	
	public Genre[] getGenres() {
		return genres;
	}
	
	public void setVolume(int vol) {
		sh.setVolume(vol);
	}
	
} //end MusicFacade
