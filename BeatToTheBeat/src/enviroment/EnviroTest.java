package enviroment;

import java.awt.Point;

public class EnviroTest {
	
	public static void main(String[] args) {
		Background b = new Background();
		b.addLayer(new BackgroundLayer(new Point(-1, 0), null));
		b.addLayer(new BackgroundLayer(new Point(-2, 0), null));
		System.out.println(b.getLayer(0).getPosition().toString() + ", " + b.getLayer(1).getPosition().toString());
		b.moveLayers();
		System.out.println(b.getLayer(0).getPosition().toString() + ", " + b.getLayer(1).getPosition().toString());
	}
}