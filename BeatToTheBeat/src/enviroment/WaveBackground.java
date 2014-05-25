package enviroment;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

import services.HomogeneousFileHandler;

/**
 * 
 * @author Hampus Dahlin
 * @date 2014-05-04
 *
 */
public class WaveBackground implements IBackground{
	private int colorChange; //the index of the currently increasing color value
	private final int LISTSIZE = 20; //the amount of soundframes being displayed
	private final int YPOS[] = {150, 350}; //the positions of the two waveforms along the Y-axis
	private final int WAVEAMP = 100; //the amplitude of the waves
	private int lifetime;
	private ArrayList<WaveForm> waveList;
	private int intensity;

	public WaveBackground(){
		this.intensity = (int)new HomogeneousFileHandler().load("options.conf").get(0);
		waveList = new ArrayList<WaveForm>();
		waveList.add(new WaveForm(new float[2][512], false, new Color(252, 0, 0)));
		lifetime = 0;
		colorChange = 1;
	}

	/**
	 * Updates the graphical representation to represent a new soundwave.
	 * @param soundwave, the float-data of the soundwave.
	 * @param beat, true if there is a beat, false otherwise.
	 */
	public void updateBackground(float[][] soundwave, boolean beat){
		for(WaveForm wave : waveList){
			wave.age();
		}
		lifetime++;
		handleWaveList(soundwave, beat);
		waveList.get(0).setColor(calcColorChange());
		
	}
	
	/**
	 * Adds a new waveform to the list and removes any excess elements down to LISTSIZE.
	 * @param soundwave
	 * @param beat
	 */
	private void handleWaveList(float[][] soundwave, boolean beat){
		waveList.add(0, new WaveForm(soundwave, beat, waveList.get(0).getColor()));
		
		if(waveList.size() > LISTSIZE){
			waveList.remove(LISTSIZE);
		}
	}
	
	/**
	 * Returns the color of the next waveform.
	 */
	private Color calcColorChange(){
		Color c = waveList.get(0).getColor();
		if(waveList.get(0).getBeat()){
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
	private Color invertColor(Color c){
		return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
	}
	
	/**
	 * Determines what to paint depending on the backgrounds intensity.
	 * @param g2d
	 * @param intensity
	 */
	public void paintBackground(Graphics2D g2d){
		switch(intensity){
		case 1:
			drawWaves(g2d);
			break;
		case 2:
			drawSinWaves(g2d);
			break;
		case 3:
			drawWaves(g2d);
			drawSinWaves(g2d);
			break;
		}
	}

	public void paintBackground(Graphics2D g2d, int range){
		paintBackground(g2d);
		paintHitBox(g2d,range);
	}
	
	public void paintHitBox(Graphics2D g2d, int range){
		final int boxWidth = range*2-10;
		final int playerPos = 450;
		g2d.setColor(invertColor(waveList.get(0).getColor()));
		g2d.drawRect(playerPos-boxWidth/2+5, 270, boxWidth, 80);
		g2d.fillRect((playerPos-boxWidth/2)+40,331,boxWidth/10,20);
		g2d.fillRect((playerPos+boxWidth/2)-50,331,boxWidth/10,20);
	}

	/**
	 * Draws a waveform using graphics g.
	 * @param g
	 */
	public void drawWaves(Graphics2D g2d){

		for(WaveForm wave : waveList){
			g2d.setColor(new Color(wave.getColor().getRGB()));//Cloning instead of giving reference
			g2d.setStroke(new BasicStroke(wave.getWidth()));
			for(int i = 0; i < 511; i++) {
				g2d.drawLine(2 * i, (int) (YPOS[0] + wave.getSoundwave()[0][i]*WAVEAMP), 2 * (i+1), (int) (YPOS[0] + wave.getSoundwave()[0][i+1]*WAVEAMP));
				g2d.drawLine(2 * i, (int) (YPOS[1] + wave.getSoundwave()[1][i]*WAVEAMP), 2 * (i+1), (int) (YPOS[1] + wave.getSoundwave()[1][i+1]*WAVEAMP));
			}
		}
	}
	
	/**
	 * Draws a waveform following a sin curve using graphics g.
	 * @param g2d
	 */
	public void drawSinWaves(Graphics2D g2d){
		
		for(WaveForm wave : waveList){
			g2d.setColor(new Color(wave.getColor().getRGB()));//Cloning instead of giving reference
			g2d.setStroke(new BasicStroke(wave.getWidth()));
			for(int i = 0; i < 511; i++) {
				double sinfactor = Math.sin((Math.PI * 2 * (i + lifetime)) / 511);
				g2d.drawLine(2 * i, (int) (YPOS[0] + (50*sinfactor) + wave.getSoundwave()[0][i]*WAVEAMP), 2 * (i+1), (int) (YPOS[0] + (50*sinfactor) + wave.getSoundwave()[0][i+1]*WAVEAMP));
				g2d.drawLine(2 * i, (int) (YPOS[1] + (50*sinfactor) + wave.getSoundwave()[1][i]*WAVEAMP), 2 * (i+1), (int) (YPOS[1] + (50*sinfactor) + wave.getSoundwave()[1][i+1]*WAVEAMP));
			}
		}
	}

	public Color getFirstColor(){
		return(waveList.get(0).getColor());
	}

	public Color getFirstCompCol(){
		return(invertColor(getFirstColor()));
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("beat")){
			updateBackground((float[][])evt.getNewValue(), (boolean)evt.getOldValue());
		}else if (evt.getPropertyName().equals("bgSlider")){
			this.intensity = (int)evt.getNewValue();
		}
	}
}
