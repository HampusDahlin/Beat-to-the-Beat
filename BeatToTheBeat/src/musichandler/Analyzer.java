package musichandler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import ddf.minim.*;
import ddf.minim.analysis.BeatDetect;

public class Analyzer implements ActionListener {
	private Minim minim;
	private AudioPlayer player;
	private AudioInput input;
	private Timer beatCheck;
	private int beatNr;
	private BeatDetect detective;
	private final int BUFFERSIZE;
	private String songName;
	private Long currentTime; //in milliseconds 
	private ArrayList<Long> beatList;
	private int sensitivity;
	private Song song;
	
	public Analyzer(Song song, int sensitivity) {
		this.song = song;
		
		this.sensitivity = sensitivity;
		beatList = new ArrayList<Long>();
		BUFFERSIZE = 512;
		songName = "songs\\" + song.getFilename();
		setup();
		detective = new BeatDetect();
		detective.setSensitivity(sensitivity);
		currentTime = new Long(0);
		beatCheck = new Timer(20, this);
		beatCheck.start();
	}

	public int getBeatNr() {
		return beatNr;
	}
	
	public void setup() {
		minim = new Minim(this);
		player = minim.loadFile(songName, BUFFERSIZE);
		// this loads song from the data folder	
		input = minim.getLineIn();
	}
	
	public void start() {
		player.mute();
		player.play();
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
	
	public void addToBeatList(Long nextBeat) {
		beatList.add(nextBeat);
		beatNr++;
	}
	
	public ArrayList<Long> getBeatList() {
		return beatList;
	}

	public void actionPerformed(ActionEvent e) {
		// getting the buffer for current time in song
		detective.detect(player.mix);
		currentTime++;
		
		if (detective.isOnset()) {
			addToBeatList(currentTime);
		} else if(beatList != null && beatList.size() != 0 &&
				currentTime - beatList.get(beatList.size() - 1) > 3000) {
					player.pause();
					beatCheck.stop();
		}
	}
	//can only give a resonable difficulty scale if called after analyzis is complete.
	public String difficulty() {
		double averageBeatsPerSecond = beatNr / (currentTime / 1000);
		
		if(beatList.size() > 1) {
			Long highestTimeBetweenBeats = beatList.get(1) - beatList.get(0);
			Long lowestTimeBetweenBeats = highestTimeBetweenBeats;
			for(int i = 1; i < beatList.size(); i++) {
				if(highestTimeBetweenBeats < (beatList.get(i + 1) - beatList.get(i))) {
					highestTimeBetweenBeats = beatList.get(i + 1) - beatList.get(i);
				} else if(lowestTimeBetweenBeats > (beatList.get(i + 1) - beatList.get(i))) {
					lowestTimeBetweenBeats = beatList.get(i + 1) - beatList.get(i);
				}
			}
		} 
	}
}