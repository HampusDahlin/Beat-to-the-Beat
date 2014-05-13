package gui;

import java.awt.Graphics;
import java.awt.Point;
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
	List<Point> NPCList;
	
	public void addNewGame(Level level){
		this.level = level;
		addBackground(this.level.getBackground());
		this.NPCList = new ArrayList<Point>();
	}

	private void addBackground(IBackground background) {
		this.background = background;
	}

	public void propertyChange(IndexedPropertyChangeEvent pce) {
		if (pce.getPropertyName().equals("move")) {
			NPCList.get(pce.getIndex()).setLocation( (Point)pce.getNewValue() );
		} else if (pce.getPropertyName().equals("death")) {
			NPCList.remove(pce.getIndex());
		}
	}

	public void propertyChange(PropertyChangeEvent evt) {
		
	}
	
	public void paint(Graphics g) {
		//loops through NPCList and draws them
		for (Point npc : NPCList) {
			g.drawRect(npc.x, npc.y, 10, 10);
		}
	}
}
