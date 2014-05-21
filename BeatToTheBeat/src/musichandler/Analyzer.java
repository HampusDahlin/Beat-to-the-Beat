package musichandler;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.BeatDetect;


/**
 * 
 * @author Bj�rn Hedstr�m
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
	private PropertyChangeSupport pcs;
	private List<float[][]> waveList;
	private final int DELAY;
	
	
	/**
	 * 
	 * @param song the song to be analyzed
	 * @param sensitivity how sensitive the Analyzer will be
	 */
	Analyzer(Song song, int sensitivity) {
		DELAY = 4000; //delay in ms
		pcs = new PropertyChangeSupport(this);
		genre = song.getGenre();
		BUFFERSIZE = 512;
		minim = new Minim(this);
		player = minim.loadFile(song.getFilename(), BUFFERSIZE);// this loads song from the data folder	
		detective = new BeatDetect(BUFFERSIZE, player.sampleRate());
		detective.setSensitivity(genre.getSense());
		this.waveList = new ArrayList<float[][]>();
		for (int i = 0; i < DELAY/10; i++) {
			waveList.add(new float[2][BUFFERSIZE]);
		}
	}
	
	/**
	 * Mutes any music playing from the analyzer and then starts analyzing the song.
	 */
	void start() {
		player.mute();
		player.play();	
	}
	
	/**
	 * Pauses the analyzer.
	 */
	void pauseAnalyzer() {
		player.pause();
	}

	/**
	 * Fires a {@link PropertyChangeEvent} with sound data, also throws GameOverException with string "win" if song is over.
	 */
	void analyze() {
		//test
		if (isGameOver()) {
			pcs.firePropertyChange("songEnd", false, true);
		} else {
			pcs.firePropertyChange("beat", isBeat(), this.getWave());
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
	
	//test
	public boolean isSongPlaying(){
		return player.isPlaying();
	}
	
	public PropertyChangeSupport getPcs(){
		return this.pcs;
	}
	
	public float[][] getWave() {
		float[][] temp = new float[2][BUFFERSIZE];
		temp[0] = player.left.toArray();
		temp[1] = player.right.toArray();
		waveList.add(temp);
		return waveList.remove(0);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
}