package gui.windows;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

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
			
		layout.putConstraint(SpringLayout.WEST, user, 20, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, user, 10, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, uField, 76, SpringLayout.EAST, user);
		layout.putConstraint(SpringLayout.NORTH, uField, 10, SpringLayout.NORTH, this);
		
		layout.putConstraint(SpringLayout.WEST, password, 20, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.NORTH, password, 30, SpringLayout.NORTH, user);
		
		layout.putConstraint(SpringLayout.WEST, pField, 40, SpringLayout.EAST, password);
		layout.putConstraint(SpringLayout.NORTH, pField, 10, SpringLayout.SOUTH, uField);	
		
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, user, 0, SpringLayout.VERTICAL_CENTER, uField);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, password, 0, SpringLayout.VERTICAL_CENTER, pField);
		
		layout.putConstraint(SpringLayout.WEST, pField, 0, SpringLayout.WEST, uField);
		
		
		this.setPreferredSize(new Dimension(500,170));
		
		msg.add(user);
		msg.add(uField);
		msg.add(password);
		msg.add(pField);
		
		msg.setPreferredSize(new Dimension(150,70));
		msg.setVisible(true);
				
		JPanel button = new JPanel();
		enter = new JButton("Enter");
		
		this.getContentPane().add(BorderLayout.NORTH, msg);
		this.getContentPane().add(BorderLayout.CENTER, button);

		button.add(enter);
		
		this.setSize(300, 110);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Panel Style
		msg.setBackground(Color.WHITE);
		button.setBackground(Color.WHITE);
		//button.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
		
		
		Font font = null;
		try {
			//File fontFile = new File(this.getClass().getResource("Toodle/src/babylon_font.ttf").toURI());
			font = Font.createFont(Font.TRUETYPE_FONT,  new File("src/babylon_font.ttf"));
		} catch (FontFormatException | IOException e) {
			
			e.printStackTrace();
		}   

		GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		genv.registerFont(font);
		
		font = font.deriveFont(20f);
		user.setFont(font);
		password.setFont(font);
		
		enter.setBackground(Color.decode("#edeff2"));
		
		enter.setBorder(BorderFactory.createLineBorder(Color.decode("#a3a3a3"), 1, true));
		Border border = enter.getBorder();
		Border margin = new EmptyBorder(5,10,5,10);
		enter.setBorder(new CompoundBorder(border, margin));
		
		
		
	}
	
	public void setController(ActionListener al){
		enter.addActionListener(al);
		pField.addActionListener(al);
	}
	
	public String getUserName(){
		return uField.getText();
	}
	
	public String getPassword(){
		return String.valueOf(pField.getPassword());
	}

}
