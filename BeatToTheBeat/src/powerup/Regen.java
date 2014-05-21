package powerup;

import actors.PC;

public class Regen extends Powerup {
	
	public Regen(PC player) {
		setOwner(player);
		setName("Regen");
		setDescription("Regenerate HP over time");
		setThreshold(10000);
	}
		
	@Override
	//heals the player gradualy over time. Can´t increase the health above 100.
	public void effect() {
		if(getOwner().getHealth() < 45) {
			getOwner().setHealth(getOwner().getHealth()+5);
		} else {
			getOwner().setHealth(50);
		}
	}

	
	
}
