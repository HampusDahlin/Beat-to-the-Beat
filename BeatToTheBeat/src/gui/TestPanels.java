package gui;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import musichandler.Genre;
import musichandler.Song;
import musichandler.SoundHandler;

public class TestPanels extends JFrame {
	
	public static void main(String[] args) {
		TestPanels s = new TestPanels();
	}
	
	public TestPanels() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(914, 600);
		this.setResizable(false);
		List<Song> songList = new ArrayList<Song>();
		Genre rock = new Genre("ROCK", 1, 1 ,1 ,1);
		Song s1 = new Song("Jubel.wav", "Jubel", "bsös", rock);
		Song s2 = new Song("Jubel.wav", "Jubel", "byös", rock);
		Song s3 = new Song("Jubel.wav", "Jubel", "böss", rock);
		Song s4 = new Song("Jubel.wav", "Jubel", "böts", rock);
		Song s5 = new Song("Jubel.wav", "Jubsel", "bsös", rock);
		Song s6 = new Song("Jubel.wav", "Jdubel", "byös", rock);
		Song s7 = new Song("Jubel.wav", "Jugbel", "böss", rock);
		Song s8 = new Song("Jubel.wav", "Jubyel", "böts", rock);
		Song s9 = new Song("Jubel.wav", "Jugbekdl", "böss", rock);
		Song s10 = new Song("Jubel.wav", "Jubyadöksljfel", "böts", rock);
		songList.add(s1);
		songList.add(s2);
		songList.add(s3);
		songList.add(s4);
		songList.add(s5);
		songList.add(s6);
		songList.add(s7);
		songList.add(s8);
		songList.add(s9);
		songList.add(s10);
		
		CardPanel cp = new CardPanel(songList);
		this.add(cp);
		this.revalidate();
		this.repaint();
	
	}

}
