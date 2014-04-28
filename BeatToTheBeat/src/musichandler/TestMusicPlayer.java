package musichandler;

import java.applet.Applet;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.print.DocFlavor.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import ddf.minim.AudioPlayer;

public class TestMusicPlayer extends JPanel implements ActionListener{
	JButton play = new JButton("Play");
	JButton pause = new JButton("Pause");
	Song s = new Song("Jubel.wav");
	java.applet.AudioClip  clip;
	
	public TestMusicPlayer(){
		setLayout(new GridLayout(2,1));
		add(play);
		play.addActionListener(this);
		pause.addActionListener(this);
		play.setVisible(true);
		add(pause);
		pause.setVisible(true);
		setVisible(true);
		
		try {
			clip =
			java.applet.Applet.newAudioClip(
			new java.net.URL("file:songs\\Jubel.wav"));
			
		} catch (java.net.MalformedURLException murle) {
			System.out.println(murle);
		}
		
	}
	
	public static void main(String[] args){
		JFrame f = new JFrame();
		
		TestMusicPlayer p = new TestMusicPlayer();
		f.add(p);
		f.setVisible(true);
		f.pack();
	}
	

	
	public void actionPerformed(ActionEvent e){
		System.out.println("!");	
		if(e.getSource() == play){
			clip.play();
		}else if(e.getSource() == pause){
			clip.stop();
		}
	}
		
	
}
