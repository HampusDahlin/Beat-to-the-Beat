package musichandler;

import support.NoAnalyzerException;

public class SoundHandler{
	private Song song;
	private MusicPlayer player;
	private Analyzer analyzer;

	
	public SoundHandler(Song song, boolean toAnalyze) {
		this.song = song;
		if(toAnalyze) {
			analyzer = new Analyzer(song, song.getGenre().getSense());
		}
		player = new MusicPlayer(song);
	}
	
	public Song getSong(){
		return song;
	}
	
	public void pause() {
		pauseMusicPlayer();
		try {
			pauseAnalyzer();
		} catch (NoAnalyzerException ex) {
			System.out.println(ex);
		}
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
		if (analyzer != null) {
			analyzer.start();
		} else {
			throw new NoAnalyzerException("Analyzer missing"); 
		}
	}
	
	public void startMusicPlayer() {
		player.start();
	}
	
	public void pauseAnalyzer() throws NoAnalyzerException {
		if (analyzer != null) {	
			analyzer.stopAnalyzer();
		} else {
			throw new NoAnalyzerException("Analyzer missing");
		}
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
}
