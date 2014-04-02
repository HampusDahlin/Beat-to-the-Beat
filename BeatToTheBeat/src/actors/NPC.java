package actors;

import java.awt.Point;

import javax.swing.Icon;

public class NPC extends Actor implements support.Movable{

	public NPC(Point position, Icon sprite){
		setHealth(1);
		setPosition(position);
		setSprite(sprite);
		setDmg(1);
		setSpeed(new Point(100,0));
		setRange(1);
	}
	
}
