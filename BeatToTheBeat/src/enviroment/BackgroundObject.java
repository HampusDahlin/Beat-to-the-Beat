package enviroment;

import java.awt.*;
/**
 * 
 * @author Hampus Dahlin
 * @version 0.0.2
 * 
 *
 */
class BackgroundObject {
	
	private Image sprite;
	private Point position;
	
	/**
	 * Constructor setting sprite and position.
	 * @param sprite
	 * @param position
	 */
	BackgroundObject(Image sprite,Point position){
		setSprite(sprite);
		setPosition(position);
	}
	
	/**
	 * 
	 * @param sprite
	 */
	public void setSprite(Image sprite){
		this.sprite= sprite;
	}
	
	/**
	 * 
	 * @return sprite
	 */
	public Image getSprite(){
		return sprite;
	}
	
	/**
	 * 
	 * @param position
	 */
	public void setPosition(Point position){
		this.position = position;
	}
	
	/**
	 * 
	 * @return position
	 */
	public Point getPosition(){
		return position;
	}

}
