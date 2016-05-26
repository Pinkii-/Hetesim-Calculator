package Presentacion;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
/**
 * @author Albert Lopez Alcacer
**/

public class MyResultTable implements TableModelListener {
	
	private ArrayList<ArrayList<String>> result;
	private JTable table;
	private MyTableModel mtm;
	private String[] columnNames = {"Entidad A",
            "Tipo",
            "Entidad B",
            "Tipo",
            "Value",
    };

	private Object[][] data;
	
	public MyResultTable (ArrayList<ArrayList<String>> result) {
		this.result = result;
		generateData();
		generateTable();
	}
	
	private void generateData() {
		/*	1) 0) First Node's name 1) First Node's type 2) Second Node's name 3) Second Node's type
		 * 1) 4) HeteSim value*/
		
		data = new Object[result.size()][5];
		
		for (int i = 1; i < result.size(); ++i) {
			for (int j = 0; j < result.get(1).size(); ++j) {
				if (j == result.get(1).size()-1) data[i][j] = new Float(Float.parseFloat(result.get(i).get(j)));
				else data[i][j] = result.get(i).get(j);
			}
		}
		
	}
	
	private void generateTable() {
		mtm = new MyTableModel(columnNames,data);
		table = new JTable(mtm);
		table.getModel().addTableModelListener(this);
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
	}

	public JTable getTable() {
		return table;
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
        //Guardar datos modificados.
        System.out.println("Change");
		
	}
	
}
