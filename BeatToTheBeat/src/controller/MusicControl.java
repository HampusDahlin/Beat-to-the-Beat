package controller;

import java.util.List;

import musichandler.MusicFacade;
import musichandler.Song;

/**
 * 
 * @author Hampus Dahlin
 *
 */
public class MusicControl {
	
	private MusicFacade music;
	
	public MusicControl() {
		music = new MusicFacade();
	}
	
	/**
	 * Checks if there is a beat at the current time in the song.
	 */
	public boolean isBeat(){
		return music.isBeat();
	}
	
	public void setSong(int index) {
		
	}
	
	public void play() {
		music.play();
	}
	
	public void pause() {
		music.pause();
	}
	
	/**
	 * Returns a 2xBUFFERSIZE float-matrix containing information for drawing a soundwave.
	 */
	public float[][] getWave() {
		return music.getWave();
	}
	
	public List<Song> getSongList() {
		return music.getSongList();
	}
}
