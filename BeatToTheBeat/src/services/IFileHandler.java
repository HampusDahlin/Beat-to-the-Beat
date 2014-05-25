package services;

import java.util.List;

/**
 * 
 * @author Hampus Dahlin
 *
 * @param <T>
 */
public interface IFileHandler <T>{
	
	/**
	 * Sets the default save location.
	 * @param saveLoc
	 * @see save()
	 */
	public void setDefaultSaveLocation(String saveLoc);
	
	/**
	 * Reads from a file with the name filename and returns the content of the file as a list.
	 * @param filename
	 * @return
	 */
	public List<?> load(String filename);
}
