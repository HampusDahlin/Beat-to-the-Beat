package powerup;

import actors.PC;

public class Regen extends Powerup {
	
	public Regen(PC player) {
		setOwner(player);
		setName("Regen");
		setDescription("Regenerate HP over time");
	}
		
	@Override
	//heals the player gradualy over time. Can´t increase the health above 100.
	public void effect() {
		if(getOwner().getHealth() < 90) {
			getOwner().setHealth(getOwner().getHealth()+10);
		} else {
			getOwner().setHealth(100);
		}
	}

	
	
}
