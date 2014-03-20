package controller;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import support.Movable;
import actors.Actor;


/**
 * 
 * @author Hampus Dahlin
 * @version 0.0.1
 *
 */
public class HeadControl implements KeyListener, PropertyChangeListener {
	
	
	/**
	 * Moves a movable argument.
	 * @param m a movable entity
	 */
	private void moveEntity(Movable m){
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		//attack
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("death")){
			CharacterControl.removeCharacter((Actor)evt.getNewValue());
		}
		
	}
	
}
