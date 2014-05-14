package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import levels.Level;
import enviroment.ABackground;

public class GamePanel extends JPanel implements PropertyChangeListener {
	
	private ABackground background;
	private Level level;
	private List<Point> npcPosList;
	private int health;
	private int combo;
	private int cash;
	private ImageIcon sprite;
	
	public GamePanel(){
		npcPosList = new ArrayList<Point>();
		this.sprite = new ImageIcon("sprites\\ninja.gif");
		
		this.setBackground(new java.awt.Color(255, 255, 255));
		setSize(914, 600);
		setMaximumSize(new java.awt.Dimension(914, 600));
		setMinimumSize(new java.awt.Dimension(914, 600));
		this.setVisible(true);
		
		revalidate();
	}

	public GamePanel(Level level) {
		npcPosList = new ArrayList<Point>();
		setLevel(level);
	}
	
	public void setLevel(Level level){
		this.level = level;
		setBackground(this.level.getBackground());
	}

	private void setBackground(ABackground background) {
		this.background = background;
	}
/*
	public void propertyChange(IndexedPropertyChangeEvent pce) {
		if (pce.getPropertyName().equals("move")) {
			npcPosList.get(pce.getIndex()).setLocation( (Point)pce.getNewValue() );
		} else if (pce.getPropertyName().equals("death")) {
			npcPosList.remove(pce.getIndex());
		}
	}*/

	public void propertyChange(PropertyChangeEvent pce) {
		if (pce.getPropertyName().equals("move") && npcPosList.size() > 0) {
			npcPosList.get(((IndexedPropertyChangeEvent) pce).getIndex()).setLocation(( (Point)pce.getNewValue() ));
		} else if (pce.getPropertyName().equals("newNPC")) {
			npcPosList.add(new Point((Point) pce.getNewValue()));
		} else if (pce.getPropertyName().equals("death")) {
			//avsluta spel
		} else if (pce.getPropertyName().equals("combo")) {
			combo = (int) pce.getNewValue();
		} else if (pce.getPropertyName().equals("hp")) {
			health = (int) pce.getNewValue();
		} else if (pce.getPropertyName().equals("cash")) {
			cash = (int) pce.getNewValue();
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//loops through NPCList and draws them
		g.setColor(Color.BLACK);
		for (Point npc : npcPosList) {
			sprite.paintIcon(this, g, npc.x, npc.y);
			//g.fillRect(npc.x, npc.y, 10, 10);
		}
		
		sprite.paintIcon(this, g, 500, 100);
		
		g.drawRect(4, 16, 101, 11);
		g.fillRect(10, 40, 200, 200);
		g.setColor(Color.RED);
		g.fillRect(5, 15, health*20, 10);
		

		//combo
		//cash?
	}
	
	public void update() {
		revalidate();
		repaint();
	}
}
