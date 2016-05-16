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
	
	public int close() {
		int ret = 0;
		int currentChild = 0;
		System.out.println("Childs :" + childs.size());
		
		while (ret == 0 && currentChild < childs.size()) {
			childs.get(currentChild).toFront();
			// En windows el toFront no te pone la ventana deltante.
			childs.get(currentChild).setAlwaysOnTop(true);
			childs.get(currentChild).setAlwaysOnTop(false);
			AbstractPanel panel = (AbstractPanel) childs.get(0).getContentPane().getComponent(0);
			ret = panel.close();
		}
		if (ret == 0) return closeIt();
		else return ret;
		
	}
	
	// Preguntar si estás seguro de querer cerrarlo. Devolver un 0 cuando si.
	abstract public int closeIt(); 
	
	abstract public void setEnabledEverything(Boolean b);
	
	void addVista(Class<?> clas, boolean bloqueante) {
		VistaSecundaria newView = new VistaSecundaria(this, bloqueante);
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
