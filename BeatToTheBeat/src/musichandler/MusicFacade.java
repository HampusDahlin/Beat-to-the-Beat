package musichandler;

import java.util.ArrayList;
import java.util.List;

/**
 * A class handling a list of songs.
 * 
 * @author Hampus Dahlin
 * @version 0.0.1
 */
public class MusicFacade {
	
	private List<Song> songlist;
	
	private int currentSong;
	
	
	/**
	 * 
	 */
	public MusicFacade() {
		songlist = new ArrayList<Song>();
		currentSong = 0;
	}
	
	
	/**
	 * 
	 * @param filename
	 */
	public void addSong(String filename){
		songlist.add(new Song(filename));
	}
	
	
	/**
	 * Plays the current song.
	 */
	public void play(){
		songlist.get(currentSong).play();
	}
	
	
	/**
	 * Pauses the current song.
	 */
	public void pause(){
		songlist.get(currentSong).pause();
	}
	
	
	/**
	 * Changes the current song to the next in the list.
	 */
	public void next(){
		if(currentSong < songlist.size()){
			currentSong++;
		}
	}
	
	
	/**
	 * Changes the current song to the previous in the list.
	 */
	public void previous(){
		if(currentSong > 0){
			currentSong--;
		}
	}
	
	
	public void changeDist(){
		//TODO
	}
	
	
	public void changeSpeed(){
		//TODO
	}
	
}
