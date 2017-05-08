package gui.windows;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * 
 * @author Blanca, Simon
 *
 */
public class Register extends JFrame{

	private static final long serialVersionUID = 1L;
	
	JButton ok;
	JButton cancel;

	public Register(String str) {
		setLayout(new BorderLayout());
		
		//Message to be display
		JPanel message = new JPanel(new FlowLayout());
		JLabel label = new JLabel(str);
		message.add(label);
		message.setVisible(true);
		
		//Buttons for applying or canceling
		JPanel buttons = new JPanel(new FlowLayout());
		ok = new JButton("Apply");
		cancel = new JButton("Cancel");
		buttons.add(ok);
		buttons.add(cancel);
		buttons.setVisible(true);
		
		add(message, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);
		
		//Window settings
		setSize(600, 110);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void setControllerOK(ActionListener al){
		ok.addActionListener(al);
	}
	
	public void setControllerCancel(ActionListener al){
		cancel.addActionListener(al);
	}

}
