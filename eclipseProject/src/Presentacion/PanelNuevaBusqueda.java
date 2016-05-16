package Presentacion;


import javax.swing.SpringLayout;
import java.awt.Dimension;
import javax.swing.JComboBox;

public class PanelNuevaBusqueda extends AbstractPanel {

	private JComboBox<String> nodeSelect;
	private static final long serialVersionUID = 1L;

	PanelNuevaBusqueda(VistaAbstracta vp) {
		super(vp);
		
		initComponents();
	}
	/**
	 * Create the panel.
	 */
	public void initComponents() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		nodeSelect = new JComboBox<String>();
		nodeSelect.setEditable(true);
		nodeSelect.setSelectedItem(-1);
		nodeSelect.setSize(new Dimension(70,30));
		springLayout.putConstraint(SpringLayout.NORTH, nodeSelect, 50, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, nodeSelect, 50, SpringLayout.WEST, this);
		add(nodeSelect);

		
		
	}

	@Override
	public int closeIt() {
		// TODO Auto-generated method stub
		String[] buttons = {"Salir", "Cancelar"};
		int result = VistaDialog.setDialog("Titulo", "¿Estas seguro que quieres salir?\n (Se perderan todo los cambios no guardados)", buttons, VistaDialog.DialogType.QUESTION_MESSAGE);
		if (result == 0) vp.continueAction();
		return result;
	}

	@Override
	public void setEnabledEverything(Boolean b) {
		// TODO Auto-generated method stub
		
	}
}