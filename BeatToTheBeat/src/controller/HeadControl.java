package controller;
import gui.CardPanel;
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
		
		mainFrame.setFocusable(true);
		mainFrame.addKeyListener(this);
		mainFrame.setVisible(true);
		
		//bakgrundsmusik i menyn.
		musicControl.playRandom();
	}
	
	public void startGame(Song song) {
		musicControl.pause();
		time.start();
		musicControl.play(song, true);
		musicControl.getAnalyzer().addPropertyChangeListener(this);
	}
	
	public void startGame(int songIndex) {
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
	public void keyPressed(KeyEvent evt) {
		if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
			actorControl.playerAttack(false);
		} else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
			actorControl.playerAttack(true);
		}
	}
	public void keyReleased(KeyEvent evt) {
		
	}
	public void keyTyped(KeyEvent evt) {
		
	}
}