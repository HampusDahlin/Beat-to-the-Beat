package musichandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class SoundHandler implements ActionListener {
	private final int DELAY;
	private MusicPlayer player;
	private Analyzer analyzer;
	private Timer timer;
	
	public SoundHandler(Song song, boolean toAnalyze) {
		DELAY = 4500;
		if (toAnalyze) {
			analyzer = new Analyzer(song, song.getGenre().getSense());
		}
		player = new MusicPlayer(song);
	}
	
	public void pause() {
		player.stop();
		if (!analyzer.equals(null)) {
			analyzer.pauseAnalyzer();
		}
	}
	
	public void start() {
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
	
	public void analyze(){
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
	
	
}
