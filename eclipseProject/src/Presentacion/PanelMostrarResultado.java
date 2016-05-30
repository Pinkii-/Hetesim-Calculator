package Presentacion;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import Dominio.CtrlResults;
import Dominio.Graph;
import Dominio.Node;
import Dominio.Pair;
import Dominio.Path;
import Dominio.Result;

public class PanelMostrarResultado extends AbstractPanel{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private MyResultTable rst;
	private CtrlResults cr;
	private JPanel infoAndActions;
	private JPanel actions;
	private JPanel info;
	private JSplitPane splitpane;
	private JButton editar;
	private JButton guardar;
	private JButton cancelar;
	private DefaultListModel<String> dlm;
	private JList<String> changes;
	private JScrollPane scrollChange;
	private ArrayList<ArrayList<String>> showedResult;
	
	public PanelMostrarResultado (VistaPrincipal v)  {
		super(v);
		this.cr = cd.getCtrlResults();
	}
	
	private void asignListeners() {
		editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//rst.repaint();
				rst.setEnabled(true);
				rst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				editar.setEnabled(false);
			}
		});
		guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				rst.setEnabled(false);
				editar.setEnabled(true);
				
			}
		});
	}

	
	public void init() {
		removeAll();
		initComponents();
	}
	
	public void setShowedResult(ArrayList<ArrayList<String>> res) {
		this.showedResult = res;
	}
	
	
	private void generateTable()  {
		
		rst = new MyResultTable(showedResult,cr,changes);
		rst.setFillsViewportHeight(true);
		rst.setEnabled(false);
		splitpane.setLeftComponent(new JScrollPane(rst));
		
	}
	private void generateInfoPanel() {
		
		info.add(scrollChange);
		//changes.setAlignmentY(TOP_ALIGNMENT);
		//changes.setAlignmentX(RIGHT_ALIGNMENT);
		//changes.setMinimumSize(new Dimension(250, 50));
		info.add(Box.createHorizontalGlue());
		info.setLayout(new BoxLayout(info,BoxLayout.PAGE_AXIS));
	}
	private void generateActionPanel() {
		actions.setLayout(new BoxLayout(actions,BoxLayout.LINE_AXIS));
		actions.setAlignmentX(LEFT_ALIGNMENT);
		actions.add(Box.createHorizontalGlue());
		actions.add(editar);
		actions.add(guardar);
		actions.add(cancelar);
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
		splitpane.setDividerLocation(540);
	}
	private void initSubPanels() {
		generateTable();
		generateInfoAndActionPanel();
		
	}
	
	private void editAndSave() {
		/*Mirar si resultado esta modificado (por ser nueva busqueda o por editar)
		 * Si esta modificado, hay que guardarlo para poder editar
		 * Boton editar -> set edited y activa panel;
		 * Boton editar pasa a ser guardar -> se guarda en disco (CtrlResult)
		 */
		
	}
	private void initComponents() {
		
		infoAndActions = new JPanel();
		actions = new JPanel();
		info = new JPanel();
		splitpane = new JSplitPane();
		editar = new JButton("Edit");
		guardar = new JButton("Save");
		cancelar = new JButton("Cancel");
		rst = new MyResultTable();
		dlm = new DefaultListModel<String>();
		changes = new JList<String>(dlm);
		scrollChange = new JScrollPane(changes);
		
		BoxLayout bl = new BoxLayout(this,BoxLayout.LINE_AXIS);
		setLayout(bl);
		editAndSave();
		initSubPanels();
		asignListeners();
		//CtrlResults cr = cd.getCtrlResults();
		//String res = cd.searchPathNodeNode("APA", 0, 3);
		//cr.addLastResult();
		
	}
	
	private void saveChanges() {
	
	}
	
	@Override
	public int closeIt() {
		saveChanges();
		String[] buttons = {"Salir", "Cancelar"};
		int result = VistaDialog.setDialog("Titulo", "¿Estas seguro que quieres salir?\n ", buttons, VistaDialog.DialogType.QUESTION_MESSAGE);
		return result;
	}
	@Override
	public void setEnabledEverything(Boolean b) {
		// TODO Auto-generated method stub
		
	}

}
