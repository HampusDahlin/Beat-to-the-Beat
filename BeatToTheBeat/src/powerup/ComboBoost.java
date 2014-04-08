package powerup;

import actors.PC;

public class ComboBoost extends Powerup {

	private int previousCombo;
	
	public ComboBoost(PC player) {
		setOwner(player);
		setName("Combo Booster");
		previousCombo = 0;
		setDescription("Double your combo!");
	}
		
	@Override
	//doubles the combo if the combo has altered from the last doubling tick.
	public void effect() {
		if(previousCombo != getOwner().getCombo()) {
			getOwner().setCombo(getOwner().getCombo()*2);
			previousCombo = getOwner().getCombo();
		}
	}
	
	public void setPreviousCombo(int newPreviousCombo) {
		previousCombo = newPreviousCombo;
	}
	
	public int getPreviousCombo() {
		return previousCombo;
	}

	
}
