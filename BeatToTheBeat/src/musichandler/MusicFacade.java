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
	private List<Genre> genres;
	private SoundHandler sh;
	
	
	/**
	 * 
	 */
	public MusicFacade() {
		genres = new ArrayList<Genre>();
		setGenres();	
		songList = new ArrayList<Song>();
		currentSong = 0;
		
		loadSonglist();
		saveSonglist();
		sh  = new SoundHandler(songList.get(0), false);
		
	}
	
	private void setGenres() {
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
	 * Plays the song.
	 * @param playSong Song to be played.
	 */
	public void play(Song playSong) {
		
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
		try {
			Scanner in = new Scanner(new FileReader("songs\\songList.list"));
			while (in.hasNextLine()) {	
				//adds a song with: filename, songname, artist and genre
				addSong(in.nextLine(), in.nextLine(), in.nextLine(), in.nextLine());		
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void saveSonglist(){
		try {
	          BufferedWriter output = new BufferedWriter(new FileWriter(new File("songs\\songList.list")));
	          
	          for(Song s: songList){
	        	  //toString() in Song and Genre are overridden to guarantee satisfaction.
		          output.write(s.toString());
		          output.close();
		          
	          }
		} catch ( IOException e ) {
	           e.printStackTrace();
	        }
}
}
