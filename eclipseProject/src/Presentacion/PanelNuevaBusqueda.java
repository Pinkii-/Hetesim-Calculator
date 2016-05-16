package Presentacion;


import javax.swing.SpringLayout;
import java.awt.Dimension;
import javax.swing.JComboBox;

public class PanelNuevaBusqueda extends AbstractPanel {

	
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
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setEditable(true);
		comboBox.setSelectedItem(-1);
		comboBox.setSize(new Dimension(70,30));
		springLayout.putConstraint(SpringLayout.NORTH, comboBox, 50, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, comboBox, 50, SpringLayout.WEST, this);
		add(comboBox);

		
		
	}

	@Override
	public int closeIt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setEnabledEverything(Boolean b) {
		// TODO Auto-generated method stub
		
	}
}