package actors;

import java.awt.Image;
import java.awt.Point;

public abstract class Actor {
	private Image sprite;
	private int health;
	private Point position;
	
	public void setSprite(Image newSprite){
		sprite = newSprite;
	}
	
	public Image getSprite(){
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
		return getHealth()<0;
	}
	
}
