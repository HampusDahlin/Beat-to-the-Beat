package controller;


import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.View;
import javax.swing.Icon;

import support.RemoveActorException;
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
		player = new PC(new Point(500, 100), null);
		facade = new ActorFacade(NPCList, player);
	}
	
	// Move all NPCs and then try to attack.
	public void moveActors() {
		for (NPC enemy : NPCList) {
			enemy.setPosition(new Point( (int) (enemy.getPosition().getX() +
					(player.getPosition().getX() - enemy.getPosition().getX() > 0 ? 1 : -1)
					*enemy.getSpeed().getX()), 100));
			
			try {
				facade.NPCattack();
			} catch (RemoveActorException e) {
				removeActor();
			}
		}
	}
	
	public void createActor() {
		NPCList.add(new NPC( new Point(
				(System.currentTimeMillis() % 2 == 0 ? 0 : 1000), 0),
			null));
	}
	
	public void removeActor(Actor actor) {
		//panelWhereActorIs.remove(actor);
	}
	
	/**
	 * Removes specified actor from actorList.
	 */
	public void removeActor(int index) {
		removeActor(NPCList.get(index));
	}
	
	/**
	 * Removes first actor in actorList.
	 */
	public void removeActor() {
		NPCList.remove(NPCList.get(0));
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
	
	public void playerAttack() {
		facade.playerAttack();
	}

}
