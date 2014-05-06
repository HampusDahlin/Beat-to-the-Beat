package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Timer;

import support.GameOverException;
import support.Movable;
import support.OutOfEnemiesException;
import support.RemoveActorException;


/**
 * 
 * @author Hampus Dahlin
 * @revisedBy Pontus "Bondi" Eriksson
 * @revisedBy Malin "Nilhet" Thelin
 * @version 0.0.5
 *
 */
public class HeadControl implements ActionListener {
	
	private long startTime;
	private long[] spawnTimes;
	private int enemyNbr;
	private ActorControl actorControl;
	private EnviromentControl enviromentControl;
	private MusicControl musicControl;
	private UIControl uIControl;
	private Timer time;
	
	public HeadControl() {
		actorControl = new ActorControl();
		enviromentControl = new EnviromentControl();
		musicControl = new MusicControl();
		uIControl = new UIControl();
	}
	
	public void startGame() {
		startTime = System.currentTimeMillis();
		time.start();
	}
	
	/**
	 * Moves a movable argument.
	 * @param m a movable entity
	 */
	private void moveEntity(Movable m){
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void actionPerformed(ActionEvent e) {
		
		//Spawns a new enemy if there is a beat in the music.
		if (musicControl.isBeat()) {
			actorControl.createActor();
		}
		
		//Moves the actors along their path.
		try {
				actorControl.moveActors();
			} catch (GameOverException exc) {
				System.out.println("Du dog!");
				System.exit(0);
			} catch (RemoveActorException exc) {
				try {
					actorControl.removeActor();
				} catch (OutOfEnemiesException except) {
					if (enemyNbr == spawnTimes.length) {
						endGame();
					}
				}
			}
		
		
		//Testkod
		//vår "musikbeat"
		if (actorControl.getFirstEnemy()!=null) {
			System.out.println(300-actorControl.getFirstEnemy().getPosition().x);
		} else {
			System.out.println("Nästa spawn om: "+ (spawnTimes[enemyNbr] - (System.currentTimeMillis()-startTime)));
		}
		
		if (enemyNbr != spawnTimes.length && spawnTimes[enemyNbr] < System.currentTimeMillis()-startTime+100 &&
				spawnTimes[enemyNbr] > System.currentTimeMillis()-startTime-100) {
			actorControl.createActor();
			System.out.println("Enemy spawned.");
			enemyNbr++;
		}
	}
	
	public void endGame() {
		System.out.println("You cleared the whole level! G_G");
		System.exit(0);
	}
	
}
