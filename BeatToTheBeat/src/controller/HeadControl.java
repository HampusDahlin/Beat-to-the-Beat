package controller;

import gui.CardPanel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import musichandler.Song;
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
		
		
		//make headcontrol observe all the songPanels. 
		for(JPanel p : mainPanel.getSongPanels()){
			p.addPropertyChangeListener(this);
		}
		
		mainFrame.add(mainPanel);
		
		actorControl = new ActorControl(mainPanel.getGamePanel());
		
		mainFrame.setVisible(true);
	}
	
	public void startGame(Song song) {
		time.start();
		musicControl.play(song, true);
		musicControl.getAnalyzer().addPropertyChangeListener(this);
	}
	
	public void startGame(int songIndex) {
		startTime = System.currentTimeMillis();
		time.start();
		
		if(songIndex >=0 && songIndex <= musicControl.getSongCount())//hur många låtar vi nu än kommer att ha
		{
			musicControl.setSong(songIndex);
			musicControl.play(true);
		}	
		
	}
	
	
	
	/**
	 * {@inheritDoc}
	 */
	public void actionPerformed(ActionEvent e) {
		musicControl.analyzeSong();
		
		//analyzes song, going down to Analyzer..
		musicControl.analyzeSong();
		
		//Moves the actors along their path.
		try {
				actorControl.moveActors();
			} catch (RemoveActorException exc) {
				actorControl.removeActor();
		}
		
		mainPanel.update();
		//uiControl.update(actorControl.getNPCList());
	}
	
	public void endGame(int score) {
		

		musicControl.pause();
		time.stop();
		
		//säg till cardpanel att visa poäng
		mainPanel.goToScore(score);
		
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("play")) {
			startGame((Song) evt.getNewValue());
		} else if(evt.getPropertyName().equals("beat") && (boolean) evt.getOldValue()) {
			actorControl.createActor(mainPanel.getGamePanel());
		} else if (evt.getPropertyName().equals("death")) {
			endGame(actorControl.getScore());
		} else if (evt.getPropertyName().equals("songEnd")) {
			endGame((int) evt.getNewValue());
		}
	}

}
