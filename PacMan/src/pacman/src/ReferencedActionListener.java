package pacman.src;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ReferencedActionListener implements ActionListener {
	
	private boolean isActivated = false;
	private JButton button;
	
	public ReferencedActionListener(JButton b) {
		button = b;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (isActivated) {
			button.setBackground(Color.LIGHT_GRAY);
		} else {
			button.setBackground(Color.BLACK);
		}
		isActivated = !isActivated;
	}

}
