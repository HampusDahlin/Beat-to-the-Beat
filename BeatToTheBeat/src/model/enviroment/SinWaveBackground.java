package model.enviroment;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class SinWaveBackground extends WaveBackground{
	private int lifetime;
	
	public SinWaveBackground(){
		super();
		lifetime = 0;
	}
	
	@Override
	public void updateBackground(float[][] soundwave, boolean beat){
		super.updateBackground(soundwave, beat);
		lifetime++;
	}
	
	@Override
	public void drawWaves(Graphics2D g2d){

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
	
}
