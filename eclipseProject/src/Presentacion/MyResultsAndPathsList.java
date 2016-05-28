package Presentacion;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//Custom JList which shows results and is attached to a text area which shows a resume of a selected result.

public class MyResultsAndPathsList extends JList implements ListSelectionListener{
	
	
	private static final long serialVersionUID = 1L;
	private String idResultSelected;
	private JTextArea resultResume;
	private Integer selectedIndex;
	
	public MyResultsAndPathsList() {
		super();
		initListHandler();
	}
	
	public MyResultsAndPathsList(JTextArea resultResulme ) {
		super();
		this.resultResume = resultResulme;
		initListHandler();
	}
	
	private void initListHandler() {
		this.getSelectionModel().addListSelectionListener(this);		
	}
	
	public int returnSelectedIndex() {
		return selectedIndex;
	}
	
	public boolean indexSelected() {
		return selectedIndex != null;
	}
	
	public void valueChanged(ListSelectionEvent e) {
    	if (!e.getValueIsAdjusting()) {
    	if (resultResume != null) {
    		resultResume.setText("");
        	resultResume.append("kek");
    	}
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        if (!lsm.isSelectionEmpty()) {
        	 int minIndex = lsm.getMinSelectionIndex();
             int maxIndex = lsm.getMaxSelectionIndex();
             for (int i = minIndex; i <= maxIndex; i++) {
                 if (lsm.isSelectedIndex(i)) {
                     selectedIndex = i;
                 }
             }
        }
    	}
        /*
        output.append("Event for indexes "
                      + firstIndex + " - " + lastIndex
                      + "; isAdjusting is " + isAdjusting
                      + "; selected indexes:");

        if (lsm.isSelectionEmpty()) {
            output.append(" <none>");
        } else {
            // Find out which indexes are selected.
            int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            for (int i = minIndex; i <= maxIndex; i++) {
                if (lsm.isSelectedIndex(i)) {
                    output.append(" " + i);
                }
            }
        }*/
	}
}