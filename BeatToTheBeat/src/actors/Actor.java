package actors;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.ImageIcon;

/**
 * @author Björn Hedström
 * @revisedBy Pontus "Bondi" Eriksson
 * @revisedBy Malin "Nilhet" Thelin
 * @revisedBy Hampus Dahlin
 *
 */
public abstract class Actor {
	private final ImageIcon SPRITE;
	private int health;
	private Point position;
	private int dmg;
	private final Point SPEED;
	private int range;

	protected PropertyChangeSupport pcs;
	
	Actor(ImageIcon sprite, Point speed) {
		this.SPRITE = sprite;
		this.SPEED = speed;
		pcs = new PropertyChangeSupport(this);
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
	
	public ImageIcon getSprite(){
		return SPRITE;
	}
	
	public void setHealth(int newHealth){
		pcs.firePropertyChange("hp", health, newHealth);
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
		//är det bra att ha en pc här? eftersom actorcontrol använder sig av denna för att flytta på skiten..
		pcs.fireIndexedPropertyChange("move", yourIndex, position, newPosition);
		position = newPosition;
	}
	
	public Point getPosition(){
		return position;
	}
	
	public boolean isDead(){
		pcs.firePropertyChange("death", null, this);
		return getHealth() <= 0;
	}
	
	public void dealDmg(Actor defender){
		defender.setHealth(defender.getHealth() - this.getDmg());
	}
	
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public abstract void setInvincible();
	
} //end Actor
