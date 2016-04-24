/**
 * @author Victor Alcazar Lopez
**/

package Dominio;

//import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;

import Persistencia.GraphImporter;

public class CtrlDominio {

	// I/O Controllers
	CtrlData ctrlData;
	CtrlImport ctrlImport;
	// Data structures Controllers
	CtrlGraph ctrlGraph;
	CtrlPaths ctrlPaths;
	CtrlResults ctrlResults;
	// Method controllers
	CtrlSearch ctrlSearch;

	public CtrlDominio() {
		ctrlData = new CtrlData();
		ctrlSearch = new CtrlSearch();
		ctrlGraph = new CtrlGraph();
		ctrlPaths = new CtrlPaths();
		ctrlResults = new CtrlResults();
	}

	public void importGraph(String filePath) {
		ctrlGraph.setGraph(GraphImporter.leMagicGoesOn(filePath));
		ctrlSearch.setGraph(ctrlGraph.getGraph());
	}

	public void createGraph() {
		ctrlGraph.setGraph(new Graf());
		ctrlSearch.setGraph(ctrlGraph.getGraph());
	}

	public ArrayList<String> loadStoredPaths() {
		ArrayList<Path> pathArray = new ArrayList<Path>();
		ArrayList<String> pathNames = new ArrayList<String>();
		try {
			pathArray = ctrlData.loadallPaths();
			ctrlPaths = new CtrlPaths(pathArray);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		for (Path p : pathArray) {
			pathNames.add(p.getNom());
		}
		return pathNames;
	}

	public void loadGraph(String idGraph) {
		Pair<Graf, ArrayList<Result>> auxPair;
		try {
			auxPair = ctrlData.loadgraphAndResults(idGraph);
			ctrlGraph = new CtrlGraph(auxPair.first);
			ctrlSearch.setGraph(ctrlGraph.getGraph());
			ctrlResults = new CtrlResults(auxPair.second);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

	}

	public void storeGraph() {
		try {
			ctrlData.storeGraf(ctrlGraph.getGraph());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * SEARCH FUNCTIONS Some general recommendations when sending search queries
	 * to CtrlDominio: All paths must be sent to the controller as a string
	 * that's its name attribute All nodes must be sent to the controller as two
	 * integers: First, one that holds the correspondent nodeIndex Second, one that
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
		System.out.println(ctrlPaths.getPath(pathName).getContingut());
		Graf graf = ctrlGraph.getGraph();
		if (ctrlGraph.isModified)
			ctrlSearch.setGraph(ctrlGraph.getGraph());
		try {
			Result r = ctrlSearch.searchPath(graf, ctrlPaths.getPath(pathName));
			ctrlResults.setLastResult(r);
			return r.toString();
		} catch (PathException e) {
			System.out.println("Path exception generated");
			e.printStackTrace();
			return null;
		}
	}

	public String searchPathNodeThreshhold(Float threshold, String pathName, Integer nodeIndex, String nodeType) {
		Graf graf = ctrlGraph.getGraph();
		if (ctrlGraph.isModified)
			ctrlSearch.setGraph(ctrlGraph.getGraph());
		try {
			Result r = ctrlSearch.searchPathNodeThreshhold(graf, threshold, ctrlPaths.getPath(pathName),
					graf.getNode(nodeIndex, Utils.getNodeType(nodeType)));
			ctrlResults.setLastResult(r);
			return r.toString();
		} catch (PathException e) {
			e.printStackTrace();
			return null;
		}

	}

	public String searchPathNode(String pathName, Integer nodeIndex, String nodeType) {
		Graf graf = ctrlGraph.getGraph();
		if (ctrlGraph.isModified)
			ctrlSearch.setGraph(ctrlGraph.getGraph());
		try {
			Result r = ctrlSearch.searchPathNode(graf, ctrlPaths.getPath(pathName),
					graf.getNode(nodeIndex, Utils.getNodeType(nodeType)));
			ctrlResults.setLastResult(r);
			return r.toString();
		} catch (PathException e) {
			e.printStackTrace();
			return null;
		}

	}

	public String searchPathNodeNodeThreshhold(Float threshold, String pathName, Integer node1Index, String node1Type,
			Integer node2Index, String node2Type) {
		Graf graf = ctrlGraph.getGraph();
		if (ctrlGraph.isModified)
			ctrlSearch.setGraph(ctrlGraph.getGraph());
		try {
			Result r = ctrlSearch.searchPathNodeNodeThreshhold(graf, threshold, ctrlPaths.getPath(pathName),
					graf.getNode(node1Index, Utils.getNodeType(node1Type)),
					graf.getNode(node2Index, Utils.getNodeType(node2Type)));
			ctrlResults.setLastResult(r);
			return r.toString();
		} catch (PathException e) {
			e.printStackTrace();
			return null;
		}

	}

	public String searchPathNodeNode(String pathName, Integer node1Index, String node1Type, Integer node2Index,
			String node2Type) {
		Graf graf = ctrlGraph.getGraph();
		if (ctrlGraph.isModified)
			ctrlSearch.setGraph(ctrlGraph.getGraph());
		try {
			Result r = ctrlSearch.searchPathNodeNode(graf, ctrlPaths.getPath(pathName),
					graf.getNode(node1Index, Utils.getNodeType(node1Type)),
					graf.getNode(node2Index, Utils.getNodeType(node2Type)));
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

	public CtrlGraph getCtrlGraph() {
		return ctrlGraph;
	}

	public CtrlPaths getCtrlPaths() {
		return ctrlPaths;
	}

	public CtrlResults getCtrlResults() {
		return ctrlResults;
	}

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
		if (ctrlGraph.isModified) {
			try {
				ctrlData.storeGraf(ctrlGraph.getGraph());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// THE RECYCLING BIN

	/*
	 * TODO Aprovechar esta clase?¿?¿ public void importGraph(String filePath){
	 * ctrlImport = new CtrlImport(filePath); try { ctrlImport.loadGraphInfo();
	 * ctrlGraph.setGraph(ctrlImport.getGraph());
	 * ctrlSearch.setGraph(ctrlGraph.getGraph()); } catch (FileNotFoundException
	 * e) { System.out.println("Error importing Graph"); e.printStackTrace(); }
	 * }
	 */

}
