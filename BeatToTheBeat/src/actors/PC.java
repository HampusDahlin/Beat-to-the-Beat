package actors;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import javax.swing.Icon;

import support.GameOverException;

public class PC extends Actor implements ActionListener {
	private final int MISSTIME;
	private final int MAXHP;
	
	private int cash;
	private int combo;
	private Timer cooldown;
	
	public PC(Point position, Icon sprite) {
		super(sprite, new Point(0,0));
		MAXHP = 5;
		cash = 0;
		combo = 0;
		setHealth(1);
		setPosition(position);
		setDmg(1);
		MISSTIME = 1000; //ms
		cooldown = new Timer(MISSTIME, this);
		cooldown.setInitialDelay(MISSTIME);
		cooldown.setRepeats(false);
	}
	
	public void death() {
		throw new GameOverException();
	}
	
	public void incCombo() {
		combo++;
	}
	
	public void resetCombo() {
		combo = 0;
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
	
	public void startCooldown() {
		cooldown.start();
		cooldown.restart();
	}
	
	public void resetCooldown() {
		cooldown.stop();
	}
	
	public boolean onCooldown() {
		return cooldown.isRunning();
	}

	public void actionPerformed(ActionEvent e) {
		//noRepeat so cooldown stops 
	}

}
