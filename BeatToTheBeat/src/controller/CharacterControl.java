package controller;


public class CharacterControl {
	
	
	//TODO Actor class and View
	private View view;
	
	
	public CharacterControl(View view){
		this.view = view;
	}
	
	
	
	public void moveCharacter(){
		
	}
	
	public void removeCharacter(Actor actor){
		if(actor.isDead){
			
			panelWhereActorIs.remove(actor);
			
		}
		
	}
	
	public void updateView(){
		this.view.update();
	}


}
