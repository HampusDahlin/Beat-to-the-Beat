package powerup;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import actors.Actor;
import actors.PC;

public class Invincible extends Powerup implements ActionListener{

	private Timer durationTimer;
	private PC p;
	
	public Invincible() {
		super("Invincible", "Invincible", 6000, 30000);
		durationTimer = new Timer(this.getDuration(),this);
	}

	@Override
	public void effect(Actor a, boolean score) {
		if(score){
			//since pc is the one sending pce to gamepanel i have to use PC here, i want to send
			//a pce to gamepanel to tell when we're invincible
			p = (PC)a;
			p.setInvincible(true);
			durationTimer.start();	
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		p.setInvincible(false);
		durationTimer.stop();
	}
}
