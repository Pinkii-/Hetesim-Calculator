/**
 * @author Victor Alcazar Lopez
**/

package Dominio.Controladores;

import Dominio.Graf;
import Dominio.Node;
import Dominio.Utils;

public class CtrlGraph {
	private Graf graph;
	Boolean isModified;

	public CtrlGraph() {
		graph = new Graf();
	}

	public CtrlGraph(Graf graph) {
		this.graph = graph;
	}

	public Graf getGraph() {
		return graph;
	}

	public void setGraf(Graf graph) {
		isModified = true;
		this.graph = graph;
	}

	public void addNode(Integer nodeId, Integer nodeType, String nodeName) {
		isModified = true;
		graph.addNode(Utils.getNodeType(nodeType), nodeId, nodeName);
	}

	public void modifyNode(Integer nodeId, Integer nodeType, String newName, Integer newLabel) {
		isModified = true;
		// TODO i cannot code any kind of error handling in here
		// because Graf does not contain a suitable function to call when
		// one does not know if a node exists or not.
		// KWALITY KONTENT
		Node n = graph.getNode(nodeId, Utils.getNodeType(nodeType));
		Node.Label label = Utils.getNodeLabel(newLabel);
		n.setLabel(label);
		n.setNom(newName);
	}

	public void eraseNode(Integer nodeId, Integer nodeType) {
		isModified = true;
		// TODO i cannot code any kind of error handling in here
		// because Graf does not contain a suitable function to call when
		// one does not know if a node exists or not.
		// KWALITY KONTENT
		Node n = graph.getNode(nodeId, Utils.getNodeType(nodeType));
		graph.deleteNode(n);
	}

	// PRE: node1 MUST be a paper
	public void addNodeRelation(Integer node1Id, Integer node1Type, Integer node2Id, Integer node2Type) {
		isModified = true;
		// TODO i cannot code any kind of error handling in here
		// because Graf does not contain a suitable function to call when
		// one does not know if a node exists or not.
		// KWALITY KONTENT
		Node n1 = graph.getNode(node1Id, Utils.getNodeType(node1Type));
		Node n2 = graph.getNode(node2Id, Utils.getNodeType(node2Type));
		if (!graph.existsArc(n1, n2)) {
			graph.setArc(node1Id, node2Id, Utils.getNodeType(node2Type));
		} else
			System.out.println("Relation already exists");
	}

	// PRE: node1 MUST be a paper
	public void eraseNodeRelation(Integer node1Id, Integer node1Type, Integer node2Id, Integer node2Type) {
		isModified = true;
		// TODO i cannot code any kind of error handling in here
		// because Graf does not contain a suitable function to call when
		// one does not know if a node exists or not.
		// KWALITY KONTENT
		Node n1 = graph.getNode(node1Id, Utils.getNodeType(node1Type));
		Node n2 = graph.getNode(node2Id, Utils.getNodeType(node2Type));
		if (graph.existsArc(n1, n2)) {
			graph.deleteArc(n1, n2);
		} else {
			System.out.println("Relation does not exist");
		}
	}

	public String toString() {
		return graph.toString();
	}

}
