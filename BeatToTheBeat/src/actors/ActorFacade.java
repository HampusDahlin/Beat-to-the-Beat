package actors;

import java.util.ArrayList;
import java.util.List;

import support.RemoveActorException;

/** 
* Puts some logic in actor-package instead of controller.
* @author Pontus "Bondi" Eriksson
* @group 14
* @date 02-04-14
*/ 
public class ActorFacade {
	private List<NPC> NPCList;
	private PC player;
	
	public ActorFacade(List<NPC> NPCList, PC player) {
		this.NPCList = NPCList;
		this.player = player;
	}
	
	public void NPCAttack() {
		if (canHitClose(player.getSprite().getIconWidth())) {
			NPCList.get(0).dealDmg(player);
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
			if (canHitClose(100 + player.getSprite().getIconHeight()/2 )) {
				//Test-printing
				System.out.println("TR�ff!");
				
				throw new RemoveActorException();
			} else {
				//Test-printing
				System.out.println("MISS!");
				
				player.startCooldown();
			}
		}
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
		
		/**
		 * Checks if first NPC in list is within range.
		 * @param range How close NPC can be to player.
		 */
		public boolean canHitClose(int range) {
			return (player.getPosition().getX() +
					player.getSprite().getIconWidth()/2) -
					NPCList.get(0).getPosition().getX() < range;
		}
}
