package actors;

import java.awt.Image;
import java.awt.Point;

import controller.CharacterControl;

/**
 * @author Björn Hedström
 *
 */
public abstract class Actor {
	private Image sprite;
	private int health;
	private Point position;
	private int dmg;
	private int speed;
	
	public void setSpeed(int newSpeed){
		speed = newSpeed;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public void setDmg(int newDmg){
		dmg = newDmg;
	}
	
	public int getDmg(){
		return dmg;
	}
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
		return getHealth() < 0;
	}
	
	public void dealDmg(Actor defender){
		defender.setHealth(defender.getHealth() - this.getDmg());
	}
	
	abstract public void attack();
	abstract public void death();
	
}
