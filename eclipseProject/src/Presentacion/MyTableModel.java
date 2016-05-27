package Presentacion;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

class MyTableModel extends AbstractTableModel implements TableModelListener{
    /**
	 * @author Albert Lopez Alcacer
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames;
    private Object[][] data;
    private Class[] columns = new Class[]{String.class, String.class, String.class, String.class, Float.class};

    
    public MyTableModel(String[] columnName, Object[][] data) {
    	this.columnNames = columnName;
    	this.data = data;
    	addTableModelListener(this);
    	
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
    /*
     * data change
     */
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