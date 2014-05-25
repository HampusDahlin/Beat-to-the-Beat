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
		
		return tests;
	}


	public boolean homogenousSaveAsLoad(){
		Integer saveObject = new Integer(1);
		
		((HomogeneousFileHandler) filehandler).saveAs("filetest.test", saveObject);
		
		return filehandler.load("filetest.test").get(0).equals(saveObject);
	}

}
