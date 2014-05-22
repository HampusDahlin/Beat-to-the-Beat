package actors;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import powerup.Powerup;
import powerup.Regen;

public class PC extends Actor implements ActionListener {
	private final int MISSTIME;
	private final int MAXHEALTH;
	
	private int score;
	private int combo;
	private int maxCombo;
	
	//test
	private int lives;
	private boolean isInvincible;
	//test
	
	private Timer cooldown;

	
	public PC(Point position, ImageIcon sprite) {
		super(sprite, new Point(0,0));
		setPosition(position);
		MAXHEALTH = 10;
		score = 0;
		combo = 0;
		maxCombo = 0;
		setPosition(position);
		setDmg(1);
		MISSTIME = 1000; //ms
		cooldown = new Timer(MISSTIME, this);
		cooldown.setInitialDelay(MISSTIME);
		cooldown.setRepeats(false);
		setRange(120);
		
		//test
		lives = 1;
		//test
	}

	public void death() {
		pcs.firePropertyChange("death", true, score);
	}
	
	public int getScore() {
		return score;
	}
	
	public void incScore(int point){
		
		if(getCombo()>1){
			//we want the player to recieve the combo points they currently have, and not the ones they will get.
			score += point * (getCombo()-1);
		}else{
			score += point;
		}
		pcs.firePropertyChange("score", score-1, score);
		
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
	
	
	/*ska vi ha detta ??
	public void setCash(int newCash){
		cash = newCash;
	}*/
	
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
	
	public int getMaxHealth(){
		return MAXHEALTH;
	}
	
	public void resetScore(){
		pcs.firePropertyChange("score",score,0);
		score = 0;
	}
	
	public void incMaxCombo(){
		maxCombo++;
		pcs.firePropertyChange("max", maxCombo-1, maxCombo);
	}
	
	public int getMaxCombo(){
		return maxCombo;
	}
	
	public void resetMaxCombo(){
		pcs.firePropertyChange("max",maxCombo,0);
		maxCombo = 0;
		
	}
	
	public void attack(boolean hit, int direction) {
		pcs.firePropertyChange("attack", direction, hit);
	}
	
	//testing

	public void addToLives(int i) {
		lives += i;
		pcs.firePropertyChange("life",lives-1,lives);
	}
	
	public void resetLives(){
		addToLives(-lives+1);
	}
	
	public int getLives(){
		return lives;
	}
	
	public void setInvincible(boolean isTrue){
		isInvincible = isTrue;
		pcs.firePropertyChange("invincible", !isInvincible(), isInvincible());
	}
	public boolean isInvincible(){
		return isInvincible;
	}
	
	//testing

	
}