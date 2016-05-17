/**
 * @author Victor Alcazar Lopez
**/

package Dominio;

import java.util.ArrayList;

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
	
	public static Node.Label getNodeLabel(Integer i) {
		return Node.Label.values()[i];
	}

	//FORMATTING STUFF ----------------------------------------------------------------
	
	private ArrayList<String> formatMatrixNodes(Matrix m, Node.Type t){
		ArrayList<String> ret = new ArrayList<String>();
		for (int i = 0; i < m.getNRows(); ++i) {
			ret.add(i + ": " + formatNode(graph.getNode(i, t)));
		}
		return ret;
	}
	
	private ArrayList<String> formatPaperNodes(Matrix m){
		ArrayList<String> ret = new ArrayList<String>();
		try{
			for (int i = 0; i < m.getNCols(); ++i) {
				ret.add(i + ": " + formatNode(graph.getNode(i, Node.Type.Paper)));
			}
		}catch(Exception e){
			//Resulta que si no hay papers peta el programa :)
		}
		return ret;

	}
	//Returns an arrayList of Strings of the nodes with type t formatted in strings
	public ArrayList<String> formatNodesOfType(String nodeType){
		Matrix mauthor = graph.getMatrixAuthor();
		Matrix mterme = graph.getMatrixTerm();
		Matrix mconf = graph.getMatrixConf();
		Node.Type t = Node.Type.valueOf(nodeType);
		if(t == Node.Type.Autor){
			return formatMatrixNodes(mauthor, t);			
		}else if(t == Node.Type.Conferencia){
			return formatMatrixNodes(mconf, t);
		}else if(t == Node.Type.Terme){
			return formatMatrixNodes(mterme, t);
		}else if(t == Node.Type.Paper){
			return formatPaperNodes(mauthor);
		}else{
			System.out.println("Node type not found " + t);
			return null;
		}
	}
	
	//Returns an arrayList of strings formatted by the following criteria:
	/*
	 * 0) Graph's name (size 1)
	 * 1) A - P relations (size NCol * NRows)
	 * 2) T - P relations (size NCol * NRows)
	 * 3) C - P relations (size NCol * NRows)
	 * 3) A nodes (size NRows)
	 * 4) T nodes (size NRows)
	 * 5) C nodes (size NRows)
	 * 6) P nodes (size NRows) 
	 */
	public ArrayList<String> getFormatted() {
		ArrayList<String> ret = new ArrayList<String>();
		ret.add(graph.getNom());
		Matrix mauthor = graph.getMatrixAuthor();
		Matrix mterme = graph.getMatrixTerm();
		Matrix mconf = graph.getMatrixConf();
		//Adding relations
		ret.addAll(formatMatrix(mauthor));
		ret.addAll(formatMatrix(mterme));
		ret.addAll(formatMatrix(mconf));
		//Adding nodes
		ret.addAll(formatMatrixNodes(mauthor, Node.Type.Autor));
		ret.addAll(formatMatrixNodes(mterme, Node.Type.Terme));
		ret.addAll(formatMatrixNodes(mconf, Node.Type.Conferencia));
		ret.addAll(formatPaperNodes(mauthor));
		return ret;

	}

	public ArrayList<String> formatMatrix(Matrix m) {
		ArrayList<String> ret = new ArrayList<String>();
		for (int i = 0; i < m.getNRows(); ++i) {
			for (int j = 0; j < m.getNCols(); ++j) {
				ret.add(m.getValue(i, j) + " ");
			}
		}
		return ret;
	}

	public String formatNode(Node n) {
		return "Name: " + n.getNom() + " Type: " + n.getTipus().toString();
	}

	public String toString() {
		return graph.toString();
	}

}
