package services.musichandler;

import java.io.*;
import java.util.Scanner;

import model.Song;

/**
 * @author Björn Hedström
 * @revisedBy Malin "Nilhet" Thelin
 *
 */
@SuppressWarnings("serial")
public class HighScoreList implements Serializable{
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
	private void initHighScoreList() {
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
	

	public void saveHighscoreList (Song song) {
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(new File( "songs\\"+song.getSongName()+"highscoreList.list" )));
			for ( String n : names ) {
				if (!n.equals("")) {
					output.write("# "+n+"\n");  
				}
			}
			for (int i : scores){
				if (i>0) {
					output.write(""+i+"\n");
				}
			}
			output.close();
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}

	public void loadHighscorelist(Song song) {
		Scanner in = null;
		try {
			in = new Scanner(new FileReader("songs\\"+song.getSongName()+"highscoreList.list"));
			int i =0;
			while (in.hasNext("[#]")) {
				names[i] = in.nextLine().substring(1);
				i++;
			}
			i = 0;
			while (in.hasNextInt()) {
				scores[i] = in.nextInt();
				i++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			in.close();
		}
	}
}
