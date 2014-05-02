package musichandler;

/** 
* Describes a music genre.
* <p> Contains the bands that should be used for a specific genre,
* 		making it easier to detect proper beats.
* @author Pontus "Bondi" Eriksson
* @group 14
* @date 02-05-14
*/ 

public class Genre {
	private final String name;
	private final int low;
	private final int high;
	private final int th; //threshold
	private final int sense; //sensitivity
	
	/**
	 * Creates a song genre.
	 * @param name Name of the genre.
	 * @param low Lowest band to be used in beats.
	 * @param high Highest band to be used in beats.
	 * @param th How many bands that need to register a beat in order to count.
	 * @param sense How many ms the beat detector sleeps after a beat.
	 */
	public Genre(String name, int low, int high, int th, int sense) {
		this.name = name;
		this.low = low;
		this.high = high;
		this.th = th;
		this.sense = sense;
	}
	
	/**
	 * Creates a song genre. Sense set to 300.
	 * @param name Name of the genre.
	 * @param low Lowest band to be used in beats.
	 * @param high Highest band to be used in beats.
	 * @param th How many bands that need to register a beat in order to count.
	 */
	public Genre(String name, int low, int high, int th) {
		this(name, low, high, th, 300);
	}
	
	public String getName() {
		return name;
	}
	
	public int getLow() {
		return low;
	}
	
	public int getHigh() {
		return high;
	}
	
	public int getThreshold() {
		return th;
	}
	
	public int getSense() {
		return sense;
	}
}
