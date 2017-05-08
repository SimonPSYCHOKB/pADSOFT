package gui.windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * 
 * @author Blanca, Simon
 *
 *A simple JFrame that allows the creation of notes 
 */
public class CreateNotes extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextArea notes;
	private JButton cancel;
	private JButton done;
	private JCheckBox visib;
	
	/**
	 * Constructor
	 */
	public CreateNotes(){
		super("Add notes");
		
		//Window layout
		getContentPane().setLayout(new BorderLayout());
		
		
		//Panel for the info
		JPanel info = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Labels
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(8, 8, 8, 8);
		info.add(new JLabel("Notes : "), gbc);
		gbc.gridy += 1;; 
		info.add(new JLabel("Visible : "), gbc);

		//Text area for the notes and checkbox for visibility
		gbc.gridx += 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		notes = new JTextArea();
		info.add(notes, gbc);
		gbc.gridy += 1;
        visib = new JCheckBox();
        info.add(visib, gbc);
		
		//Panel info settings
		info.setVisible(true);
		info.setPreferredSize(new Dimension(400, 200));
		
		
		//Panel for the buttons
		JPanel buttons = new JPanel(new FlowLayout());
		
		//Cancel button
		cancel = new JButton("Cancel");
		buttons.add(cancel);
		done = new JButton("Done");
		buttons.add(done);
		
		
		//Window settings
		getContentPane().add(info, BorderLayout.CENTER);
		getContentPane().add(buttons, BorderLayout.SOUTH);
		
		setSize(800, 200);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		
	}
	
	/**
	 * This method sets the controller for the cancel button
	 * @param al - the controller
	 */
	public void setControllerCancel(ActionListener al){
		cancel.addActionListener(al);
	}
	
	/**
	 * This method sets the controller for the done button
	 * @param al - the controller
	 */
	public void setControllerDone(ActionListener al){
		done.addActionListener(al);
	}
	
	/**
	 * This method returns the text entered for the note
	 * @return the text area with the notes
	 */
	public JTextArea getNotes(){
		return notes;
	}
	
	/**
	 * This method returns the visibility for the note
	 * @return the check box with the visibility
	 */
	public JCheckBox getVisibility(){
		return visib;
	}

}
