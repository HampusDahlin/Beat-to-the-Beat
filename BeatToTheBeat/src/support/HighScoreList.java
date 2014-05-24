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
		initHighScoreList();
	}
	/**
	 * @param name
	 * @param score
	 * @param index
	 */
	public void add(String name, int score) {
		//moves places below the indexdown one step.
		for(int i = 4; i > isEligible(score); i--) {
			names[i] = names[i - 1];
			scores[i] = scores[i - 1];
		}
		names[isEligible(score)] = name;
		scores[isEligible(score)] = score;
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

	/**
	 * initializes the list to empty variables
	 */
	public void initHighScoreList() {
		for(int i = 0; i < 5; i++) {
			names[i] = "";
		}
	}

	public int[] getScores() {
		return scores.clone();
	}

	public String[] getNames() {
		return names.clone();
	}
}
