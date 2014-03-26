package musichandler;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;


/**
 * 
 * @author Hampus Dahlin
 * @version 0.0.1
 *
 */
public class Amplitude {

	private int[][] ampLvls;
	private byte[] bytes;
	
	
	/**
	 * 
	 * @param audioInputStream
	 */
	public Amplitude(AudioInputStream audioInputStream){
		
		bytes = new byte[(int) (audioInputStream.getFrameLength()) * (audioInputStream.getFormat().getFrameSize())];

		
		try {
			audioInputStream.read(bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Get amplitude values for each audio channel in an array.
		ampLvls = this.getUnscaledAmplitude(bytes, 1);
		
	}
	
	
	

	/**
	 * 
	 * @param eightBitByteArray
	 * @param nbChannels
	 * @return
	 */
	public int[][] getUnscaledAmplitude(byte[] eightBitByteArray, int nbChannels)
	{
	    int[][] toReturn = new int[nbChannels][eightBitByteArray.length / (2 * nbChannels)];
	    int index = 0;

	    for (int audioByte = 0; audioByte < eightBitByteArray.length;)
	    {
	        for (int channel = 0; channel < nbChannels; channel++)
	        {
	            // Do the byte to sample conversion.
	            int low = (int) eightBitByteArray[audioByte];
	            audioByte++;
	            int high = (int) eightBitByteArray[audioByte];
	            audioByte++;
	            int sample = (high << 8) + (low & 0x00ff);

	            toReturn[channel][index] = sample;
	        }
	        index++;
	    }

	    return toReturn;
	}
	
	
	public int getAmp(int channel, int frame){
		
		return ampLvls[channel][frame];
	}
}
