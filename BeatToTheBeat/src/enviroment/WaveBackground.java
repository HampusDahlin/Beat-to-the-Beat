package enviroment;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * A class for handling a JPanel showing a waveform.
 * @author Hampus Dahlin
 *
 */
public class WaveBackground extends ABackground{

	int colorChange; //the index of the currently increasing color value
	float[][] soundwave;
	boolean beat;
	Color c;
	
	/**
	 * 
	 */
	public WaveBackground(){
		soundwave = new float[2][512];
		beat = false;
		colorChange = 1;
		c = new Color(252, 0, 0);
	}
	
	/**
	 * Updates the graphical representation to represent a new soundwave.
	 * @param soundwave, the float-data of the soundwave.
	 * @param beat, true if there is a beat, false otherwise.
	 */
	public void updateBackground(float[][] soundwave, boolean beat) {
		this.soundwave = soundwave;
		this.beat = beat;
		this.setBackground(invertColor(c));
		repaint();
	}
	
	public Color invertColor(Color c){
		return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g; 
		
		
		//Changes the color of the soundwave
		if(beat){
			for(int i = 0; i < 150; i++){
				c = gradientChange(c);
				g2d.setStroke(new BasicStroke(3));
		}
		}else{
			c = gradientChange(c);
			if(((BasicStroke)(g2d.getStroke())).getLineWidth() > 1){
				g2d.setStroke(new BasicStroke(((BasicStroke)(g2d.getStroke())).getLineWidth() - 1));
			}
		}
		
		g.setColor(c);
		
		drawWave(g);
	}
	
	private void drawWave(Graphics g){
		for(int i = 0; i < 511; i++) {
			//g.clearRect(0, 0, 914, 600);
			g.drawLine(i, (int) (50 + soundwave[0][i]*50), i+1, (int) (50 + soundwave[0][i+1]*50));
			g.drawLine(i, (int) (150 + soundwave[1][i]*50), i+1, (int) (150 + soundwave[1][i+1]*50));
		}
	}
	
	/**
	 * 
	 * @param prevColor
	 * @return
	 */
	public Color gradientChange(Color prevColor){
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
}
