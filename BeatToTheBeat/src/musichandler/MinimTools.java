package musichandler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import ddf.minim.*;
import ddf.minim.analysis.BeatDetect;
import ddf.minim.analysis.FFT;

/** 
* MinimTools is used to test Minim and create genres.
* <p> A class written with the purpose to test Minim and to in an easy way create new genres and test their values for beatdetection.
* @author Pontus "Bondi" Eriksson
* @group 14
*/

@SuppressWarnings("serial")
public class MinimTools extends JPanel implements ActionListener { // NO_UCD (used code)
	Minim minim;
	AudioPlayer player;
	AudioInput input;
	Timer timer;
	boolean one;
	int beatNr;
	BeatDetect detective;
	final int BUFFERSIZE;
	boolean mode;
	boolean visMode;
	boolean wave;
	double ballSize;
	FFT fft;
	Genre genres[];
	Song songs[];
	Song activeSong;
	
	public MinimTools() {
		genres = new Genre[10];
		genres[0] = new Genre("Happy Hardcore", 0, 0, 1, 330);
		genres[1] = new Genre("Rap", 2, 4, 2, 330);
		genres[2] = new Genre("Rock", 0, 5, 3, 330);
		genres[3] = new Genre("Ballade", 0, 4, 2, 330);
		//Add your genre here,
		
		songs = new Song[10];
		songs[0] = new Song("Eminem - Till I Collapse.mp3", "Till I Collapse", "Eminem", genres[1]);
		songs[1] = new Song("Rotterdam Termination Source - Poing.mp3", "Poing", "Rotterdam Terminator Source", genres[0]);
		songs[2] = new Song("Groove Armada - Edge Hill", "Groove Armada", "Edge Hill", genres[3]);
		//your song here, and choose genre
		songs[3] = new Song("FILENAME", "SONGNAME", "ARTIST", genres[0]);
		//and set this value to same as your song.
		activeSong = songs[0];
		mode = false; 		//Set TRUE for sound-energy, FALSE for frequency-energy
		visMode = true; 	//Set TRUE for frequency, FALSE for "ball"
		wave = false;		//Set TRUE for waveform, FALSE for spectrum
		BUFFERSIZE = 512;
		one = false;
		setup();
		
		if (mode) {
			detective = new BeatDetect();
		} else {
			detective = new BeatDetect(BUFFERSIZE, player.sampleRate());
		}
		
		detective.setSensitivity(activeSong.getGenre().getSense());

		timer = new Timer(1, this);
		timer.start();
		/*this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);*/
	}

	void setup() {
		if (visMode) {
			this.setSize(BUFFERSIZE, 200);
		} else {
			this.setSize(300, 300);
		}

		minim = new Minim(this);
		player = minim.loadFile(activeSong.getFilename(), BUFFERSIZE);
		// this loads song from the data folder
		player.play(10000);
		
		fft = new FFT(player.bufferSize(), player.sampleRate());
		
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
			if (wave) {
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
				fft.forward(player.mix);
				// draw the spectrum as a series of vertical lines
				// I multiple the value of getBand by 4 
				// so that we can see the lines better
				g.setColor(Color.BLACK);
				for(int i = 0; i < fft.specSize(); i+=10) {
					int temp = 0;
					for(int j = 0; j < 10; j++) {
						temp += fft.getBand(i+j);
					}
					g.fillRect(i*2, this.getHeight()-10, 5, -temp);
					g.drawString(""+(i/10), i*2, this.getHeight());
				}
			}
		} else {
			g.setColor(Color.BLACK);
			g.fillOval(150-((int)ballSize/2), 150-((int)ballSize/2), (int)ballSize, (int)ballSize);
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
		JFrame frame = new JFrame();
		MinimTools test = new MinimTools();
		frame.setSize(test.getSize().width, test.getSize().height+50);
		frame.add(test);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		// getting the buffer for current time in song
		detective.detect(player.mix);

		if ((mode && detective.isOnset()) ||
				(!mode && detective.isRange(activeSong.getGenre().getLow(), activeSong.getGenre().getHigh(), activeSong.getGenre().getThreshold()))) {
			System.out.println("BEAT: " + beatNr);
			beatNr++;
			ballSize = 200;
		} else {
			ballSize *= 0.997;
		}

		if (one) {
			repaint();
		}
		one = !one;
		
		repaint();
	}
}