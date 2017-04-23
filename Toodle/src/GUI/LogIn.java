package GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LogIn extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private JButton enter;
	private JTextField uField;
	private JPasswordField pField;

	public LogIn() {
		super("Log-in");
		
		this.getContentPane().setLayout(new BorderLayout());

		SpringLayout layout = new SpringLayout();
		JPanel msg = new JPanel();
		msg.setLayout(layout);
		
		JLabel user = new JLabel("User: ");
		JLabel password = new JLabel("Password: ");
		
		uField = new JTextField(10);
		pField = new JPasswordField(10);
			
		layout.putConstraint(SpringLayout.WEST, user, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, user, 5, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, uField, 5, SpringLayout.EAST, user);
		layout.putConstraint(SpringLayout.NORTH, uField, 5, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, password, 0, SpringLayout.WEST, user);
		layout.putConstraint(SpringLayout.NORTH, password, 8, SpringLayout.SOUTH, user);
		layout.putConstraint(SpringLayout.WEST, pField, 5, SpringLayout.EAST, password);
		layout.putConstraint(SpringLayout.NORTH, pField, 5, SpringLayout.SOUTH, uField);	
		
		msg.add(user);
		msg.add(uField);
		msg.add(password);
		msg.add(pField);
		
		msg.setPreferredSize(new Dimension(250,50));
		msg.setVisible(true);
				
		JPanel button = new JPanel();
		enter = new JButton("Enter");
		
		this.getContentPane().add(BorderLayout.NORTH, msg);
		this.getContentPane().add(BorderLayout.CENTER, button);

		button.add(enter);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		this.setVisible(true);
		this.setSize(300, 110);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void setController(ActionListener al){
		enter.addActionListener(al);
	}
	
	public void setControllerEnter(ActionListener al){
		pField.addActionListener(al);
	}
	
	public String getUserName(){
		return uField.getText();
	}
	
	public String getPassword(){
		return String.valueOf(pField.getPassword());
	}

}
