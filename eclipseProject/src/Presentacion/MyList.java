package Presentacion;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Custom implementaton of <code>JList</code> to display the node information.<p>
 * 
 * @author Xavier Peñalosa
 *
 */
public class MyList extends JList implements ListSelectionListener, MouseListener {

	
	/*
	 * public MyList(ArrayList<String)
	 */
	public MyList(){
		super();
		initParams();
		
	}
	
	public MyList(ArrayList<NodePairPresentacion> alist){
		super();
		
		initParams();
	}
	private void initParams(){
		

		setBorder(new TitledBorder(LineBorder.createGrayLineBorder(),""));
		setEnabled(false);
		setFocusable(true);
		setPreferredSize(new Dimension(348,208));
		
		addListSelectionListener(this);
		addMouseListener(this);
	}
	public void setResult(ArrayList<NodePairPresentacion> alist){
		
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if (getSelectedIndex() == 0){
			System.out.print("List");
		}
	}



}
