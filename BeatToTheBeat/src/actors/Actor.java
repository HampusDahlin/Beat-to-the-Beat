package actors;

import java.awt.Point;

/**
 * @author Björn Hedström
 * @revisedBy Pontus "Bondi" Eriksson
 * @revisedBy Malin "Nilhet" Thelin
 * @revisedBy Hampus Dahlin
 *
 */
public abstract class Actor {
	protected int health;
	private Point position;
	private int dmg;
	private final Point SPEED;
	private int range;
	
	Actor(Point speed) {
		this.SPEED = speed;
	}
	
	public void setRange(int newRange){
		range = newRange;
	}
	
	public int getRange(){
		return range;
	}
	
	public Point getSpeed(){
		return SPEED;
	}
	
	public void setDmg(int newDmg){
		dmg = newDmg;
	}
	
	public int getDmg(){
		return dmg;
	}
	
	public void setHealth(int newHealth){
		health = newHealth;
	}
	
	public int getHealth(){
		return health;
	}
	
	/**
	 * Only used for constructor.
	 * Please use setPosition(newPosition, yourIndex) instead.
	 */
	public void setPosition(Point newPosition) {
		position = newPosition;
	}
	
	public void setPosition(Point newPosition, int yourIndex){
		position = newPosition;
	}
	
	public Point getPosition(){
		return position;
	}
	
	public boolean isDead(){
		return getHealth() <= 0;
	}
	
	public void dealDmg(Actor defender){
		defender.setHealth(defender.getHealth() - this.getDmg());
	}

	/**
	public void setInvincible(boolean isTrue) {
		isInvincible = isTrue;
	}*/
	
	
} //end Actor
