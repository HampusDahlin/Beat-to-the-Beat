package actors;

public class PC extends Actor{
	int cash;
	int combo;
	
	public void setCombo(int newCombo){
		combo = newCombo;
	}
	
	public int getCombo(){
		return combo;
	}
	
	public void setCash(int newCash){
		cash = newCash;
	}
	
	public int getCash(){
		return cash;
	}
	
}
