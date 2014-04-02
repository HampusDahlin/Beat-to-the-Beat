package services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
	
	private FileWriter fw;
	private Scanner fr;
	private String defaultSaveLocation;
	
	public FileHandler() {
		
	}
	
	/**
	 * 
	 * @param filename the name of the file to load from
	 * @return String containing rows of content separated by a |
	 */
	public List<String> load(String filename){
		List<String> list = new ArrayList<String>();
		File f = new File(filename);
		
		try {
			list.addAll(Files.readAllLines(f.toPath(), StandardCharsets.UTF_8));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	/**
	 * 
	 * @param toWrite the text to write
	 */
	public void save(String toWrite){
		try {
			fw = new FileWriter(defaultSaveLocation);
			fw.write(toWrite);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param filename the name of the file to write to
	 * @param toWrite the text to write
	 */
	public void saveAs(String filename, String toWrite){
		
		try {
			fw = new FileWriter(filename);
			fw.write(toWrite);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setDefaultSaveLocation(String saveLoc){
		defaultSaveLocation = saveLoc;
	}
}
