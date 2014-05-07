package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
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
 * @version 0.0.6
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
	private ChooseSong songPanel;
	
	public HeadControl(JFrame mainFrame) {
		actorControl = new ActorControl();
		enviromentControl = new EnviromentControl();
		musicControl = new MusicControl();
		time = new Timer(10, this);
		uIControl = new UIControl(mainFrame);
		songPanel = new ChooseSong(musicControl.getSongList());
		mainFrame.add(songPanel);
		mainFrame.setVisible(true);
	}
	
	public void startGame(int songIndex) {
		startTime = System.currentTimeMillis();
		time.start();
		int amntOfSongs = 10;//tillfällig! bara för att if satsen skall se vettig ut. kan finnas i en annan klass senare?
		if(songIndex >=0 && songIndex <= amntOfSongs)//hur många låtar vi nu än kommer att ha
		{
			musicControl.setSong(songIndex);
			musicControl.play();
		}	
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
		
		enviromentControl.updateBackground(musicControl.getWave());
		
		
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
