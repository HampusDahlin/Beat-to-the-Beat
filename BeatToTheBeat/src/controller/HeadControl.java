package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Timer;

import levels.BttBLevel;
import enviroment.Background;
import support.GameOverException;
import support.Movable;
import support.OutOfEnemiesException;
import support.RemoveActorException;
import test.TestGame;


/**
 * 
 * @author Hampus Dahlin
 * @revisedBy Pontus "Bondi" Eriksson
 * @revisedBy Malin "Johanna" Thelin
 * @version 0.0.4
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
	private KeyListener keyListener;
	
	//TestGame är som en view här
	public HeadControl(TestGame game) {
		actorControl = new ActorControl();
		
		long[] spawn1 = {5000, 12000};
		long[] spawn2 = {4000, 10000};
		
		level1 = new BttBLevel("file1", "song1", new Background(), spawn1);
		level2 = new BttBLevel("file2", "song2", new Background(), spawn2);
		spawnTimes = level1.getSpawnTimes();
		enemyNbr = 0;
		
		
		//testkod
		time = new Timer(1000, this);
		System.out.println("Game started!");
		System.out.print("Hit button in: ");
		startTime = System.currentTimeMillis();
		time.start();
		
		this.keyListener = new KeyAdapter(){
			public void keyPressed(KeyEvent evt){
				if(evt.getKeyCode() == KeyEvent.VK_SPACE){
					try {
						actorControl.playerAttack();
					} catch (OutOfEnemiesException e) {
						if (enemyNbr == spawnTimes.length) {
							endGame();
						}
					}
				}
			}
		};
		
		game.addKeyListener(keyListener);
	}
	
	
	/**
	 * Moves a movable argument.
	 * @param m a movable entity
	 */
	private void moveEntity(Movable m){
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		
		System.out.println("hallåaåå");
		
		//attack
		if(e.getKeyCode()== KeyEvent.VK_SPACE){
			System.out.println("hej");
		}
			
		try {
			actorControl.playerAttack();
		} catch (RemoveActorException exc) {
			actorControl.removeActor();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("hallå");
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("death")){
			//actorControl.removeActor((Actor)evt.getNewValue());
		}
		
	}
	
	public void actionPerformed(ActionEvent e) {
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
		
		/*
		if(timerOn){
			i++;
			if(i == 4){
				System.out.println("SLÅ!");
				i=0;
			}else{
				//skriver ut "sekunderna"
				System.out.println(i);
				//time.stop();
				//timerOn=false;
			}
		}
		//time.start();
		//timerOn = true;
		*/
		
		
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
