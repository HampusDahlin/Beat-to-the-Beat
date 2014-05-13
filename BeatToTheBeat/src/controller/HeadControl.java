package controller;

import gui.CardPanel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import support.GameOverException;
import support.RemoveActorException;


/**
 * 
 * @author Hampus Dahlin
 * @revisedBy Pontus "Bondi" Eriksson
 * @revisedBy Malin "Nilhet" Thelin
 * @version 0.0.6
 *
 */
public class HeadControl implements ActionListener, PropertyChangeListener {
	
	private long startTime;
	private long[] spawnTimes;
	private int enemyNbr;
	private ActorControl actorControl;
	private EnviromentControl enviromentControl;
	private MusicControl musicControl;
	private UIControl uiControl;
	private Timer time;
	private CardPanel mainPanel;
	//private JFrame mainFrame;
	
	public HeadControl(JFrame mainFrame) {
		musicControl = new MusicControl();
		enviromentControl = new EnviromentControl();
		
		time = new Timer(10, this);
		uiControl = new UIControl(mainFrame);
		mainPanel = new CardPanel(musicControl.getSongList());
		mainFrame.add(mainPanel);
		
		actorControl = new ActorControl(mainPanel.getGamePanel());
		
		mainFrame.setVisible(true);
	}
	
	public void startGame(int songIndex) {
		startTime = System.currentTimeMillis();
		time.start();
		if(songIndex >=0 && songIndex <= musicControl.getSongCount())//hur många låtar vi nu än kommer att ha
		{
			musicControl.setSong(songIndex);
			musicControl.play(true);
		}	
		
		//säg till cardpanel att visa spelet
		mainPanel.goToGame();
	}
	
	
	
	/**
	 * {@inheritDoc}
	 */
	public void actionPerformed(ActionEvent e) {
		try {
			musicControl.analyzeSong();
		} catch (GameOverException exc) {
			System.out.println("Song Finished");
			endGame();
		}
		
		//TODO Spawn a new enemy if there is a beat in the music.
		
		//Moves the actors along their path.
		try {
				actorControl.moveActors();
			} catch (GameOverException exc) {
				endGame();
			} catch (RemoveActorException exc) {
				actorControl.removeActor();
			}
		
		//Testkod
		if (actorControl.getFirstEnemy()!=null) {
			System.out.println(300-actorControl.getFirstEnemy().getPosition().x);
		} else {
			System.out.println("Nästa spawn om: "+ (spawnTimes[enemyNbr] - (System.currentTimeMillis()-startTime)));
		}
		
		
		if (enemyNbr != spawnTimes.length && spawnTimes[enemyNbr] < System.currentTimeMillis()-startTime+100 &&
				spawnTimes[enemyNbr] > System.currentTimeMillis()-startTime-100) {
			actorControl.createActor(mainPanel.getGamePanel());
			System.out.println("Enemy spawned.");
			enemyNbr++;
		}
		
		uiControl.update(actorControl.getNPCList());
	}
	
	public void endGame() {
		
		time.stop();
		
		//säg till cardpanel att visa poäng
		mainPanel.goToScore();
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
	
}
