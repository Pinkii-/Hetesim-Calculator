package Presentacion;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VistaSecundaria extends VistaAbstracta {

	AbstractPanel parent;
	
	VistaSecundaria(AbstractPanel p) {
		parent = p;
		parent.setEnabledEverything(false);
		VistaSecundaria vs = this;
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("windowClosing");
				AbstractPanel currentPanel = (AbstractPanel) vs.getContentPane().getComponent(0);
				currentPanel.close();
			}
		});
	}
	
	@Override
	void continueAction() {
		System.out.println("continueAction");
		parent.setEnabledEverything(true);
		parent.childs.remove(this);
		dispose();
	}	

}
