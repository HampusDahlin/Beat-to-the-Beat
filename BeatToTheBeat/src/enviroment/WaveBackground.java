package enviroment;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A class for handling a JPanel showing a waveform.
 * @author Hampus Dahlin
 *
 */
public class WaveBackground extends ABackground{

	Graphics g;
	int colorChange; //the index of the currently increasing color value
	
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
			for(int i = 0; i < 255; i++){
				g.setColor(gradientChange(g.getColor()));
			}
		}else{
			g.setColor(gradientChange(g.getColor()));
		}
		
		//Draws the soundwave.
		for(int i = 0; i < 511; i++) {
			g.clearRect(0, 0, 914, 600);
			g.drawLine(i, (int) (50 + soundwave[0][i]*50), i+1, (int) (50 + soundwave[0][i+1]*50));
			g.drawLine(i, (int) (150 + soundwave[1][i]*50), i+1, (int) (150 + soundwave[1][i+1]*50));
		}
	}
	
	public Color gradientChange(Color prevColor){
		Color nextColor;
		float[] colorRGB = prevColor.getRGBColorComponents(null);
		
		if(colorRGB[colorChange] == 255){
			colorChange = (colorChange + 1) % 3;
		}
		
		colorRGB[colorChange - 1]--;
		colorRGB[colorChange]++;
		
		nextColor = new Color(colorRGB[0], colorRGB[1], colorRGB[2]);
		return nextColor;
	}
}
