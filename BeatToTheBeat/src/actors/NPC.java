package actors;

import java.awt.Image;
import java.awt.Point;

import controller.CharacterControl;

public class NPC extends Actor implements support.Movable{


	public NPC(Point position, Image sprite){
		setHealth(1);
		setPosition(position);
		setSprite(sprite);
	}
	
	public void attack(){
		if(canHit()){
			dealDmg(/*CharacterControls PC variabel*/);
		}
		death();
	}
	
	public void death(){
		CharacterControl.removeCharacter(this);
	}
}
