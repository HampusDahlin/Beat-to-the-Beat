package controller;

import enviroment.Background;

public class EnviromentControl {
	
	private Background background;
	
	public EnviromentControl(Background background){
		this.background=background;
	}
	
	
	public void moveBackground(){
		if(this.background.getNmbrOfLayers() < 2){
			int tmpSpeed = this.background.getLayer(0).getSpeed().x;
		}
	}

	
	
}
