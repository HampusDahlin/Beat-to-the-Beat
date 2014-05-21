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
import javax.swing.JLabel;
import javax.swing.JPanel;

import services.FileHandler;
import services.HomogeneousFileHandler;
import enviroment.WaveBackground;

/**
 * 
 * @author Björn Hedström
 * @revisedBy Malin Thelin
 * @revisedBy Hampus Dahlin
 * @version 0.0.4
 *
 */

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements PropertyChangeListener {
	
	private WaveBackground backgroundWave;
	private List<Point> npcPosList;
	private int health;
	private int combo;
	private int maxCombo;
	private int score;
	private ImageIcon[] walkImg;
	private MirroredImageIcon[] leftWalkImg;
	private PauseMenuPanel pauspanel;
	
	private int bgIntensity;
	private int walkIndex;
	private ImageIcon[] attackImg;
	private JLabel pause;
	
	private MirroredImageIcon[]leftAttackImg;
	private boolean right;
	private int attackIndex;
	private boolean hit;
	private boolean paused;
	
	public GamePanel(){
		bgIntensity = (int)new HomogeneousFileHandler().load("options.conf").get(0);
		pauspanel = new PauseMenuPanel();
		this.attackImg = new ImageIcon[16];
		//test
		this.leftAttackImg = new MirroredImageIcon[16];
		right = true;
		
		
		attackIndex = -1;
		this.walkImg = new ImageIcon[8];
		this.leftWalkImg = new MirroredImageIcon[8];
		walkIndex = 0;
		npcPosList = new ArrayList<Point>();
		for (int i = 0; i < 8; i++) {
			//this.walkImg[i-1] = new ImageIcon("sprites\\walk1.gif");
			this.walkImg[i] = new ImageIcon("sprites\\walk" + (i+1) + ".gif");
			this.leftWalkImg[i] = new MirroredImageIcon("sprites\\walk" + (i+1) + ".gif");
		}
		
		for (int i = 0; i < 16; i++) {
			this.attackImg[i] = new ImageIcon("sprites\\attack" + (i+1) + ".gif");
			//test
			this.leftAttackImg[i] = new MirroredImageIcon("sprites\\attack" + (i+1) + ".gif");
		}
		
		pauspanel.setVisible(false);
		this.add(pauspanel, CENTER_ALIGNMENT);
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
	
	public PauseMenuPanel getPausepanel(){
		return this.pauspanel;
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
	
	public void pause(){
		this.paused  = true;
		this.pauspanel.setVisible(true);
	}
	
	public void unPause(){
		this.paused = false;
		this.pauspanel.setVisible(false);
	}
	
	public void setBgIntensity(int intensity){
		this.bgIntensity = intensity;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		if (walkIndex == 79) {
			walkIndex = 0;
		} else {
			walkIndex++;
		}
		
		switch(bgIntensity){
		case 1:
			backgroundWave.drawWaves((Graphics2D)g);
			break;
		case 2:
			backgroundWave.drawSinWaves((Graphics2D)g);
			break;
		case 3:
			backgroundWave.drawWaves((Graphics2D)g);
			backgroundWave.drawSinWaves((Graphics2D)g);
			break;
		}
		
		//paint the hitbox, with the extrapoint zones
		int boxWidth = 230;
		int playerPos = 450;
		g.setColor(backgroundWave.getFirstCompCol());
		g.drawRect(playerPos-boxWidth/2+5, 270, boxWidth, 80);
		g.fillRect((playerPos-boxWidth/2)+40,331,boxWidth/10,20);
		g.fillRect((playerPos+boxWidth/2)-50,331,boxWidth/10,20);

		//loops through NPCList and draws them
		g.setColor(Color.BLACK);
		for (Point npc : npcPosList) {
			if(npc.x>450){
				walkImg[walkIndex/10].paintIcon(this, g, npc.x, npc.y);
			}else{
				leftWalkImg[walkIndex/10].paintIcon(this, g, npc.x, npc.y,true);
			}
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
		
		if(this.paused){
			this.pauspanel.setVisible(true);
		}
	}
	
	void update() {
		revalidate();
		repaint();
	}
}
