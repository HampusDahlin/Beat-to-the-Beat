package controller;
import gui.CardPanel;
import gui.GamePanel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
public class HeadControl implements ActionListener, PropertyChangeListener, KeyListener {
	
	private ActorControl actorControl;
	private MusicControl musicControl;
	private Timer time;
	private CardPanel mainPanel;
	
	private Timer menuTime;
	
	public HeadControl(JFrame mainFrame) {
		musicControl = new MusicControl();
		
		time = new Timer(10, this);
		//this is for looping music in menu
		menuTime = new Timer(2000,this);
		
		mainPanel = new CardPanel(musicControl.getSongList(), musicControl.getGenres());
		
		mainPanel.getOptionsPanel().addPropertyChangeListener(this);
		
		//make headcontrol observe all the songPanels. 
		for(JPanel p : mainPanel.getSongPanels()){
			p.addPropertyChangeListener(this);
		}
		
		mainFrame.add(mainPanel);	
		
		//creates an actorcontrol. the gamepanel is sent to listen to a PC *player*
		//also this headcontrol will listen to the PC *player*
		actorControl = new ActorControl((PropertyChangeListener) mainPanel.getGamePanel());
		actorControl.getPlayer().addPropertyChangeListener(this);
		
		//adds keylistener to the game
		mainFrame.setFocusable(true);
		mainFrame.addKeyListener(this);
		mainFrame.setVisible(true);
		
		//background music for the menu.
		musicControl.playRandom();
		musicControl.getAnalyzer().addPropertyChangeListener(this);
		menuTime.start();
		
	}
	
	private void startGame(Song song) {
		
		//pause the music in menu
		musicControl.pause();
		menuTime.stop();
		time.start();
		
		//start the music for the game
		musicControl.play(song, true);
		musicControl.getAnalyzer().addPropertyChangeListener(this);
		
		//reset the PC values
		actorControl.resetHealth();
		actorControl.resetScore();
		actorControl.resetCombo();
		actorControl.resetMaxCombo();
		//empty out the npclist
		actorControl.emptyNPCList();
	}	
	
	/**
	 * {@inheritDoc}
	 */
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource().equals(menuTime)){
			musicControl.loopMusic(false);
			musicControl.analyzeSong();
		}else{
			musicControl.analyzeSong();
			//Moves the actors along their path.
			try {
					actorControl.moveActors();
				} catch (RemoveActorException exc) {
					actorControl.removeActor();
				}
			mainPanel.update();
			}
	}
	
	private void endGame(int score) {
		//stop the music and the gametime
		musicControl.pause();
		time.stop();
		
		//tells cardpanel to go to the scorescreen, and play background music again 
		mainPanel.goToScore(score);

		//trying out some code to make the music loop
		menuTime.start();
		
		
	}
	
	
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("play")) {
			startGame((Song) evt.getNewValue());
		} else if(evt.getPropertyName().equals("beat")){
			if((boolean) evt.getOldValue()) {
				actorControl.createActor(mainPanel.getGamePanel());
			}
			((GamePanel)(mainPanel.getGamePanel())).getBackgroundWave().updateBackground((float[][])evt.getNewValue(), (boolean)evt.getOldValue());
			
			if(menuTime.isRunning()){
				mainPanel.beat();
			}
		} else if (evt.getPropertyName().equals("death")) {
			endGame((int) evt.getNewValue());
		} else if (evt.getPropertyName().equals("songEnd")) {
			endGame(actorControl.getScore());
		} else if (evt.getPropertyName().equals("volumeChange")) {
			
		} else if (evt.getPropertyName().equals("backgroundSlider")) {
			
		}
	}
	
	
	public void keyPressed(KeyEvent evt) {
		if(time.isRunning()){
			if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
				actorControl.playerAttack(false);
			} else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
				actorControl.playerAttack(true);
			} else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
				time.stop();
				((GamePanel)(mainPanel.getGamePanel())).pause();
			}
		}else{
			if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
				time.start();
				((GamePanel)(mainPanel.getGamePanel())).unPause();
			}
		}
	}
	
	public void keyReleased(KeyEvent evt) { }
	public void keyTyped(KeyEvent evt) { }
	
} //end HeadControl