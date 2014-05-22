package powerup;

import actors.Actor;
import actors.PC;

public class ExtraLife extends Powerup{

	public ExtraLife() {
		super("Extra Life", "Gain 1 extra life", 0, 10);
	}

	@Override
	public void effect(Actor a, boolean score) {
		if(!score){

			PC p = (PC)a;
			p.addToLives(1);
			System.out.println("NU får du ett extraliv");

		}	
	}

}
