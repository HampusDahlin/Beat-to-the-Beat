package powerup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import actors.*;

public class Range extends Powerup implements ActionListener {
	
	private Timer durationTimer;
	private Actor a;
	
	public Range(){
		super("Range","Range Increased",5000,40);
		durationTimer = new Timer(this.getDuration(),this);
	}

	@Override
	public void effect(Actor a, boolean score) {
		if(!score){
			this.a = a;
			a.setRange(a.getRange()*2);
			durationTimer.start();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		a.setRange(a.getRange()/2);
		durationTimer.stop();
	}	
}
