package enviroment;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Hampus Dahlin
 * @version 0.0.2
 *
 */
public class StaticBackground implements IBackground{
	
	private List<BackgroundLayer> layers;
	private int numberOfLayers;
	
	/**
	 * Default constructor for Background
	 */
	public StaticBackground(){
		numberOfLayers = 0;
		layers = new ArrayList<BackgroundLayer>();
		
	}
	
	public void update(){
		
	}
	
	/**
	 * Adds a BackgroundLayer to the list.
	 * @param layer the layer to be added
	 */
	public void addLayer(BackgroundLayer layer){
		layers.add(layer);
		numberOfLayers++;
	}
	
	/**
	 * Adds an array of BackgroundLayer to the list.
	 * @param layers
	 */
	public void addLayers(BackgroundLayer[] layers){
		for(BackgroundLayer l : layers){
			this.layers.add(l);
		}
	}
	
	/**
	 * Removes a BackgroundLayer from the list.
	 * @param layerIndex the index of the layer to be removed
	 */
	public void removeLayer(int layerIndex){
		layers.remove(layerIndex);
		numberOfLayers--;
	}
	
	/**
	 * 
	 * @return the number of layers
	 */
	public int getNmbrOfLayers(){
		return numberOfLayers;
	}
	
	/**
	 * 
	 * @param layerIndex
	 * @return the layer at index layerIndex
	 */
	public BackgroundLayer getLayer(int layerIndex){
		return layers.get(layerIndex);
	}
	
	/**
	 * Moves all the BackgroundLayer in the list.
	 */
	public void moveLayers(){
		for(BackgroundLayer bgl: layers){
			bgl.moveLayer();
		}
	}
	
}
