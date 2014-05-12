package enviroment;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * A class for handling a JPanel showing a waveform.
 * @author Hampus Dahlin
 *
 */
public class WaveBackground extends JPanel implements IBackground{

	Graphics g;
	
	/**
	 * 
	 */
	public WaveBackground(){
	}
	
	/**
	 * Updates the graphical representation to represent a new soundwave.
	 * @param soundwave, the float-data of the soundwave.
	 * @param beat, true if there is a beat, false otherwise.
	 */
	public void updateBackground(float[][] soundwave, boolean beat) {
		
		//If there is a beat, switch the color of the graphical representation of the soundwave.
		if(beat){
			g.setColor((g.getColor() == Color.CYAN) ? Color.RED : Color.CYAN);
		}
		
		//Draws the soundwave.
		for(int i = 0; i < 511; i++) {
			g.drawLine(i, (int) (50 + soundwave[0][i]*50), i+1, (int) (50 + soundwave[0][i+1]*50));
			g.drawLine(i, (int) (150 + soundwave[1][i]*50), i+1, (int) (150 + soundwave[1][i+1]*50));
		}
	}
}
