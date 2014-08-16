package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.BeatToTheBeatModel;
import model.actors.NPC;
import model.enviroment.IBackground;
import model.enviroment.WaveBackground;

/**
 * Displays the Gamefield, where all the action happens.
 * @author Björn Hedström
 * @revisedBy Malin Thelin
 * @revisedBy Hampus Dahlin
 * @revisedBy Pontus Eriksson
 * @version 0.2.0
 */

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements PropertyChangeListener {
	private BeatToTheBeatModel model;
	
	private List<IBackground> background;

	private int walkIndex;
	private final MirrorableImageIcon[] walkImg;
	private final ImageIcon hat;
	
	private final gui.MirrorableImageIcon[] attackImg;

	private PauseMenuPanel pauspanel;
	private boolean paused;
	private boolean isInvincible;
	
	/**
	 * Creates a GamePanel containing a player, NPC-list etc.
	 */
	GamePanel(BeatToTheBeatModel model) {
		this.model = model;
		
		hat = new ImageIcon("sprites\\hatt.gif");
		
		background = new ArrayList<IBackground>();
			
		pauspanel = new PauseMenuPanel();
		pauspanel.setVisible(false);
		this.add(pauspanel, CENTER_ALIGNMENT);
		this.setBorder(new EmptyBorder(100, 0, 0, 0));

		walkIndex = 0;
		
		this.walkImg = new MirrorableImageIcon[8];
		
		//initiates walkanimation
		for (int i = 0; i < 8; i++) {
			this.walkImg[i] = new MirrorableImageIcon("sprites\\walk" + (i+1) + ".gif");
		}

		this.attackImg = new MirrorableImageIcon[16];
		
		//initiates attackanimation
		for (int i = 0; i < 16; i++) {
			this.attackImg[i] = new MirrorableImageIcon("sprites\\attack" + (i+1) + ".gif");
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
		if (pce.getPropertyName().equals("invincible")) {
			isInvincible = (boolean)pce.getNewValue();
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
			b.paintBackground((Graphics2D)g, model.getPlayer().getRange());
		}
		
		//loops through NPCList and draws them
		g.setColor(Color.BLACK);
		for (NPC npc : model.getNpcList()) {
			if (npc.getPosition().x > 450) {
				walkImg[walkIndex/10].paintIcon(this, g, npc.getPosition().x, npc.getPosition().y);
			}else{
				walkImg[walkIndex/10].paintMirroredIcon(this, g, npc.getPosition().x, npc.getPosition().y,true);
			}
		}
		
		//draw hat indicator on the first enemy
		if (model.getNpcList().size()>0) {
			if (model.getNpcList().get(0).getPosition().x > 450) {
				hat.paintIcon(this, g, model.getNpcList().get(0).getPosition().x+5, model.getNpcList().get(0).getPosition().y-13);
			}else{
				hat.paintIcon(this, g, model.getNpcList().get(0).getPosition().x-23, model.getNpcList().get(0).getPosition().y-13);
			}
		}
		
		//draw the player
		int attackIndex = model.getPlayer().getAttackIndex();
		
		if (attackIndex < 0) {
			walkImg[0].paintIcon(this, g,
					(457-walkImg[0].getIconWidth()/2), 300);
		} else {
			if (!model.getPlayer().hitsRight()) {
				attackImg[attackIndex/2].paintIcon(this, g,
						(457-attackImg[attackIndex/5].getIconWidth()/2), 300);
			} else {
				attackImg[attackIndex/2].paintMirroredIcon(this, g, 480, 150, false);
			}
			
			//if animation complete, reset it, otherwise increment attackIndex.
			model.getPlayer().setAttackIndex(attackIndex < 30 ? attackIndex+1 : -1);
		}

		//draw the healthbar
		g.drawRect(4, 16, 100, 11);
		g.setColor(Color.RED);
		g.fillRect(4, 16, model.getPlayer().getHealth()*10, 11);
		
		//drawing combo,maxcombo and score on screen
		g.setColor(Color.WHITE);
		g.setFont(new Font("Sans", Font.BOLD, 24));
		g.drawString("Combo:",750,20);
		g.drawString("Max:",750,60);
		g.drawString("Score:", 420, 20);
		g.setColor(Color.RED);
		g.drawString(""+model.getPlayer().getCombo(), 780, 40);
		g.drawString(""+model.getPlayer().getMaxCombo(),780,80);
		g.drawString(""+model.getPlayer().getScore(),450 , 40);
		
		//draw extralives
		for(int i = 1; i < model.getPlayer().getLives(); i++){
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
