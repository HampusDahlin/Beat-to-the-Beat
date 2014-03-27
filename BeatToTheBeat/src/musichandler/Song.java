package musichandler;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 * A class for handling a single song.
 * 
 * @author Hampus Dahlin
 *
 */

public class Song {

	private File f;
	private AudioInputStream audioInputStream;
	private Amplitude amplitude;
	private Player player;
	
	
	/**
	 * Empty constructor throwing an exception.
	 */
	public Song() throws IllegalArgumentException{
		throw new IllegalArgumentException("A song has to be linked to a file, please reffer to any of the "
				+ "following constructors: Song(String)");
	}
	
	
	/**
	 * 
	 * @param filename the name of the file containing the song.
	 */
	public Song(String filename){
		
		f = new File(filename);
		
		
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(f)));
			
		} catch (FileNotFoundException e){
			e.printStackTrace();
			
		} catch (UnsupportedAudioFileException e){
			e.printStackTrace();
			
		} catch ( IOException e){
			e.printStackTrace();

		}
		
		player = new Player(audioInputStream);
		
		amplitude = new Amplitude(audioInputStream);
	}
	
	
	
	public void play(){
		player.start();
	}
	
	
	public void pause(){
		player.pause();
	}
	
	
	/**
	 * Gets the amplitude of the supplied frame in the supplied channel
	 * @param channel the sound channel to get amplitude from
	 * @param frame the frame in the supplied channel to get amplitude from
	 * @return
	 */
	public int getAmpLvl(int channel, int frame){
		
		return amplitude.getAmp(channel, frame);
	}
	
	
	
	/**
	 * Gets the amplitude of the supplied frame in default channel 0
	 * @param frame
	 * @return
	 */
	public int getAmpLvl(int frame){
		
		return getAmpLvl(0, frame);
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String getFileName(){
		return f.getName();
	}
	
}
