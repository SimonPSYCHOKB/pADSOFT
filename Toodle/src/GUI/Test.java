package GUI;

import javax.swing.*;

import Test.*;

public class Test extends JPanel{

	private static final long serialVersionUID = 1L;

	public Test(Exercise e) {
		JLabel text = new JLabel(e.showQuestionTest());
		add(text);
		setVisible(true);
	}

}
