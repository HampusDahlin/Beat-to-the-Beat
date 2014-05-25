package enviroment;

import java.awt.Color;

/**
 * A class for handling colorchanges in backgrounds.
 * @author Hampus Dahlin
 *
 */
public class ColorHandler {

	int colorChange;
	Color c;
	
	public ColorHandler(){
		c = new Color(252,0,0);
		colorChange = 1;
	}
	
	/**
	 * Returns the color of the next waveform.
	 */
	public Color calcColorChange(boolean beat){
		if(beat){
			for(int i = 0; i < 150; i++){
				c = gradientChange(c);
			}
		}else{
			c = gradientChange(c);
		}
		return c;
	}
	
	
	/**
	 * Simulates a gradient colorchange.
	 * @param prevColor
	 * @return nextColor
	 */
	private Color gradientChange(Color prevColor){
		Color nextColor;
	
		int[] colorRGB = {prevColor.getRed(), prevColor.getGreen(), prevColor.getBlue()};
	
		if(colorRGB[((colorChange + 2) % 3)] == 0){
			colorChange = ((colorChange + 1) % 3);
		}
	
		colorRGB[(colorChange + 2) % 3]--;
		colorRGB[colorChange]++;
	
		nextColor = new Color(colorRGB[0], colorRGB[1], colorRGB[2]);
		return nextColor;
	}

	/**
	 * 
	 * @param c
	 * @return complementary color of c
	 */
	public Color invertColor(Color c){
		return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
	}

}
