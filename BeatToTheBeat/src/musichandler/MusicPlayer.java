package musichandler;

import java.io.FileInputStream;
import java.io.InputStream;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class MusicPlayer { //needs to be public for minim
	private Song song;
	private AudioPlayer player;
	private Minim minim;
	private final int BUFFERSIZE = 512;

	MusicPlayer(Song song) {
		this.song = song;
		minim = new Minim(this);
		player = minim.loadFile(song.getFilename(), BUFFERSIZE);// this loads song from the data folder
	}
	
	public Song getSong() {
		return song;
	}
	
	@SuppressWarnings("ucd")
	void stop() {
		player.pause();
		player.rewind();
	}
	
	void pause() {
		player.pause();
	}
	
	void start() {
		player.play();
	}
	
	public void setVolume(float vol) {
		player.setVolume(vol);
		player.setGain(vol);
	}
	
	/**
	 * Sets volume in %, I.E. with an int 0-100.
	 * @param vol Volume, needs to be >=0 && <=100.
	 */
	public void setVolume(int vol) {
		setVolume((float)vol/100);
	}
	
	//needed for minim
	public String sketchPath(String fileName) { // NO_UCD (unused code)
		return "sketchPath: " + fileName;
	}
	
	//needed for minim
	public InputStream createInput(String fileName) { // NO_UCD (unused code)
		//System.out.println("creating inputStream from file: " + fileName);
		InputStream is;
		try {
			is = new FileInputStream(fileName);
			//System.out.println("Success!");
			return is;
		} catch (Exception e) {
			//System.out.println("Failed! Exception: " + e);
			is = null;
		}
		return is;
	}
	
	
}
