package enviroment;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * A class for handling a JPanel showing a waveform.
 * @author Hampus Dahlin
 *
 */
public class WaveBackground extends ABackground{

	private int colorChange; //the index of the currently increasing color value
	private final int LISTSIZE = 20; //the amount of soundframes being displayed
	private final int YPOS[] = {150, 350}; //the positions of the two waveforms along the Y-axis
	private final int WAVEAMP = 100; //the amplitude of the waves
	ArrayList<WaveForm> waveList;

	/**
	 * 
	 */
	public WaveBackground(){
		waveList = new ArrayList<WaveForm>();
		waveList.add(new WaveForm(new float[2][512], false, new Color(252, 0, 0)));
		colorChange = 1;
	}

	/**
	 * Updates the graphical representation to represent a new soundwave.
	 * @param soundwave, the float-data of the soundwave.
	 * @param beat, true if there is a beat, false otherwise.
	 */
	public void updateBackground(float[][] soundwave, boolean beat) {
		for(WaveForm wave : waveList){
			wave.age();
		}
		
		waveList.add(0, new WaveForm(soundwave, beat, waveList.get(0).getColor()));
		if(waveList.size() > LISTSIZE){
			waveList.remove(LISTSIZE);
		}

		//Changes the color of the soundwave
		if(waveList.get(0).getBeat()){
			for(int i = 0; i < 150; i++){
				waveList.get(0).setColor(gradientChange(waveList.get(0).getColor()));
			}
		}else{
			waveList.get(0).setColor(gradientChange(waveList.get(0).getColor()));
			if(waveList.get(0).getWidth() > 1){
				waveList.get(0).setWidth(waveList.get(0).getWidth() - 1);
			}
		}

		this.setBackground(invertColor(waveList.get(0).getColor()));


		repaint();
	}

	/**
	 * 
	 * @param c
	 * @return complementary color of c
	 */
	private Color invertColor(Color c){
		return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D)g; 
		drawWaves(g2d);
	}

	/**
	 * Draws a waveform using graphics g.
	 * @param g
	 */
	private void drawWaves(Graphics2D g2d){

		for(WaveForm wave : waveList){
			g2d.setColor(new Color(wave.getColor().getRGB()));//Cloning instead of giving reference
			g2d.setStroke(new BasicStroke(wave.getWidth()));
			for(int i = 0; i < 511; i++) {
				g2d.drawLine(i, (int) (YPOS[0] + wave.getSoundwave()[0][i]*WAVEAMP), i+1, (int) (YPOS[0] + wave.getSoundwave()[0][i+1]*WAVEAMP));
				g2d.drawLine(i, (int) (YPOS[1] + wave.getSoundwave()[1][i]*WAVEAMP), i+1, (int) (YPOS[1] + wave.getSoundwave()[1][i+1]*WAVEAMP));
			}
		}
	}

	/**
	 * Simulates a gradient colorchange.
	 * @param prevColor
	 * @return nextColor
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
