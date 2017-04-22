package GUI;

import java.awt.event.*;

import Application.*;

public class LogOutController implements ActionListener{
	
	private Application model;
	private General view;
	
	public LogOutController(Application model, General view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		model.logOut();	
		view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
	}

}
