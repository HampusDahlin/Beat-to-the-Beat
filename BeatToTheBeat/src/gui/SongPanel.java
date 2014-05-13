package gui;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import musichandler.Song;

/**
*
* @author Bj�rn Hedstr�m
* @revised by Malin "Nilhet" Thelin
*/
public class SongPanel extends JPanel{
	
	private PropertyChangeSupport pcs;

   /**
    * Creates new form SongPanel
    */
   public SongPanel(String bgFileName) {
	   this.bgFileName = bgFileName;
	   pcs = new PropertyChangeSupport(this);
       initComponents();
       addMouseListener(listener);
   }
   
   public void paintComponent(Graphics g) {
	   image = null;
	   try {
		   URL url = getClass().getResource(bgFileName);
		   image = ImageIO.read(new File(url.getPath()));
	   } catch (IOException e) {
		   e.printStackTrace();
	   }
	   g.drawImage(image, 0, 0, null);
   }
   
   public void presentSong() {
	   this.artistLabel.setText(song.getArtist());
	   this.genreLabel.setText((song.getGenre()).getName());
	   this.songNameLabel.setText(song.getSongName());
   }

   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
   private void initComponents() {

       songNameLabel = new javax.swing.JLabel();
       genreLabel = new javax.swing.JLabel();
       artistLabel = new javax.swing.JLabel();

       setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
       setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
       setMaximumSize(new java.awt.Dimension(250, 60));
       setMinimumSize(new java.awt.Dimension(250, 60));
       setPreferredSize(new java.awt.Dimension(250, 60));

       songNameLabel.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
       songNameLabel.setForeground(new java.awt.Color(255, 255, 255));
       songNameLabel.setOpaque(false);

       genreLabel.setForeground(new java.awt.Color(255, 255, 255));
       genreLabel.setOpaque(false);
       
       artistLabel.setForeground(new java.awt.Color(255, 255, 255));
       artistLabel.setOpaque(false);

       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
       this.setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addContainerGap()
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(layout.createSequentialGroup()
                       .addComponent(songNameLabel)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
                       .addComponent(genreLabel))
                   .addGroup(layout.createSequentialGroup()
                       .addComponent(artistLabel)
                       .addGap(0, 0, Short.MAX_VALUE)))
               .addContainerGap())
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addContainerGap()
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addComponent(genreLabel)
                   .addComponent(songNameLabel))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
               .addComponent(artistLabel)
               .addContainerGap())
       );
   }// </editor-fold>   
   
   public JLabel getArtistLabel() {
	   return artistLabel;
   }
   
   public JLabel getGenreLabel() {
	   return genreLabel;
   }
   
   public JLabel getSongNameLabel() {
	   return songNameLabel;
   }
   
   public void setSong(Song song) {
	   this.song = song;
       presentSong();
   }
   
   private MouseListener listener = new MouseAdapter() {
	   public void mouseClicked(MouseEvent e) {
		   ((CardPanel)(getParent().getParent())).goToGame();
		   pcs.firePropertyChange("play", null, song);
	   }
   };

   // Variables declaration - do not modify                     
   private javax.swing.JLabel artistLabel;
   private javax.swing.JLabel genreLabel;
   private javax.swing.JLabel songNameLabel;
   private String bgFileName;
   private Song song;
   private BufferedImage image;
   // End of variables declaration                   


public void addObserver(PropertyChangeListener observer) {
	pcs.addPropertyChangeListener(observer);
}


public void removeObserver(PropertyChangeListener observer) {
	pcs.removePropertyChangeListener(observer);
	
}
}
