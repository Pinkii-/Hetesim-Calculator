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
import javax.swing.JTable;
import javax.swing.JTextArea;
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
	private JTable table;
	private JSplitPane splitpane;
	private Result rs;
	private JButton editar;
	private JButton second;
	
	public PanelMostrarResultado (VistaPrincipal v)  {
		super(v);		
		initComponents();
		try {
			//generateResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//assingListeners();
	}
	private void asignListeners() {
		editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				table.setEnabled(true);
				System.out.println("hola");
			}
		});
	}
	private void generateResult() throws Exception {
		Graph g = new Graph();
		ArrayList<Pair<Integer,Float>> m = new ArrayList<>();
		for (int i = 0; i < 10; ++i){
			m.add(new Pair<Integer,Float>(i,i*0.1f));
		}
		Path p = new Path();
		Node n1 = new Node();
			n1.initialize(Node.Type.Autor, 25, "NodeOrigin");
		Float f = 0.1f;
		rs = new Result(g,f,m,p,n1);
	}
	private void setMyResultTable(ArrayList<ArrayList<String>> res) {
		//CtrlResults cr = cd.getCtrlResults();
		rst = new MyResultTable(res);
	}
	private void generateTable() {
		/*Float d = 0.1f;
		cd.importGraph("/home/albert/prop/PROP/GraphForTesting");
		String res = cd.searchPathNodeNodeThreshhold(d, "APC", (Integer)1, (Integer)1);
		//System.out.println(res);*/
		/*String[] columnNames = {"Entidad A",
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
			};*/
		/*table = new JTable(data, columnNames);
		table.setFillsViewportHeight(true);
		table.setEnabled(false);*/
		splitpane.setLeftComponent(new JScrollPane(table));
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
	
	private void editAndSave() {
		/*Mirar si resultado esta modificado (por ser nueva busqueda o por editar)
		 * Si esta modificado, hay que guardarlo para poder editar
		 * Boton editar -> set edited y activa panel;
		 * Boton editar pasa a ser guardar -> se guarda en disco (CtrlResult)
		 */
		JButton edit = new JButton("Editar");
		actions.add(edit);
		
	}
	private void initComponents() {
		
		infoAndActions = new JPanel();
		actions = new JPanel();
		info = new JPanel();
		splitpane = new JSplitPane();
		editar = new JButton("lmao");
		second = new JButton("ayy");
		
		BoxLayout bl = new BoxLayout(this,BoxLayout.LINE_AXIS);
		setLayout(bl);
		editAndSave();
		initSubPanels();
		asignListeners();
		//CtrlResults cr = cd.getCtrlResults();
		//String res = cd.searchPathNodeNode("APA", 0, 3);
		//cr.addLastResult();
		
	}
	
	@Override
	public int closeIt() {
		// TODO Auto-generated method stub
		String[] buttons = {"Salir", "Cancelar"};
		int result = VistaDialog.setDialog("Titulo", "¿Estas seguro que quieres salir?\n ", buttons, VistaDialog.DialogType.QUESTION_MESSAGE);
		if (result == 0) vp.continueAction();
		return result;
	}
	@Override
	public void setEnabledEverything(Boolean b) {
		// TODO Auto-generated method stub
		
	}

}
