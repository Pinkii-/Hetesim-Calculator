package Dominio;

import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;

public class CtrlDominio {

	// Class references
	CtrlSearch ctrlSearch;
	CtrlData ctrlData;
	CtrlGraph ctrlGraph;
	CtrlPaths ctrlPaths;
	CtrlResults ctrlResults;

	public CtrlDominio() throws ClassNotFoundException, IOException {
		ctrlData = new CtrlData();
		ctrlSearch = new CtrlSearch();
		ctrlGraph = new CtrlGraph();

		ArrayList<Path> pathArray;
		pathArray = ctrlData.loadallPaths();
		ctrlPaths = new CtrlPaths(pathArray);
		ctrlResults = new CtrlResults();

	}

	public void createGraph() {

	}

	public void loadGraph(String idGraph) throws NotDirectoryException, ClassNotFoundException {
		Pair<Graf, ArrayList<Result>> auxPair = ctrlData.loadgraphAndResults(idGraph);
		ctrlGraph = new CtrlGraph(auxPair.first);
		ctrlResults = new CtrlResults(auxPair.second);
	}

	public void storeGraph() {
		ctrlData.storeGraf(ctrlGraph.getGraph());
	}

	// Add, erase and modify nodes

	public void addGraphNode(Integer nodeId, Integer nodeType, String name, Integer label) {
		Node n = new Node();
		n.initialize(Utils.getNodeType(nodeType), nodeId, name);
		n.setLabel(Utils.getNodeLabel(label));
	}

	/*
	 * SEARCH FUNCTIONS Some general recommendations when sending search queries
	 * to CtrlDominio: All paths must be sent to the controller as a string
	 * that's its name attribute All nodes must be sent to the controller as two
	 * integers: First, one that holds the correspondent nodeId Second, one that
	 * holds its type. WARNING: the Integer that's being passed has to be the
	 * same as the index of the type of node that is wanted. Threshold is passed
	 * as a float.
	 */

	public String searchPathThreshhold(Float threshold, String pathName) throws PathException {
		Graf graf = ctrlGraph.getGraph();
		if (graf == null)
			throw new NullPointerException("Graf not initialised");
		return ctrlSearch.searchPathThreshhold(graf, threshold, ctrlPaths.getPath(pathName)).toString();

	}

	public String searchPath(String pathName) throws PathException {
		Graf graf = ctrlGraph.getGraph();
		if (graf == null)
			throw new NullPointerException("Graf not initialised");
		return ctrlSearch.searchPath(graf, ctrlPaths.getPath(pathName)).toString();
	}

	public String searchPathNodeThreshhold(Float threshold, String pathName, Integer nodeId, Integer nodeType)
			throws PathException {
		Graf graf = ctrlGraph.getGraph();
		if (graf == null)
			throw new NullPointerException("Graf not initialised");
		return ctrlSearch.searchPathNodeThreshhold(graf, threshold, ctrlPaths.getPath(pathName),
				graf.getNode(nodeId, Utils.getNodeType(nodeType))).toString();

	}

	public String searchPathNode(String pathName, Integer nodeId, Integer nodeType) throws PathException {
		Graf graf = ctrlGraph.getGraph();
		if (graf == null)
			throw new NullPointerException("Graf not initialised");
		return ctrlSearch.searchPathNode(graf, ctrlPaths.getPath(pathName), graf.getNode(nodeId, Utils.getNodeType(nodeType)))
				.toString();

	}

	public String searchPathNodeNodeThreshhold(Float threshold, String pathName, Integer node1Id, Integer node1Type,
			Integer node2Id, Integer node2Type) throws PathException {
		Graf graf = ctrlGraph.getGraph();
		if (graf == null)
			throw new NullPointerException("Graf not initialised");
		return ctrlSearch
				.searchPathNodeNodeThreshhold(graf, threshold, ctrlPaths.getPath(pathName),
						graf.getNode(node1Id, Utils.getNodeType(node1Type)), graf.getNode(node2Id, Utils.getNodeType(node2Type)))
				.toString();

	}

	public String searchPathNodeNode(String pathName, Integer node1Id, Integer node1Type, Integer node2Id,
			Integer node2Type) throws PathException {
		Graf graf = ctrlGraph.getGraph();
		if (graf == null)
			throw new NullPointerException("Graf not initialised");
		return ctrlSearch.searchPathNodeNode(graf, ctrlPaths.getPath(pathName), graf.getNode(node1Id, Utils.getNodeType(node1Type)),
				graf.getNode(node2Id, Utils.getNodeType(node2Type))).toString();

	}

}
