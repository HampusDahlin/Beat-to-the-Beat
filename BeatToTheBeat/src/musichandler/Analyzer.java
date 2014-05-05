package musichandler;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
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
	private final int timerCd;
	
	public Analyzer(Song song, int sensitivity) {
		timerCd = 20;
		beatList = new ArrayList<Long>();
		BUFFERSIZE = 512;
		songName = "songs\\" + song.getFilename();
		setup();
		detective = new BeatDetect();
		detective.setSensitivity(sensitivity);
		currentTime = new Long(0);
		beatCheck = new Timer(timerCd, this);
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
	
	public void stopAnalyzer() {
		player.pause();
		beatCheck.stop();
	}

	public void actionPerformed(ActionEvent e) {
		// getting the buffer for current time in song
		detective.detect(player.mix);
		currentTime = currentTime + timerCd;
		
		if (detective.isOnset()) {
			addToBeatList(currentTime);
		} else if(beatList != null && beatList.size() != 0 &&
				currentTime - beatList.get(beatList.size() - 1) > 3000) {
				stopAnalyzer();
		}
	}
	
}