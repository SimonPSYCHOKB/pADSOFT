package gui.windows;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CreateUnit extends JFrame{

	private static final long serialVersionUID = 1L;
	
	JTextField name;
	JCheckBox visib;
	JButton done;
	JButton cancel;

	public CreateUnit(){
		super("Add new unit");
		
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(8, 8, 8, 8);
		getContentPane().add(new JLabel("Name : "), gbc);
		gbc.gridy += 1;; 
		getContentPane().add(new JLabel("Visible : "), gbc);
		
		gbc.gridx += 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		name = new JTextField(20);
		getContentPane().add(name, gbc);
        gbc.gridy += 1;
        visib = new JCheckBox();
        getContentPane().add(visib, gbc);
        
        gbc.gridx = 0;
        gbc.gridy += 1;
        done = new JButton("Done");
        getContentPane().add(done, gbc);
        gbc.gridx += 1;
        cancel = new JButton("Cancel");
        getContentPane().add(cancel, gbc);        
        
        
        setSize(800, 200);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	public JTextField getNameField(){
		return name;
	}
	
	public JCheckBox getVisibility(){
		return visib;
	}
	
	public void setControllerCancel(ActionListener al){
		cancel.addActionListener(al);
	}
	
	public void setControllerDone(ActionListener al){
		done.addActionListener(al);
	}
}