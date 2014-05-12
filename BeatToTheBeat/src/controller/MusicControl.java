package controller;

import java.util.List;

import musichandler.Analyzer;
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
	
	public void analyzeSong(){
		music.analyze();
	}
	
	public void setSong(int index) {
		
	}
	
	public void play() {
		music.play();
	}
	
	public void pause() {
		music.pause();
	}
	
	public Analyzer getAnalyzer(){
		return music.getAnalyzer();
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
	
	/**
	 * How many songs are available.
	 * @return songCount.
	 */
	public int getSongCount() {
		return music.getSongCount();
	}
}
