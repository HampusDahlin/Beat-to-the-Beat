package actors;

import java.awt.Point;
import java.util.List;

import support.RemoveActorException;

/** 
* Puts some logic in actor-package instead of controller.
* @author Pontus "Bondi" Eriksson
* @group 14
*/ 
public class ActorFacade {
	private List<NPC> NPCList;
	private PC player;
	
	public ActorFacade(List<NPC> NPCList, PC player) {
		this.NPCList = NPCList;
		this.player = player;
	}
	
	public void NPCAttack() {
		if (canHitClose(100)) { //take damage and remove enemy
			NPCList.get(0).dealDmg(player);
			System.out.println("TRÄFFAd! ouch!");
			if (player.getHealth() <= 0) {
				player.death();
			} else {
				player.resetCooldown();
			}
			throw new RemoveActorException();
		}
	}
	
	public void playerAttack() {
		if (!player.onCooldown()) {
			if (canHitClose(200)) { //+ player.getSprite().getIconHeight()/2 )) {
				//Test-printing
				System.out.println("TRÄff!");
				player.incCombo();
				
				throw new RemoveActorException();
			} else {
				//Test-printing
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

	public void moveActors() {
		for (NPC enemy : NPCList) {
			enemy.setPosition(new Point( (int) (enemy.getPosition().getX() +
					(player.getPosition().getX() - enemy.getPosition().getX() > 0 ? 1 : -1)
					*enemy.getSpeed().getX()), 100));
		}
		NPCAttack();
	}
}
