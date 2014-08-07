package model;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import powerup.ExtraLife;
import powerup.Invincible;
import powerup.Powerup;
import powerup.Regen;
import actors.NPC;
import actors.PC;


public class BeatToTheBeatModel {
	private PC player;
	private List<NPC> npcList;
	private List<Powerup> powerups;
	
	public BeatToTheBeatModel() {
		player = new PC(new Point(450, 0));
		player.setHealth(player.getMaxHealth()); //setting health after so player fires event to gamepanel		
		npcList = new ArrayList<NPC>();
		powerups = new ArrayList<Powerup>();
		addPowerups();
	}
	
	private void addPowerups() {
		powerups.add(new Regen());
		powerups.add(new Invincible());
		powerups.add(new ExtraLife());
	}
	
	public PC getPlayer() {
		return player;
	}
	
	public List<NPC> getNpcList() {
		return npcList;
	}
	
	public List<Powerup> getPowerups() {
		return powerups;
	}
}
