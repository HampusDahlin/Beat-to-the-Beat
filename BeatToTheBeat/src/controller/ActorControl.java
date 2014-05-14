package controller;


import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import support.RemoveActorException;
import actors.Actor;
import actors.NPC;
import actors.PC;


public class ActorControl {
	private List<NPC> NPCList;
	private PC player;
	private ImageIcon sprite;
	
	public ActorControl(JPanel listener) {
		this.sprite = new ImageIcon("sprites\\ninja.gif");
		NPCList = new ArrayList<NPC>();
		player = new PC(new Point(500, 100), sprite);
		player.addPropertyChangeListener((PropertyChangeListener)listener);
	}
	
	public void createActor(JPanel listener) {
		NPCList.add(new NPC( new Point(System.currentTimeMillis() % 2 == 0 ? 0 : 1000, 0), //random which side
			sprite, (PropertyChangeListener)listener));
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
	
	/**
	 * Tries to attack with the closest NPC.
	 */
	public void NPCAttack() {
		if (canHitClose(100)) { //take damage and remove enemy
			NPCList.get(0).dealDmg(player);
			if (player.getHealth() <= 0) {
				player.death();
			} else {
				player.resetCooldown();
			}
			throw new RemoveActorException();
		}
	}
	
	public List<NPC> getNPCList() {
		return NPCList;
	}
	
	public void playerAttack() {
		if (!player.onCooldown()) {
			if (canHitClose(200 + player.getSprite().getIconHeight()/2 )) {
				System.out.println("TRÄff!");
				player.incCombo();
				
				throw new RemoveActorException();
			} else {
				System.out.println("MISS!");
				
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

	/**
	 * Checks if first NPC in list is within range.
	 * @param range How close NPC can be to player.
	 */
	public boolean canHitClose(int range) {
		return !NPCList.isEmpty() &&
				player.getPosition().getX() -
				(NPCList.get(0).getPosition().getX() +
						(NPCList.get(0).getSprite().getIconWidth()/2)) <= range;
	}

	// Move all NPCs and then try to attack.
	public void moveActors() {
		for (int i = 0; i < NPCList.size(); i++) {
			NPCList.get(i).setPosition(new Point( (int) (NPCList.get(i).getPosition().getX() +
					(player.getPosition().getX() - NPCList.get(i).getPosition().getX() > 0 ? 1 : -1)
					*NPCList.get(i).getSpeed().getX()), 300), i);
		}
		NPCAttack();
	}

}
