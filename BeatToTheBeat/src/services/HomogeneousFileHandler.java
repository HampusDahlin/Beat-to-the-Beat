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
public class HomogeneousFileHandler implements IFileHandler<Object>{
	private FileOutputStream fileOut;
	private ObjectOutputStream objectOut;
	
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
		
	}

	
	/**
	 * {@inheritDoc}
	 * 
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
		
		if(temp instanceof ArrayList<?>){
			list = ((ArrayList<Object>)temp);
		}else{
			System.out.println("Temp is not a List, corrupt file?");
		}
		
		return list;
	}

}
