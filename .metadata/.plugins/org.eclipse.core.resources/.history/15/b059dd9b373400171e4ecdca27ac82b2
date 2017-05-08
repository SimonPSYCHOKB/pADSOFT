package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * 
 * @author Blanca,  Simon
 * Generic Controller for the Cancel button which destroys the view that is passed to the Cancel button
 *
 */
public class CancelController implements ActionListener{

	private JFrame view;
	
	public CancelController(JFrame view){
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		view.dispose();		
	}
}
