package enviroment;

import java.awt.*;
/**
 * 
 * @author Hampus Dahlin
 * @version 0.0.1
 * 
 *
 */
public class BackgroundObject {
	
	private Image sprite;
	private Point position;
	
	public BackgroundObject(Image sprite,Point position){
		setSprite(sprite);
		setPosition(position);
	}
	
	public void setSprite(Image sprite){
		this.sprite= sprite;
	}
	
	public Image getSprite(){
		return sprite;
	}
	
	public void setPosition(Point position){
		this.position = position;
	}
	
	public Point getPosition(){
		return position;
	}

}
