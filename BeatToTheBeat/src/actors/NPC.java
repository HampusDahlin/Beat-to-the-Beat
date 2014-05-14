package actors;

import java.awt.Point;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;

public class NPC extends Actor implements support.Movable{

	public NPC(Point position, ImageIcon sprite, PropertyChangeListener listener) {
		super(sprite, new Point(1,0)); //speed sideways, up
		setHealth(1);
		setPosition(position);
		setDmg(1);
		setRange(1);
		pcs.addPropertyChangeListener(listener);
		pcs.firePropertyChange("newNPC", null, getPosition());
	}
	
}
