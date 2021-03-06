package gui;

import java.awt.Graphics;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
*
* @author Bj�rn Hedstr�m
*/

@SuppressWarnings("serial")
public class MainMenu extends ZoomablePanel {

   /**
    * Creates new form MainMenu
    */
   public MainMenu() {
	   pcs = new PropertyChangeSupport(this);
       initComponents();
       repaint(); 
   }
   
   public void paintComponent(Graphics g) {
	   super.paintComponent(g);
   }
   
   private void playSongButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
		pcs.firePropertyChange("selection", true, false);
   }  
   
   private void optionsButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
		pcs.firePropertyChange("options", true, false);
   }

   private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
	   System.exit(0);
   }  
   
   private void songUploadButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
		pcs.firePropertyChange("upload", true, false);
   }  
   
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
   
   //Here begins the autogenerated code

   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
   private void initComponents() {
       logo = new javax.swing.JLabel();
       playSongButton = new javax.swing.JButton();
       optionsButton = new javax.swing.JButton();
       exitButton = new javax.swing.JButton();
       songUploadButton = new javax.swing.JButton();

       setBackground(new java.awt.Color(255, 255, 255));
       setMaximumSize(new java.awt.Dimension(915, 600));
       setMinimumSize(new java.awt.Dimension(915, 600));

       logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("images\\beat3.PNG"))); // NOI18N

       playSongButton.setBackground(new java.awt.Color(204, 204, 204));
       playSongButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("images\\playSong.png"))); // NOI18N
       playSongButton.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               playSongButtonActionPerformed(evt);
           }
       });

       optionsButton.setBackground(new java.awt.Color(204, 204, 204));
       optionsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("images\\options.png"))); // NOI18N
       optionsButton.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               optionsButtonActionPerformed(evt);
           }
       });
       
       exitButton.setBackground(new java.awt.Color(204, 204, 204));
       exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("images\\exit.png"))); // NOI18N
       exitButton.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               exitButtonActionPerformed(evt);
           }
       });

       songUploadButton.setBackground(new java.awt.Color(204, 204, 204));
       songUploadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("images\\uploadSong.png"))); // NOI18N
       songUploadButton.addActionListener(new java.awt.event.ActionListener() {
           public void actionPerformed(java.awt.event.ActionEvent evt) {
               songUploadButtonActionPerformed(evt);
           }
       });

       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
       this.setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
               .addContainerGap(299, Short.MAX_VALUE)
               .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addComponent(exitButton)
                   .addComponent(optionsButton)
                   .addComponent(logo)
                   .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                       .addComponent(songUploadButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                       .addComponent(playSongButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
               .addGap(330, 330, 330))
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addGap(35, 35, 35)
               .addComponent(logo)
               .addGap(54, 54, 54)
               .addComponent(playSongButton)
               .addGap(18, 18, 18)
               .addComponent(songUploadButton)
               .addGap(18, 18, 18)
               .addComponent(optionsButton)
               .addGap(18, 18, 18)
               .addComponent(exitButton)
               .addContainerGap(48, Short.MAX_VALUE))
       );
   }      
                              


   // Variables declaration - do not modify                     
   private javax.swing.JButton exitButton;
   private javax.swing.JButton playSongButton;
   private javax.swing.JLabel logo;
   private javax.swing.JButton optionsButton;
   private javax.swing.JButton songUploadButton;
   public PropertyChangeSupport pcs;
   // End of variables declaration
   
}