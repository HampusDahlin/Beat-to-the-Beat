package enviroment;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class BarBackground implements IBackground {

	private int colorChange; //the index of the currently increasing color value
	protected final int LISTSIZE = 20; //the amount of soundframes being displayed
	protected final int YPOS = 600; //the positions of the two waveforms along the Y-axis
	protected final int WAVEAMP = 200; //the amplitude of the waves
	protected ArrayList<WaveForm> waveList;

	public BarBackground(){
		waveList = new ArrayList<WaveForm>();
		waveList.add(new WaveForm(new float[2][512], false, new Color(252, 0, 0)));
		colorChange = 1;
	}

	/**
	 * Updates the graphical representation to represent a new soundwave.
	 * @param soundwave, the float-data of the soundwave.
	 * @param beat, true if there is a beat, false otherwise.
	 */
	public void updateBackground(float[][] soundwave, boolean beat){
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
	 * Draws a bar representation of a soundwave using graphics g.
	 * @param g
	 */
	public void drawWaves(Graphics2D g2d){

		for(WaveForm wave : waveList){
			g2d.setColor(new Color(wave.getColor().getRGB()));//Cloning instead of giving reference
			g2d.setStroke(new BasicStroke(wave.getWidth()));
			for(int i = 0; i < 511; i += 5) {
				g2d.drawLine(3*i, (int) (YPOS), 3*i, (int) Math.abs(YPOS + wave.getSoundwave()[0][i]*WAVEAMP));
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
		}
	}

	@Override
	public void paintBackground(Graphics2D g2d) {
		drawWaves(g2d);

	}

	@Override
	public void paintBackground(Graphics2D g, int range) {
		paintBackground(g);

	}

}
