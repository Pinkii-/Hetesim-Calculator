package Presentacion;


import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import Dominio.CtrlResults;
import Presentacion.FormattedResult;

/**
 * @author Albert Lopez Alcacer
 */

class MyTableModel extends AbstractTableModel implements TableModelListener{
    
	private static final long serialVersionUID = 1L;
	private String[] columnNames = {"Entidad A",
            "Tipo",
            "Entidad B",
            "Tipo",
            "Value",
    };
	private Class[] columns = new Class[]{String.class, String.class, String.class, String.class, Float.class};
	private FormattedResult result;
    private Object[][] data;
    private DefaultListModel<String> dlm;
    private JList<String> changes;
    private CtrlResults cr;
    Integer nChange = 0;
  

    public MyTableModel(FormattedResult result, JList<String> changes) {
    	this.result = result;
    	this.changes = changes;
    	data = result.getResultData();
    	dlm = (DefaultListModel<String>) changes.getModel();
    	dlm.setSize(result.getNumberOfValues());
    	addTableModelListener(this);
    	
    }
    
    public MyTableModel(FormattedResult result) {
    	this.result = result;
    	data = result.getResultData();
    	
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
    	
    	return col == 4; 
    	
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
    
	
	public void clearListChanges() {
		changes.removeAll();
	}
	
	@Override
	public void tableChanged(TableModelEvent e) {
		Integer row = e.getFirstRow();
        Integer column = e.getColumn();
        System.out.println(Integer.toString(row)+" "+Integer.toString(column));
        TableModel model = (TableModel)e.getSource();
        Float data = (Float) model.getValueAt(row, column);
       
        nChange = row;
        
        result.setNewValue(row, data);
    	Float oldValue = result.getOldValue(row);
    	Float newValue = result.getNewValue(row);
    	String ch = Integer.toString(nChange) +") [Old value: "+ oldValue +", New value: "+ newValue +"]";
    	
    	dlm.set(row, ch);
    	
        System.out.println("Change");
		
	}

}