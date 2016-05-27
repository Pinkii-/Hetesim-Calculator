package Presentacion;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import Dominio.Result;
import Tests.CtrlDataTest;
import Tests.CtrlDominioTest;
import Dominio.CtrlResults;
import Dominio.Graph;
import Dominio.Node;
import Dominio.Pair;
import Dominio.Path;
import Presentacion.FormattedResultsManager.FormattedResult;
import Presentacion.VistaPrincipal.Panels;

public class PanelLoadResult extends AbstractPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VistaPrincipal vp;
	private CtrlResults cr;
	private JSplitPane splitpane;
	private JPanel searchAndActionsPanel;
	private JPanel resultsPanel;
	private JPanel searchPanel;
	private JPanel actionsPanel;
	private MyResultsList loadedResults;
	private JTextArea resultResume;
	private JScrollPane scrollPane;
	private DefaultListModel DLM;
	private ListSelectionModel LSM;
	private JButton show;
	private JButton delete;
	private FormattedResultsManager frm;
	private HashMap<String,Integer> entries;
	ArrayList<FormattedResult> formattedResults;
	
	public PanelLoadResult (VistaPrincipal vp)   {
		super(vp);
		initComponents();
		this.vp = vp;
	}
	
	
	private Result generateResult(int j)  {
		Graph g = new Graph();
		ArrayList<Pair<Integer,Float>> m = new ArrayList<>();
		for (int i = 0; i < 10; ++i){
			m.add(new Pair<Integer,Float>(i,i*0.1f));
		}
		Path p = new Path();
		p.setContingut("AA");
		p.setNom("AA"+Integer.toString(j));
		p.setDescripcio("AA");
		Node n1 = new Node();
			n1.initialize(Node.Type.Autor, 25, "NodeOrigin");
		Float f = 0.1f;
		Result rs;
		rs = new Result(g,f,f,p,n1,n1);
		return rs;
	}
	
	private void generateResults() {
		for (int i = 0; i < 5; i++) {
			Result r = generateResult(i);
			System.out.println(r.getIdResult());
			cr.addResult(r.getIdResult(),r);
		}
		
	}
	
	
	
	private void generateResultsPanel(){
		resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.PAGE_AXIS));
		generateResults();
		formattedResults = frm.getFormattedResults();
		
		for(int i = 0; i < formattedResults.size(); ++i)
			DLM.addElement(formattedResults.get(i));
			
		loadedResults.setModel(DLM);
		scrollPane = new JScrollPane(loadedResults);
		resultsPanel.add(scrollPane);		
	}
	
	

	private void generateSearchPanel() {
		
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.PAGE_AXIS));
		searchPanel.setAlignmentX(RIGHT_ALIGNMENT);
		searchPanel.add(Box.createHorizontalGlue());
		JLabel lab = new JLabel("FILTER");
		JLabel lab2 = new JLabel("FILTER");
		JLabel lab3 = new JLabel("FILTER");
		searchPanel.add(lab);
		searchPanel.add(lab2);
		searchPanel.add(lab3);
		
	}
	
	private void generateActionPanel() {
		actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.PAGE_AXIS));
		actionsPanel.add(resultResume);
		//show.setAlignmentY(RIGHT_ALIGNMENT);
		//actionsPanel.setAlignmentX(BOTTOM_ALIGNMENT);
		actionsPanel.add(Box.createHorizontalGlue());
		actionsPanel.add(show);
	}
	
	private void generateSearchAndActionsPanel() {
		searchAndActionsPanel.setLayout(new BoxLayout(searchAndActionsPanel, BoxLayout.PAGE_AXIS));
		generateSearchPanel();
		searchAndActionsPanel.add(searchPanel);
		searchAndActionsPanel.add(Box.createVerticalGlue());
		generateActionPanel();
		searchAndActionsPanel.add(actionsPanel);

	}
	
	private void initSubPanels() {
		generateResultsPanel();
		generateSearchAndActionsPanel();
		splitpane.setAlignmentX(LEFT_ALIGNMENT);
		splitpane.setLeftComponent(resultsPanel);
		splitpane.setRightComponent(searchAndActionsPanel);
		splitpane.resetToPreferredSizes();
		add(splitpane);
		
	}
	
	private void assignListeners() {
		show.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (loadedResults.indexSelected()) {
					int index = loadedResults.getSelectedIndex();
					vp.panelMostrarResultado.setShowedResult(formattedResults.get(index).getFormattedResult());
					vp.changePanel(Panels.PanelMostrarResultado);
					System.out.println("hola");
				}
				else { System.out.println("Selecciona un resultado");
					/*generar cosa o habilitar al seleccionar*/}
			}
		});
		
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (loadedResults.indexSelected()) {
					int index = loadedResults.getSelectedIndex();
					frm.addToDelete(index);
					loadedResults.remove(index);
				}
				else { System.out.println("Selecciona un resultado");
					/*generar cosa o habilitar al seleccionar*/}
			}
		});
		
	}
	private void initComponents(){
		splitpane = new JSplitPane();
		resultsPanel = new JPanel();
		actionsPanel = new JPanel();
		resultResume = new JTextArea("lel");
		loadedResults = new MyResultsList(resultResume);
		searchPanel = new JPanel();
		searchAndActionsPanel = new JPanel();
		DLM = new DefaultListModel();
		show = new JButton("Show");
		delete = new JButton("Delete");
		cr = cd.getCtrlResults();
		frm = new FormattedResultsManager(cd);
		formattedResults = new ArrayList<FormattedResult>();
		
		BoxLayout bl = new BoxLayout(this,BoxLayout.LINE_AXIS);
		setLayout(bl);
		initSubPanels();
		assignListeners();
		
	}
	private void deleteResults() {
		frm.commitDeletions();
	}
	private void saveChanges() {
		deleteResults();
	}
	
	@Override
	public int closeIt() {
		saveChanges();
		String[] buttons = {"Salir", "Cancelar"};
		int result = VistaDialog.setDialog("Titulo", "¿Estas seguro que quieres salir?\n (No se va a perder nada, no has hecho nada, vete.)", buttons, VistaDialog.DialogType.QUESTION_MESSAGE);
//		if (result == 0) vp.continueAction();
		return result;
	}
	@Override
	public void setEnabledEverything(Boolean b) {
		// TODO Auto-generated method stub
		
	}

}
