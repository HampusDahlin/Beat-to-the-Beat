package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TutorialPanel extends JPanel {
	private BufferedImage image;
	private JButton b;
	
	
	public TutorialPanel() {
		loadImage();
		this.setVisible(true);
		this.setPreferredSize(new Dimension(914,600));
		b = new JButton("Test");
		this.add(b);
		repaint();
		revalidate();
	}
	
    private void loadImage() {  
        String fileName = "images\\tutorial.png";  
        try {  
            URL url = getClass().getResource(fileName);  
            image = ImageIO.read(url);  
        } catch(MalformedURLException mue) {  
            System.out.println("URL trouble: " + mue.getMessage());  
        } catch(IOException ioe) {  
            System.out.println("read trouble: " + ioe.getMessage());  
        }  
    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
	}


}
