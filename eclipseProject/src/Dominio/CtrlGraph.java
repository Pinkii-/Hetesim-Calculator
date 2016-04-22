package Dominio;

public class CtrlGraph {
	Graf graph;
	
	public CtrlGraph(){
		graph = new Graf();
	}
	
	public CtrlGraph(Graf graph){
		this.graph = graph;
	}
	
	public Graf getGraph(){
		return graph;
	}
	
	public void setGraf(Graf graph){
		this.graph = graph;
	}
	
	public void modifyGraphNode(Integer nodeId, Integer nodeType, String newName, Integer newLabel){
		Node n = graph.getNode(nodeId, Utils.getNodeType(nodeType));
		Node.Label label = Utils.getNodeLabel(newLabel);
		n.setLabel(label);
		n.setNom(newName);
	}
	
	public void eraseGraphNode(Integer nodeId, Integer nodeType){
		Node n = graph.getNode(nodeId, Utils.getNodeType(nodeType));
		graph.deleteNode(n);
	}
	
	//PRE: node1 MUST be a paper
	public void addNodeRelation(Integer node1Id, Integer node1Type, Integer node2Id, Integer node2Type){
		Node n1 = graph.getNode(node1Id, Utils.getNodeType(node1Type));
		Node n2 = graph.getNode(node2Id, Utils.getNodeType(node2Type));
		if(!graph.existsArc(n1, n2))
		{
			graph.setArc(node1Id, node2Id, Utils.getNodeType(node2Type));
		}
	}
	
	public void eraseNodeRelation(Integer node1Id, Integer node1Type, Integer node2Id, Integer node2Type){
		Node n1 = graph.getNode(node1Id, Utils.getNodeType(node1Type));
		Node n2 = graph.getNode(node2Id, Utils.getNodeType(node2Type));
		if(graph.existsArc(n1, n2))
		{
			graph.deleteArc(n1, n2);
		}
	}

}
