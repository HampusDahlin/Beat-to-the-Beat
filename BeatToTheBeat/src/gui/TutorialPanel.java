package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TutorialPanel extends JPanel {
	private BufferedImage image;
	private JButton procced;

	
	
	public TutorialPanel() {
		loadImage();
		this.setVisible(true);
		this.setPreferredSize(new Dimension(914,600));
		procced = new JButton("Procced");
		procced.setSize(100,30);
		procced.setLocation(790,525);
		JCheckBox show = new JCheckBox("Don't show this again");
		show.setBackground(new Color(24,24,24));
		show.setForeground(Color.WHITE);

		this.setLayout(new BorderLayout());
		this.add(procced, BorderLayout.SOUTH);
		this.add(show, BorderLayout.SOUTH);
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
