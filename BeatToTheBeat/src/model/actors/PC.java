package model.actors;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class PC extends Actor implements ActionListener {
	private final int MISSTIME;
	private final int MAXHEALTH;
	
	private boolean right;
	private int attackIndex;
	private int score;
	private int combo;
	private int maxCombo;
	
	//test
	private int lives;
	private boolean isInvincible;
	//test
	
	private Timer cooldown;

	
	public PC(Point position) {
		super(new Point(0,0));
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
		
		attackIndex = -1;
		
		lives = 1;	
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
	
	public int getHealth() {
		return health;
	}
	
	public int getMaxHealth(){
		return MAXHEALTH;
	}
	
	public void resetScore(){
		score = 0;
	}
	
	public void incMaxCombo(){
		maxCombo++;
	}
	
	public int getMaxCombo(){
		return maxCombo;
	}
	
	public void resetMaxCombo(){
		maxCombo = 0;
		
	}

	public void addToLives(int i) {
		lives += i;
	}
	
	public void resetLives(){
		addToLives(-lives+1);
	}
	
	public int getLives(){
		return lives;
	}
	
	public void setInvincible(boolean isTrue){
		isInvincible = isTrue;
	}
	public boolean isInvincible(){
		return isInvincible;
	}

	public int getAttackIndex() {
		return attackIndex;
	}
	
	public void setAttackIndex(int newIndex) {
		attackIndex = newIndex;
	}

	public void attack(boolean right) {
		attackIndex = 0;
		this.right = right;
	}

	/**
	 * Returns true if hitting right, otherwise false.
	 */
	public boolean hitsRight() {
		return right;
	}
	
}