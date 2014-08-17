package model;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import model.actors.*;
import model.powerup.*;


public class BeatToTheBeatModel {
	private PC player;
	private List<NPC> npcList;
	private List<Powerup> powerups;
	
	public BeatToTheBeatModel() {
		player = new PC(new Point(450, 0));
		player.setHealth(player.getMaxHealth()); //setting health after so player fires event to gamepanel		
		npcList = new ArrayList<NPC>();
		powerups = new ArrayList<Powerup>();
		addPowerups();
	}
	
	private void addPowerups() {
		powerups.add(new Regen());
		powerups.add(new Invincible());
		powerups.add(new ExtraLife());
	}
	
	public List<Powerup> getPowerups() {
		return powerups;
	}
	
	public void createNpc() {
		getNpcList().add(new NPC( new Point(System.currentTimeMillis() % 2 == 0 ? -25 : 915, 0))); //random which side
	}
	
	public void removeActor(Actor actor) {
		getNpcList().remove(actor);
	}
	
	/**
	 * Removes first actor in actorList.
	 */
	public void removeActor() {
		removeActor(getNpcList().get(0));
	}
	
	/**
	 * Returns the first enemy in getNpcList().
	 */
	public NPC getFirstEnemy() {
		try {
			return getNpcList().get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	/**
	 * Tries to attack with the closest NPC.
	 */
	private void NPCAttack() {
		if (canHitClose(15, false) || canHitClose(15,true) && getNpcList().size()>0) { //take damage and remove enemy
			//if getPlayer() is invincible this code tells the getPlayer() to attack the closest enemy by itself
			//instead of losing hp and/or dying
			if (getPlayer().isInvincible()) {
				playerAttack((canHitClose(15,false) ? false : true));

			} else {

				getNpcList().get(0).dealDmg(getPlayer());
				getPlayer().resetCombo();

				if (getPlayer().getHealth() <= 0) {
					getPlayer().addToLives(-1);
					if(getPlayer().getLives() <= 0){
						getNpcList().clear();
					}else {
						getPlayer().setHealth(getPlayer().getMaxHealth());
					}
				}else {
					getPlayer().resetCooldown();
				}

				removeActor();
			}
		}
	}
	
	public List<NPC> getNpcList() {
		return npcList;
	}

	public void playerAttack(boolean right) {
		if (!getPlayer().onCooldown()) {
			getPlayer().attack(right);
			
			if (canHitClose(getPlayer().getRange(), right)) {
				int prevScore = getPlayer().getScore();
				
				if(getPlayer().getRange() == 120){

					getPlayer().incScore((int) ((70 - Math.abs(getNpcList().get(0).getPosition().x
									- (right ? 515 : 400))) / 7));
						
				}else {
					getPlayer().incScore(5);
				}
				
				getPlayer().incCombo();
				getPlayer().incMaxCombo();
				powerupCheck(prevScore);				
				
				removeActor();
			} else {
				getPlayer().startCooldown();
				getPlayer().resetCombo();
			}
		}
	}

	
	
	// Possibly to be used with powerups etc later.
	/**
	 * Checks which NPCs are within range of getPlayer().
	 * @param range How close NPC can be to getPlayer().
	 * @return List<NPC> with close enemies.
	 */
	/*
	public List<NPC> canHit(int range) {
		List<NPC> hittable = new ArrayList<NPC>();
		for (NPC enemy : getNpcList()) {
			if ((getPlayer().getPosition().getX() +
					getPlayer().getSprite().getIconWidth()/2) -
					enemy.getPosition().getX() < range) {
				hittable.add(enemy);
			}
		}
		return hittable;
	}
	*/

	private void powerupCheck(int prevScore) {
		for(Powerup p : getPowerups()){
			if(p.getThreshold() < 1000 && getPlayer().getCombo() % p.getThreshold() == 0){
				p.effect(getPlayer(),false);
			}else if(getPlayer().getScore() % p.getThreshold() < prevScore % p.getThreshold()){
				p.effect(getPlayer(),true);
			}
		}

	}

	/**
	 * Checks if first NPC in list is within range.
	 * @param range How close NPC can be to getPlayer().
	 * @param right If attack is directed to the right.
	 */
	private boolean canHitClose(int range, boolean right) {
		return !getNpcList().isEmpty() && (getNpcList().get(0).getSpeed().x < 0 == right) &&
				((right ? getNpcList().get(0) : getPlayer()).getPosition().x) -
				((right ? getPlayer() : getNpcList().get(0)).getPosition().x +
						//(right ? getPlayer() : getNpcList().get(0)).getSprite().getIconWidth()) <
						30 ) <
				range;
	}
	
	// Move all NPCs and then try to attack.
	public void moveActors() {
		for (int i = 0; i < getNpcList().size() && !getNpcList().isEmpty(); i++) {
			getNpcList().get(i).move();
		}
		NPCAttack();
	}
	
	
	//BTW! ALL GETTERS AND SETTER AND METHODS BELOW HAVE BEEN CREATED TO BE USED; ERGO THEY ARE NECESSARY
	
	public int getScore() {
		return getPlayer().getScore();
	}
	
	public PC getPlayer(){
		return player;
	}
	
	public void resetHealth(){
		getPlayer().setHealth(getPlayer().getMaxHealth());
	}
	
	public void resetScore(){
		getPlayer().resetScore();
	}
	
	public void resetCombo(){
		getPlayer().resetCombo();
	}
	
	public void resetMaxCombo(){
		getPlayer().resetMaxCombo();
	}
	
	public void emptyNpcList(){
		getNpcList().clear();
	}
	
	public void resetLives(){
		getPlayer().resetLives();
	}
	
}
