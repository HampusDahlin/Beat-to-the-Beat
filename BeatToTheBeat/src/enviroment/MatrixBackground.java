package enviroment;

import java.awt.Color;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;

public class MatrixBackground implements IBackground{

	private Integer[][]numbers;
	
	public MatrixBackground(){
		numbers = new Integer[80][100];
		initNumbers();
	}
	
	private void initNumbers() {
		
		for(int i = 0; i < numbers.length; i++){
			for (int j = 0; j < numbers[i].length; j++) {
				numbers[i][j] = (int) (Math.random()*10);
			}
		}
	}
	
	private void updateNumbers() {
		for (int i = numbers.length-1; i > 0; i--) {
			numbers[i] = numbers[i-1];
		}
		initRow(0);
	}

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
