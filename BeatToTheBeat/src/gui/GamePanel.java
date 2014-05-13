package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import levels.Level;
import enviroment.ABackground;
import enviroment.WaveBackground;

public class GamePanel extends JPanel implements PropertyChangeListener {
	
	private ABackground background;
	private Level level;
	private List<Point> npcPosList;
	private int health;
	private int combo;
	private int cash;
	
	public GamePanel(Level level) {
		npcPosList = new ArrayList<Point>();
		setLevel(level);
		
	}
	
	public void setLevel(Level level){
		this.level = level;
		setBackground(this.level.getBackground());
		this.npcPosList = new ArrayList<Point>();
	}

	private void setBackground(ABackground background) {
		this.background = background;
	}

	public void propertyChange(IndexedPropertyChangeEvent pce) {
		if (pce.getPropertyName().equals("move")) {
			npcPosList.get(pce.getIndex()).setLocation( (Point)pce.getNewValue() );
		} else if (pce.getPropertyName().equals("death")) {
			npcPosList.remove(pce.getIndex());
		}
	}

	public void propertyChange(PropertyChangeEvent pce) {
		if (pce.getPropertyName().equals("combo")) {
			combo = (int) pce.getNewValue();
		} else if (pce.getPropertyName().equals("hp")) {
			health = (int) pce.getNewValue();
		} else if (pce.getPropertyName().equals("cash")) {
			cash = (int) pce.getNewValue();
		}
	}
	
	public void paint(Graphics g) {
		//loops through NPCList and draws them
		for (Point npc : npcPosList) {
			g.drawRect(npc.x, npc.y, 10, 10);
		}
		
		g.drawRect(4, 16, 101, 11);
		g.setColor(Color.RED);
		g.fillRect(5, 15, health*20, 10);

		//combo
		//cash?
	}
}
