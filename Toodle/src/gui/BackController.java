package gui;

import gui.windows.General;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BackController implements ActionListener{

	private General view;
	
	public BackController(General view){
		this.view = view;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		view.previous();
	}

}
