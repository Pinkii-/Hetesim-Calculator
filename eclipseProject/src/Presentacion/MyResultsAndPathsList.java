package Presentacion;

import java.util.ArrayList;
import java.util.HashMap;


import javax.swing.DefaultListModel;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Dominio.CtrlPaths;
import Dominio.CtrlResults;
import Presentacion.FormattedResult;

//Custom JList which shows results or paths and is attached to a text area which shows a resume of a selected result.

/**
 * @author Albert Lopez Alcacer
**/

public class MyResultsAndPathsList extends JList<String> implements ListSelectionListener{
	
	private static final long serialVersionUID = 1L;
	private CtrlResults cr;
	private CtrlPaths cp;
	private DefaultListModel<String> dlm;
	private JEditorPane resultResume;
	private HashMap<String,FormattedResult> results;
	private HashMap<String,String> toDelete; //name,idresult
	private HashMap<String,ArrayList<String>> paths; //name,path
	private Integer selectedIndex;
	private String searchOriginNode = "";
	private String searchDestinationNode = "";
	private String searchType = "";
	private String searchPath = "";
	private String threshold = "";
	private String nvalues = "";
	private String listMode = "";
	
	public MyResultsAndPathsList() {
		super();
		initListHandler();
	}
	
	public MyResultsAndPathsList(CtrlPaths cp) {
		super();
		toDelete = new HashMap<String,String>();
		dlm = new DefaultListModel<>();
		
		this.cp = cp;
		this.setModel(dlm);
		this.paths = getAndAddFormattedPaths();
		
		initListHandler();
		listMode = "Paths";
	}
	
	public MyResultsAndPathsList(JEditorPane resultResulme, CtrlResults cr ) {
		super();
		toDelete = new HashMap<String,String>();
		dlm = new DefaultListModel<>();
		
		this.cr = cr;
		this.setModel(dlm);
		this.results = getAndAddFormattedResults();
		this.resultResume = resultResulme;
		resultResume.setEditable(false);
		resultResume.setContentType("text/html");
		
		initListHandler();
		listMode = "Results";
	}
	

	private HashMap<String,FormattedResult> getAndAddFormattedResults() {
		
		HashMap<String,FormattedResult> ret = new HashMap<String,FormattedResult>();
		ArrayList<String> resultIds = cr.getAllResultIds();
		
		for (int i = 0; i < resultIds.size(); ++i) {
			//cambiar al nombre, que se mostrara.
			ArrayList<ArrayList<String>> auxs = cr.getFormatted(resultIds.get(i));
			FormattedResult aux = new FormattedResult(auxs,cr);
			dlm.addElement(aux.getAllInfo());
			ret.put(aux.getAllInfo(), aux);
		}
		return ret;
	}
	
	private HashMap<String,ArrayList<String>> getAndAddFormattedPaths() {
		
		HashMap<String,ArrayList<String>> ret = new HashMap<String,ArrayList<String>>();
		ArrayList<ArrayList<String>> aux = cp.getFormattedPaths();
		for (ArrayList<String> p: aux) {
			String info = "Path's name: " + p.get(0) +"   Path's description: " + p.get(1) + "   Path's content: " + p.get(2);
			dlm.addElement(info);
			ret.put(info, p);
		}
		
		return ret;
	}
	
	public void deleteResult() {
		if (!toDelete.containsKey(dlm.getElementAt(selectedIndex))) {
			String nameAux = dlm.getElementAt(selectedIndex);
			toDelete.put(nameAux,results.get(nameAux).getIdResult());
			dlm.remove(selectedIndex);
			selectedIndex = 0;
		}
	}

	public ArrayList<ArrayList<String>> getFormattedResult() {
		if (dlm.getSize() > 0)return results.get(dlm.getElementAt(selectedIndex));
		else{
			System.out.println("No Results");
			return null;
		}
	}
	
	public ArrayList<String> getFormattedPath() {
		if (dlm.getSize() > 0) return paths.get(dlm.getElementAt(selectedIndex));
		else {
			System.out.println("No paths");
			return null;
		}
		
	}
	
	
	private void generateInfo() {
		
		searchOriginNode = "";
		searchDestinationNode = "";
		searchType = results.get(dlm.getElementAt(selectedIndex)).getResultType();
		searchPath = results.get(dlm.getElementAt(selectedIndex)).getSearchPath();
		//threshold = results.get(selectedIndex).get
		nvalues = Integer.toString(results.get(dlm.getElementAt(selectedIndex)).getNumberOfValues());
		
		resultResume.setText("<b>Search origin Node</b>:  "+searchOriginNode+"<HR>"+
				 "<b>Search destination Node</b>:  "+searchDestinationNode+"<HR>"+
				 "<b>Search type</b>:  "+searchType+"<HR>"+
				 "<b>Search Path</b>:  "+searchPath+"<HR>"+
				 "<b>Search Threshold</b>:  "+threshold+"<HR>"+
				 "<b>Values</b>:  "+nvalues+"<HR>");
	
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
	
	public int getListSize() {
		return dlm.getSize();
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
            if (dlm.getSize() == 0) resultResume.setText("");
			if (listMode == "Results" && dlm.getSize() > 0 ) {
				if (selectedIndex >= 0 && selectedIndex < dlm.size())
					generateInfo();
			} 
    	}
	}
}