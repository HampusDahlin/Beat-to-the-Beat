package enviroment;

import java.awt.Color;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;

public class MatrixBackground implements IBackground{

	private Integer[][]numbers;
	
	public MatrixBackground(){
		numbers = new Integer[100][80];
		fillColumn();
	}
	
	private void fillColumn() {
		
		for(int i = 0; i < numbers.length; i++){
			for (int j = 0; j < numbers[i].length; j++) {
				numbers[i][j] = (int) (Math.random()*10);
			}
		}
		
	}

	@Override
	public void paintBackground(Graphics2D g2d) {
		g2d.setColor(Color.GREEN);
		for(int i = 0; i < numbers.length; i++){
			for (int j = 0; j < numbers[i].length; j++) {
				g2d.drawString(numbers[i][j].toString(), 10*i, 10*j);
			}
		}
		
	}

	

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("beat")){
			fillColumn();
		}
		
	}
}
