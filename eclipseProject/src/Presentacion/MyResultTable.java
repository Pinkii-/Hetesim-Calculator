package Presentacion;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import Dominio.CtrlResults;
import Presentacion.FormattedResult;
/**
 * @author Albert Lopez Alcacer
**/

public class MyResultTable extends JTable implements ListSelectionListener  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FormattedResult result;
	private MyTableModel mtm;
	private ListSelectionModel cellSelectionModel;
	private CtrlResults cr;
	private HashMap<Integer,Float> oldValues;
	private HashMap<Integer,Float> newValues;
	
	public MyResultTable() {
	}
	
	public MyResultTable (ArrayList<ArrayList<String>> result, CtrlResults cr) {
		this.cr = cr;
		this.result = new FormattedResult(result);
		oldValues = new HashMap<Integer,Float>();
		newValues = new HashMap<Integer,Float>();
		cellSelectionModel = getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setFillsViewportHeight(true);
		setEnabled(false);
		setCellSelectionEnabled(true);
		generateTable();
	}
	
	private void generateTable() {
		mtm = new MyTableModel(result,newValues);
		setModel(mtm);
		
	}
	
	
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
	        Float selectedData = null;
	
	        int[] selectedRow = getSelectedRows();
	        int[] selectedColumns = getSelectedColumns();
	
	        for (int i = 0; i < selectedRow.length; i++) {
	          for (int j = 0; j < selectedColumns.length; j++) {
	            selectedData =  (Float) getValueAt(selectedRow[i], selectedColumns[j]);
	          }
	        }
	        
	        oldValues.put(selectedRow[0], selectedData);
	        System.out.println("Selected: " + selectedData);
	        System.out.println("OldValues:");
	        for (Float s: oldValues.values()) {
	        	System.out.println(Float.toString(s));
	        }
	        System.out.println("newValues:");
	        for (Float s: newValues.values()) {
	        	System.out.println(Float.toString(s));
	        }
	        
	    }
	}
	
}
