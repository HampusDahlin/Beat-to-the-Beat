package powerup;

import actors.PC;

/** 
* Enables powerups, giving the player abilities.
* <p> Powerups can increase different stats, abilities etc.
*/ 

public abstract class Powerup {
	private PC owner;
	private final String NAME;
	private final String DESCRIPTION;
	private final int DURATION;
	private final int THRESHOLD;
	
	public Powerup(PC owner, String name, String description, int duration, int threshold) {
		this.owner = owner;
		this.NAME = name;
		this.DESCRIPTION = description;
		this.DURATION = duration;
		this.THRESHOLD = threshold;
	}
	
	/**
	 * Gets how much points needed to get powerup.
	 * @return Amount of points.
	 */
	public int getThreshold(){
		return THRESHOLD;
	}
	
	/**
	 * @return The name of the powerup.
	 */
	public String getName() {
		return NAME;
	}
	
	/**
	 * Describes the powerup in a String.
	 * @return Description.
	 */
	public String getDescription() {
		return DESCRIPTION;
	}
	
	/**
	 * Returns how long the powerup is active in ms.
	 * @return Duration.
	 */
	public int getDuration() {
		return DURATION;
	}
	
	abstract public void effect();
	
	
}
