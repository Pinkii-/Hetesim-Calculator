package Presentacion;


import java.util.HashMap;

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
	private JTextArea changes;
    private Object[][] data;
    private HashMap<Integer,Float> newValues;
    private CtrlResults cr;
  

    public MyTableModel(FormattedResult result, HashMap<Integer,Float> newValues) {
    	this.result = result;
    	this.newValues = newValues;
    	data = result.getResultData();
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
   
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
    private String obtainChange() {
    	return "";
    }
    
	@Override
	public void tableChanged(TableModelEvent e) {
		Integer row = e.getFirstRow();
        Integer column = e.getColumn();
        
        System.out.println(Integer.toString(row)+" "+Integer.toString(column));
        TableModel model = (TableModel)e.getSource();
        
        Float data = (Float) model.getValueAt(row, column);
        newValues.put(row, data);
        System.out.println("Change");
		
	}

}