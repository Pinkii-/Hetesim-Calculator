/**
 * @author Victor Alcazar Lopez
**/

package Dominio;

public class CtrlGraph {
	private Graph graph;
	Boolean isModified = false;

	public CtrlGraph() {
		graph = new Graph();
	}

	public CtrlGraph(Graph graph) {
		this.graph = graph;
	}

	public Graph getGraph() {
		return graph;
	}

	public void setGraph(Graph graph) {
		isModified = true;
		this.graph = graph;
	}

	public int addNode(String nodeType, String nodeName) {
		isModified = true;
		return graph.addNode(Utils.getNodeType(nodeType), nodeName);
	}

	public void modifyNode(Integer nodeIndex, String nodeType, String newName) {
		// TODO maybe there's an error here who knows, only God
		isModified = true;
		Node n = graph.getNode(nodeIndex, Utils.getNodeType(nodeType));
		Node.Label label = Utils.getNodeLabel(0);
		n.setLabel(label);
		n.setNom(newName);
	}

	public void eraseNode(Integer nodeIndex, String nodeType) {
		isModified = true;
		Node n = graph.getNode(nodeIndex, Utils.getNodeType(nodeType));
		graph.deleteNode(n);
	}

	// PRE: node1 MUST be a paper
	public void addNodeRelation(Integer node1Index, Integer node2Index, String node2Type) {
		isModified = true;
		Node n1 = null;
		Node n2 = null;
		try {
			n1 = graph.getNode(node1Index, Node.Type.Paper);
		} catch (Exception e) {
			System.out.println("Node 1 does not exist");
			return;
		}
		try {
			n2 = graph.getNode(node2Index, Utils.getNodeType(node2Type));
		} catch (Exception e) {
			System.out.println("Node 2 does not exist");
			return;
		}
		try {
			if (!graph.existsArc(n1, n2)) {
				graph.setArc(node1Index, node2Index, Utils.getNodeType(node2Type));
			} else
				System.out.println("Relation already exists");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printNodesOfType(String t){		
		Utils.printNodesOfType(graph, Utils.getNodeType(t));
	}
	
	public void printGraf(){
		Utils.printGraf(graph);
	}

	// PRE: node1 MUST be a paper
	public void eraseNodeRelation(Integer node1IndexPaper, Integer node2Index, String node2Type) {
		isModified = true;
		Node n1 = graph.getNode(node1IndexPaper, Node.Type.Paper);
		Node n2 = graph.getNode(node2Index, Utils.getNodeType(node2Type));
		try {
			if (graph.existsArc(n1, n2)) {
				graph.deleteArc(n1, n2);
			} else {
				System.out.println("Relation does not exist");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String toString() {
		return graph.toString();
	}

}
