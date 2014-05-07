package musichandler;

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
		player = minim.loadFile(song.getFilename(), BUFFERSIZE);// this loads song from the data folder
	}
	
	public Song getSong() {
		return song;
	}
	
	public void stop() {
		player.pause();
	}
	
	public void start() {
		player.play();
	}
}
