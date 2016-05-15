package Presentacion;

import java.awt.Color;
import java.awt.FlowLayout;

public class PanelLoadResult extends AbstractPanel{
	public PanelLoadResult (VistaPrincipal vp) {
		super(vp);
		setLayout(new FlowLayout());
	}
	@Override
	public void closeIt() {
		// TODO Auto-generated method stub
		vp.continueAction();
	}

}
