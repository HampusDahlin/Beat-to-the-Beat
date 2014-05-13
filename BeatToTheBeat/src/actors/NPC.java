package actors;

import java.awt.Point;

import javax.swing.Icon;

public class NPC extends Actor implements support.Movable{

	public NPC(Point position, Icon sprite){
		super(sprite, new Point(10,0));
		setHealth(1);
		setPosition(position);
		setDmg(1);
		setRange(1);
	}
	
}
