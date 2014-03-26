package actors;

import java.awt.Image;
import java.awt.Point;

/**
 * @author Björn Hedström
 *
 */
public abstract class Actor {
	private Image sprite;
	private int health;
	private Point position;
	
	/**
	 * @param newSprite
	 */
	public void setSprite(Image newSprite){
		sprite = newSprite;
	}
	
	/**
	 * @return sprite
	 */
	public Image getSprite(){
		return sprite;
	}
	
	/**
	 * @param newHealth
	 */
	public void setHealth(int newHealth){
		health = newHealth;
	}
	
	/**
	 * @return health
	 */
	public int getHealth(){
		return health;
	}
	
	/**
	 * @param newPosition
	 */
	public void setPosition(Point newPosition){
		position = newPosition;
	}
	
	/**
	 * @return
	 */
	public Point getPosition(){
		return position;
	}
	
	/**
	 * @return true/false
	 */
	public boolean isDead(){
		return getHealth()<0;
	}
	
}
