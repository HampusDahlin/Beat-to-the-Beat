package controller;

import enviroment.Background;

/**
 * 
 * @author Hampus Dahlin
 *
 */
public class EnviromentControl {
	
	private Background background;
	
	/**
	 * Default, empty constructor.
	 */
	public EnviromentControl() {
		
	}
	
	/**
	 * Constructor setting background to a supplied Background.
	 * @param background
	 */
	public EnviromentControl(Background background){
		this.setBackground(background);
	}

	/**
	 * Sets the background to a supplied Background.
	 * @param background
	 */
	public void setBackground(Background background){
		this.background=background;
	}
	
	/**
	 * Moves the background.
	 */
	public void moveBackground(){
		if(this.background != null){
			//DO SOMETHING!!! OH PLEASE THINK OF THE CHILDREN!!!
		}
	}

	
	
}
