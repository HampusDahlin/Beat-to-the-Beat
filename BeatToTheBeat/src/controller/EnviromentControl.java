package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

import musichandler.Analyzer;
import enviroment.WaveBackground;

/**
 * 
 * @author Hampus Dahlin
 *
 */
public class EnviromentControl implements PropertyChangeListener{
	
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
	public EnviromentControl(WaveBackground background, Analyzer analyzer){
		this.setBackground(background);
		analyzer.getPcs().addPropertyChangeListener(this);
	}

	/**
	 * Sets the background to a supplied WaveBackground.
	 * @param background
	 */
	public void setBackground(WaveBackground background){
		this.background=background;
	}
	
	/**
	 * Updates the background, then returns it.
	 * @return
	 */
	public JPanel getNextBackground(float[][] bajskorv, boolean beat){
		updateBackground(bajskorv, beat);
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
	 * 
	 * @param soundWave, the float-data of the soundwave.
	 * @param beat, true if there is a beat, false otherwise.
	 */
	public void updateBackground(float[][] soundWave, boolean beat){
		if(this.background != null){
			this.background.updateBackground(soundWave, beat);
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
}
