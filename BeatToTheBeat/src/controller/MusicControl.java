package controller;

import java.util.List;
import java.util.Random;

import musichandler.Analyzer;
import musichandler.Genre;
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
	
	public void play(Song song, boolean analyze) {
		music.play(song, analyze);
	}
	
	public void play(boolean analyze) {
		music.playCurrent(analyze);
	}
	
	public void playRandom(){
		Random r = new Random();
		int rand = r.nextInt(getSongCount());
		Song song = music.getSongList().get(rand);
		music.play(song,false);
		
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
	
	
	//Code to loop music in menu
	public void loopMusic(boolean inGame){
		while(!inGame && !isSongPlaying()){
			playRandom();
		}
	}
	
	public boolean isSongPlaying(){
		return music.getAnalyzer().isSongPlaying();
	}
	
	public Genre[] getGenres() {
		return music.getGenres();
	}
	
	public void setVolume(int vol) {
		music.setVolume(vol);
	}
}
