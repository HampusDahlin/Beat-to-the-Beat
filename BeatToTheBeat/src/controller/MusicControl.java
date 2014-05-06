package controller;

import musichandler.Analyzer;
import musichandler.MusicFacade;

/**
 * 
 * @author Hampus Dahlin
 *
 */
public class MusicControl {
	
	MusicFacade music;
	
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
}
