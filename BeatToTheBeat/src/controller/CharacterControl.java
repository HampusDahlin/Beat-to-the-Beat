package controller;

import javax.swing.text.View;

import actors.Actor;


public class CharacterControl {
	
	
	//TODO Actor class and View
	private View view;
	
	
	public CharacterControl(View view){
		this.view = view;
	}
	
	
	
	public void moveCharacter(){
		
	}
	
	public static void removeCharacter(Actor actor){
		if(actor.isDead){
			
			panelWhereActorIs.remove(actor);
			
		}
		
	}
	
	public void updateView(){
		this.view.update();
	}


}
