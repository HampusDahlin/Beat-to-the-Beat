package services.musichandler;

import model.music.Song;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class MusicPlayer extends Analyzable implements IMusicPlayer { //needs to be public for minim
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
	public void stop() {
		player.pause();
		player.rewind();
	}
	
	public void pause() {
		player.pause();
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
	
}
