package powerup;

import actors.PC;



public abstract class Powerup {
	private String name;
	private String description;
	private Double duration;
	private PC owner;
	private int threshold;
	
	public void setThreshold(int threshold){
		this.threshold = threshold;
	}
	
	public int getThreshold(){
		return this.threshold;
	}
	
	public void setOwner(PC newOwner) {
		owner = newOwner;
	}
	
	public PC getOwner() {
		return owner;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String newDescription) {
		description = newDescription;
	}
	
	public Double getDuration() {
		return duration;
	}
	
	public void setDuration(double newDuration) {
		duration = newDuration;
	}
	
	abstract public void effect();
	
	
}
