package gui;

import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import levels.Level;
import enviroment.IBackground;

public class GamePanel extends JPanel implements PropertyChangeListener {
	
	IBackground background;
	Level level;
	List<Object> NPCList;
	
	public void addNewGame(Level level){
		this.level = level;
		addBackground(this.level.getBackground());
		this.NPCList = new ArrayList<Object>();
	}

	private void addBackground(IBackground background) {
		this.background = background;
	}

	public void propertyChange(IndexedPropertyChangeEvent pce) {
		if (pce.getPropertyName().equals("move")) {
			//MOVE NPCList.get(pce.getIndex()).MOVE();
		} else if (pce.getPropertyName().equals("death")) {
			NPCList.remove(pce.getIndex());
		}
	}

	public void propertyChange(PropertyChangeEvent evt) {
		
	}
}
