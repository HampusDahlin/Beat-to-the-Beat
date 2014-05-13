package actors;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.Icon;

/**
 * @author Björn Hedström
 * @revisedBy Pontus "Bondi" Eriksson
 * @revisedBy Malin "Nilhet" Thelin
 * @revisedBy Hampus Dahlin
 *
 */
public abstract class Actor{
	private final Icon SPRITE;
	private int health;
	private Point position;
	private int dmg;
	private final Point SPEED;
	private int range;

	protected PropertyChangeSupport pcs;
	
	public Actor(Icon sprite, Point speed) {
		this.SPRITE = sprite;
		this.SPEED = speed;
		//propertychange, vår sak som skall firea när saker händer till observers.
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
	
	public Icon getSprite(){
		return SPRITE;
	}
	
	public void setHealth(int newHealth){
		health = newHealth;
	}
	
	public int getHealth(){
		return health;
	}
	
	/**
	 * Unused but needed for compilation.
	 * Please use setPosition(newPosition, yourIndex) instead.
	 */
	public void setPosition(Point newPosition) {}
	
	public void setPosition(Point newPosition, int yourIndex){
		//är det bra att ha en pc här? eftersom actorcontrol använder sig av denna för att flytta på skiten..
		pcs.fireIndexedPropertyChange("move", yourIndex, position, newPosition);
		position = newPosition;
	}
	
	public Point getPosition(){
		return position;
	}
	
	public boolean isDead(){
		pcs.firePropertyChange("death", this, this);
		return getHealth() <= 0;
	}
	
	public void dealDmg(Actor defender){
		//tmp variabel för att hålla defenders hp innan attacken.
		int tmpHP = defender.getHealth();
		defender.setHealth(defender.getHealth() - this.getDmg());
		//om den som blev skadad är spelaren vill vi ju skicka en annorlunda pc (?) 
		if(defender instanceof PC){
			pcs.firePropertyChange("hp", tmpHP, defender.getHealth());
		}else{
			pcs.firePropertyChange("hpEnemy", tmpHP, defender.getHealth());
		}
	}
	
	
	public void addObserver(PropertyChangeListener observer) {
		pcs.addPropertyChangeListener(observer);
	}

	
	
	public void removeObserver(PropertyChangeListener observer) {
		pcs.removePropertyChangeListener(observer);
		
	}
	
	//attack and death moved to ActorFacade until further notice
	//abstract public void attack();
	//abstract public void death();
	
}
