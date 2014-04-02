package controller;


import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.View;

import actors.Actor;
import actors.ActorFacade;
import actors.NPC;
import actors.PC;


public class ActorControl {
	private List<NPC> NPCList;
	private PC player;
	private ActorFacade facade;
	
	public ActorControl(){
		NPCList = new ArrayList<NPC>();
		createActor();
		player = new PC(new Point(500, 100), null);
		facade = new ActorFacade(NPCList, player);
	}
	
	// Move all NPCs and then try to attack.
	public void moveActors() {
		for (NPC enemy : NPCList) {
			enemy.setPosition(new Point( (int) (enemy.getPosition().getX() +
					(player.getPosition().getX() - enemy.getPosition().getX() > 0 ? 1 : -1)
					*enemy.getSpeed().getX()), 100));
			
		    facade.NPCAttack();
		}
	}
	
	public void createActor() {
		NPCList.add(new NPC( new Point(-100, 100),
				//System.currentTimeMillis() % 2 == 0 ? 0 : 1000, 0),
			null));
	}
	
	public void removeActor(Actor actor) {
		//panelWhereActorIs.remove(actor);
	}
	
	/**
	 * Removes first actor in actorList.
	 */
	public void removeActor() {
		NPCList.remove(0);
		createActor();
	}
	
	public void updateView(){
		//this.view.update();
	}
	
	public PC getPlayer() {
		return player;
	}
	
	/**
	 * Returns the first enemy in NPCList.
	 */
	public NPC getFirstEnemy() {
		return NPCList.get(0);
	}
	
	// Possibly to be used with powerups etc later.
	/**
	 * Checks which NPCs are within range of player.
	 * @param range How close NPC can be to player.
	 * @return List<NPC> with close enemies.
	 */
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
	
	public void playerAttack() {
		facade.playerAttack();
	}

}
