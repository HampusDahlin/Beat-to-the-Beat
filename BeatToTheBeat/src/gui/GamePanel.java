package gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

import levels.Level;
import enviroment.IBackground;

public class GamePanel extends JPanel implements PropertyChangeListener {
	
	IBackground background;
	Level level;
	
	public void addNewGame(Level level){
		this.level = level;
		addBackground(this.level.getBackground());
	}

	private void addBackground(IBackground background) {
		this.background = background;
	}

	public void propertyChange(PropertyChangeEvent pce) {
		
	}
}
