package enviroment;

import java.awt.Image;
import java.util.List;

/**
 * 
 * @author Hampus Dahlin
 * @version 0.0.1 
 *
 */
public class BackgroundLayer {
	
	private int speed;
	private Image sprite;
	private List<BackgroundObject> objects;
	
	public BackgroundLayer(int speed,Image sprite){
		this.speed = speed;
		this.sprite = sprite;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	public Image getSprite(){
		return sprite;
	}
	
	public void addBackgroundObject(BackgroundObject object){
		objects.add(object);
	}
	
	public void removeBackgroundObject(int bgIndex){
		objects.remove(bgIndex);
	}

}
