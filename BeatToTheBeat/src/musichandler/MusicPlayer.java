package musichandler;

import java.io.FileInputStream;
import java.io.InputStream;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class MusicPlayer {
	private Song song;
	private AudioPlayer player;
	private Minim minim;
	private final int BUFFERSIZE = 512;

	public MusicPlayer(Song song) {
		this.song = song;
		minim = new Minim(this);
		//System.out.println(song.getFilename());
		player = minim.loadFile(song.getFilename(), BUFFERSIZE);// this loads song from the data folder
	}
	
	public Song getSong() {
		return song;
	}
	
	public void stop() {
		player.pause();
		player.rewind();
	}
	
	public void start() {
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
	
	public String sketchPath(String fileName) {
		return "sketchPath: " + fileName;
	}
	
	public InputStream createInput(String fileName) {
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
