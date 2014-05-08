package actors;

import java.awt.Point;

import javax.swing.Icon;

/**
 * @author Björn Hedström
 * @revisedBy Pontus "Bondi" Eriksson
 * @revisedBy Malin "Nilhet" Thelin
 *
 */
public abstract class Actor {
	private final Icon SPRITE;
	private int health;
	private Point position;
	private int dmg;
	private final Point SPEED;
	private int range;
	
	public Actor(Icon sprite, Point speed) {
		this.SPRITE = sprite;
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
	
	public Icon getSprite(){
		return SPRITE;
	}
	
	public void setHealth(int newHealth){
		health = newHealth;
	}
	
	public int getHealth(){
		return health;
	}
	
	public void setPosition(Point newPosition){
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
	
	//attack and death moved to ActorFacade until further notice
	//abstract public void attack();
	//abstract public void death();
	
}
