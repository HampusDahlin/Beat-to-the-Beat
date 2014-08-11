package model.enviroment;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

/**
 * 
 * @author Hampus Dahlin
 *
 */
public class BarBackground implements IBackground {

	private final int LISTSIZE = 20; //the amount of soundframes being displayed
	private final int YPOS = 600; //the positions of the two waveforms along the Y-axis
	private final int WAVEAMP = 200; //the amplitude of the waves
	private ArrayList<WaveForm> waveList;
	
	public BarBackground(){
		waveList = new ArrayList<WaveForm>();
		waveList.add(new WaveForm(new float[2][512], false, new Color(252, 0, 0)));
	}

	/**
	 * Updates the graphical representation to represent a new soundwave.
	 * @param soundwave, the float-data of the soundwave.
	 * @param beat, true if there is a beat, false otherwise.
	 */
	private void updateBackground(float[][] soundwave, boolean beat){
		handleWaveList(soundwave, beat);
		waveList.get(0).setColor(ColorHandler.calcColorChange(beat));
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
	 * Draws a bar representation of a soundwave using graphics g.
	 * @param g
	 */
	private void drawWaves(Graphics2D g2d){

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
		return(ColorHandler.invertColor(getFirstColor()));
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
