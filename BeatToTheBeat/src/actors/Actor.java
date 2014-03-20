package actors;

import java.awt.Image;
import java.awt.Point;

public abstract class Actor {
	Image sprite;
	int health;
	Point position;
	
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
	
	public Point getPosition(){
		return position;
	}
	
	public void setPosition(Point newPosition){
		position = newPosition;
	}
	
}
