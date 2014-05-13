package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import musichandler.Song;

/**
*
* @author Bj�rn Hedstr�m
*/
public class SongSelection extends javax.swing.JPanel {
	
   /**
    * Creates new form SongSelection
    */
   public SongSelection(List<Song> songList) {
	   this.songList = songList;
	   presentedSongList = songList;
       initComponents();
       presentSongList(0);
   } 
   
   public void paintComponent(Graphics g) {
	   
	   BufferedImage image = null;
	   try {
		   URL url = getClass().getResource("speaker.png");
		   image = ImageIO.read(new File(url.getPath()));
	   } catch (IOException e) {
		   e.printStackTrace();
	   }
	   g.drawImage(image, 0, 0, null);
   }
   
   
   public List<Song> searchSongList(String searchTerm) {
	   searchTerm = searchTerm.toLowerCase();
	   List<Song> searchResults = new ArrayList<Song>();
	   if(searchTerm.equals("")) {
		   return songList;
	   } else {
		   for(int i = 0; i < songList.size(); i++) {
			   if(((songList.get(i).getSongName()).toLowerCase()).contains(searchTerm)) {
				   searchResults.add(songList.get(i));
			   } else if(((songList.get(i).getArtist()).toLowerCase()).contains(searchTerm)) {
				   searchResults.add(songList.get(i));
			   } else if((((songList.get(i).getGenre()).getName())
					   .toLowerCase()).contains(searchTerm)) {
				   searchResults.add(songList.get(i));
			   }
		   }
	   }
	   return searchResults;
   }
   
   private void backButtonActionPerformed(java.awt.event.ActionEvent evt) { 
	   clearSearch();
	   ((CardPanel)this.getParent()).back();
   }   
   
   private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {  
	   presentSongList(previousNewFirst + 4);
   }     
   
   private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {  
	   presentSongList(previousNewFirst - 4);
   }    
   
   private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {  
	   presentedSongList = searchSongList(searchField.getText());
	   if(!presentedSongList.equals(songList)) {
		   clearSearchButton.setVisible(true);
	   }
	   presentSongList(0);
	   searchField.setText("");
   }    
   
   private void clearSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {  
	   clearSearch();
   } 
   
   public void clearSearch() {
	   clearSearchButton.setVisible(false);
	   presentedSongList = songList;
	   presentSongList(0);
	   searchField.setText("");
   }
   
   
   public void presentSongList(int newFirst) {
	   
	   previousNewFirst = newFirst;
	   if(newFirst < 0) {
		   newFirst = 0;
	   } else if(newFirst >= presentedSongList.size()) {
		   newFirst = presentedSongList.size() - 1;
	   }
	   
	   if(newFirst > 0) {
		   previousButton.setVisible(true);
	   } else {
		   previousButton.setVisible(false);
	   }
		
	   for(int i = 0; i < 4; i++) {
		   if((newFirst + i) < presentedSongList.size()) {
			   songPanels[i].setVisible(true);
			   songPanels[i].setSong(presentedSongList.get(newFirst + i));
			   //songPanels[i].setIndex(newFirst + i);
		   } else {
			   nextButton.setVisible(false);
			   songPanels[i].setVisible(false);
		   }
	   }	
	   
	   for(int i = 0; i < 4; i++) {
		   if(!songPanels[i].isVisible() || (newFirst + 4) >= presentedSongList.size()) {
			   nextButton.setVisible(false);
		   } else {
			   nextButton.setVisible(true);
		   }
	   }
   }
   
   public SongPanel[] getSongPanels() {
	   return songPanels;
   }
   
   //(mostly)autogenerated code starts here

   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
   private void initComponents() {
	   
	   titleLabel = new javax.swing.JLabel();
       songPanels = new SongPanel[10];
       songPanels[0] = new SongPanel("k1plate.jpg");
       songPanels[1] = new SongPanel("k2plate.jpg");
       songPanels[2] = new SongPanel("k3plate.jpg");
       songPanels[3] = new SongPanel("k4plate.jpg");
       
       nextButton = new javax.swing.JButton();
       previousButton = new javax.swing.JButton();
       backButton = new javax.swing.JButton();
       searchField = new javax.swing.JTextField();
       searchButton = new javax.swing.JButton();
       clearSearchButton = new javax.swing.JButton();
       clearSearchButton.setVisible(false);
       clearSearchButton.setPreferredSize(new Dimension(61, 23));


       setBackground(new java.awt.Color(255, 255, 255));

       titleLabel.setIcon(new javax.swing.ImageIcon("C:\\Users\\edge\\Downloads\\chooseASong.png")); // NOI18N

       nextButton.setText("Next");
       nextButton.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               nextButtonActionPerformed(evt);
           }
       });

       previousButton.setText("Previous");
       previousButton.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               previousButtonActionPerformed(evt);
           }
       });

       backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("images\\back.png"))); // NOI18N
       backButton.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               backButtonActionPerformed(evt);
           }
       });

       searchField.setSelectionColor(new java.awt.Color(102, 102, 102));

       searchButton.setIcon(new javax.swing.ImageIcon("C:\\Users\\edge\\Pictures\\search.png")); // NOI18N
       searchButton.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               searchButtonActionPerformed(evt);
           }
       });

       clearSearchButton.setText("Reset");
       clearSearchButton.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               clearSearchButtonActionPerformed(evt);
           }
       });

       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
       this.setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addContainerGap(153, Short.MAX_VALUE)
               .addComponent(titleLabel)
               .addGap(153, 153, 153))
           .addGroup(layout.createSequentialGroup()
               .addGap(332, 332, 332)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                   .addGroup(layout.createSequentialGroup()
                       .addComponent(previousButton)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                       .addComponent(clearSearchButton)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                   .addComponent(songPanels[3], javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                   .addComponent(songPanels[2], javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                   .addComponent(songPanels[1], javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                   .addComponent(songPanels[0], javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                   .addComponent(backButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                   .addGroup(layout.createSequentialGroup()
                       .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                       .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
               .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addGap(35, 35, 35)
               .addComponent(titleLabel)
               .addGap(26, 26, 26)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                   .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addComponent(songPanels[0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addComponent(songPanels[1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addComponent(songPanels[2], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addComponent(songPanels[3], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                   .addComponent(previousButton)
                   .addComponent(nextButton)
                   .addComponent(clearSearchButton))
               .addGap(26, 26, 26)
               .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
               .addComponent(backButton)
               .addContainerGap(48, Short.MAX_VALUE))
       );
   }// </editor-fold>                               

   // Variables declaration - do not modify     
   private SongPanel[] songPanels;
   private javax.swing.JButton backButton;
   private javax.swing.JButton nextButton;
   private javax.swing.JButton previousButton;
   private javax.swing.JButton searchButton;
   private javax.swing.JTextField searchField;
   private javax.swing.JLabel titleLabel;
   private List<Song> songList;
   private List<Song> presentedSongList;
   private int previousNewFirst;
   private javax.swing.JButton clearSearchButton;

   // End of variables declaration                   
}