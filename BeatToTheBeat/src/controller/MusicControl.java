package controller;

import java.util.List;
import java.util.Random;

import model.Genre;
import model.Song;
import services.musichandler.Analyzer;
import services.musichandler.MusicFacade;

/**
 * 
 * @author Hampus Dahlin
 *
 */
class MusicControl {
	
	private MusicFacade music;
	
	public MusicControl() {
		music = new MusicFacade();
	}
	
	void analyzeSong(){
		music.analyze();
	}
	
	void play(Song song, boolean isDelay) {
		music.play(song, isDelay);
	}
	
	void playRandom(){
		Random r = new Random();
		int rand = r.nextInt(getSongCount());
		Song song = music.getSongList().get(rand);
		music.play(song,false);
		
	}
	
	void pause() {
		music.pause();
	}
	
	void resume() {
		music.resume();
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
	void loopMusic(boolean inGame){
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
