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
	 * Saves an object toWrite of the type T to a file with the name filename
	 * @param filename
	 * @param toWrite
	 */
	public void saveAs(String filename, T toWrite);
	
	/**
	 * Sets the default save location.
	 * @param saveLoc
	 * @see save()
	 */
	public void setDefaultSaveLocation(String saveLoc);
	
	/**
	 * Saves an object toWrite of the type T to the default file.
	 * @param toWrite
	 */
	public void save(T toWrite);
	
	/**
	 * Reads from a file with the name filename and returns the content of the file as a list.
	 * @param filename
	 * @return
	 */
	public List<?> load(String filename);
}
