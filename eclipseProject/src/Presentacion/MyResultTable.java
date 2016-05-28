package Presentacion;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import Presentacion.FormattedResultsManager.FormattedResult;
/**
 * @author Albert Lopez Alcacer
**/

public class MyResultTable extends JTable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FormattedResult result;
	private MyTableModel mtm;
	
	public MyResultTable() {
	}
	
	public MyResultTable (ArrayList<ArrayList<String>> result) {
		this.result = new FormattedResult(result);
		generateTable();
	}
	
	private void generateTable() {
		mtm = new MyTableModel(result);
		mtm.addTableModelListener(this);
		setModel(mtm);
		setFillsViewportHeight(true);
		setEnabled(false);
	}

	
}
