package gui.windows;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * 
 * @author Blanca, Simon
 * 
 * This asbtract JFrame is the foundation upon most of the Application rest. 
 * It has the logo, as well as the options, and all the JPannels necessary for a 
 * good user experience
 *
 */
public abstract class General extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JMenu options;
	private JMenuItem logOut;
	private JMenuItem courses;
	private JPanel central;
	private JPanel previous;
	private JPanel preprevious;
	private JPanel top;
	private JPanel east;
	private JPanel west;
	private BorderLayout layout;
	private BufferedImage myPicture;
	
	
	/**
	 * Constructor
	 */
	public General() {
		super("Toodle");
		
		layout = new BorderLayout();
		this.getContentPane().setLayout(layout);
		
		//Menu with the user options
		JMenuBar bar = new JMenuBar();
		bar.add(Box.createHorizontalGlue());
		
		options = new JMenu("User", true);
		options.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); 
		
		bar.add(options);
				
		courses = new JMenuItem("Courses");
		options.add(courses);
		logOut = new JMenuItem("Log out");
		options.add(logOut);
		
		setJMenuBar(bar);
		
		//Top panel with the logo
		top = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		try {
			myPicture = ImageIO.read(new File("src/Toodle.logo.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		top.add(picLabel);
//		top.setBorder(BorderFactory.createLineBorder(getForeground(), 1));
		top.setPreferredSize(new Dimension(250, 130));
		top.setVisible(true);
		
		//Central panel
		central = new JPanel();
		east = new JPanel();
		west = new JPanel();
		
		this.getContentPane().add(BorderLayout.NORTH, top);
		this.getContentPane().add(BorderLayout.WEST, west);
		this.getContentPane().add(BorderLayout.EAST, east);
		this.getContentPane().add(BorderLayout.CENTER, central);

		
		//Window settings
		this.setSize(1800, 1000);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * This method adds a panel in the center of the general frame
	 * @param p - panel to be added
	 */
	public void addPanel(JPanel p){
		preprevious = previous;
		previous = central;
		if(central != null){
			remove(layout.getLayoutComponent(BorderLayout.CENTER));	
			central = null;
		}
		if(west != null){
			remove(layout.getLayoutComponent(BorderLayout.WEST));
			west = null;
		}
		if(east != null){
			remove(layout.getLayoutComponent(BorderLayout.EAST));
			east = null;
		}
		p.setVisible(true);
		central = p;
		this.getContentPane().add(BorderLayout.CENTER, p);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	/**
	 * This method adds a panel in the west of the general frame
	 * @param p - panel to be added
	 */
	public void addPanelWest(JPanel p){
		if(central != null){
			remove(layout.getLayoutComponent(BorderLayout.CENTER));	
			central = null;
		}
		if(west != null){
			remove(layout.getLayoutComponent(BorderLayout.WEST));
			west = null;
		}
		west = p;
		p.setVisible(true);
		this.getContentPane().add(BorderLayout.WEST, p);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	/**
	 * This method adds a panel in the east of the general frame
	 * @param p
	 */
	public void addPanelEast(JPanel p){
		if(central != null){
			remove(layout.getLayoutComponent(BorderLayout.CENTER));	
			central = null;
		}
		if(east != null){
			remove(layout.getLayoutComponent(BorderLayout.EAST));
			east = null;
		}
		east = p;
		p.setVisible(true);
		this.getContentPane().add(BorderLayout.EAST, p);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	/**
	 * This method shows the previous panel that was showed in the center of the frame
	 */
	public void previous(){
		central.setVisible(false);
		if(previous.equals(central)){
			central = preprevious;
			preprevious.setVisible(true);
		}
		else{
			central = previous;
			previous = preprevious;
			central.setVisible(true);
		}
		this.getContentPane().add(BorderLayout.CENTER, central);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	/**
	 * This method sets the controller for the log out option on the menu
	 * @param al - the controller
	 */
	public void addControllerLogOut(ActionListener al){
		logOut.addActionListener(al);
	}
	
	/**
	 * This method sets the controller for the courses option on the menu
	 * @param al - the controller
	 */
	public void addControllerCourses(ActionListener al){
		courses.addActionListener(al);
	}
	
	/**
	 * This method adds an option to the menu
	 * @param mi - option to be added
	 */
	public void addOption(JMenuItem mi){
		options.add(mi, 0);
	}
}
