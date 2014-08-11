package services.musichandler;

import model.music.Song;

/**
 * @author Pontus "Bondi" Eriksson
 */
public interface IMusicPlayer {
	
	public Song getSong();
	
	/**
	 * Stops playing the current song completely.
	 */
	public void stop();
	
	/**
	 * Pauses song.
	 */
	public void pause();
	
	/**
	 * Starts selected song.
	 */
	public void start();
	
	/**
	 * Sets volume in %, I.E. with a float 0-1.
	 * @param vol Volume, needs to be >=0 && <=1.
	 */
	public void setVolume(float vol);
	
	/**
	 * Sets volume in %, I.E. with an int 0-100.
	 * @param vol Volume, needs to be >=0 && <=100.
	 */
	public void setVolume(int vol);
	
}
