package Presentacion;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
	private JList changes;
	
	public MyResultTable() {
		
	}
	
	public MyResultTable (ArrayList<ArrayList<String>> result, CtrlResults cr) {
		this.cr = cr;
		this.result = new FormattedResult(result,cr);
		cellSelectionModel = getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mtm = new MyTableModel(this.result);
		setModel(mtm);
		setFillsViewportHeight(true);
		setEnabled(false);
		setCellSelectionEnabled(false);
	}
		
	
	public MyResultTable (ArrayList<ArrayList<String>> result, CtrlResults cr, JList<String> changes) {
		this.cr = cr;
		this.result = new FormattedResult(result,cr);
		this.changes = changes;
		cellSelectionModel = getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mtm = new MyTableModel(this.result,changes);
		setModel(mtm);
		setFillsViewportHeight(true);
		setEnabled(false);
		setCellSelectionEnabled(true);
	}
	
	
	
	public void valueChanged(ListSelectionEvent e) {
        Float selectedData = null;

        int[] selectedRow = getSelectedRows();
        int[] selectedColumns = getSelectedColumns();
        if (selectedRow.length > 0 && selectedColumns.length >0) {
	        if (selectedColumns[0] == 4) {
		        for (int i = 0; i < selectedRow.length; i++) {
		          for (int j = 0; j < selectedColumns.length; j++) {
		            selectedData =  (Float) getValueAt(selectedRow[i], selectedColumns[j]);
		          }
		        }
		        
		        result.setOldValue(selectedRow[0], selectedData);
		        this.repaint();
		    
	        }
        }
	}

	public void saveChanges() {
		result.commitChanges();
	}
	
	public void clearChanges() {
		result.clearChanges();
		mtm.clearListChanges();
		
		
	}
}
