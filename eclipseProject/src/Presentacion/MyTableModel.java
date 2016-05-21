package Presentacion;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

class MyTableModel extends AbstractTableModel{
    /**
	 * @author Albert Lopez Alcacer
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames;
    private Object[][] data;
    
    public MyTableModel(String[] columnName, Object[][] data) {
    	this.columnNames = columnName;
    	this.data = data;
    	
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

    /*
     * data change
     */
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }

	

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

}