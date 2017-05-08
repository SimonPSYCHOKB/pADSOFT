package gui.panels.controllers;


import java.awt.Point;

import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

/**
 * 
 * @author Blanca, Simon
 *
 *This is used for the lists of students when a teacher is viewing a course. 
 */
public class TablePopUp implements PopupMenuListener{
	
	private JTable table;
	private JPopupMenu popup;

	public TablePopUp(JTable table, JPopupMenu popup){
		this.table = table;
		this.popup = popup;
	}

	@Override
	public void popupMenuCanceled(PopupMenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                int rowAtPoint = table.rowAtPoint(SwingUtilities.convertPoint(popup, new Point(0, 0), table));
                if (rowAtPoint > -1) {
                    table.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                }
            }
        });		
	}
	

}
