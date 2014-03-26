package actors;

import java.awt.Point;

public class NPC extends Actor implements support.Movable{
	private int speed;
	private int dmg;
	private Point p;
	
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

	@Override
	public void setPosition(Point p) {
		this.p = p;
	}
}
