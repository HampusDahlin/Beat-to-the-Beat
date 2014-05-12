package levels;

import musichandler.SoundHandler;

/**
 * 
 * @author Hampus Dahlin
 * @date 2014-05-12
 * 
 *
 */
public class MusicLevel implements ILevel{
	SoundHandler soundhandler;
	
	public MusicLevel(SoundHandler soundhandler){
		this.soundhandler = soundhandler;
	}
	
	public void start(){
		
	}
}
