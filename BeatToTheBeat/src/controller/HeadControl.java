package controller;
import gui.CardPanel;
import gui.GamePanel;
import gui.Options;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import musichandler.Song;
import services.HomogeneousFileHandler;
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
	private Song placeholder;
	
	private Timer menuTime;
	
	public HeadControl(JFrame mainFrame) {
		
		fileCheck();
		
		musicControl = new MusicControl();
		
		time = new Timer(10, this);
		
		//this is for looping music in menu
		menuTime = new Timer(10,this);
		
		mainPanel = new CardPanel(musicControl.getSongList(), musicControl.getGenres());
		
		((Options)(mainPanel.getOptionsPanel())).pcs.addPropertyChangeListener(this);
		((GamePanel)(mainPanel.getGamePanel())).getPausepanel().pcs.addPropertyChangeListener(this);
		
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

	private void fileCheck() {
		try {
			new FileInputStream(new File("options.conf"));
		} catch (FileNotFoundException e) {
			new HomogeneousFileHandler().saveAs("options.conf", 1);
		}
	}
	
	private void startGame(Song song) {
		
		//pause the music in menu
		musicControl.pause();
		menuTime.stop();
		time.start();
		
		//start the music for the game
		musicControl.play(song, true);
		musicControl.getAnalyzer().addPropertyChangeListener(this);
		
		//gives the GamePanel information about the song
		
		//reset the PC values
		actorControl.resetHealth();
		actorControl.resetScore();
		actorControl.resetCombo();
		actorControl.resetMaxCombo();
		actorControl.resetLives();
		//empty out the npclist
		actorControl.emptyNPCList();
	}	
	
	/**
	 * {@inheritDoc}
	 */
	public void actionPerformed(ActionEvent e) {
		
		musicControl.analyzeSong();
		if(e.getSource().equals(menuTime)){
			musicControl.loopMusic(false);
		}else{
			//Moves the actors along their path.
			try {
					actorControl.moveActors();
				} catch (RemoveActorException exc) {
					actorControl.removeActor();
				}
			mainPanel.update();
			}
	}
	
	private void endGame(int score, Song song) {
		//stop the music and the gametime
		musicControl.pause();
		time.stop();
		
		//tells cardpanel to go to the scorescreen, and play background music again 
		mainPanel.goToScore(score, song);

		//trying out some code to make the music loop
		menuTime.start();
		
		
	}
	
	
	public void propertyChange(PropertyChangeEvent evt) {
		if (menuTime.isRunning()) {
			mainPanel.repaint();
		}
		
		if (evt.getPropertyName().equals("play")) {
			placeholder = (Song)evt.getNewValue();
			startGame((Song) evt.getNewValue());
		} else if(evt.getPropertyName().equals("beat")){
			if((boolean) evt.getOldValue()) {
				if (menuTime.isRunning()) {
					mainPanel.beat();
				} else {
					actorControl.createActor(mainPanel.getGamePanel());
				}
			}
			((GamePanel)(mainPanel.getGamePanel())).getBackgroundWave().updateBackground(
					(float[][])evt.getNewValue(), (boolean)evt.getOldValue());
		} else if (evt.getPropertyName().equals("death")) {
			endGame((int) evt.getNewValue(), placeholder);
		} else if (evt.getPropertyName().equals("songEnd")) {
			endGame(actorControl.getScore(), placeholder);
		} else if (evt.getPropertyName().equals("volumeChange")) {
			
		} else if (evt.getPropertyName().equals("bgSlider")) {
			((GamePanel)(mainPanel.getGamePanel())).setBgIntensity((int)evt.getNewValue());
		} else if (evt.getPropertyName().equals("resumeGame")){
			time.start();
			musicControl.resume();
			((GamePanel)(mainPanel.getGamePanel())).unPause();
		} else if (evt.getPropertyName().equals("quitGame")){
			((GamePanel)(mainPanel.getGamePanel())).unPause();
			endGame(0,null);
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
				musicControl.pause();
				((GamePanel)(mainPanel.getGamePanel())).pause();
			}
		}else{
			if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
				time.start();
				musicControl.resume();
				((GamePanel)(mainPanel.getGamePanel())).unPause();
			}
		}
	}
	
	public void keyReleased(KeyEvent evt) { }
	public void keyTyped(KeyEvent evt) { }
	
} //end HeadControl