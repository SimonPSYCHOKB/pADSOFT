package GUI;

import javax.swing.*;

public class GeneralTeacher extends General{

	private static final long serialVersionUID = 1L;
	JMenuItem stats;
	
	public GeneralTeacher() {
		stats = new JMenuItem("Statistics");
		addOption(stats);
	}

}
