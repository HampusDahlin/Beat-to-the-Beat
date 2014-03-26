package enviroment;

import java.util.List;

/**
 * 
 * @author Hampus Dahlin
 * @version 0.0.1
 *
 */
public class Background {
	
	private List<BackgroundLayer> layers;
	private int numberOfLayers;
	
	
	public Background(){
		
		
	}
	
	public void addLayer(BackgroundLayer layer){
		layers.add(layer);
	}
	
	public void removeLayer(int layerIndex){
		layers.remove(layerIndex);
	}
	
	public int getNmbrOfLayers(){
		return numberOfLayers;
	}
	
	public void setNmbrOfLayers(int numberOfLayers){
		this.numberOfLayers = numberOfLayers;
	}
	
	public BackgroundLayer getLayer(int layerIndex){
		return layers.get(layerIndex);
	}

}
