package actors;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class PC extends Actor implements ActionListener {
	private final int MISSTIME;
	private final int MAXHEALTH;
	
	private int score;
	private int combo;
	private Timer cooldown;
	
	public PC(Point position, ImageIcon sprite) {
		super(sprite, new Point(0,0));
		
		setPosition(position);
		MAXHEALTH = 100;
		score = 0;
		combo = 0;
		setHealth(MAXHEALTH);
		setPosition(position);
		setDmg(1);
		MISSTIME = 1000; //ms
		cooldown = new Timer(MISSTIME, this);
		cooldown.setInitialDelay(MISSTIME);
		cooldown.setRepeats(false);
	}
	
	public void death() {
		pcs.firePropertyChange("death", true, score);
	}
	
	public int getScore() {
		return score;
	}
	
	public void incCombo() {
		combo++;
		pcs.firePropertyChange("combo", combo-1, combo);
	}
	
	public void resetCombo() {
		pcs.firePropertyChange("combo", combo, 0);
		combo = 0;
	}
	
	public int getCombo(){
		return combo;
	}
	
	public void setCash(int newCash){
		pcs.firePropertyChange("cash", score, newCash);
		score = newCash;
	}
	
	public int getCash(){
		return score;
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
