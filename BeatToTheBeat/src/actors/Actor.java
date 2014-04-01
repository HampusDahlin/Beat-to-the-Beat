package actors;

import java.awt.Image;
import java.awt.List;
import java.awt.Point;

import controller.ActorControl;

/**
 * @author Björn Hedström
 *
 */
public abstract class Actor {
	private Image sprite;
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
	
	public List canHit(){
		
		List<Actor> hittable;
		if(this instanceof PC){
			for (NPC enemy : ActorControl.getNPCList()) {
				if(position.getX()-range == 
						enemy.getPosition().getX()+enemy.getSprite().getWidth(null)
						|| position.getX()+range+sprite.getWidth(null) == 
						enemy.getPosition().getX()){
					
					hittable.add(enemy);
					
				}
			}
		}else if(this instanceof NPC){
			if(position.getX()+range == getPlayer().getPosition().getX()) ||
				position.getX()-range == getPlayer().getPosition().getX()+getPlayer().getSprite()
					.getWidth()){
						
						hittable.add(getPlayer());
						
			}
		}
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
