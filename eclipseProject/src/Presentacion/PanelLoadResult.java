package Presentacion;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

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
import javax.swing.SwingConstants;

<<<<<<< HEAD
import Dominio.Result;
import Tests.CtrlDataTest;
=======
import Dominio.CtrlDataTest;
import Dominio.CtrlResults;
import Dominio.Result;
import Presentacion.VistaPrincipal.Panels;
>>>>>>> origin/master

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
	private JList loadedResults;
	private JScrollPane scrollPane;
	private DefaultListModel DLM;
	private JButton mostrar;
	private VistaPrincipal vp;
	
	public PanelLoadResult (VistaPrincipal vp)  {
		super(vp);
		initComponents();
		try {
			//generateResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.vp = vp;
	}
	
	
	private void generateResultsPanel() {
		resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.PAGE_AXIS));
		
		//cd.importGraph("/home/albert/PROP/PROP/GraphForTesting");
		//cd.searchPath("AP");
		DLM.addElement("lmao 1");
		for (int i = 0; i < 50; i++) {
			DLM.addElement("lmao 2");
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
		actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.LINE_AXIS));
		mostrar = new JButton("Mostrar");
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
				vp.changePanel(Panels.PanelMostrarResultado);
				System.out.println("hola");
			}
		});
	}
	private void initComponents() {
		splitpane = new JSplitPane();
		resultsPanel = new JPanel();
		actionsPanel = new JPanel();
		loadedResults = new JList();
		searchPanel = new JPanel();
		searchAndActionsPanel = new JPanel();
		DLM = new DefaultListModel();
		
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
