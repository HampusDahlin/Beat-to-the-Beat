package musichandler;

public class MusicPlayer {
	private Song song;
	private java.applet.AudioClip  clip;

	public MusicPlayer(Song song) {
		this.song = song;
		try {
			clip =
			java.applet.Applet.newAudioClip(
			new java.net.URL("file:songs\\" + song.getFilename()));
			
		} catch (java.net.MalformedURLException murle) {
			System.out.println(murle);
		}
	}
	
	public Song getSong() {
		return song;
	}
	
	public void stop() {
		clip.stop();
	}
	
	public void start() {
		clip.play();
	}
}
