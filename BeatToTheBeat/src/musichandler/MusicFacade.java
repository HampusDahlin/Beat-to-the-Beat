package musichandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
			new Genre("Happy Hardcore", 2, 6, 3, 100),
			new Genre("Rap", 0, 5, 3, 200),
			new Genre("Rock", 0, 5, 3, 200)
		};
	}
	
	/**
	 * 
	 * @param fileName
	 */
	public void addSong(String fileName, String songName, String artist, Genre genre) {
		songList.add(new Song(fileName, songName, artist, genre));
	}
	
	public void addSong(String fileName, String songName, String artist, String genre) {
		System.out.println(genre);
		for (Genre g : genres) {
			System.out.println(genre.equals(g.getName()));
			if (g.getName().equalsIgnoreCase(genre)) {
				addSong(fileName, songName, artist, g);
				return;
			}
		}
		System.out.println("Hittade ingen genre som heter: \"" + genre + "\"");
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
	 * Plays the current song and choose if to analyze it or not.
	 */
	public void playCurrent(boolean isDelay) {
		play(songList.get(currentSong), isDelay);
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
	
	public void analyze(){
		sh.analyze();
	}
	
	public Analyzer getAnalyzer(){
		return sh.getAnalyzer();
	}
	
	public int getIndexOfCurrentSong() {
		return currentSong;
	}
	
	// TODO
	public void setIndex(int newIndex) {
		if(newIndex < 0) {
			currentSong = 0;
		} else if(newIndex > songList.size()) {
			currentSong = 0;
		} else {
			currentSong = newIndex;
		}
	}
	
	public void changeDist(){
		//TODO
	}
	
	
	public void changeSpeed(){
		//TODO
	}
	
	public float[][] getWave() {
		return sh.getWave();
	}
	
	public List<Song> getSongList() {
		return songList;
	}
	
	public void loadSonglist() {
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
	
	public void saveSonglist(){
		try {
	          BufferedWriter output = new BufferedWriter(new FileWriter(new File("songs\\songList.list")));
	          System.out.println(songList.size());
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
