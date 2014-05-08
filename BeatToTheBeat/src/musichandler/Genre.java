package musichandler;

/** 
* Describes a music genre.
* <p> Contains the bands that should be used for a specific genre,
* 		making it easier to detect proper beats.
* @author Pontus "Bondi" Eriksson
* @group 14
* @date 02-05-14
*/ 

public final class Genre {
	private final String NAME;
	private final int LOW;
	private final int HIGH;
	private final int TH; //threshold
	private final int SENSE; //sensitivity
	
	/**
	 * Creates a song genre.
	 * @param name Name of the genre.
	 * @param low Lowest band to be used in beats.
	 * @param high Highest band to be used in beats.
	 * @param th How many bands that need to register a beat in order to count.
	 * @param sense How many ms the beat detector sleeps after a beat.
	 */
	public Genre(String name, int low, int high, int th, int sense) {
		this.NAME = name;
		this.LOW = low;
		this.HIGH = high;
		this.TH = th;
		this.SENSE = sense;
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
		return NAME;
	}
	
	public int getLow() {
		return LOW;
	}
	
	public int getHigh() {
		return HIGH;
	}
	
	public int getThreshold() {
		return TH;
	}
	
	public int getSense() {
		return SENSE;
	}
}
