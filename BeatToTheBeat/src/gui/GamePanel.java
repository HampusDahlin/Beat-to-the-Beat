package gui;

import javax.swing.JPanel;

import levels.Level;
import enviroment.IBackground;

public class GamePanel extends JPanel{
	
	IBackground background;
	Level level;
	
	public void addNewGame(Level level){
		this.level = level;
		addBackground(this.level.getBackground());
	}

	private void addBackground(IBackground background) {
		this.background = background;
	}
}
