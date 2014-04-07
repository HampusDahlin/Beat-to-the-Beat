package musichandler;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JFrame;

import ddf.minim.*;

public class MinimHandler extends JFrame {
	Minim minim;
	AudioPlayer player;
	AudioInput input;

	void setup() {
		//size(100, 100);

		minim = new Minim(this);
		player = minim.loadFile("Jubel.wav");
		// this loads mysong.wav from the data folder
		player.play();
		
		//input = minim.getLineIn();
	}

	void draw() {
		// do what you do
	}
	
	public String sketchPath(String fileName) {
		return "sketchPath: " + fileName;
	}
	
	public InputStream createInput(String fileName) {
		System.out.println("creating inputStream from file: " + fileName);
		InputStream is;
		try {
			is = new FileInputStream(fileName);
			System.out.println("Success!");
			return is;
		} catch (Exception e) {
			System.out.println("Failed! Exception: " + e);
			is = null;
		}
		return is;
	}
	
	public static void main(String[] args) {
		MinimHandler test = new MinimHandler();
		test.setup();
	}
}