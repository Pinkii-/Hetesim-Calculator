package Presentacion;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

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
import Presentacion.VistaPrincipal.Panels;

public class PanelLoadResult extends AbstractPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private JButton mostrar;
	private VistaPrincipal vp;
	private CtrlResults cr;
	private ArrayList<ArrayList<String>> formattedSelectedResult;
	ArrayList<String> idResults= new ArrayList<String>();
	
	public PanelLoadResult (VistaPrincipal vp)   {
		
		super(vp);
		try {
		initComponents();
		
			//generateResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.vp = vp;
	}
	
	public ArrayList<ArrayList<String>> getFormattedSelectedResult() {
		return formattedSelectedResult;
	}
	
	private Result generateResult(int j)  {
		ArrayList<String> idresults = new ArrayList<String>();
		idresults = cr.getAllResultIds();
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
			idResults.add(r.getIdResult());
			cr.addResult(r.getIdResult(),r);
		}
		
	}
	
	
	
	private void generateResultsPanel(){
		generateResults();
		
		resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.PAGE_AXIS));
		for(int i = 0; i < idResults.size(); ++i) {
			DLM.addElement(cr.getFormatted(idResults.get(i)));
		}
		loadedResults.setModel(DLM);
		scrollPane = new JScrollPane(loadedResults);
		resultsPanel.add(scrollPane);
		
        //Add the scroll pane to this panel.
		
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
		//mostrar.setAlignmentY(RIGHT_ALIGNMENT);
		//actionsPanel.setAlignmentX(BOTTOM_ALIGNMENT);
		actionsPanel.add(Box.createHorizontalGlue());
		actionsPanel.add(mostrar);
	}
	
	private void generateSearchAndActionsPanel() {
		searchAndActionsPanel.setLayout(new BoxLayout(searchAndActionsPanel, BoxLayout.PAGE_AXIS));
		generateSearchPanel();
		searchAndActionsPanel.add(searchPanel);
		searchAndActionsPanel.add(Box.createVerticalGlue());
		generateActionPanel();
		searchAndActionsPanel.add(actionsPanel);
		
		/*
		 * Use only for entertaining purpose
		 * 
		 * URL url = null;
		try {
			url = new URL("https://media.giphy.com/media/XreQmk7ETCak0/giphy.gif");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Icon icon = new ImageIcon(url);
	    JLabel label = new JLabel(icon);
	    actionsPanel.add(label);*/
	  

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
		mostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (loadedResults.indexSelected()) {
					int index = loadedResults.getSelectedIndex();
					String idResultSelected = idResults.get(index);
					//Hay que pasar el result formatted a atributo y hacer un getter para lanzarlo desde initcomponents de panelMostrarResultado
					formattedSelectedResult = cr.getFormatted(idResultSelected);
					vp.panelMostrarResultado.setShowedResult(formattedSelectedResult);
					vp.changePanel(Panels.PanelMostrarResultado);
					System.out.println("hola");
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
		mostrar = new JButton("Mostrar");
		cr = cd.getCtrlResults();
		
		BoxLayout bl = new BoxLayout(this,BoxLayout.LINE_AXIS);
		setLayout(bl);
		
		initSubPanels();
		assignListeners();
		
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
