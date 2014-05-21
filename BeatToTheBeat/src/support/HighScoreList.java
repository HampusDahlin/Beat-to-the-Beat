package support;

/**
 * @author Björn Hedström
 *
 */
public class HighScoreList {
	private String[] names;
	private int[] scores;
	
	public HighScoreList() {
		scores = new int[5];
		names = new String[5];
	}
	/**
	 * @param name
	 * @param score
	 * @param index
	 */
	public void add(String name, int score, int index) {
		//moves places below the indexdown one step.
		for(int i = 4; i > index; i--) {
			names[i] = names[i - 1];
			scores[i] = scores[i - 1];
		}
		names[index] = name;
		scores[index] = score;
	}
	/**
	 * @param score
	 * @return the place in the array that the score is eligible for. -1 if the score is to low.
	 */
	public int isEligible(int score) {
		for(int i = 0; i < 5; i++) {
			if(score > scores[i]) {
				return i;
			}
		}
		return -1;
	}
	
	public int[] getScores() {
		return scores;
	}
	
	public String[] getNames() {
		return names;
	}
}
