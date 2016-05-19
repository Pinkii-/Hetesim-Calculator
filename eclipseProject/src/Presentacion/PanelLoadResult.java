package Presentacion;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import Dominio.CtrlDataTest;
import Dominio.Result;

public class PanelLoadResult extends AbstractPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel infoAndActions = new JPanel();
	private JPanel actions = new JPanel();
	private JPanel info = new JPanel();
	private JTable table;
	private JSplitPane splitpane = new JSplitPane();
	private Result rs;
	private JButton first = new JButton("lmao");
	private JButton second = new JButton("ayy");
	
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
	private void generateInfoPanel() {
		JLabel label = new JLabel("INFO	");
		JLabel origen = new JLabel("Origen: ");
		JLabel destino = new JLabel("Destino: ");
		JLabel path = new JLabel("Path usado: ");
		JLabel threshold = new JLabel("Threshold usado: ");
		JLabel modificado = new JLabel("Resultado modificado: ");
		JCheckBox cb = new JCheckBox();
		cb.setSelected(true);
		cb.setOpaque(true);
		info.add(Box.createHorizontalGlue());
		info.setLayout(new BoxLayout(info,BoxLayout.PAGE_AXIS));
		info.setAlignmentX(RIGHT_ALIGNMENT);
		info.add(label);
		info.add(Box.createRigidArea(new Dimension(0,50)));
		info.add(origen);
		info.add(destino);
		info.add(path);
		info.add(threshold);
		info.add(cb);
	}
	private void generateActionPanel() {
		actions.setLayout(new BoxLayout(actions,BoxLayout.LINE_AXIS));
		actions.setAlignmentX(LEFT_ALIGNMENT);
		actions.add(Box.createHorizontalGlue());
		actions.add(first);
		actions.add(second);
	}
	private void generateInfoAndActionPanel() {
		
		infoAndActions.setLayout(new BoxLayout(infoAndActions,BoxLayout.PAGE_AXIS));
		
		generateInfoPanel();
		infoAndActions.add(info);
		
		JSeparator seperator = new JSeparator(SwingConstants.HORIZONTAL);
		seperator.setMaximumSize( new Dimension(Integer.MAX_VALUE, 1) );
		infoAndActions.add(seperator);
		
		infoAndActions.add(Box.createVerticalGlue());
		generateActionPanel();
		infoAndActions.add(actions);
		
		splitpane.setRightComponent(infoAndActions);
		splitpane.resetToPreferredSizes();
		add(splitpane);
	}
	private void initSubPanels() {
		generateTable();
		generateInfoAndActionPanel();
		
	}
	private void initComponents() {
		BoxLayout bl = new BoxLayout(this,BoxLayout.LINE_AXIS);
		setLayout(bl);
		initSubPanels();
		
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
