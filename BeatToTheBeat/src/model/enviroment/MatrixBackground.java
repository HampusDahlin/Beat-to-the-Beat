package model.enviroment;

import java.awt.Color;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;

/**
 * Simulates a "Matrix"-style background using randomly generated green numbers.
 * @author Hampus Dahlin
 *
 */
public class MatrixBackground implements IBackground{

	private Integer[][]numbers;
	
	public MatrixBackground(){
		numbers = new Integer[80][100];
		initNumbers();
	}
	
	/**
	 * Initiates the matrix numbers by filling it with random integers between 0 - 9.
	 */
	private void initNumbers() {
		
		for(int i = 0; i < numbers.length; i++){
			initRow(i);
		}
	}
	
	/**
	 * Updates the matrix numbers by moving all "rows" one step down and filling the first one with random integers between 0-9.
	 */
	private void updateNumbers() {
		for (int i = numbers.length-1; i > 0; i--) {
			numbers[i] = numbers[i-1];
		}
		initRow(0);
	}

	/**
	 * Fills a row with random integers between 0 - 9.
	 * @param row
	 */
	private void initRow(int row) {
		for (int i = 0; i < numbers[row].length; i++) {
			numbers[row][i] = (int) (Math.random()*10);
		}
	}

	@Override
	public void paintBackground(Graphics2D g2d) {
		g2d.setColor(Color.GREEN);
		for(int i = 0; i < numbers.length; i++){
			for (int j = 0; j < numbers[i].length; j++) {
				g2d.drawString(numbers[i][j].toString(), 10*j, 10*i);
			}
		}
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("beat")){
			updateNumbers();
		}
		
	}

	@Override
	public void paintBackground(Graphics2D g2d, int range) {
		paintBackground(g2d);
		
	}
}
