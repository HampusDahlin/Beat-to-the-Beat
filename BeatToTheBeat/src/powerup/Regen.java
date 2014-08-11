package powerup;

import model.Actor;

public class Regen extends Powerup {
	
	public Regen() {
		super("Regen", "Regenerate HP", 0, 10000);
	}
		
	@Override
	//heals the player gradualy over time. Can´t increase the health above 50.
	public void effect(Actor a, boolean score) {
		if(score){
			if(a.getHealth() < 15) {
				a.setHealth(a.getHealth()+1);
			} else {
				a.setHealth(15);
			}	
		}

	}

	
	
}
