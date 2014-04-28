package musichandler;

import support.NoAnalyzerException;

public class SoundHandler {
	private Song song;
	private MusicPlayer player;
	private Analyzer analyzer;
	
	public SoundHandler(Song song, boolean toAnalyze, int sensitivity) {
		this.song = song;
		if(toAnalyze) {
			analyzer = new Analyzer(song, sensitivity);
		}
		player = new MusicPlayer(song);
	}
	
	public Song getSong(){
		return song;
	}
	
	public void pause() {
		pauseMusicPlayer();
		pauseAnalyzer();
	}
	
	public void start() {
		player.start();
		try {
			startAnalyzer();
		} catch (NoAnalyzerException ex) {
			System.out.println(ex);
		}
	}
	
	public void startAnalyzer() throws NoAnalyzerException {
		if(analyzer != null) {
			analyzer.start();
		} else {
			throw new NoAnalyzerException("Analyzer missing"); 
		}
	}
	
	public void startMusicPlayer() {
		player.start();
	}
	
	public void pauseAnalyzer() {
		try {
			analyzer.wait();
		}catch (InterruptedException ex) {
			System.out.println(ex);
		}
	}
	
	public void pauseMusicPlayer() {
		player.stop();
	}
	
}
