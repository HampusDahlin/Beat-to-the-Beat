package gui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class MirroredImageIcon extends ImageIcon {

	public MirroredImageIcon(String imagePath) {
		super(imagePath);
	}

	public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.translate(getIconWidth(), 0);
		g2d.scale(-1, 1);
		super.paintIcon(c, g2d, x, y);
	}

} //end MirroredImageIcon