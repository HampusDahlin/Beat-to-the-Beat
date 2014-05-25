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
import javax.swing.border.EmptyBorder;

import enviroment.IBackground;
import enviroment.WaveBackground;

/**
 * Displays the Gamefield, where all the action happens.
 * @author Björn Hedström
 * @revisedBy Malin Thelin
 * @revisedBy Hampus Dahlin
 * @revisedBy Pontus Eriksson
 * @version 0.1.0
 */

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements PropertyChangeListener {
	
	private List<IBackground> background;
	private List<Point> npcPosList;
	private int health;
	private int combo;
	private int maxCombo;
	private int score;
	private int lives;
	private int range;

	private boolean right;
	private int walkIndex;
	private int attackIndex;
	private final ImageIcon[] walkImg;
	private final MirroredImageIcon[] leftWalkImg;
	private final ImageIcon[] attackImg;
	private final MirroredImageIcon[]leftAttackImg;
	private final ImageIcon hat;

	private PauseMenuPanel pauspanel;
	private boolean paused;
	private boolean isInvincible;
	
	/**
	 * Creates a GamePanel containing a player, NPC-list etc.
	 */
	public GamePanel(){
		
		hat = new ImageIcon("sprites\\hatt.gif");
		
		background = new ArrayList<IBackground>();
			
		pauspanel = new PauseMenuPanel();
		pauspanel.setVisible(false);
		this.add(pauspanel, CENTER_ALIGNMENT);
		this.setBorder(new EmptyBorder(100, 0, 0, 0));
		
		npcPosList = new ArrayList<Point>();

		attackIndex = -1;
		walkIndex = 0;
		range = 120;
		
		this.attackImg = new ImageIcon[16];
		this.leftAttackImg = new MirroredImageIcon[16];
		
		//initiates attackanimation
		for (int i = 0; i < 16; i++) {
			this.attackImg[i] = new ImageIcon("sprites\\attack" + (i+1) + ".gif");
			this.leftAttackImg[i] = new MirroredImageIcon("sprites\\attack" + (i+1) + ".gif");
		}
		
		this.walkImg = new ImageIcon[8];
		this.leftWalkImg = new MirroredImageIcon[8];
		
		//initiates walkanimation
		for (int i = 0; i < 8; i++) {
			this.walkImg[i] = new ImageIcon("sprites\\walk" + (i+1) + ".gif");
			this.leftWalkImg[i] = new MirroredImageIcon("sprites\\walk" + (i+1) + ".gif");
		}
		
		this.setBackground(new java.awt.Color(0,0,0));
		this.addBackgroundWave(new WaveBackground());
		setSize(914, 600);
		setMaximumSize(new java.awt.Dimension(914, 600));
		setMinimumSize(new java.awt.Dimension(914, 600));
		this.setVisible(true);
	}

	public void addBackgroundWave(IBackground background) {
		this.background.add(background);
	}
	
	public List<IBackground> getBackgroundWaves(){
		return this.background;
	}
	
	public PauseMenuPanel getPausepanel(){
		return this.pauspanel;
	}

	public void propertyChange(PropertyChangeEvent pce) {
		if (pce.getPropertyName().equals("move") && npcPosList.size() > 0) {
			npcPosList.get(((IndexedPropertyChangeEvent) pce).getIndex()).setLocation(( (Point)pce.getNewValue() ));
		} else if (pce.getPropertyName().equals("newNPC")) {
			npcPosList.add(new Point((Point) pce.getNewValue()));
		} else if (pce.getPropertyName().equals("removeNPC") && npcPosList.size()>0) {
			npcPosList.remove((int)pce.getNewValue());
		} else if (pce.getPropertyName().equals("death")) {
			npcPosList.clear();
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
			attackIndex = 0;
			right = (int)pce.getOldValue() == 1;
		}else if (pce.getPropertyName().equals("life")) {
			lives = (int)pce.getNewValue();
		}else if (pce.getPropertyName().equals("invincible")) {
			if((boolean)pce.getNewValue()){
				isInvincible = true;
			}else{
				isInvincible = false;
			}
		} else if (pce.getPropertyName().equals("range")) {
			range = (int)pce.getNewValue();
        }

	}
	
	public void pause(){
		this.paused  = true;
		this.pauspanel.setVisible(true);
	}
	
	public void unPause(){
		this.paused = false;
		this.pauspanel.setVisible(false);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (walkIndex == 79) {
			walkIndex = 0;
		} else {
			walkIndex++;
		}
		
		for(IBackground b : background){
			b.paintBackground((Graphics2D)g,range);
		}
		
		//loops through NPCList and draws them
		g.setColor(Color.BLACK);
		for (Point npc : npcPosList) {
			if(npc.x>450){
				walkImg[walkIndex/10].paintIcon(this, g, npc.x, npc.y);
			}else{
				leftWalkImg[walkIndex/10].paintIcon(this, g, npc.x, npc.y,true);
			}
		}
		
		//draw hat indicator on the first enemy
		if(npcPosList.size()>0){
			if(npcPosList.get(0).x > 450){
				hat.paintIcon(this, g, npcPosList.get(0).x+5, npcPosList.get(0).y-13);
			}else{
				hat.paintIcon(this, g, npcPosList.get(0).x-23, npcPosList.get(0).y-13);
			}
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
				leftAttackImg[attackIndex/2].paintIcon(this, g, 480, 150, false);
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
		
		//draw extralives
		for(int i = 1; i < lives; i++){
			g.setColor(Color.PINK);
			g.fillRect(10 + (i-1)*20, 50, 10, 10);
		}
		
		//draw invincible text
		if(isInvincible){
			g.setColor(Color.WHITE);
			g.drawString("You are INVINCIBLE", 350, 400);
		}
		
		
		if(this.paused){
			this.pauspanel.setVisible(true);
		}
	}
	
	void update() {
		revalidate();
		repaint();
	}
}
