package actors;

import java.awt.Image;
import java.awt.Point;
import java.util.Timer;

import javax.swing.Icon;

public class PC extends Actor{
	private int cash;
	private int combo;
	private Timer cooldown = new Timer();
	
	public PC(Point position, Icon sprite){
		cash = 0;
		combo = 0;
		setHealth(100);
		setPosition(position);
		setSprite(sprite);
		setSpeed(new Point(0,0));
		setDmg(1);
	}
	
	public void attack() {
		if (canHit(100 + getSprite().getIconHeight()/2 )) {
			
		}
	}
	
	/*public void attack() {
		try(canHit()){
			dealDmg(canHit()[0]);
		}catch(OutOfReachException ex){
			//cooldown start
		}
	}*/
	
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
