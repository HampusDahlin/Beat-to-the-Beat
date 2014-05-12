package levels;

import musichandler.SoundHandler;
import enviroment.IBackground;

/**
 * 
 * @author Hampus Dahlin
 * @date 2014-05-12
 * 
 *
 */
public class MusicLevel implements ILevel{
	SoundHandler soundhandler;
	IBackground background;
	
	public MusicLevel(SoundHandler soundhandler, IBackground background){
		this.soundhandler = soundhandler;
		this.background = background;
	}
	
	public void start(){
		
	}
}
