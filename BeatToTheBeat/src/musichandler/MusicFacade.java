package musichandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Hampus Dahlin
 * @version 0.0.1
 */
public class MusicFacade {
	
	List<Song> songlist;
	
	
	/**
	 * 
	 */
	public MusicFacade() {
		songlist = new ArrayList<Song>();
	}
	
	
	/**
	 * 
	 * @param filename
	 */
	public void addSong(String filename){
		songlist.add(new Song(filename));
	}
	
	
	public void changeDist(){
		
	}
	
	
	public void changeSpeed(){
		
	}
	
}
