package actors;

import java.awt.Point;

import javax.swing.Icon;

/**
 * @author Björn Hedström
 * @revisedBy Pontus "Bondi" Eriksson
 *
 */
public abstract class Actor {
	private Icon sprite;
	private int health;
	private Point position;
	private int dmg;
	private Point speed;
	private int range;
	
	public void setRange(int newRange){
		range = newRange;
	}
	
	public int getRange(){
		return range;
	}
	
	public void setSpeed(Point newSpeed){
		speed = newSpeed;
	}
	
	public Point getSpeed(){
		return speed;
	}
	
	public void setDmg(int newDmg){
		dmg = newDmg;
	}
	
	public int getDmg(){
		return dmg;
	}

	public void setSprite(Icon newSprite){
		sprite = newSprite;
	}
	
	public Icon getSprite(){
		return sprite;
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
		return getHealth() < 0;
	}
	
	public void dealDmg(Actor defender){
		defender.setHealth(defender.getHealth() - this.getDmg());
	}
	
	//attack and death moved to ActorFacade until further notice
	//abstract public void attack();
	//abstract public void death();
	
}
