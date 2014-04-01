package actors;

import java.awt.List;
import java.awt.Point;

import javax.swing.Icon;

import controller.ActorControl;

/**
 * @author Björn Hedström
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
		return hittable;
	}
	
	public boolean isDead(){
		return getHealth() < 0;
	}
	
	public void dealDmg(Actor defender){
		defender.setHealth(defender.getHealth() - this.getDmg());
	}
	
	abstract public void attack();
	abstract public void death();
	
}
