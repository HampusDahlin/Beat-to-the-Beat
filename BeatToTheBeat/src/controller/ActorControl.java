package controller;


import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.View;

import actors.Actor;
import actors.NPC;
import actors.PC;


public class ActorControl {
	List<NPC> NPCList;
	PC player;
	
	public ActorControl(){
		NPCList = new ArrayList<NPC>();
		player = new PC(new Point(500, 100), null);
	}
	
	// Move all NPCs and then try to attack.
	public void moveActors() {
		for (NPC enemy : NPCList) {
			enemy.setPosition(new Point( (int) (enemy.getPosition().getX() +
					(player.getPosition().getX() - enemy.getPosition().getX() > 0 ? 1 : -1)
					*enemy.getSpeed().getX()), 100));
			
		    enemy.attack();
		}
	}
	
	public void createActor() {
		NPCList.add(new NPC( new Point(
				System.currentTimeMillis() % 2 == 0 ? 0 : 1000, 0),
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
	}
	
	public void updateView(){
		//this.view.update();
	}
	
	public PC getPlayer() {
		return player;
	}

}
