package Presentacion;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.MutableComboBoxModel;

import Dominio.CtrlDominio;
import Dominio.Node;

/**
 * Custom implementaton of <code>JComboBox</code> to filter the dropdown list relative to the typed text.<p>
 * Needs a parent from which to read events and select the corresponding list.
 * 
 * @author Xavier Peñalosa
 *
 */
public class MyComboBox extends JComboBox<String> implements ActionListener, KeyListener{


	private static final long serialVersionUID = 1L;
	private JComboBox<String> parentBox;
	
	private CtrlDominio cd = new CtrlDominio();
	private Boolean autocomplete;
	
	private ComboBoxModel<String>[] rawStrings = new ComboBoxModel[4];
	private ComboBoxModel<String> filteredStrings;
	private Integer inCase = new Integer(-1);
	private Integer matchStart = new Integer(0);
	
	public MyComboBox(){
		super();
		
		initParams();
	}
	
	/**
	 * <b>Stub:</b> Gives the <code>JComboBox</code> a <code>CtrlDominio</code>
	 * instance in order to get the needed information for the different selections.
	 * 
	 * @param cd - CtrlDominio, used to request node information
	 */
	public void tempUseCtrlDominio(CtrlDominio cd){
		this.cd = cd;
		initStrings(this.cd);
	}
	
	public void setParent(JComboBox<String> parent){
		parentBox = parent;
		parent.addActionListener(this);
	}
	private void initParams(){
		autocomplete = false;
		
		setPreferredSize(new Dimension(148,24));
		setEditable(true);
		setEnabled(false);
		setFocusable(true);
		setSelectedIndex(-1);
		addActionListener(this);
		getEditor().getEditorComponent().addKeyListener(this);
	}
	
	/**
	 * Calling this function with a <b>true</b> value will autocomplete the
	 * items in the <code>JComboBox</code> once a match is found with more
	 * than 4 characters.<p> Calling this function with a <b>false</b> value
	 * will disable the automatic completion of the field.
	 * 
	 * @param b - New value
	 */
	public void setAutocomplete(Boolean b){
		autocomplete = b;
	}
	
	/**
	 * Gets the node names for the dropdown <code>JComboBox</code> and stores them in a
	 * <code>Model</code><p><b>Warning:</b><p>The strings must be in ascending order for
	 * the autocompletion to work 
	 * 
	 * @param cd - CtrlDominio, to which the node information is requested
	 * @see #setAutocomplete(Boolean)
	 */
	private void initStrings(CtrlDominio cd){
		
		filteredStrings = (MutableComboBoxModel<String>) new DefaultComboBoxModel();
		rawStrings = new ComboBoxModel[4];

		/*
		 * Will use these once we have access to the graph
		 * 
		String[] papers = getNodeNames(cd.getCtrlGraph().getGraph().getPapers());
			rawStrings[0] = new DefaultComboBoxModel<String>(papers);
		String[] autors = getNodeNames(cd.getCtrlGraph().getGraph().getAutors());
			rawStrings[1] = new DefaultComboBoxModel<String>(autors);
		String[] conferencies = getNodeNames(cd.getCtrlGraph().getGraph().getConferencies());
			rawStrings[2] = new DefaultComboBoxModel<String>(conferencies);
		String[] terms = getNodeNames(cd.getCtrlGraph().getGraph().getTermes());
			rawStrings[3] = new DefaultComboBoxModel<String>(terms);
		*/
			
		rawStrings[0] = new DefaultComboBoxModel<String>(
			new String[]{" - Pick a paper -","Paper 1","Paper 2","Paper 3","Paper 4","Paper 5","Paper 6","Paper 7","Paper 8"});
		rawStrings[1] = new DefaultComboBoxModel<String>(
			new String[]{" - Pick an author -","Author 1","Author 2","Author 3","Autor 4","Autor 5","Autora 6","Autora 7","Autora 8","This is tooooooooooooooooooo long"});
		rawStrings[2] = new DefaultComboBoxModel<String>(
			new String[]{" - Pick a conf. -","Conference 1","Conference 2","Conference 2","Conference 3","Conferencia 4","Conferencia 5","Conferencia 6","Conferencia 7","Conferencia 8"});
		rawStrings[3] = new DefaultComboBoxModel<String>(
			new String[]{" - Pick a term -","Term 1","Term 2","Term 3","Term 4","Term 5","Term 6","Term 7","Term 8"});
		
	}
	/**
	 * <b>Stub:</b> Gets an <code>ArrayList</code> of <code>String</code>s which corresponds to
	 * the names of the nodes in <u>alnode</u>.
	 * 
	 * @param alnode <code>Node</code> list from which we will get the names
	 * @return Array of node names as string
	 */
	@SuppressWarnings("unused")
	private String[] getNodeNames(ArrayList<Node> alnode) {
		ArrayList<String> nodeNames = new ArrayList<String>();
		for (int i = 0; i < alnode.size(); ++i){
			nodeNames.add(alnode.get(i).getNom());
		}
		Collections.sort(nodeNames);
		return (String[]) nodeNames.toArray().clone();
	}
	
	/**
	 * Add all the <code>String</code>s that match the prefix typed in the <code>JComboBox</code>
	 * to the filtered <code>ArrayList</code>.<p> Calls a binary search to find the first element
	 * that matches (<u>start</u>) and scans lineally until the string doesn't match.
	 * 
	 * 
	 * @see #findStart(ComboBoxModel, String, int, int)
	 */
	private void updateSubstrings(){
		matchStart = findStart(rawStrings[inCase], getEditor().getItem().toString(), 0, rawStrings[inCase].getSize()-1);
		if (matchStart >= 0 && matchStart < rawStrings[inCase].getSize() && filteredStrings != null){
			ArrayList<String> alist = new ArrayList<String>();
			while (matchStart < rawStrings[inCase].getSize() && rawStrings[inCase].getElementAt(matchStart).startsWith(getEditor().getItem().toString())){
				alist.add(rawStrings[inCase].getElementAt(matchStart));
				++matchStart;
			}
			filteredStrings = new DefaultComboBoxModel(alist.toArray());
		}
		else if (matchStart == -1){
			auxSetModel(rawStrings[inCase]);
		}
		else {
			throw new RuntimeException("\n\n Something went wrong in updateSubstrings! \n Contact fox! \n");
		}
	}
	
	/**
	 * Keeps the current text in display and updates the dropdown options with the ComboBoxModel <u>model</u>
	 * 
	 * @param model - Model to be set
	 */
	private void auxSetModel(ComboBoxModel<String> model){
		String temp = getEditor().getItem().toString();
		setModel(model);
		getEditor().setItem(temp);
	}
	
	/**
	 * Recursive call to find the first element that starts with the <code>String</code> <u>text</u>
	 * 
	 * @param nodeNames - List of all the available strings
	 * @param text - The prefix we are looking for in the strings
	 * @param begin - Lower bound for the search
	 * @param end - Upper bound for the search
	 * @return The first element which starts with the substring <u>text</u>
	 */
	private Integer findStart(ComboBoxModel<String> nodeNames, String text, int begin, int end){
		if (begin == end || begin +1 == end){
			if (nodeNames.getElementAt(begin).startsWith(text)) return begin;
			else if (nodeNames.getElementAt(end).startsWith(text)) return end;
			else return -1;
		}
		else {
			int middle = (begin + end)/2;
			if (nodeNames.getElementAt(middle).compareToIgnoreCase(text) < 0){
				return findStart(nodeNames,text,middle,end);
			}
			else{
				return findStart(nodeNames,text,begin,middle);
			}
		}
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (parentBox != null && e.getSource().equals(parentBox)){
			int index = parentBox.getSelectedIndex() - 1;
			if (index >= 0){
				/*
				 * Either of the valid types
				 * 
				 * 0 - Paper
				 * 1 - Author
				 * 2 - Conference
				 * 3 - Term
				 */
				if (inCase != index){
					setModel(rawStrings[index]);
					setSelectedIndex(0);
					inCase = index;
				}
				setEnabled(true);
			}
			else if (index == -1) { //Pick a type
				setSelectedIndex(-1);
				setEnabled(false);
			}
			else if (index == -2) throw new RuntimeException("\n\n ~MyComboBox exploded, contact a fox to get it fixed~ \n");
		}
		else if (e.getSource().equals(this)){
			/*
			 * The main idea is to lock the dropdown to the selected item...
			 * 
			if (getSelectedIndex != -1){
				updateSubstrings();
				auxSetModel(filteredStrings);
			}
			*/
		}
		else {
			throw new RuntimeException("\n\n ~MyComboBox exploded, contact a fox to get it fixed~ \n");
		}
	}
	
	

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() != e.VK_BACK_SPACE){
			if (getEditor().getItem().toString().length() > 3){
				updateSubstrings();
				
				if (autocomplete){
					setModel(filteredStrings);
					setSelectedIndex(0); //Update JComboBox visually
					getEditor().setItem(getSelectedItem()); //Update text in the JComboBox
				}
				else {
					auxSetModel(filteredStrings);
				}
			}
			else {
				auxSetModel(rawStrings[inCase]);
			}
		}
		else {
			if (getEditor().getItem().toString().length() > 3){
				updateSubstrings();
				
				auxSetModel(filteredStrings);
			}
		}
	}

}
