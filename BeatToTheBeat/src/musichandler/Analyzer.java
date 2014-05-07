package musichandler;


import java.io.FileInputStream;
import java.io.InputStream;

import support.GameOverException;
import ddf.minim.*;
import ddf.minim.analysis.BeatDetect;

public class Analyzer {
	private Minim minim;
	private AudioPlayer player;
	private AudioInput input; //what for?
	private BeatDetect detective;
	private final int BUFFERSIZE;
	private Genre genre;
	
	public Analyzer(Song song, int sensitivity) {
		genre = song.getGenre();
		BUFFERSIZE = 512;
		minim = new Minim(this);
		player = minim.loadFile(song.getFilename(), BUFFERSIZE);// this loads song from the data folder	
		input = minim.getLineIn();
		detective = new BeatDetect();
		detective.setSensitivity(genre.getSense());
	}
	
	public void start() {
		player.mute();
		player.play();
	}
	
	public InputStream createInput(String fileName) {
		System.out.println("creating inputStream from file: " + fileName);
		InputStream is;
		try {
			is = new FileInputStream(fileName);
			System.out.println("Success!");
			return is;
		} catch (Exception e) {
			System.out.println("Failed! Exception: " + e);
			is = null;
		}
		return is;
	}
	
	public void pauseAnalyzer() {
		player.pause();
	}
	
	/**
	 * Returns if beat or not, throws GameOverException with string "win" if song is over.
	 */
	public boolean isBeat() {
		if (!player.isPlaying()) {
			throw new GameOverException("win");
		}
		// getting the buffer for current time in song
		detective.detect(player.mix);
		return detective.isRange(genre.getLow(), genre.getHigh(), genre.getThreshold());
	}
	
	public float[][] getWave() {
		float[][] temp = new float[2][BUFFERSIZE];
		temp[0] = player.left.toArray();
		temp[1] = player.right.toArray();
		return temp;
	}
	
}