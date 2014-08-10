package actors;

import java.awt.Point;

public class NPC extends Actor implements support.Movable{

	public NPC(Point position) {
		super(new Point((position.x < 450 ? 1 : -1), 0)); //speed sideways, up
		setHealth(1);
		setPosition(position);
		setDmg(1);
		setRange(15);
	}	
}