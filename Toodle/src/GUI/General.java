package GUI;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public abstract class General extends JFrame {

	private static final long serialVersionUID = 1L;
	
	JMenu options;
	JMenuItem logOut;
	
	public General() {
		super("Toodle");
		
		this.getContentPane().setLayout(new BorderLayout());
		
		SpringLayout layout = new SpringLayout();
		JPanel top = new JPanel();
		top.setLayout(layout);
		
		JMenuBar bar = new JMenuBar();
		bar.add(Box.createHorizontalGlue());
		
		options = new JMenu("User", true);
		options.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); 
		
		bar.add(options);
		
		layout.putConstraint(SpringLayout.EAST, options, 1500, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, options, 10, SpringLayout.NORTH, this);
		
		logOut = new JMenuItem("Log out");
		options.add(logOut);
		
		setJMenuBar(bar);
		
		top.setBorder(BorderFactory.createLineBorder(getForeground(), 1));
		top.setPreferredSize(new Dimension(250, 50));
		top.setVisible(true);
		
		this.getContentPane().add(BorderLayout.NORTH, top);
		this.getContentPane().add(BorderLayout.WEST, new JPanel());
		
		this.setVisible(true);
		this.setSize(1800, 1000);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void addPanel(JPanel p){
		p.setVisible(true);
		this.getContentPane().add(BorderLayout.CENTER, p);
	}
	
	public void addControllerLogOut(ActionListener al){
		logOut.addActionListener(al);
	}
	
	public void addOption(JMenuItem mi){
		options.add(mi, 0);
	}
}
