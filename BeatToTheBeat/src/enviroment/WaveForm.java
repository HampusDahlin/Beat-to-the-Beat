package enviroment;

import java.awt.Color;

public class WaveForm {
	private final float[][] soundwave;
	private final boolean beat;
	private Color c;
	private int width;
	
	public WaveForm(float[][] soundwave, boolean beat, Color c){
		this.soundwave = soundwave;
		this.beat = beat;
		this.c = c;
		width = 5;
	}
	
	public void age(){
		width = width > 1 ? width - 1 : 0;
		setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), 0));
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public void setColor(Color c){
		this.c = c;
	}
	
	public Color getColor(){
		return this.c;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public float[][] getSoundwave(){
		return this.soundwave;
	}
	
	public boolean getBeat(){
		return this.beat;
	}
}
