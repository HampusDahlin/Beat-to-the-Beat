package musichandler;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.io.FileInputStream;
import java.io.InputStream;

import support.GameOverException;
import ddf.minim.*;
import ddf.minim.analysis.BeatDetect;


/**
 * 
 * @author Björn Hedström
 * @revisedBy Hampus Dahlin
 * 
 * @version 0.0.2
 *
 */
public class Analyzer {
	private Minim minim;
	private AudioPlayer player;
	private BeatDetect detective;
	private final int BUFFERSIZE;
	private Genre genre;
	PropertyChangeSupport pcs;
	
	/**
	 * 
	 * @param song the song to be analyzed
	 * @param sensitivity how sensitive the Analyzer will be
	 */
	public Analyzer(Song song, int sensitivity) {
		pcs = new PropertyChangeSupport(this);
		genre = song.getGenre();
		BUFFERSIZE = 512;
		minim = new Minim(this);
		player = minim.loadFile(song.getFilename(), BUFFERSIZE);// this loads song from the data folder	
		detective = new BeatDetect();
		detective.setSensitivity(genre.getSense());
	}
	
	/**
	 * Mutes any music playing from the analyzer and then starts analyzing the song.
	 */
	public void start() {
		player.mute();
		player.play();
	}
	
	/**
	 * Creates an InputStream using the supplied filename fileName.
	 * @param fileName the files filename
	 * @return an InputStream linked to the filename fileName.
	 */
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
	
	/**
	 * Pauses the analyzer.
	 */
	public void pauseAnalyzer() {
		player.pause();
	}

	/**
	 * Fires a {@link PropertyChangeEvent} with sound data, also throws GameOverException with string "win" if song is over.
	 */
	public void analyze() {
		if (isGameOver()) {
			throw new GameOverException("win");
		} else {
			if (isBeat()) {
				pcs.firePropertyChange("Beat Detected", true, this.getWave());
			} else {
				pcs.firePropertyChange("No Beat Detected", false, this.getWave());
			}
		}
	}
	
	/**
	 * 
	 * @return true if there currently is a beat, false otherwise
	 */
	public boolean isBeat() {
		// getting the buffer for current time in song
		detective.detect(player.mix);
		return detective.isRange(genre.getLow(), genre.getHigh(), genre.getThreshold());
	}
	
	/**
	 * 
	 * @return true if the game is over, false otherwise
	 */
	public boolean isGameOver(){
		return (!player.isPlaying());
	}
	
	public PropertyChangeSupport getPcs(){
		return this.pcs;
	}
	
	public float[][] getWave() {
		float[][] temp = new float[2][BUFFERSIZE];
		temp[0] = player.left.toArray();
		temp[1] = player.right.toArray();
		return temp;
	}
	
}