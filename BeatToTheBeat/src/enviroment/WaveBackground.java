package enviroment;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

/**
 * 
 * @author Hampus Dahlin
 * @date 2014-05-04
 *
 */
public class WaveBackground implements IBackground{
	private final int LISTSIZE = 20; //the amount of soundframes being displayed
	protected final int YPOS[] = {150, 350}; //the positions of the two waveforms along the Y-axis
	protected final int WAVEAMP = 100; //the amplitude of the waves
	protected ArrayList<WaveForm> waveList;
	ColorHandler ch;

	public WaveBackground(){
		waveList = new ArrayList<WaveForm>();
		waveList.add(new WaveForm(new float[2][512], false, new Color(252, 0, 0)));
		ch = new ColorHandler();
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
		handleWaveList(soundwave, beat);
		waveList.get(0).setColor(ch.calcColorChange(waveList.get(0).getBeat()));
		
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
	
	public void paintBackground(Graphics2D g2d, int range){
		paintBackground(g2d);
		paintHitBox(g2d,range);
	}
	
	private void paintHitBox(Graphics2D g2d, int range){
		final int boxWidth = range*2-10;
		final int playerPos = 450;
		g2d.setColor(ch.invertColor(waveList.get(0).getColor()));
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

	public Color getFirstColor(){
		return(waveList.get(0).getColor());
	}

	public Color getFirstCompCol(){
		return(ch.invertColor(getFirstColor()));
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
}
