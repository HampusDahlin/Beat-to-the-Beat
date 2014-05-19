package enviroment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;

import musichandler.Genre;
import musichandler.Song;
import musichandler.SoundHandler;

import javax.swing.Timer;

public class WaveTest extends JFrame implements PropertyChangeListener, ActionListener{

	WaveBackground back;
	SoundHandler sound;
	Timer time;
	
	public WaveTest(){
		back = new WaveBackground();
		time = new Timer(10, this);
		sound = new SoundHandler(new Song("Rotterdam Termination Source - Poing.mp3", "Jubel", "Bajsis", new Genre("Happy Hardcore", 0, 10, 3, 200)), false);
		sound.getAnalyzer().getPcs().addPropertyChangeListener(this);
		
		this.add(back);
		back.setVisible(true);
		this.setSize(500, 500);
		this.setVisible(true);
		revalidate();
		repaint();
		time.start();
		sound.start();
	}
	
	public static void main(String[] args){
		WaveTest v = new WaveTest();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("beat")){
			back.updateBackground((float[][])evt.getNewValue(), (boolean)evt.getOldValue());
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(time)){
			sound.analyze();
		}
		
	}
}
