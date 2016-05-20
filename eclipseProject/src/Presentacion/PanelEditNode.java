package Presentacion;

import java.util.ArrayList;

import Dominio.CtrlGraph;

public class PanelEditNode extends AbstractPanel{

	CtrlGraph ctrlGraph;
	ArrayList<String> nodeInfo;
	@Override
	public int closeIt() {

		return 0;
	}


	@Override
	public void setEnabledEverything(Boolean b) {
		
	}
	
	public PanelEditNode(VistaAbstracta vp) {
		super(vp);
		ctrlGraph = cd.getCtrlGraph();
	}
	
	public void setNodeToEdit(Integer index, String nodeType){
		nodeInfo = ctrlGraph.getNodeFormatted(index, nodeType);
		updatePanel();
	}
	
	private void updatePanel(){
		
	}


}
