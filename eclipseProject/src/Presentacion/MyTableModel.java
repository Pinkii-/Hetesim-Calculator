package Presentacion;

import java.util.ArrayList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

class MyTableModel extends AbstractTableModel implements TableModelListener{
    /**
	 * @author Albert Lopez Alcacer
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames = {"Entidad A",
            "Tipo",
            "Entidad B",
            "Tipo",
            "Value",
    };
	private Class[] columns = new Class[]{String.class, String.class, String.class, String.class, Float.class};
	private ArrayList<ArrayList<String>> result;
    private Object[][] data;
  

    public MyTableModel(ArrayList<ArrayList<String>> result) {
    	this.result = result;
    	generateData();
    	addTableModelListener(this);
    	
    }
    
    private void generateData() {
		
		data = new Object[result.size()-1][5];
		
		for (int i = 1; i < result.size(); ++i) {
			for (int j = 0; j < result.get(1).size(); ++j) {
				if (j == result.get(1).size()-1) data[i-1][j] = new Float(Float.parseFloat(result.get(i).get(j)));
				else data[i-1][j] = result.get(i).get(j);
			}
		}
		
	}

    
    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public boolean isCellEditable(int row, int col) {
    	
    	return col == 4; //Only HeteSim value
    }
    
    /*Only correct data is accepted
     */
    
    @Override
    public Class getColumnClass(int column) {
		return columns[column];
    	
    }
   
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }

	@Override
	public void tableChanged(TableModelEvent e) {
			System.out.println(e.getSource());
			Integer row = e.getFirstRow();
	        Integer column = e.getColumn();
	        TableModel model = (TableModel)e.getSource();
	        String columnName = model.getColumnName(column);
	        Object data = model.getValueAt(row, column);
	        if (column == null) System.out.println("column nulo");
	        //Guardar datos modificados.
	        System.out.println("Change");
		
	}

}