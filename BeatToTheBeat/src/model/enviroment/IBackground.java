package model.enviroment;

import java.awt.Graphics2D;
import java.beans.PropertyChangeListener;

public interface IBackground extends PropertyChangeListener{

	public void paintBackground(Graphics2D g2d);

	public void paintBackground(Graphics2D g, int range);
}
