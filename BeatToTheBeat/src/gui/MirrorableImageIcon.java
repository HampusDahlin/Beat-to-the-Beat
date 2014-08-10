package gui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

/** 
* Mirrors an ImageIcon.
* @author Pontus "Bondi" Eriksson
* @revisedBy Malin "Nilhet" Thelin
* @group 14
*/ 
@SuppressWarnings("serial")
public class MirrorableImageIcon extends ImageIcon {

	/**
	 * Creates a mirrored ImageIcon from the specified path.
	 */
	public MirrorableImageIcon(String imagePath) {
		super(imagePath);
	}

	public synchronized void paintMirroredIcon(Component c, Graphics g, int x, int y, boolean isMoving) {
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.translate(x*2,y - (isMoving ? 300 : 0));
		g2d.scale(-1, 1);
		super.paintIcon(c, g2d, x, y);
	}
	
	public synchronized void paintIcon(Component c, Graphics g, int x, int y, boolean isMoving) {
		super.paintIcon(c, g, x, y);
	}


} //end MirroredImageIcon