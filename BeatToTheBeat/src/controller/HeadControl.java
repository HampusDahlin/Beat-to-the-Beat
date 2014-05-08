package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import support.GameOverException;
import support.RemoveActorException;
import enviroment.WaveBackground;
import gui.CardPanel;
import gui.SongSelection;


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
	private UIControl uiControl;
	private Timer time;
	private CardPanel mainPanel;
	private JFrame mainFrame;
	
	public HeadControl(JFrame mainFrame) {
		actorControl = new ActorControl();
		enviromentControl = new EnviromentControl(new WaveBackground(), musicControl.getAnalyzer());
		musicControl = new MusicControl();
		time = new Timer(10, this);
		uiControl = new UIControl(mainFrame);
		mainFrame.setVisible(true);
		mainPanel = new CardPanel(musicControl.getSongList()); // h�r skall en songlist skickas med eventually
	}
	
	public void startGame(int songIndex) {
		startTime = System.currentTimeMillis();
		time.start();
		int amntOfSongs = 10;//tillf�llig! bara f�r att if satsen skall se vettig ut. kan finnas i en annan klass senare?
		if(songIndex >=0 && songIndex <= amntOfSongs)//hur m�nga l�tar vi nu �n kommer att ha
		{
			musicControl.setSong(songIndex);
			musicControl.play();
		}	
		
		
		
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
		//v�r "musikbeat"
		if (actorControl.getFirstEnemy()!=null) {
			System.out.println(300-actorControl.getFirstEnemy().getPosition().x);
		} else {
			System.out.println("N�sta spawn om: "+ (spawnTimes[enemyNbr] - (System.currentTimeMillis()-startTime)));
		}
		
		
		if (enemyNbr != spawnTimes.length && spawnTimes[enemyNbr] < System.currentTimeMillis()-startTime+100 &&
				spawnTimes[enemyNbr] > System.currentTimeMillis()-startTime-100) {
			actorControl.createActor();
			System.out.println("Enemy spawned.");
			enemyNbr++;
		}
		
		uiControl.update(actorControl.getNPCList());
	}
	
	public void endGame() {
		
		time.stop();
		
		//typ pseudo-kod
		mainFrame.remove(mainPanel);
		
		
	}
	
	
}
