package musichandler;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

/**
 * A class for handling a single song.
 * 
 * @author Hampus Dahlin
 *
 */

public class Song extends JFrame{

	private File f;
	private AudioInputStream audioInputStream;
	private Amplitude amplitude;
	private Player audio;
	
	
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

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Test Sound Clip");
		this.setSize(300, 200);
		this.setVisible(true);
		
		f = new File(filename);
		
		
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(f)));
			System.out.println("Penis");
		} catch (FileNotFoundException e){
			System.out.println("Filnamn");
			e.printStackTrace();
			
		} catch (UnsupportedAudioFileException e){
			e.printStackTrace();
			System.out.println("Format");
			
		} catch ( IOException e){
			e.printStackTrace();
			System.out.println("Annat");

		}
		
		amplitude = new Amplitude(audioInputStream);
		
		Clip clip = null;
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(clip != null){
				clip.open(audioInputStream);
			}
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int[][] lvls = amplitude.getamplvls();
		
		for(int i = 0; i < lvls.length; i++){
			for(int j = 0; j < lvls[i].length; j++){
				System.out.println(lvls[i][j]);
			}
		}
		
		clip.start();
	}
	
	
	
	public void play(){
//		if(this.isAlive()){
//			notify();
//		}else{
//			run();
//		}
	}
	
	
	public void pause(){
//		try {
//			this.wait();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
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
