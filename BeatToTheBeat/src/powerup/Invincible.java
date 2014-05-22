package powerup;

import actors.Actor;

public class Invincible extends Powerup{

	public Invincible() {
		super("Invincible", "You are invincible for a short period of time", 10, 100);
		// TODO change threshold, this is just for testing! 
	}

	@Override
	public void effect(Actor a) {
		System.out.println("Nu är du odödlig");
		a.setInvincible();
	}
	

}
