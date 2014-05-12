package services;

import java.util.ArrayList;
import java.util.List;

public class FileTest {
	
	public static void main(String[] args){
		FileHandler fh = new FileHandler();
		List<String> s = new ArrayList<String>();
//		Scanner sc = new Scanner(System.in);
//		
//		fh.saveAs("test.txt", sc.next());
		
		s = fh.load("test.txt");
		
		for(String l : s){
			System.out.println(l);
		}
	}

}
