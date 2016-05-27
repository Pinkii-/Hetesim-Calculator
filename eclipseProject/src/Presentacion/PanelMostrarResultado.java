package Presentacion;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
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
	 * Cosas:
	 * Guardar resultado antes de modificar.
	 * Mostrar si el resultado esta guardado. (ya sea por busqueda recien realizada, o por que ha sido editado)
	 * Set editable solo si esta guardado en memoria.
	 */
	private static final long serialVersionUID = 1L;
	private MyResultTable rst;
	private JPanel infoAndActions;
	private JPanel actions;
	private JPanel info;
	private JSplitPane splitpane;
	private Result rs;
	private JButton editar;
	private JButton guardar;
	private PanelLoadResult plr;
	private ArrayList<ArrayList<String>> showedResult;
	
	public PanelMostrarResultado (VistaPrincipal v)  {
		
		super(v);
	}
	
	private void asignListeners() {
		editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				rst.setEnabled(true);
				rst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				System.out.println("hola");
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
	
	private void setMyResultTable(ArrayList<ArrayList<String>> res) {
		rst = new MyResultTable(res);
	}
	private void generateTable()  {
		setMyResultTable(showedResult);
		rst.setFillsViewportHeight(true);
		rst.setEnabled(false);
		splitpane.setLeftComponent(new JScrollPane(rst));
	}
	private void generateInfoPanel() {
		JLabel label = new JLabel("INFO	");
		JLabel origen = new JLabel("Origen: ");
		JLabel destino = new JLabel("Destino: ");
		JLabel path = new JLabel("Path usado: ");
		JLabel threshold = new JLabel("Threshold usado: ");
		JLabel modificado = new JLabel("Resultado modificado: ");
		JTextArea text = new JTextArea("text");
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
		actions.add(editar);
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
		editar = new JButton("Editar");
		rst = new MyResultTable();
		
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
