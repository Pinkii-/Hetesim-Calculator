package Presentacion;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.MutableComboBoxModel;

import Dominio.CtrlDominio;
import Dominio.Node;

/**
 * Custom implementaton of <strong>JComboBox</strong> to filter the dropdown list relative to the typed text
 * Needs a parent from which to read events and select the corresponding list.
 * @author Xavier Peñalosa
 *
 */
public class MyComboBox extends JComboBox<String> implements ActionListener, KeyListener{


	private static final long serialVersionUID = 1L;
	private JComboBox<String> parentBox;
	
	private CtrlDominio cd = new CtrlDominio();
	
	private ComboBoxModel<String>[] rawStrings = new ComboBoxModel[4];
	private ComboBoxModel<String> filteredStrings;
	private Integer inCase = new Integer(-1);
	private Integer matchStart = new Integer(0);
	
	public MyComboBox(){
		super();
		
		initParams();
	}
	
	public void tempUseCtrlDominio(CtrlDominio cd){
		this.cd = cd;
		initStrings(this.cd);
	}
	
	public void setParent(JComboBox<String> parent){
		parentBox = parent;
	}
	private void initParams(){
		setPreferredSize(new Dimension(148,24));
		setEditable(true);
		setEnabled(false);
		setFocusable(true);
		setSelectedIndex(-1);
		addActionListener(this);
		getEditor().getEditorComponent().addKeyListener(this);
	}
	/**
	 * Gets the node names for the dropdown JComboBox and stores them in a Model
	 * 
	 * <strong>Warning</strong> The strings must be ordered for the autocompletion to work 
	 * 
	 * @param alist Contains the names of the nodes
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
			new String[]{" - Pick an author -","Autor 1","Autor 2","Autor 3","Autor 4","Autor 5","Autor 6","Autor 7","Autor 8","This is tooooooooooooooooooo long"});
		rawStrings[2] = new DefaultComboBoxModel<String>(
			new String[]{" - Pick a conf. -","Conferencia 1","Conferencia 2","Conferencia 2","Conferencia 3","Conferencia 4","Conferencia 5","Conferencia 6","Conferencia 7","Conferencia 8"});
		rawStrings[3] = new DefaultComboBoxModel<String>(
			new String[]{" - Pick a term -","Term 1", "Term 2","Term 3", "Term 4","Term 5", "Term 6","Term 7", "Term 8"});
		
	}
	/**
	 * Stub
	 * 
	 * @param alnode Node list from which we will get the names
	 * @return Array of node names as string
	 */
	private String[] getNodeNames(ArrayList<Node> alnode) {
		ArrayList<String> nodeNames = new ArrayList<String>();
		for (int i = 0; i < alnode.size(); ++i){
			nodeNames.add(alnode.get(i).getNom());
		}
		Collections.sort(nodeNames);
		return (String[]) nodeNames.toArray().clone();
	}
	
	/**
	 * Add all the strings that match the prefix typed in the JComboBox to the filtered arraylist
	 * Calls a binary search to find the first element that matches (<strong>start</strong>)and scans lineally until the string doesn't match
	 * 
	 * 
	 * @see #findStart(ComboBoxModel, String, int, int)
	 */
	private void updateSubstrings(){
		System.out.println(rawStrings[inCase].getSize());
		matchStart = findStart(rawStrings[inCase], getEditor().getItem().toString(), 1, rawStrings[inCase].getSize()-1);
		if (matchStart >= 0 && matchStart < rawStrings[inCase].getSize() && filteredStrings != null){
			/*System.out.println("A 2");
			for (int i = 0, c = filteredStrings.getSize(); i < c; ++i){
				filteredStrings.removeElementAt(i);
			}
			System.out.println("A 3");
			while (rawStrings[inCase].getElementAt(start).startsWith(getSelectedItem().toString())){
				filteredStrings.addElement(rawStrings[inCase].getElementAt(start));
				++start;
			}*/
			setSelectedIndex(matchStart); //Update JComboBox visually
			getEditor().setItem(getSelectedItem()); //Update text in the JComboBox
		}
		else if (matchStart == 10) System.out.println("\n\n Contact fox \n");
	}
	/**
	 * Recursive call to find the first element that starts with the string <strong>text</strong>
	 * 
	 * @param nodeNames List of all the available strings
	 * @param text The prefix we are looking for
	 * @param begin Lower bound for the search
	 * @param end Upper bound for the search
	 * @return The first element which starts with the substring <strong>text</strong>
	 */
	private Integer findStart(ComboBoxModel<String> nodeNames, String text, int begin, int end){
		if (begin == end || begin +1 == end){
			if (nodeNames.getElementAt(begin).startsWith(text)) {System.out.println(begin);return begin;}
			else if (nodeNames.getElementAt(end).startsWith(text)) {System.out.println(end);return end;}
			else return -1;
		}
		else {
			System.out.println(nodeNames.getElementAt(begin));
			System.out.println(nodeNames.getElementAt(end));
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
		if (e.getSource().equals(parentBox)){
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
				//System.out.println(getSelectedItem().toString());
				updateSubstrings();
				//setModel(filteredStrings);
				//if (filteredStrings != null) setModel(filteredStrings);
				//else System.out.println("\n\nContact fox\n");
			}
			else {
				setModel(rawStrings[inCase]);
			}
		}
	}

}
