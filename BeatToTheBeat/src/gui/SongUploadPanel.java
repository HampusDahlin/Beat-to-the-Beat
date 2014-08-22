package gui;

import java.awt.Choice;
import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*import org.blinkenlights.jid3.ID3Exception;
import org.blinkenlights.jid3.MP3File;
import org.blinkenlights.jid3.v1.ID3V1Tag;

/**
 *
 * @author Bj�rn Hedstr�m
 */
@SuppressWarnings("serial")
public class SongUploadPanel extends ZoomablePanel {
	/**
	 * Creates new form SongUploadPanel
	 */
	
	public void clearFields() {
		getSuccesLabel().setVisible(false);
		getArtistField().setText("");
		getSongNameField().setText("");
		getOriginalFilepathField().setText("");
	}
	
	public SongUploadPanel() {
		pcs = new PropertyChangeSupport(this);
		initComponents();
	}

	public JLabel getSuccesLabel() {
		return succesLabel;
	}

	public JTextField getArtistField() {
		return artistField;
	}

	public JTextField getSongNameField() {
		return songNameField;
	}

	public JTextField getOriginalFilepathField() {
		return originalFilepathField;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSource() {
		return source;
	}

	public Choice getGenreChoice() {
		return genreChoice;
	}

	private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {       
		pcs.firePropertyChange("back", true, false);
	}                                          

	private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) { 
		pcs.firePropertyChange("load", true, false);
	}          

	private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
		pcs.firePropertyChange("browse", true, false);
	}   

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// autogenerated code starts here                  
	private void initComponents() {

		titleLabel = new javax.swing.JLabel();
		backButton = new javax.swing.JButton();
		loadPanel = new javax.swing.JPanel();
		originalFileLabel = new javax.swing.JLabel();
		originalFilepathField = new javax.swing.JTextField();
		browseButton = new javax.swing.JButton();
		songNameField = new javax.swing.JTextField();
		songNameLabel = new java.awt.Label();
		artistField = new javax.swing.JTextField();
		artistLabel = new java.awt.Label();
		genreChoice = new java.awt.Choice();
		genreLabel = new java.awt.Label();
		loadButton = new javax.swing.JButton();
		succesLabel = new javax.swing.JLabel();

		originalFilepathField.setEditable(false);
		loadPanel.setSize(getPreferredSize());

		titleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("images\\loadSong.png"))); // NOI18N

		backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("images\\back.png"))); // NOI18N
		backButton.setMaximumSize(new java.awt.Dimension(255, 47));
		backButton.setMinimumSize(new java.awt.Dimension(255, 47));
		backButton.setPreferredSize(new java.awt.Dimension(255, 47));
		backButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				backButtonActionPerformed(evt);
			}
		});

		loadPanel.setBackground(new java.awt.Color(255, 255, 255));

		originalFileLabel.setText("Original file:");

		browseButton.setText("...");
		browseButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				browseButtonActionPerformed(evt);
			}
		});

		songNameLabel.setText("Song Name:");

		artistLabel.setText("Artist Name:");

		genreLabel.setText("Genre:");

		loadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("images\\loadSongToGame.png"))); // NOI18N
		loadButton.setMaximumSize(new java.awt.Dimension(255, 47));
		loadButton.setMinimumSize(new java.awt.Dimension(255, 47));
		loadButton.setPreferredSize(new java.awt.Dimension(255, 47));
		loadButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				loadButtonActionPerformed(evt);
			}
		});

		succesLabel.setText("Label that states if succesful");
		succesLabel.setForeground(Color.white);

		javax.swing.GroupLayout loadPanelLayout = new javax.swing.GroupLayout(loadPanel);
		loadPanel.setLayout(loadPanelLayout);
		loadPanelLayout.setHorizontalGroup(
				loadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(loadPanelLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(loadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(loadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(songNameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(artistLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(originalFileLabel)
										.addComponent(genreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(loadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(genreChoice, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(songNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGroup(loadPanelLayout.createSequentialGroup()
														.addComponent(originalFilepathField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(browseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(artistField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(succesLabel))
														.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
														.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loadPanelLayout.createSequentialGroup()
																.addContainerGap(46, Short.MAX_VALUE)
																.addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(46, 46, 46))
				);
		loadPanelLayout.setVerticalGroup(
				loadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(loadPanelLayout.createSequentialGroup()
						.addGap(32, 32, 32)
						.addGroup(loadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(originalFilepathField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(browseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(originalFileLabel))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(loadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addComponent(songNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(songNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(loadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(artistField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(artistLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(loadPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(genreChoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(genreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGap(34, 34, 34)
														.addComponent(succesLabel)
														.addGap(10, 10, 10)
														.addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addContainerGap(35, Short.MAX_VALUE))
				);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(loadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(283, 283, 283))
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addGap(240, 240, 240)
												.addComponent(titleLabel))
												.addGroup(layout.createSequentialGroup()
														.addGap(330, 330, 330)
														.addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addContainerGap(239, Short.MAX_VALUE))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(35, 35, 35)
						.addComponent(titleLabel)
						.addGap(36, 36, 36)
						.addComponent(loadPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(26, 26, 26)
						.addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(34, Short.MAX_VALUE))
				);
	}                                                 

	// Variables declaration - do not modify                     
	private javax.swing.JTextField artistField;
	private java.awt.Label artistLabel;
	private javax.swing.JButton backButton;
	private javax.swing.JButton browseButton;
	private java.awt.Choice genreChoice;
	private java.awt.Label genreLabel;
	private javax.swing.JButton loadButton;
	private javax.swing.JPanel loadPanel;
	private javax.swing.JLabel originalFileLabel;
	private javax.swing.JTextField originalFilepathField;
	private javax.swing.JTextField songNameField;
	private java.awt.Label songNameLabel;
	private javax.swing.JLabel succesLabel;
	private javax.swing.JLabel titleLabel;
	private String source = "";
	public PropertyChangeSupport pcs;
	// End of variables declaration                   



}


