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
		model.saveApplication();
		view.dispose();
		LogIn lg = new LogIn();
		ActionListener lic = new LogInController(Application.getApplication(), lg);
		lg.setController(lic);
	}

}
