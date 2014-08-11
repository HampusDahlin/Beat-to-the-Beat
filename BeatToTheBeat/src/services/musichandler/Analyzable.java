package services.musichandler;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Abstract class containing all the necessary methods for an object being analyzable by Minim.
 * @author Pontus "Bondi" Eriksson
 */
public abstract class Analyzable {
	
	/**
	 * Sketches the path for fileName.
	 * @param fileName Name of the file.
	 * @return Path to fileName.
	 */
	public String sketchPath(String fileName) {
		return "sketchPath: " + fileName;
	}
	
	/**
	 * Returns an InputStream for enclosed file.
	 * @param fileName 
	 * @return
	 */
	public InputStream createInput(String fileName) {
		//System.out.println("creating inputStream from file: " + fileName);
		InputStream is;
		try {
			is = new FileInputStream(fileName);
			//System.out.println("Success!");
			return is;
		} catch (Exception e) {
			//System.out.println("Failed! Exception: " + e);
			is = null;
		}
		return is;
	}
}
