package actors;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import support.IObservable;

import javax.swing.Icon;

/**
 * @author Björn Hedström
 * @revisedBy Pontus "Bondi" Eriksson
 * @revisedBy Malin "Nilhet" Thelin
 *
 */
public abstract class Actor implements IObservable{
	private final Icon SPRITE;
	private int health;
	private Point position;
	private int dmg;
	private final Point SPEED;
	private int range;

	private PropertyChangeSupport pcs;
	
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
		//tmp variabel för att hålla defenders hp innan attacken.
		int tmpHP = defender.getHealth();
		defender.setHealth(defender.getHealth() - this.getDmg());
		//om den som blev skadad är spelaren
		if(defender instanceof PC){
			pcs.firePropertyChange("hp", tmpHP, defender.getHealth());
		}else{
			pcs.firePropertyChange("hpEnemy", tmpHP, defender.getHealth());
		}
	}
	
	@Override
	public void addObserver(PropertyChangeListener observer) {
		pcs.addPropertyChangeListener(observer);
	}

	@Override
	public void removeObserver(PropertyChangeListener observer) {
		pcs.removePropertyChangeListener(observer);
		
	}
	
	//attack and death moved to ActorFacade until further notice
	//abstract public void attack();
	//abstract public void death();
	
}
