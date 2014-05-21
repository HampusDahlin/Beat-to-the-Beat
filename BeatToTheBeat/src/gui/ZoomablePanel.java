package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public abstract class ZoomablePanel extends JPanel implements ActionListener {
    private BufferedImage image;  
    private final double STANDARDSCALE = 1.0;
    private final double ZOOMSCALE = 1.1;
    private double scale = STANDARDSCALE;

    protected void paintComponent(Graphics g) {
        loadImage();  
        super.paintComponent(g);  
        Graphics2D g2 = (Graphics2D)g;  
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,  
                            RenderingHints.VALUE_INTERPOLATION_BICUBIC);  
        int w = getWidth();  
        int h = getHeight();  
        int imageWidth = image.getWidth();  
        int imageHeight = image.getHeight();  
        double x = (w - scale * imageWidth)/2;  
        double y = (h - scale * imageHeight)/2;  
        AffineTransform at = AffineTransform.getTranslateInstance(x,y);  
        at.scale(scale, scale);  
        g2.drawRenderedImage(image, at);

        if (scale != STANDARDSCALE) {
        	scale -= 0.01;
        	System.out.println(scale);
        }
    }  
    
    public void setScale(double s) {
    	scale = s;
    	repaint();
    	revalidate();
    }
    
    public void zoom() {
    	setScale(ZOOMSCALE);
    }
    
    public void recead() {
    	setScale(STANDARDSCALE);
    }
   
    private void loadImage() {  
        String fileName = "speaker.png";  
        try {  
            URL url = getClass().getResource(fileName);  
            image = ImageIO.read(url);  
        } catch(MalformedURLException mue) {  
            System.out.println("URL trouble: " + mue.getMessage());  
        } catch(IOException ioe) {  
            System.out.println("read trouble: " + ioe.getMessage());  
        }  
    }
    
} //end ZoomablePanel