package musichandler;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Song {

	private File f;
	private AudioInputStream audioInputStream;
	private Amplitude amplitude;
	
	
	public Song() {
		throw new IllegalArgumentException("A song has to be linked to a file, please reffer to any of the "
				+ "following constructors: Song(String)");
	}
	
	
	/**
	 * 
	 * @param filename
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
		
		amplitude = new Amplitude(audioInputStream);
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
