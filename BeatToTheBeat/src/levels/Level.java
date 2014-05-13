package levels;

import musichandler.SoundHandler;
import enviroment.ABackground;

/** 
* Interface for Levels(or stages) for any game.
* <p> Contains everything needed for a level.
* @author  Pontus "Bondi" Eriksson
* @group 14
* @revisedBy Hampus Dahlin
* @date 2014-05-12
*/ 

public abstract class Level {
	ABackground  background;
	SoundHandler soundHandler;
	
	public ABackground getBackground(){
		return this.background;
	}
	
	public SoundHandler getSoundHandler(){
		return this.soundHandler;
	}
	
}