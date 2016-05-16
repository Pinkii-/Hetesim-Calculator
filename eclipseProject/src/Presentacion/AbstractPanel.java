package Presentacion;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

abstract public class AbstractPanel extends JPanel {
	
	VistaAbstracta vp;
	ArrayList<VistaAbstracta> childs;
	
	AbstractPanel(VistaAbstracta vp) {
		this.vp = vp;
		childs = new ArrayList<VistaAbstracta>();
	}
	
	public void close() {
		if (childs.size() != 0) {
			childs.get(0).toFront();
			// En windows el toFront no te pone la ventana deltante.
			childs.get(0).setAlwaysOnTop(true);
			childs.get(0).setAlwaysOnTop(false);
			AbstractPanel panel = (AbstractPanel) childs.get(0).getContentPane().getComponent(0);
			panel.close();
		}
		else closeIt();
		
	}
	
	// Preguntar si estás seguro de querer cerrarlo
	abstract public void closeIt(); 
	
	abstract public void setEnabledEverything(Boolean b);
	
	void addVista(Class<?> clas) {
		VistaSecundaria newView = new VistaSecundaria(this);
		newView.setSize(600, 400);
		newView.setMinimumSize(newView.getSize());
		newView.setResizable(false);
		
		newView.setLocationRelativeTo(null);
		newView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//set the first contentPanel
		JPanel contentPane = (JPanel) newView.getContentPane();
		
		System.out.println(clas);
		try {
//			Class<?> clas = Class.forName(className);
			Constructor<?> construct = clas.getConstructor(VistaAbstracta.class);
			AbstractPanel p = (AbstractPanel) construct.newInstance(new Object[] {newView} );
			contentPane.add(p);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		
		newView.setEnabled(true);
		newView.pack();
		newView.setVisible(true);
		
		childs.add(newView);
	}
	
}
