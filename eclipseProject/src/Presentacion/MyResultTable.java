package Presentacion;

import java.util.ArrayList;

import javax.swing.JTable;
/**
 * @author Albert Lopez Alcacer
**/

public class MyResultTable {
	
	private ArrayList<ArrayList<String>> result;
	private JTable table;
	private String[] columnNames = {"Entidad A",
            "Tipo",
            "Entidad B",
            "Tipo",
            "Value",
    };

	private Object[][] data;
	
	private void generateTable() {
		/*	1) 0) First Node's name 1) First Node's type 2) Second Node's name 3) Second Node's type
		 * 1) 4) HeteSim value*/
		data = new Object[result.size()-1][5];
		
		for (int i = 1; i < result.size(); ++i) {
			for (int j = 0; j < 4; ++j) {
				data[i][j] = result.get(i).get(j);
			}
		}
		
	}
	
	public MyResultTable (ArrayList<ArrayList<String>> result) {
		this.result = result;
		generateTable();
		table = new JTable(data, columnNames);
		table.setFillsViewportHeight(true);
		table.setEnabled(false);
	}
	public JTable getTable() {
		return table;
	}
	
}
