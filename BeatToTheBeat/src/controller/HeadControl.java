package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Timer;

import levels.BttBLevel;
import enviroment.Background;
import support.Movable;
import actors.Actor;


/**
 * 
 * @author Hampus Dahlin
 * @revisedBy Pontus "Bondi" Eriksson
 * @version 0.0.2
 *
 */
public class HeadControl implements KeyListener, PropertyChangeListener, ActionListener {
	
	private BttBLevel level1;
	private BttBLevel level2;
	private long startTime;
	private Timer time;
	private long[] spawnTimes;
	private int enemyNbr;
	private ActorControl actorControl;
	
	public HeadControl() {
		actorControl = new ActorControl();
		
		long[] spawn1 = {5000, 10000};
		long[] spawn2 = {4000, 8000};
		
		level1 = new BttBLevel("file1", "song1", new Background(), spawn1);
		level2 = new BttBLevel("file2", "song2", new Background(), spawn2);
		spawnTimes = level1.getSpawnTimes();
		enemyNbr = 0;
		
		time = new Timer(1, this);
		System.out.println("Game started!");
		System.out.print("Hit button in: ");
		startTime = System.currentTimeMillis();
		time.start();
	}
	
	
	/**
	 * Moves a movable argument.
	 * @param m a movable entity
	 */
	private void moveEntity(Movable m){
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		//attack
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("death")){
			actorControl.removeActor((Actor)evt.getNewValue());
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
		actorControl.moveActors();
		
		if (spawnTimes[enemyNbr] == System.currentTimeMillis()) {
			actorControl.createActor();
		}
	}
	
	public void endGame() {
		
	}
	
}
