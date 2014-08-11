package services.musichandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.Song;


class SoundHandler implements ActionListener {
	private final int DELAY;
	private MusicPlayer player;
	private Analyzer analyzer;
	private Timer timer;
	
	SoundHandler(Song song, boolean isDelay) {
		//when we play music in the menu we don't want the delay, but when we play it in the game we want it in order to
		//analyze perfectly
		if (isDelay) {
			DELAY = 4000;
		}else{
			DELAY = 0;
		}
		analyzer = new Analyzer(song, song.getGenre().getSense(), DELAY);
		player = new MusicPlayer(song);
	}
	
	void pause() {
		player.pause();
		if (!analyzer.equals(null)) {
			analyzer.pauseAnalyzer();
		}
	}
	
	void resume() {
		if (analyzer != null){
			analyzer.resumeAnalyzer();
			player.start();
		}
	}
	
	void start() {
		if (analyzer !=null) {
			timer = new Timer(DELAY, this);
			timer.setInitialDelay(DELAY);
			timer.setRepeats(false);
			timer.start();
			analyzer.start();
		} else {
			player.start();
		}
	}
	
	void analyze(){
		analyzer.analyze();
	}
	
	public Analyzer getAnalyzer(){
		return this.analyzer;
	}

	public void actionPerformed(ActionEvent e) {
		player.start();
	}
	
	/*
	public void startMusicPlayer() {
		player.start();
	}
	
	public void pauseMusicPlayer() {
		player.stop();
	}
	
	public Analyzer getAnalyzer() {
		return analyzer;
	}
	
	public MusicPlayer getMusicPlayer() {
		return player;
	}
	*/
	
	public float[][] getWave() {
		return analyzer.getWave();
	}
	
	public void setVolume(int vol) {
		player.setVolume(vol);
	}
	
}
