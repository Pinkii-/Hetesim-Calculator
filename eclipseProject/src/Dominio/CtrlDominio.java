package Dominio;

import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CtrlDominio {
	Map<String, Path> paths;
	Map<String, Result> results;
	Graf graf;

	// Class references
	CtrlSearch ctrlSearch;
	CtrlData ctrlData;

	public CtrlDominio() throws ClassNotFoundException, IOException {
		graf = null;
		ArrayList<Path> pathArray;
		paths = new HashMap<String, Path>();
		pathArray = ctrlData.loadallPaths();
		for (int i = 0; i < pathArray.size(); ++i) {
			Path p = pathArray.get(i);
			paths.put(p.getNom(), p);
		}

	}
	
	public void createGraph(){
		
	}

	public void loadGraph(String idGraph) throws NotDirectoryException, ClassNotFoundException {
		results = new HashMap<String, Result>();
		Pair<Graf, ArrayList<Result>> auxPair = ctrlData.loadgraphAndResults(idGraph);
		graf = auxPair.first;
		for (int i = 0; i < auxPair.second.size(); ++i) {
			Result r = auxPair.second.get(i);
			results.put(r.getIdResult(), r);
		}
	}
	
	public void storeGraph(){
		ctrlData.storeGraf(graf);
	}
	
	//Add, erase and modify nodes
	
	public void addGraphNode(Integer nodeId, Integer nodeType, String name, Integer label){
		Node n = new Node();
		n.initialize(getNodeType(nodeType), nodeId, name);
		n.setLabel(getNodeLabel(label));
	}
		
	public void modifyGraphNode(Integer nodeId, Integer nodeType, String newName, Integer newLabel){
		Node n = graf.getNode(nodeId, getNodeType(nodeType));
		Node.Label label = getNodeLabel(newLabel);
		n.setLabel(label);
		n.setNom(newName);
	}
	
	public void eraseGraphNode(Integer nodeId, Integer nodeType){
		Node n = graf.getNode(nodeId, getNodeType(nodeType));
		graf.deleteNode(n);
	}
	
	public void addNodeRelation(){
		//TODO
	}
	
	public void eraseNodeRelation(){
		//TODO
	}
	
	//Add, erase and modify paths
	
	public void addPath(){
		//TODO
	}
		
	public void modifyPath(){
		//TODO
	}
	
	public void erasePath(){
		//TODO
	}	


	/*SEARCH FUNCTIONS
	 * Some general recommendations when sending search queries to CtrlDominio:
	 * All paths must be sent to the controller as a string that's its name
	 * attribute All nodes must be sent to the controller as two integers:
	 * First, one that holds the correspondent nodeId Second, one that holds its
	 * type. WARNING: the Integer that's being passed has to be the same as the
	 * index of the type of node that is wanted. Threshold is passed as a float.
	 */

	public String searchPathThreshhold(Float threshold, String pathName) throws PathException {
		if(graf == null)
			throw new NullPointerException("Graf not initialised");
		return ctrlSearch.searchPathThreshhold(graf, threshold, paths.get(pathName)).toString();

	}

	public String searchPath(String pathName) throws PathException {
		if(graf == null)
			throw new NullPointerException("Graf not initialised");
		return ctrlSearch.searchPath(graf, paths.get(pathName)).toString();

	}

	public String searchPathNodeThreshhold(Float threshold, String pathName, Integer nodeId, Integer nodeType)
			throws PathException {
		if(graf == null)
			throw new NullPointerException("Graf not initialised");
		return ctrlSearch.searchPathNodeThreshhold(graf, threshold, paths.get(pathName),
				graf.getNode(nodeId, getNodeType(nodeType))).toString();

	}

	public String searchPathNode(String pathName, Integer nodeId, Integer nodeType) throws PathException {
		if(graf == null)
			throw new NullPointerException("Graf not initialised");
		return ctrlSearch.searchPathNode(graf, paths.get(pathName), graf.getNode(nodeId, getNodeType(nodeType)))
				.toString();

	}

	public String searchPathNodeNodeThreshhold(Float threshold, String pathName, Integer node1Id, Integer node1Type,
			Integer node2Id, Integer node2Type) throws PathException {
		if(graf == null)
			throw new NullPointerException("Graf not initialised");
		return ctrlSearch.searchPathNodeNodeThreshhold(graf, threshold, paths.get(pathName),
				graf.getNode(node1Id, getNodeType(node1Type)),
				graf.getNode(node2Id, getNodeType(node2Type))).toString();

	}

	public String searchPathNodeNode(String pathName, Integer node1Id, Integer node1Type, Integer node2Id,
			Integer node2Type) throws PathException {
		if(graf == null)
			throw new NullPointerException("Graf not initialised");
		return ctrlSearch
				.searchPathNodeNode(graf, paths.get(pathName), graf.getNode(node1Id, getNodeType(node1Type)),
						graf.getNode(node2Id, getNodeType(node2Type))).toString();

	}
	
	//UTILS
	private Node.Type getNodeType(Integer i){
		return Node.Type.values()[i];
	}
	
	private Node.Label getNodeLabel(Integer i){
		return Node.Label.values()[i];
	}

}
