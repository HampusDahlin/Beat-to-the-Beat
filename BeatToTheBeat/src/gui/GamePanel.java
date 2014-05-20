package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import enviroment.WaveBackground;

/**
 * 
 * @author Björn Hedström
 * @revisedBy Malin "Nilhet" Thelin
 * @version 0.0.3
 *
 */

public class GamePanel extends JPanel implements PropertyChangeListener {
	
	private WaveBackground backgroundWave;
	private List<Point> npcPosList;
	private int health;
	private int combo;
	private int maxCombo;
	private int score;
	private ImageIcon[] walkImg;
	private int walkIndex;
	private ImageIcon[] attackImg;
	//test
	private ImageIcon[]leftAttackImg;
	private boolean right;
	
	private int attackIndex;
	private boolean hit;
	
	
	public GamePanel(){
		this.attackImg = new ImageIcon[16];
		//test
		this.leftAttackImg = new MirroredImageIcon[16];
		right = true;
		
		attackIndex = -1;
		this.walkImg = new ImageIcon[16];
		walkIndex = 0;
		npcPosList = new ArrayList<Point>();
		for (int i = 0; i < 16; i++) {
			//this.walkImg[i-1] = new ImageIcon("sprites\\walk1.gif");
			this.walkImg[i] = new ImageIcon("sprites\\walk" + (i+1) + ".gif");
		}
		
		for (int i = 0; i < 16; i++) {
			this.attackImg[i] = new ImageIcon("sprites\\attack" + (i+1) + ".gif");
			//test
			this.leftAttackImg[i] = new MirroredImageIcon("sprites\\attack" + (i+1) + ".gif");
		}
		
		this.setBackground(new java.awt.Color(0,0,0));
		this.setBackgroundWave(new WaveBackground());
		setSize(914, 600);
		setMaximumSize(new java.awt.Dimension(914, 600));
		setMinimumSize(new java.awt.Dimension(914, 600));
		this.setVisible(true);
	}

	private void setBackgroundWave(WaveBackground background) {
		this.backgroundWave = background;
	}
	
	public WaveBackground getBackgroundWave(){
		return this.backgroundWave;
	}

	public void propertyChange(PropertyChangeEvent pce) {
		if (pce.getPropertyName().equals("move") && npcPosList.size() > 0) {
			npcPosList.get(((IndexedPropertyChangeEvent) pce).getIndex()).setLocation(( (Point)pce.getNewValue() ));
		} else if (pce.getPropertyName().equals("newNPC")) {
			npcPosList.add(new Point((Point) pce.getNewValue()));
		} else if (pce.getPropertyName().equals("removeNPC")) {
			npcPosList.remove((int)pce.getNewValue());
		} else if (pce.getPropertyName().equals("death")) {
			npcPosList.clear();
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
			hit = (boolean) pce.getNewValue();
			//attackIndex = ((int) pce.getOldValue() == 1 ? 0 : 16);
			attackIndex = 0;
			right = ((int)pce.getOldValue() == 1 ? true : false);
		}
	}
	
	Long time = null;
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		if (walkIndex == 79) {
			walkIndex = 0;
		} else {
			walkIndex++;
		}
		
		backgroundWave.drawWaves((Graphics2D)g);

		//loops through NPCList and draws them
		g.setColor(Color.BLACK);
		for (Point npc : npcPosList) {
			(npc.x > 450 ? walkImg[walkIndex/10] : walkImg[(walkIndex/10)+8]).paintIcon(this, g, npc.x, npc.y);
			//g.fillRect(npc.x, npc.y, 10, 10);
		}
		
		//draw the player
		if (attackIndex < 0) {
			walkImg[0].paintIcon(this, g,
					(457-walkImg[0].getIconWidth()/2), 300);
		} else {
			if(right){
				attackImg[attackIndex/2].paintIcon(this, g,
						(457-attackImg[attackIndex/5].getIconWidth()/2), 300);
				if (attackIndex < 30) {
					attackIndex++;
				} else {
					attackIndex = -1;
				}
				
			}else{
				leftAttackImg[attackIndex/2].paintIcon(this, g,480, 150);
				if (attackIndex < 30) {
					attackIndex++;
				} else {
					attackIndex = -1;
				}
			}
		}

		//draw the healthbar
		g.drawRect(4, 16, 100, 11);
		g.setColor(Color.RED);
		g.fillRect(4, 16, health*10, 11);
		
		//drawing combo,maxcombo and score on screen
		g.setColor(Color.WHITE);
		g.setFont(new Font("Sans", Font.BOLD, 24));
		g.drawString("Combo:",750,20);
		g.drawString("Max:",750,60);
		g.drawString("Score:", 420, 20);
		g.setColor(Color.RED);
		g.drawString(""+combo, 780, 40);
		g.drawString(""+maxCombo,780,80);
		g.drawString(""+score,450 , 40);
		
		
	}
	
	public void update() {
		revalidate();
		repaint();
	}
}
