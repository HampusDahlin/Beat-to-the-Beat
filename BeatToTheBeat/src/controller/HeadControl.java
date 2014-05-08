package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import enviroment.WaveBackground;
import support.GameOverException;
import support.Movable;
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
	private UIControl uiControl;
	private Timer time;
	private ChooseSong songPanel;
	private GamePanel gamePanel;
	private JFrame mainFrame;
	
	public HeadControl(JFrame mainFrame) {
		actorControl = new ActorControl();
		enviromentControl = new EnviromentControl(new WaveBackground(), musicControl.getAnalyzer());
		musicControl = new MusicControl();
		time = new Timer(10, this);
		uiControl = new UIControl(mainFrame);
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
		//osäkert vart detta skall göras.. 
		mainFrame.remove(songPanel);
		mainFrame.add(gamePanel);
	}
	
	
	
	/**
	 * {@inheritDoc}
	 */
	public void actionPerformed(ActionEvent e) {
		boolean beat;
		try {
			beat = musicControl.isBeat();
		} catch (GameOverException exc) {
			//låten avklarad
		}
		
		enviromentControl.updateBackground(musicControl.getWave(), beat);
		
		
		//Spawns a new enemy if there is a beat in the music.
		if (beat) {
			actorControl.createActor();
		}
		
		//Moves the actors along their path.
		try {
				actorControl.moveActors();
			} catch (GameOverException exc) {
				endGame();
			} catch (RemoveActorException exc) {
				actorControl.removeActor();
			}
		
		//see if there's still a beat
		try {
			musicControl.isBeat();
		}catch(GameOverException exc){
			endGame();
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
		
		uiControl.update(actorControl.getNPCList());
	}
	
	public void endGame() {
		
		time.stop();
		
		//typ pseudoeu-kod
		mainFrame.remove(gamePanel);
		mainFrame.add(resultPanel);
		
		
	}
	
	
}
