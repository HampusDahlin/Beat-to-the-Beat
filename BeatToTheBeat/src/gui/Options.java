package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import services.HomogeneousFileHandler;

/**
 *
 * @author Bj�rn Hedstr�m
 * @revisedBy Hampus Dahlin
 */
@SuppressWarnings("serial")
public class Options extends ZoomablePanel implements ChangeListener {

	/**
	 * Creates new form Options
	 */
	public Options() {
		pcs = new PropertyChangeSupport(this);
		initComponents();
		volumeSlider.addChangeListener(this);
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
		((CardPanel)this.getParent()).back();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == volumeSlider) {
			pcs.firePropertyChange("volumeChange", null, volumeSlider.getValue());
		} else if (e.getSource().equals(intensitySlider)) {
			pcs.firePropertyChange("bgSlider", true, intensitySlider.getValue());

			new HomogeneousFileHandler().saveAs("options.conf", intensitySlider.getValue());
		}
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}


	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents() {

		titleLabel = new javax.swing.JLabel();
		volumeSlider = new javax.swing.JSlider();
		maxVolume = new javax.swing.JLabel();
		miniVolume = new javax.swing.JLabel();
		backButton = new javax.swing.JButton();
		volumeLabel = new javax.swing.JLabel();
		intensitySlider = new javax.swing.JSlider();
		backgroundIntensityLabel = new javax.swing.JLabel();
		maxIntensity = new javax.swing.JLabel();
		miniIntensity = new javax.swing.JLabel();

		setBackground(new java.awt.Color(255, 255, 255));
		setMaximumSize(new java.awt.Dimension(914, 600));
		setMinimumSize(new java.awt.Dimension(914, 600));

		titleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("images\\options2.png"))); // NOI18N

		volumeSlider.setBackground(new java.awt.Color(255, 255, 255));
		volumeSlider.setForeground(new java.awt.Color(255, 51, 102));
		volumeSlider.setMinorTickSpacing(10);
		volumeSlider.setPaintTicks(true);
		volumeSlider.setSnapToTicks(true);
		volumeSlider.setOpaque(false);

		maxVolume.setText("100");
		maxVolume.setForeground(Color.white);

		miniVolume.setText("0");
		miniVolume.setForeground(Color.white);

		backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("images\\back.png"))); // NOI18N
		backButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				backButtonActionPerformed(evt);
			}
		});

		volumeLabel.setText("Volume");
		volumeLabel.setForeground(Color.white);


		intensitySlider.setBackground(new java.awt.Color(255, 255, 255));
		intensitySlider.setForeground(new java.awt.Color(255, 51, 102));
		intensitySlider.setMaximum(2);
		intensitySlider.setOpaque(false);
		intensitySlider.setMinorTickSpacing(1);
		intensitySlider.setPaintTicks(true);
		intensitySlider.setSnapToTicks(true);
		intensitySlider.addChangeListener(this);
		intensitySlider.setValue((int)new HomogeneousFileHandler().load("options.conf").get(0));


		backgroundIntensityLabel.setText("Background Intensity");
		backgroundIntensityLabel.setForeground(Color.white);

		maxIntensity.setText("2");
		maxIntensity.setForeground(Color.white);

		miniIntensity.setText("0");
		miniIntensity.setForeground(Color.white);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGap(329, 329, 329)
										.addComponent(backButton))
										.addGroup(layout.createSequentialGroup()
												.addGap(286, 286, 286)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
														.addGroup(layout.createSequentialGroup()
																.addComponent(miniIntensity)
																.addGap(100, 100, 100)
																.addComponent(backgroundIntensityLabel)
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(maxIntensity))
																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
																		.addGroup(layout.createSequentialGroup()
																				.addComponent(miniVolume)
																				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																				.addComponent(volumeLabel)
																				.addGap(137, 137, 137)
																				.addComponent(maxVolume))
																				.addComponent(titleLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																				.addComponent(volumeSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																				.addComponent(intensitySlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
																				.addGap(286, 286, 286))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(60, 60, 60)
						.addComponent(titleLabel)
						.addGap(106, 106, 106)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(maxVolume)
								.addComponent(miniVolume)
								.addComponent(volumeLabel))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(volumeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(48, 48, 48)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(backgroundIntensityLabel)
										.addComponent(maxIntensity)
										.addComponent(miniIntensity))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(intensitySlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(48, 48, 48)
										.addComponent(backButton)
										.addContainerGap(95, Short.MAX_VALUE))
				);
	}// </editor-fold>                        


	// Variables declaration - do not modify                     
	private javax.swing.JButton backButton;
	private javax.swing.JLabel backgroundIntensityLabel;
	private javax.swing.JSlider intensitySlider;
	private javax.swing.JLabel maxIntensity;
	private javax.swing.JLabel maxVolume;
	private javax.swing.JLabel miniIntensity;
	private javax.swing.JLabel miniVolume;
	private javax.swing.JLabel titleLabel;
	private javax.swing.JLabel volumeLabel;
	private javax.swing.JSlider volumeSlider;
	public PropertyChangeSupport pcs;
	// End of variables declaration                   
}
