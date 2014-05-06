package controller;

import enviroment.IBackground;
import enviroment.StaticBackground;

/**
 * 
 * @author Hampus Dahlin
 *
 */
public class EnviromentControl {
	
	private IBackground background;
	
	/**
	 * Default, empty constructor.
	 */
	public EnviromentControl() {
		
	}
	
	/**
	 * Constructor setting background to a supplied Background.
	 * @param background
	 */
	public EnviromentControl(IBackground background){
		this.setBackground(background);
	}

	/**
	 * Sets the background to a supplied Background.
	 * @param background
	 */
	public void setBackground(IBackground background){
		this.background=background;
	}
	
	/**
	 * Updates the background.
	 */
	public void updateBackground(){
		if(this.background != null){
			this.background.update();
		}
	}

	
	
}
