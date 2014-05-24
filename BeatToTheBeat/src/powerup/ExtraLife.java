package powerup;

import actors.Actor;
import actors.PC;

public class ExtraLife extends Powerup{

	public ExtraLife() {
		super("Extra Life", "Gain 1 extra life", 0, 100);
	}

	@Override
	/**
	 * You gain one extra life if you get a 100 combo
	 */
	public void effect(Actor a, boolean score) {
		if(!score){
			PC p = (PC)a;
			p.addToLives(1);

		}	
	}

}
