package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 *
 * @author Bj�rn Hedstr�m
 */
public class ScorePanel extends ZoomablePanel {

    /**
     * Creates new form ScorePanel
     */
    public ScorePanel() {
        initComponents();
    }
    
    public void presentScore(int score) {
    	scoreLabel.setText(score + "");
    }
    
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {  
    	((CardPanel)this.getParent()).playSong();
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



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
                    
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        scoreLabel = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();

        titleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("images\\score.png")));

        scoreLabel.setFont(new java.awt.Font("Serif", 1, 89));
        scoreLabel.setForeground(new java.awt.Color(255, 255, 255));
        scoreLabel.setText("3458900");
        scoreLabel.setOpaque(true);

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("images\\back.png")));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(326, 326, 326)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scoreLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(backButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(titleLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(273, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(titleLabel)
                .addGap(104, 104, 104)
                .addComponent(scoreLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
                .addComponent(backButton)
                .addGap(49, 49, 49))
        );
    }// </editor-fold>                        

    // Variables declaration - do not modify                     
    private javax.swing.JButton backButton;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration                   
}