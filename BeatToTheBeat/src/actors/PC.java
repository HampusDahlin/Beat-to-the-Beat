package actors;

import java.awt.Point;
import java.util.Timer;

import javax.swing.Icon;

import support.GameOverException;

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
	
	/*public void attack() {
		try(canHit()){
			dealDmg(canHit()[0]);
		}catch(OutOfReachException ex){
			//cooldown start
		}
	}*/
	
	public void death(){
		throw new GameOverException();
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
	
	public void resetCooldown() {
		cooldown.cancel();
	}

}
