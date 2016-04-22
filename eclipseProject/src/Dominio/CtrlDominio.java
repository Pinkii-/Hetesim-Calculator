/**
 * @author Victor Alcazar Lopez
**/

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

	public CtrlDominio() {
		ctrlData = new CtrlData();
		ctrlSearch = new CtrlSearch();
		ctrlGraph = new CtrlGraph();
		ctrlPaths = new CtrlPaths();
		ctrlResults = new CtrlResults();
	}

	public void createGraph() {
		ctrlGraph.setGraf(new Graf());
		ctrlSearch.setGraph(ctrlGraph.getGraph());
	}

	public void loadPaths() {
		ArrayList<Path> pathArray;
		try {
			pathArray = ctrlData.loadallPaths();
			ctrlPaths = new CtrlPaths(pathArray);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	public void loadGraph(String idGraph) {
		Pair<Graf, ArrayList<Result>> auxPair;
		try {
			auxPair = ctrlData.loadgraphAndResults(idGraph);
			ctrlGraph = new CtrlGraph(auxPair.first);
			ctrlSearch.setGraph(ctrlGraph.getGraph());
			ctrlResults = new CtrlResults(auxPair.second);
		} catch (NotDirectoryException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void storeGraph() {
		ctrlData.storeGraf(ctrlGraph.getGraph());
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

	public String searchPathThreshhold(Float threshold, String pathName) {
		Graf graf = ctrlGraph.getGraph();
		if (ctrlGraph.isModified)
			ctrlSearch.setGraph(ctrlGraph.getGraph());
		try {
			Result r = ctrlSearch.searchPathThreshhold(graf, threshold, ctrlPaths.getPath(pathName));
			ctrlResults.setLastResult(r);
			return r.toString();
		} catch (PathException e) {
			e.printStackTrace();
			return null;
		}

	}

	public String searchPath(String pathName) {
		Graf graf = ctrlGraph.getGraph();
		if (ctrlGraph.isModified)
			ctrlSearch.setGraph(ctrlGraph.getGraph());
		try {
			Result r = ctrlSearch.searchPath(graf, ctrlPaths.getPath(pathName));
			ctrlResults.setLastResult(r);
			return r.toString();
		} catch (PathException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String searchPathNodeThreshhold(Float threshold, String pathName, Integer nodeId, Integer nodeType) {
		Graf graf = ctrlGraph.getGraph();
		if (ctrlGraph.isModified)
			ctrlSearch.setGraph(ctrlGraph.getGraph());
		try {
			Result r = ctrlSearch.searchPathNodeThreshhold(graf, threshold, ctrlPaths.getPath(pathName),
					graf.getNode(nodeId, Utils.getNodeType(nodeType)));
			ctrlResults.setLastResult(r);
			return r.toString();
		} catch (PathException e) {
			e.printStackTrace();
			return null;
		}

	}

	public String searchPathNode(String pathName, Integer nodeId, Integer nodeType) {
		Graf graf = ctrlGraph.getGraph();
		if (ctrlGraph.isModified)
			ctrlSearch.setGraph(ctrlGraph.getGraph());
		try {
			Result r = ctrlSearch.searchPathNode(graf, ctrlPaths.getPath(pathName),
					graf.getNode(nodeId, Utils.getNodeType(nodeType)));
			ctrlResults.setLastResult(r);
			return r.toString();
		} catch (PathException e) {
			e.printStackTrace();
			return null;
		}

	}

	public String searchPathNodeNodeThreshhold(Float threshold, String pathName, Integer node1Id, Integer node1Type,
			Integer node2Id, Integer node2Type) {
		Graf graf = ctrlGraph.getGraph();
		if (ctrlGraph.isModified)
			ctrlSearch.setGraph(ctrlGraph.getGraph());
		try {
			Result r = ctrlSearch.searchPathNodeNodeThreshhold(graf, threshold, ctrlPaths.getPath(pathName),
					graf.getNode(node1Id, Utils.getNodeType(node1Type)),
					graf.getNode(node2Id, Utils.getNodeType(node2Type)));
			ctrlResults.setLastResult(r);
			return r.toString();
		} catch (PathException e) {
			e.printStackTrace();
			return null;
		}

	}

	public String searchPathNodeNode(String pathName, Integer node1Id, Integer node1Type, Integer node2Id,
			Integer node2Type) {
		Graf graf = ctrlGraph.getGraph();
		if (ctrlGraph.isModified)
			ctrlSearch.setGraph(ctrlGraph.getGraph());
		try {
			Result r = ctrlSearch.searchPathNodeNode(graf, ctrlPaths.getPath(pathName),
					graf.getNode(node1Id, Utils.getNodeType(node1Type)),
					graf.getNode(node2Id, Utils.getNodeType(node2Type)));
			ctrlResults.setLastResult(r);
			return r.toString();
		} catch (PathException e) {
			e.printStackTrace();
			return null;
		}

	}

	public void saveLastSearchResult() {
		ctrlResults.addLastResult();
	}

	// OTHER FUNCTIONS

	public void saveAllModifiedEntities() {
		ArrayList<Path> modifiedPaths = ctrlPaths.getModifiedPaths();
		for (Path p : modifiedPaths) {
			try {
				ctrlData.storePath(p);
			} catch (CloneNotSupportedException | IOException e) {
				System.out.println("Error saving path");
				e.printStackTrace();
			}
		}
		ArrayList<Result> modifiedResults = ctrlResults.getModifiedResults();
		for (Result r : modifiedResults) {
			try {
				ctrlData.storeResult(r);
			} catch (CloneNotSupportedException | IOException e) {
				System.out.println("Error saving result");
				e.printStackTrace();
			}
		}
		if(ctrlGraph.isModified){
			ctrlData.storeGraf(ctrlGraph.getGraph());
		}
	}

}
