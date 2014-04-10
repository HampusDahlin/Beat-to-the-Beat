package services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Hampus Dahlin
 * 
 *
 */
public class FileHandler implements IFileHandler<String>{
	
	private FileWriter fw;
	private String defaultSaveLocation;
	
	public FileHandler() {
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<String> load(String filename){
		List<String> list = new ArrayList<String>();
		File f = new File(filename);
		
		try {
			list.addAll(Files.readAllLines(f.toPath(), StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void save(String toWrite){
		try {
			fw = new FileWriter(defaultSaveLocation);
			fw.write(toWrite);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void saveAs(String filename, String toWrite){
		
		try {
			fw = new FileWriter(filename);
			fw.write(toWrite);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setDefaultSaveLocation(String saveLoc){
		defaultSaveLocation = saveLoc;
	}
}
