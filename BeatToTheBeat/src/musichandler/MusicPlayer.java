package musichandler;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Player extends Thread{

	private final int BUFFER_SIZE = 128000;
	private AudioFormat audioFormat;
	private SourceDataLine sourceLine;
	private int nBytesRead;
	private AudioInputStream audioStream;
	
	

	public Player(AudioInputStream audioStream){

		this.audioStream = audioStream;
		audioFormat = audioStream.getFormat();

		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		try {
			sourceLine = (SourceDataLine) AudioSystem.getLine(info);
			sourceLine.open(audioFormat);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		nBytesRead = 0;


	}
	
	
	public void start(){
		sourceLine.start();
		this.run();
		
	}
	
	
	public void pause(){
		sourceLine.stop();
	}
	
	
	public void close(){
		this.interrupt();
		sourceLine.drain();
		sourceLine.close();
	}
	
	
	public void run(){
		
		byte[] abData = new byte[BUFFER_SIZE];
		while (nBytesRead != -1) {
			try {
				nBytesRead = audioStream.read(abData, 0, abData.length);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (nBytesRead >= 0) {
				@SuppressWarnings("unused")
				int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
			}
		}
		
	}
}