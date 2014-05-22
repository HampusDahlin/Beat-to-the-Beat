package powerup;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import actors.Actor;

public class Invincible extends Powerup implements ActionListener{

	private Timer durationTimer;
	private Actor a;
	
	public Invincible() {
		super("Invincible", "Invincible", 6000, 20000);
		durationTimer = new Timer(this.getDuration(),this);
	}

	@Override
	public void effect(Actor a) {
		this.a = a;
		this.a.setInvincible(true);
		durationTimer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		a.setInvincible(false);
		durationTimer.stop();
	}
}
