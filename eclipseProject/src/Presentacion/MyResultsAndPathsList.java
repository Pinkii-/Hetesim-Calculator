package Presentacion;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Presentacion.FormattedResultsManager.FormattedResult;

//Custom JList which shows results or paths and is attached to a text area which shows a resume of a selected result.

public class MyResultsAndPathsList extends JList<String> implements ListSelectionListener{
	
	private static final long serialVersionUID = 1L;
	private DefaultListModel<String> dlm;
	private JEditorPane resultResume;
	private ArrayList<FormattedResult> results;
	private ArrayList<ArrayList<String>> paths;
	private Integer selectedIndex;
	private String searchOriginNode = "";
	private String searchDestinationNode = "";
	private String searchType = "";
	private String searchPath = "";
	private String threshold = "";
	private String nvalues = "";
	
	public MyResultsAndPathsList() {
		super();
		initListHandler();
	}
	
	public MyResultsAndPathsList(ArrayList<ArrayList<String>> paths) {
		super();
		this.paths = paths;
		dlm = new DefaultListModel<String>();
		addPaths();
		initListHandler();
	}
	
	public MyResultsAndPathsList(JEditorPane resultResulme, ArrayList<FormattedResult> results ) {
		super();
		this.resultResume = resultResulme;
		this.results = results;
		dlm = new DefaultListModel<String>();
		resultResume.setEditable(false);
		resultResume.setContentType("text/html");
		addResults();
		initListHandler();
		setText();
	}
	
	private void addResults() {
		for(int i = 0; i < results.size(); ++i)
			dlm.addElement("ResultName # "+i);
			
		this.setModel(dlm);
	}
	
	private void addPaths() {
		for (ArrayList<String> path: paths) {
			dlm.addElement("Path's name: " + path.get(0) +"   Path's description: " + path.get(1) + "   Path's content: " + path.get(2));
		}
		
		this.setModel(dlm);
	}
	
	private void generateInfo() {
		searchOriginNode = "";
		searchDestinationNode = "";
		searchType = results.get(selectedIndex).getResultType();
		searchPath = results.get(selectedIndex).getSearchPath();
		//threshold = results.get(selectedIndex).get
		nvalues = Integer.toString(results.get(selectedIndex).getNumberOfValues());
	
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
	
	private void setText() {
		
		resultResume.setText("<b>Search origin Node</b>:  "+searchOriginNode+"<HR>"+
				 "<b>Search destination Node</b>:  "+searchDestinationNode+"<HR>"+
				 "<b>Search type</b>:  "+searchType+"<HR>"+
				 "<b>Search Path</b>:  "+searchPath+"<HR>"+
				 "<b>Search Threshold</b>:  "+threshold+"<HR>"+
				 "<b>Values</b>:  "+nvalues+"<HR>");
	}
	
	public void valueChanged(ListSelectionEvent e) {
    	if (!e.getValueIsAdjusting()) {
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
			if (resultResume != null) {
				generateInfo();
				setText();
			} 
    	}
	}
}