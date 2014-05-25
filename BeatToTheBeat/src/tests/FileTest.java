package tests;

import services.HomogeneousFileHandler;
import services.IFileHandler;


public class FileTest {
	IFileHandler<Object> filehandler;
	
	public static void main(String[] args){
		new FileTest();
	}
	
	
	public FileTest(){
		System.out.println("Testing HomogenousFileHandler.java...");
		if (homogenousFileHandlerTest()) {
			System.out.println("All tests successful!");
		} else {
			System.out.println("Tests failed!");
		}
	}
	
	
	public boolean homogenousFileHandlerTest(){
		filehandler = new HomogeneousFileHandler();
		boolean tests = true;
		
		if (homogenousSaveAsLoad()) {
			System.out.println("SaveAs + Load test successful");
		} else {
			System.out.println("SaveAs + Load test failed");
			tests = false;
		}
		
		if (homogenousSetDefaultSaveLocationSave()) {
			System.out.println("SetDefaultSaveLocation + Save test successful");
		} else {
			System.out.println("SetDefaultSaveLocation + Save test failed");
			tests = false;
		}
		
		return tests;
	}
	
	private boolean homogenousSetDefaultSaveLocationSave() {
		Integer testObject = new Integer(2);
		
		filehandler.setDefaultSaveLocation("default.test");
		filehandler.save(testObject);
		
		return filehandler.load("default.test").get(0).equals(testObject);
	}


	public boolean homogenousSaveAsLoad(){
		Integer saveObject = new Integer(1);
		
		filehandler.saveAs("filetest.test", saveObject);
		
		return filehandler.load("filetest.test").get(0).equals(saveObject);
	}

}
