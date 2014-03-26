package actors;

import java.awt.Image;
import java.awt.Point;

public class PC extends Actor{
	private int cash;
	private int combo;
	
	public PC(Point position, Image sprite){
		cash = 0;
		combo = 0;
		setHealth(100);
		setPosition(position);
		setSprite(sprite);
	}
	
	
	public void setCombo(int newCombo){
		combo = newCombo;
	}
	
	public int getCombo(){
		return combo;
	}
	
	public void setCash(int newCash){
		cash = newCash;
	}
	
	public int getCash(){
		return cash;
	}
	
	
}
