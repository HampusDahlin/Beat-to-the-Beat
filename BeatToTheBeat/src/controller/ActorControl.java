package controller;


import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

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
		facade.moveActors();
	}
	
	public void createActor() {
		NPCList.add(new NPC( new Point(-100, 100),
				//System.currentTimeMillis() % 2 == 0 ? 0 : 1000, 0), //random which side
			null));
	}
	
	public void removeActor(Actor actor) {
		NPCList.remove(actor);
		//panelWhereActorIs.remove(actor);
	}
	
	/**
	 * Removes first actor in actorList.
	 */
	public void removeActor() {
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
	
	public void playerAttack() {
		try {
			facade.playerAttack();
		} catch (RemoveActorException e) {
			removeActor();
		}
	}

}
