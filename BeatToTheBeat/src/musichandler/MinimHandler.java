package musichandler;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.Timer;

import ddf.minim.*;
import ddf.minim.analysis.BeatDetect;

public class MinimHandler extends JFrame implements ActionListener {
	Minim minim;
	AudioPlayer player;
	AudioInput input;
	Timer timer;
	boolean one;
	boolean two;
	int beatNr;
	BeatDetect detective;
	
	public MinimHandler() {
		one = false;
		two = false;
		detective = new BeatDetect();

		setup();
		timer = new Timer(1, this);
		timer.start();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	void setup() {
		this.setSize(512, 200);

		minim = new Minim(this);
		player = minim.loadFile("Jubel.wav", 512);
		// this loads mysong.wav from the data folder
		player.play();
		
		input = minim.getLineIn();
	}

	void draw() {
		//background(0);
		//stroke(255);
		//repaint();
	}
	
	public void paint(Graphics g) {
		if (one) {
			g.clearRect(0, 0, this.getWidth(), this.getHeight());
			one = false;
		} else {
			one = true;
		}
		// we draw the waveform by connecting neighbor values with a line
		// we multiply each of the values by 50 
		// because the values in the buffers are normalized
		// this means that they have values between -1 and 1. 
		// If we don't scale them up our waveform 
		// will look more or less like a straight line.
		for(int i = 0; i < player.bufferSize() - 1; i++) {
			g.drawLine(i, (int) (50 + player.left.get(i)*50), i+1, (int) (50 + player.left.get(i+1)*50));
			g.drawLine(i, (int) (150 + player.right.get(i)*50), i+1, (int) (150 + player.right.get(i+1)*50));
		}
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
	}

	public void actionPerformed(ActionEvent e) {
		detective.detect(player.mix);
		if (detective.isOnset() && !two) {
			System.out.println("BEAT: " + beatNr);
			beatNr++;
			two = true;
		} else {
			two = false;
		}
		repaint();
	}
}