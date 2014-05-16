package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import actors.PC;
import levels.Level;
import enviroment.ABackground;

public class GamePanel extends JPanel implements PropertyChangeListener {
	
	private ABackground background;
	private Level level;
	private List<Point> npcPosList;
	private int health;
	private int combo;
	private int maxCombo;
	private int score;
	private ImageIcon[] walkImg;
	private int walkIndex;
	
	public GamePanel(){
		this.walkImg = new ImageIcon[16];
		walkIndex = 0;
		npcPosList = new ArrayList<Point>();
		for (int i = 0; i < 16; i++) {
			//this.walkImg[i-1] = new ImageIcon("sprites\\walk1.gif");
			this.walkImg[i] = new ImageIcon("sprites\\walk" + (i+1) + ".gif");
		}
		
		this.setBackground(new java.awt.Color(255, 255, 255));
		setSize(914, 600);
		setMaximumSize(new java.awt.Dimension(914, 600));
		setMinimumSize(new java.awt.Dimension(914, 600));
		this.setVisible(true);
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

	public void propertyChange(PropertyChangeEvent pce) {
		if (pce.getPropertyName().equals("move") && npcPosList.size() > 0) {
			npcPosList.get(((IndexedPropertyChangeEvent) pce).getIndex()).setLocation(( (Point)pce.getNewValue() ));
		} else if (pce.getPropertyName().equals("newNPC")) {
			npcPosList.add(new Point((Point) pce.getNewValue()));
		} else if (pce.getPropertyName().equals("removeNPC")) {
			npcPosList.remove((int)pce.getNewValue());
			System.out.println(npcPosList.size());
		} else if (pce.getPropertyName().equals("death")) {
			//avsluta spel
		} else if (pce.getPropertyName().equals("combo")) {
			
			combo = (int) pce.getNewValue();
		} else if (pce.getPropertyName().equals("hp")) {
			health = (int) pce.getNewValue();
		} else if (pce.getPropertyName().equals("score")) {
			score = (int) pce.getNewValue();
		}else if(pce.getPropertyName().equals("max")) {
			if((int)pce.getNewValue() == 0){
				maxCombo = 0;
			}
			if(combo > maxCombo){
				maxCombo = combo;
			}				
		} else if (pce.getPropertyName().equals("attack")) {
			
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (walkIndex == 79) {
			walkIndex = 0;
		} else {
			walkIndex++;
		}

		//loops through NPCList and draws them
		g.setColor(Color.BLACK);
		for (Point npc : npcPosList) {
			(npc.x > 450 ? walkImg[walkIndex/10] : walkImg[(walkIndex/10)+8]).paintIcon(this, g, npc.x, npc.y);
			//g.fillRect(npc.x, npc.y, 10, 10);
		}

		walkImg[0].paintIcon(this, g, 450, 300);

		//draw the healthbar
		g.drawRect(4, 16, 101, 11);
		g.setColor(Color.RED);
		g.fillRect(4, 16, health*20, 11);
		
		
		
		g.setFont(new Font("Sans", Font.BOLD, 24));
		g.drawString("Combo:",750,20);
		g.drawString(""+combo, 780, 40);
		//drawing maxcombo on screen
		g.drawString("Max:",750,60);
		g.drawString(""+maxCombo,780,80);
		
		
		//draw the score on screen
		g.drawString("Score:", 420, 20);
		g.drawString(""+score,450 , 40);
	}
	
	public void update() {
		revalidate();
		repaint();
	}
}
