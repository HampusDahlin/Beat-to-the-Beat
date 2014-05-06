package controller;

import javax.swing.JPanel;

import enviroment.WaveBackground;

/**
 * 
 * @author Hampus Dahlin
 *
 */
public class EnviromentControl {
	
	private WaveBackground background;
	
	/**
	 * Default, empty constructor.
	 */
	public EnviromentControl() {
		
	}
	
	/**
	 * Constructor setting background to a supplied Background.
	 * @param background
	 */
	public EnviromentControl(WaveBackground background){
		this.setBackground(background);
	}

	/**
	 * Sets the background to a supplied Background.
	 * @param background
	 */
	public void setBackground(WaveBackground background){
		this.background=background;
	}
	
	/**
	 * Updates the background and returns it.
	 * @return
	 */
	public JPanel getNextBackground(float[][] bajskorv){
		updateBackground(bajskorv);
		return getBackground();
	}
	
	/**
	 * Returns the current background.
	 * @return
	 */
	public JPanel getBackground(){
		return background;
	}
	
	/**
	 * Updates the background.
	 */
	public void updateBackground(float[][] bajskorv){
		if(this.background != null){
			this.background.updateBackground(bajskorv);
		}
	}

	
	
}
