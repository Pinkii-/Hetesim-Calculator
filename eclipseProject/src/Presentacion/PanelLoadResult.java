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
	private JSplitPane splitpane = new JSplitPane();
	private JPanel resultsPanel = new JPanel();
	private JPanel actionsPanel = new JPanel();
	private JList loadedResults = new JList();
	private DefaultListModel DLM = new DefaultListModel();
	private JButton mostrar;
	private Boolean Closing = true;
	private VistaPrincipal vp;
	
	public PanelLoadResult (VistaPrincipal vp)  {
		super(vp);
		//vp.changePanel();
		//vp.panelMostrarResultado.
		initComponents();
		try {
			//generateResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//assingListeners();
		this.vp = vp;
	}
	
	
	private void generateResultsPanel() {
		resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.PAGE_AXIS));
		
		//cd.importGraph("/home/albert/PROP/PROP/GraphForTesting");
		//cd.searchPath("AP");
		DLM.addElement("lmao 1");
		DLM.addElement("lmao 2");
		loadedResults.setModel(DLM);
		resultsPanel.add(loadedResults);
	}
	
	private void generateActionsPanel() {
		actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.PAGE_AXIS));
		mostrar = new JButton("Mostrar");
		actionsPanel.add(mostrar);
		
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
		generateActionsPanel();
		splitpane.setLeftComponent(resultsPanel);
		splitpane.setRightComponent(actionsPanel);
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
