package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Hampus Dahlin
 *
 */
public class SpawnFileHandler implements IFileHandler<Object>{
	FileOutputStream fileOut;
	ObjectOutputStream objectOut;
	private String defaultSaveLocation;
	
	
	/**
	 * {@inheritDoc}
	 */
	public void saveAs(String filename, Object toWrite) {
		List<Object> l = new ArrayList<Object>();
		l.add(toWrite);
		try {
			fileOut = new FileOutputStream(filename);
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(l);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	
	/**
	 * {@inheritDoc}
	 */
	public void setDefaultSaveLocation(String saveLoc) {
		this.defaultSaveLocation = saveLoc;
		
	}

	
	/**
	 * {@inheritDoc}
	 */
	public void save(Object toWrite) {
		try {
			fileOut = new FileOutputStream(this.defaultSaveLocation);
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(toWrite);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	
	/**
	 * {@inheritDoc}
	 */
	public List<Object> load(String filename) {
		List<Object> list = new ArrayList<Object>();
		File file = new File(filename);
		FileInputStream in;
		Object temp = null;
		
		
		try {
			in = new FileInputStream(file);
			ObjectInputStream oi = new ObjectInputStream(in);
			temp = oi.readObject();
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if(temp instanceof List<?>){
			list = ((ArrayList<Object>)temp);
		}else{
			System.out.println("Temp is not a List, corrupt file?");
		}
		
		return list;
	}

}
