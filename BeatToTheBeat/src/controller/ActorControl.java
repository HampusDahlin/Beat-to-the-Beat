package controller;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import powerup.Powerup;
import actors.Actor;
import actors.NPC;
import actors.PC;

class ActorControl {
	private List<NPC> NPCList;
	private PC player;
	private final ImageIcon SPRITE;
	
	ActorControl(PropertyChangeListener listener) {
		this.SPRITE = new ImageIcon("sprites\\ninja.gif");
		NPCList = new ArrayList<NPC>();
		player = new PC(new Point(450, 0), SPRITE);
		player.addPropertyChangeListener((PropertyChangeListener)listener);
		player.setHealth(player.getMaxHealth()); //setting health after so player fires event to gamepanel
	}
	
	void createActor(JPanel listener) {
		NPCList.add(new NPC( new Point(System.currentTimeMillis() % 2 == 0 ? 0 : 900, 0), //random which side
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
		if (canHitClose(15, false) || canHitClose(15,true)) { //take damage and remove enemy
			NPCList.get(0).dealDmg(player);
			player.resetCombo();
			if (player.getHealth() <= 0) {
				player.setLives(-1);
				if(player.getLives() <= 0){
					player.death();
				}
				player.setHealth(player.getMaxHealth());
			} else {
				player.resetCooldown();
				removeActor();
			}
		}
	}
	
	public List<NPC> getNPCList() {
		return NPCList;
	}
	
	void playerAttack(boolean right) {
		if (!player.onCooldown()) {
			boolean hit = canHitClose(120, right);
			player.attack(hit, (right ? -1 : 1));
			if (hit) {
				player.incCombo();
				int prevScore = player.getScore();
				player.incScore((int) (Math.abs((right ? 51 : 66) - Math.abs(NPCList.get(0).getPosition().x
						- (right ? 515 : 375))) / (right ? 5.5 : 6.6 )));
				player.incMaxCombo();
				
				removeActor();
				
				powerupCheck(prevScore);
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

		for(Powerup p : player.getPowerups()){
			if(player.getScore() % p.getThreshold() < prevScore % p.getThreshold()){
				p.effect();
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