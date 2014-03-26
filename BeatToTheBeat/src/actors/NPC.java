package actors;

import java.awt.Image;
import java.awt.Point;

public class NPC extends Actor implements support.Movable{
	private int speed;
	private int dmg;
	
	public NPC(Point position, Image sprite){
		setHealth(1);
		setPosition(position);
		setSprite(sprite);
	}
	
	public void setSpeed(int newSpeed){
		speed = newSpeed;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public void setDmg(int newDmg){
		dmg = newDmg;
	}
	
	public int getDmg(){
		return dmg;
	}
}
