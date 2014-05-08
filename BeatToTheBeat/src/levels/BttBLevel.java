package levels;


/** 
* Levels(or stages) for the game "Beat to the Beat".
* <p> Contains everything needed for a level.
* @author  Pontus "Bondi" Eriksson
* @group 14
* @date 01-04-14
*/ 

public class BttBLevel implements ILevel {
	private final String fileName;
	private final String songName;
	private final long[] spawnTimes; //Array containing the spawntimes for NPCs.
	
	//Loading from saved file.
	public BttBLevel(String fileName, String songName, long[] spawnTimes) {
		this.fileName = fileName;
		this.songName = songName;
		this.spawnTimes = spawnTimes;
	}
	
	//Creating a new level from a song.
	/*public BttBLevel(String fileName, String songName, Background background) {
		
	}*/
	
	public String getFileName() {
		return fileName;
	}
	
	public String getSongName() {
		return songName;
	}
	
	public long[] getSpawnTimes() {
		return spawnTimes;
	}

} // end BttBLevel
