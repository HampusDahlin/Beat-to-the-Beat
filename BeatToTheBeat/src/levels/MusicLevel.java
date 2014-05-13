package levels;

import musichandler.SoundHandler;
import enviroment.ABackground;

/**
 * 
 * @author Hampus Dahlin
 * @date 2014-05-12
 * 
 *
 */
public class MusicLevel extends Level{
	
	public MusicLevel(SoundHandler soundhandler, ABackground background){
		this.soundHandler = soundhandler;
		this.background = background;
	}
	
	public void start(){
		soundHandler.start();
	}
}
