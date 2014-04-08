package musichandler;

import java.awt.Color;
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
	int beatNr;
	BeatDetect detective;
	final int BUFFERSIZE;
	boolean mode;
	String songName;
	boolean visMode;
	int ballSize;
	
	public MinimHandler() {
		mode = true; // true for sound-energy, false for frequency-energy
		visMode = true; // true for frequency, false for "ball" (only works in sound-energy so far)
		BUFFERSIZE = 512;
		one = false;
		songName = "songs\\Jubel.wav";
		setup();
		
		if (mode) {
			detective = new BeatDetect();
		} else {
			detective = new BeatDetect(BUFFERSIZE, player.sampleRate());
		}
		
		detective.setSensitivity(300);

		timer = new Timer(1, this);
		timer.start();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	void setup() {
		if (visMode) {
			this.setSize(BUFFERSIZE, 200);
		} else {
			this.setSize(300, 300);
		}

		minim = new Minim(this);
		player = minim.loadFile(songName, BUFFERSIZE);
		// this loads song from the data folder
		player.play();
		
		input = minim.getLineIn();
	}

	void draw() {
		//background(0);
		//stroke(255);
		//repaint();
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		if (visMode) {
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
		} else {
			g.setColor(Color.BLACK);
			g.fillOval(150-(ballSize/2), 150-(ballSize/2), ballSize, ballSize);
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
		// getting the buffer for current time in song
		detective.detect(player.mix);
		
		if (detective.isOnset()) { // always false in freq.mode
			System.out.println("BEAT: " + beatNr);
			beatNr++;
			ballSize = 200;
		} else if (!mode) {
			if (detective.isHat()) {
				System.out.println("HAT!");
			}
			if (detective.isKick()) {
				System.out.println("KICK!");
			}
			if (detective.isSnare()) {
				System.out.println("SNARE!");
			}
		} else if (one) {
			ballSize--;
		}

		if (one) {
			repaint();
			one = false;
		} else {
			one = true;
		}
		
	}
}