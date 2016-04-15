package Dominio;

import java.util.Map;

public class CtrlDominio {
	CtrlHetesim ctrlHetesim;
	Map<String, Path> paths;
	Graf graf;
	
	public CtrlDominio(){
		
	}
	
	/*
	 * 	Some general recommendations when sending search queries to CtrlDominio:
	 * 	All paths must be sent to the controller as a string that's its name attribute
	 * 	All nodes must be sent to the controller as two integers:
	 * 		First, one that holds the correspondent nodeId
	 * 		Second, one that holds its type. WARNING: the Integer that's being passed has to
	 * 		be the same as the index of the type of node that is wanted.
	 * 	Threshold is passed as a float.
	 */
	
	public String searchPathThreshhold(Float threshold, String pathName){		 
		return ctrlHetesim.searchPathThreshhold(graf, threshold, paths.get(pathName)).toString();	
	}
	
	public String searchPath(String pathName){		
		return ctrlHetesim.searchPath(graf, paths.get(pathName)).toString();	
	}
	
	public String searchPathNodeThreshhold(Float threshold, String pathName, Integer nodeId, Integer nodeType){			
		return ctrlHetesim.searchPathNodeThreshhold(graf,
				threshold,
				paths.get(pathName),
				graf.getNode(nodeId, Node.Type.values()[nodeType])).toString();	
	}
	
	public String searchPathNode(String pathName, Integer nodeId, Integer nodeType){
		return ctrlHetesim.searchPathNode (graf, paths.get(pathName),
				graf.getNode(nodeId, Node.Type.values()[nodeType])).toString().toString();	
	}	
	
	public String searchPathNodeNodeThreshhold(Float threshold, String pathName, Integer node1Id, Integer node1Type,
			Integer node2Id, Integer node2Type){
		return ctrlHetesim.searchPathNodeNodeThreshhold(
				graf,
				threshold,
				paths.get(pathName),
				graf.getNode(node1Id, Node.Type.values()[node1Type]),
				graf.getNode(node2Id, Node.Type.values()[node2Type])).toString();	
	}
	
	public String searchPathNodeNode(String pathName, Integer node1Id, Integer node1Type,
			Integer node2Id, Integer node2Type){
		return ctrlHetesim.searchPathNodeNode(
				graf,				
				paths.get(pathName),
				graf.getNode(node1Id, Node.Type.values()[node1Type]),
				graf.getNode(node2Id, Node.Type.values()[node2Type])).toString();	
	}
}
