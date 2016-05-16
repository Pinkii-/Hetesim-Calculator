package Presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

/**
 * 
 * @author Xavier Peñalosa
 *
 */
public class ComboBoxListener implements ActionListener{

	private JComboBox<String> myBox = new JComboBox<String>();
	private ArrayList<String> al = new ArrayList<String>();
	private ArrayList<String> filteredAl = new ArrayList<String>();
	//private ComboBoxModel<String>[] filteredStrings = new ComboBoxModel[0];
	private Integer characters = 0;
	
	public ComboBoxListener(JComboBox box){
		myBox = box;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (myBox.getSelectedItem().getClass() == String.class && myBox.getSelectedItem().toString().length() >= 3){
			updateSubstrings();
			//myBox.setModel(filteredAl);
		}
	}

	/**
	 * Add all the strings that match the prefix typed in the JComboBox to the filtered arraylist
	 * Calls a binary search to find the first element that matches and scans lineally until the string doesn't match
	 * 
	 * @see #findStart(ArrayList, String, int, int)
	 */
	private void updateSubstrings(){
		int start = findStart(al,myBox.getSelectedItem().toString(), 0, al.size());
		filteredAl.clear();
		if (start >= 0 && start < al.size()){
			while (al.get(start).startsWith(myBox.getSelectedItem().toString())){
				filteredAl.add(al.get(start));
				++start;
			}
		}
	}
	
	/**
	 * Recursive call to find the first element that starts with the string <strong>text</strong>
	 * 
	 * @param al The arraylist to scan
	 * @param text The prefix we are looking for
	 * @param begin Lower bound for the search
	 * @param end Upper bound for the search
	 * @return The first element which starts with the substring <strong>text</strong>
	 */
	private Integer findStart(ArrayList<String> al, String text, int begin, int end){
		if (begin == end) return begin;
		else {
			int middle = (begin + end)/2;
			if (al.get(middle).compareToIgnoreCase(text) < 0){
				return findStart(al,text,middle,end);
			}
			else{
				return findStart(al,text,begin,end);
			}
		}
	}

}
