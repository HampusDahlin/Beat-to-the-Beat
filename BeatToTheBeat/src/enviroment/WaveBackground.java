package enviroment;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class WaveBackground extends JPanel{

	Graphics g;
	public WaveBackground(){
	}
	
	
	public void updateBackground(float[][] soundwave, boolean beat) {
		Color c = null;
		if(beat){
			c = (g.getColor() == Color.CYAN) ? Color.RED : Color.CYAN;
		}
		
		for(int i = 0; i < 511; i++) {
			g.drawLine(i, (int) (50 + soundwave[0][i]*50), i+1, (int) (50 + soundwave[0][i+1]*50));
			g.drawLine(i, (int) (150 + soundwave[1][i]*50), i+1, (int) (150 + soundwave[1][i+1]*50));
		}
	}
}
