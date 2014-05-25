package controller;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import powerup.*;
import actors.*;

class ActorControl {
	private List<NPC> NPCList;
	private PC player;
	private final ImageIcon SPRITE;
	private List<Powerup> powerups;
	
	ActorControl(PropertyChangeListener listener) {
		this.SPRITE = new ImageIcon("sprites\\ninja.gif");
		powerups = new ArrayList<Powerup>();
		addPowerups();
		NPCList = new ArrayList<NPC>();
		player = new PC(new Point(450, 0), SPRITE);
		player.addPropertyChangeListener((PropertyChangeListener)listener);
		player.setHealth(player.getMaxHealth()); //setting health after so player fires event to gamepanel
	}
	
	private void addPowerups() {
		powerups.add(new Regen());
		powerups.add(new Invincible());
		powerups.add(new ExtraLife());
		powerups.add(new Range());
	}

	void createActor(JPanel listener) {
		NPCList.add(new NPC( new Point(System.currentTimeMillis() % 2 == 0 ? -25 : 915, 0), //random which side
			SPRITE, (PropertyChangeListener)listener));
	}
	
	private void removeActor(Actor actor) {
		NPCList.remove(actor);
		}
	
	/**
	 * Removes first actor in actorList.
	 */
	void removeActor() {
		NPCList.get(0).removeYourself(0);
		removeActor(NPCList.get(0));
	}
	
	/**
	 * Returns the first enemy in NPCList.
	 */
	public NPC getFirstEnemy() {
		try {
			return NPCList.get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	/**
	 * Tries to attack with the closest NPC.
	 */
	private void NPCAttack() {
		if (canHitClose(15, false) || canHitClose(15,true) && NPCList.size()>0) { //take damage and remove enemy
			//if player is invincible this code tells the player to attack the closest enemy by itself
			//instead of losing hp and/or dying
			if(player.isInvincible()){
				playerAttack((canHitClose(15,false) ? false : true));

			}else{

				NPCList.get(0).dealDmg(player);
				player.resetCombo();

				if (player.getHealth() <= 0) {
					player.addToLives(-1);
					if(player.getLives() <= 0){
						player.death();
					}else {
						player.setHealth(player.getMaxHealth());
					}
				}else {
					player.resetCooldown();
				}

				removeActor();
			}
		}
	}
	
	public List<NPC> getNPCList() {
		return NPCList;
	}

	void playerAttack(boolean right) {
		if (!player.onCooldown()) {
			boolean hit = canHitClose(player.getRange(), right);
			player.attack(hit, (right ? -1 : 1));
			if (hit) {
				int prevScore = player.getScore();
				if(player.getRange() == 120){

					player.incScore((int) ((70 - Math.abs(NPCList.get(0).getPosition().x
									- (right ? 515 : 400))) / 7));
						
				}else {
					player.incScore(5);
				}
				player.incCombo();
				player.incMaxCombo();
				powerupCheck(prevScore);
				
				//System.out.println("LIV:: "+ player.getLives());
				
				
				removeActor();

			} else {
				player.startCooldown();
				player.resetCombo();

			}
		}
	}

	
	
	// Possibly to be used with powerups etc later.
	/**
	 * Checks which NPCs are within range of player.
	 * @param range How close NPC can be to player.
	 * @return List<NPC> with close enemies.
	 */
	/*
	public List<NPC> canHit(int range) {
		List<NPC> hittable = new ArrayList<NPC>();
		for (NPC enemy : NPCList) {
			if ((player.getPosition().getX() +
					player.getSprite().getIconWidth()/2) -
					enemy.getPosition().getX() < range) {
				hittable.add(enemy);
			}
		}
		return hittable;
	}
	*/

	private void powerupCheck(int prevScore) {
		for(Powerup p : powerups){
			if(p.getThreshold() < 1000 && player.getCombo() % p.getThreshold() == 0){
				p.effect(player,false);
			}else if(player.getScore() % p.getThreshold() < prevScore % p.getThreshold()){
				p.effect(player,true);
			}
		}

	}

	/**
	 * Checks if first NPC in list is within range.
	 * @param range How close NPC can be to player.
	 * @param right If attack is directed to the right.
	 */
	private boolean canHitClose(int range, boolean right) {
		return !NPCList.isEmpty() && (NPCList.get(0).getSpeed().x < 0 == right) &&
				((right ? NPCList.get(0) : player).getPosition().x) -
				((right ? player : NPCList.get(0)).getPosition().x +
						(right ? player : NPCList.get(0)).getSprite().getIconWidth()) <
				range;
	}
	
	// Move all NPCs and then try to attack.
	void moveActors() {
		for (int i = 0; i < NPCList.size() && !NPCList.isEmpty(); i++) {
			NPCList.get(i).setPosition(new Point(
					(NPCList.get(i).getPosition().x + NPCList.get(i).getSpeed().x), 300), i);
		}
		NPCAttack();
	}
	
	
	//BTW! ALL GETTERS AND SETTER AND METHODS BELOW HAVE BEEN CREATED TO BE USED; ERGO THEY ARE NECESSARY
	
	public int getScore() {
		return player.getScore();
	}
	
	//gjgj
	public PC getPlayer(){
		return player;
	}
	
	void resetHealth(){
		player.setHealth(player.getMaxHealth());
	}
	
	void resetScore(){
		player.resetScore();
	}
	
	void resetCombo(){
		player.resetCombo();
	}
	
	void resetMaxCombo(){
		player.resetMaxCombo();
	}
	
	void emptyNPCList(){
		NPCList.clear();
	}
	
	void resetLives(){
		player.resetLives();
	}
	
	
}