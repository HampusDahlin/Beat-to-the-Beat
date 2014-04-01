package actors;

import java.awt.Image;
import java.awt.Point;
import java.util.Timer;

public class PC extends Actor{
	private int cash;
	private int combo;
	private Timer cooldown = new Timer();
	
	public PC(Point position, Image sprite){
		cash = 0;
		combo = 0;
		setHealth(100);
		setPosition(position);
		setSprite(sprite);
		setSpeed(0);
		setDmg(1);
	}
	
	public void attack() {
		try(canHit()){
			dealDmg(canHit()[0]);
		}catch(OutOfReachException ex){
			//cooldown start
		}
	}
	
	public void death(){
		
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
