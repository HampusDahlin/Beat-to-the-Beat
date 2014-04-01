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
			enemy.setPosition(new Point( (enemy.getPosition().x +
					(player.getPosition().x - enemy.getPosition().x > 0 ? 1 : -1)
					*enemy.getSpeed().x), 100));
			
		    enemy.attack();
		}
	}
	
	public void createActor() {
		
	}
	
	public void removeActor(Actor actor) {
		//panelWhereActorIs.remove(actor);
	}
	
	public void removeActor() {
		//remove first actor in list.
	}
	
	public void updateView(){
		this.view.update();
	}


}
