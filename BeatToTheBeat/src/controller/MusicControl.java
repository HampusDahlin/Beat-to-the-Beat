package controller;

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
	 * @return
	 */
	public boolean hasBeat(){
		return music.hasBeat();
	}
}
