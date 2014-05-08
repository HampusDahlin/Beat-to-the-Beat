package support;
/**
 * @author Malin "Nilhet" Thelin
 */

import java.beans.PropertyChangeListener;

public interface IObservable {
	
	public void addObserver(PropertyChangeListener observer);
	
	public void removeObserver(PropertyChangeListener observer);

}
