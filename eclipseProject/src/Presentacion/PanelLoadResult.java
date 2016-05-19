package Presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import Dominio.CtrlDataTest;
import Dominio.Result;

public class PanelLoadResult extends AbstractPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel subpanel = new JPanel();
	JPanel panel = new JPanel();
	
	JTable table;
	JSplitPane splitpane = new JSplitPane();
	JPanel infoAndActions = new JPanel();
	JLabel label = new JLabel("INFO	");
	
	JButton first = new JButton("lmao");
	JButton second = new JButton("ayy");
	
	Result rs;
	
	public PanelLoadResult (VistaPrincipal vp)  {
		super(vp);
		initComponents();
		try {
			//generateResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//assingListeners();
	}
	
	private void generateResult() throws Exception {
		rs = CtrlDataTest.enterDataResult();
	}
	
	private void generateTable() {
		String[] columnNames = {"Entidad A",
                "Tipo",
                "Entidad B",
                "Tipo",
                "Value",
        };
		Object[][] data = {
			    {"null", "null",
			     "null", "null", new Float(0.5)},
			    {"null", "null",
				 "null", "null", new Float(0.5)},
			    {"null", "null",
				 "null", "null", new Float(0.5)},
			    {"null", "null",
				 "null", "null", new Float(0.5)},
			    {"null", "null",
				 "null", "null", new Float(0.5)},
			};
		table = new JTable(data, columnNames);
		table.setFillsViewportHeight(true);
		splitpane.setLeftComponent(new JScrollPane(table));
	}
	
	private void initSubPanels(BoxLayout sp) {
		generateTable();
		infoAndActions.setLayout(new BoxLayout(infoAndActions,BoxLayout.PAGE_AXIS));
		infoAndActions.add(label);
		infoAndActions.add(Box.createVerticalGlue());
		infoAndActions.add(first);
		infoAndActions.add(second);
		splitpane.setRightComponent(infoAndActions);
		splitpane.resetToPreferredSizes();
		add(splitpane);
	}
	private void initComponents() {
		BoxLayout bl = new BoxLayout(this,BoxLayout.LINE_AXIS);
		setLayout(bl);
		setLayout(bl);
		initSubPanels(bl);
		
	}
	
	@Override
	public int closeIt() {
		// TODO Auto-generated method stub
		String[] buttons = {"Salir", "Cancelar"};
		int result = VistaDialog.setDialog("Titulo", "¿Estas seguro que quieres salir?\n (No se va a perder nada, no has hecho nada, vete.)", buttons, VistaDialog.DialogType.QUESTION_MESSAGE);
		if (result == 0) vp.continueAction();
		return result;
	}
	@Override
	public void setEnabledEverything(Boolean b) {
		// TODO Auto-generated method stub
		
	}

}
