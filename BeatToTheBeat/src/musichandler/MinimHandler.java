package musichandler;

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
	
	public static void main(String[] args) {
		MinimHandler test = new MinimHandler();
		test.setup();
	}
}