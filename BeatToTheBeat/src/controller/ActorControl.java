package controller;

import java.util.List;

import model.BeatToTheBeatModel;
import model.actors.Actor;
import model.actors.NPC;
import model.actors.PC;
import model.powerup.*;

class ActorControl {
	private BeatToTheBeatModel model;
	
	ActorControl() {
		model = new BeatToTheBeatModel();
	}

	void createActor() {
		model.createNpc();
	}
	
	private void removeActor(Actor actor) {
		model.getNpcList().remove(actor);
	}
	
	/**
	 * Removes first actor in actorList.
	 */
	private void removeActor() {
		removeActor(model.getNpcList().get(0));
	}
	
	/**
	 * Returns the first enemy in model.getNpcList().
	 */
	public NPC getFirstEnemy() {
		try {
			return model.getNpcList().get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	/**
	 * Tries to attack with the closest NPC.
	 */
	private void NPCAttack() {
		if (canHitClose(15, false) || canHitClose(15,true) && model.getNpcList().size()>0) { //take damage and remove enemy
			//if model.getPlayer() is invincible this code tells the model.getPlayer() to attack the closest enemy by itself
			//instead of losing hp and/or dying
			if(model.getPlayer().isInvincible()){
				playerAttack((canHitClose(15,false) ? false : true));

			}else{

				model.getNpcList().get(0).dealDmg(model.getPlayer());
				model.getPlayer().resetCombo();

				if (model.getPlayer().getHealth() <= 0) {
					model.getPlayer().addToLives(-1);
					if(model.getPlayer().getLives() <= 0){
						model.getNpcList().clear();
					}else {
						model.getPlayer().setHealth(model.getPlayer().getMaxHealth());
					}
				}else {
					model.getPlayer().resetCooldown();
				}

				removeActor();
			}
		}
	}
	
	public List<NPC> getNpcList() {
		return model.getNpcList();
	}

	void playerAttack(boolean right) {
		if (!model.getPlayer().onCooldown()) {
			model.getPlayer().attack(right);
			
			if (canHitClose(model.getPlayer().getRange(), right)) {
				int prevScore = model.getPlayer().getScore();
				
				if(model.getPlayer().getRange() == 120){

					model.getPlayer().incScore((int) ((70 - Math.abs(model.getNpcList().get(0).getPosition().x
									- (right ? 515 : 400))) / 7));
						
				}else {
					model.getPlayer().incScore(5);
				}
				
				model.getPlayer().incCombo();
				model.getPlayer().incMaxCombo();
				powerupCheck(prevScore);				
				
				removeActor();
			} else {
				model.getPlayer().startCooldown();
				model.getPlayer().resetCombo();
			}
		}
	}

	
	
	// Possibly to be used with powerups etc later.
	/**
	 * Checks which NPCs are within range of model.getPlayer().
	 * @param range How close NPC can be to model.getPlayer().
	 * @return List<NPC> with close enemies.
	 */
	/*
	public List<NPC> canHit(int range) {
		List<NPC> hittable = new ArrayList<NPC>();
		for (NPC enemy : model.getNpcList()) {
			if ((model.getPlayer().getPosition().getX() +
					model.getPlayer().getSprite().getIconWidth()/2) -
					enemy.getPosition().getX() < range) {
				hittable.add(enemy);
			}
		}
		return hittable;
	}
	*/

	private void powerupCheck(int prevScore) {
		for(Powerup p : model.getPowerups()){
			if(p.getThreshold() < 1000 && model.getPlayer().getCombo() % p.getThreshold() == 0){
				p.effect(model.getPlayer(),false);
			}else if(model.getPlayer().getScore() % p.getThreshold() < prevScore % p.getThreshold()){
				p.effect(model.getPlayer(),true);
			}
		}

	}

	/**
	 * Checks if first NPC in list is within range.
	 * @param range How close NPC can be to model.getPlayer().
	 * @param right If attack is directed to the right.
	 */
	private boolean canHitClose(int range, boolean right) {
		return !model.getNpcList().isEmpty() && (model.getNpcList().get(0).getSpeed().x < 0 == right) &&
				((right ? model.getNpcList().get(0) : model.getPlayer()).getPosition().x) -
				((right ? model.getPlayer() : model.getNpcList().get(0)).getPosition().x +
						//(right ? model.getPlayer() : model.getNpcList().get(0)).getSprite().getIconWidth()) <
						30 ) <
				range;
	}
	
	// Move all NPCs and then try to attack.
	void moveActors() {
		for (int i = 0; i < model.getNpcList().size() && !model.getNpcList().isEmpty(); i++) {
			model.getNpcList().get(i).move();
		}
		NPCAttack();
	}
	
	
	//BTW! ALL GETTERS AND SETTER AND METHODS BELOW HAVE BEEN CREATED TO BE USED; ERGO THEY ARE NECESSARY
	
	public int getScore() {
		return model.getPlayer().getScore();
	}
	
	public PC getPlayer(){
		return model.getPlayer();
	}
	
	void resetHealth(){
		model.getPlayer().setHealth(model.getPlayer().getMaxHealth());
	}
	
	void resetScore(){
		model.getPlayer().resetScore();
	}
	
	void resetCombo(){
		model.getPlayer().resetCombo();
	}
	
	void resetMaxCombo(){
		model.getPlayer().resetMaxCombo();
	}
	
	void emptyNpcList(){
		model.getNpcList().clear();
	}
	
	void resetLives(){
		model.getPlayer().resetLives();
	}

	public BeatToTheBeatModel getModel() {
		return model;
	}	
	
}