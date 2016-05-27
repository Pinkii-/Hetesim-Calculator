package Presentacion;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
/**
 * @author Albert Lopez Alcacer
**/

public class MyResultTable extends JTable {
	
	private ArrayList<ArrayList<String>> result;
	private MyTableModel mtm;
	private String[] columnNames;
	private Object[][] data;
	
	public MyResultTable() {}
	
	public MyResultTable (ArrayList<ArrayList<String>> result) {
		this.result = result;
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
