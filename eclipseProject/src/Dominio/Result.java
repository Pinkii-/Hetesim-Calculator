//package Dominio;
package Dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Result {
	private Node firstN; //Search origin node
	private Node lastN; //Search destination node
	private Path usedP; //Search Path
	private String idResult;
	private Float threshold;
	
	private ArrayList<NodePair> resultList; //Hetesim results, structured in NodePairs and (hopefully) sorted by Hetesim values
	
	
	
	public Result(final Graf g, final Float threshold, final Matrix resultHete, final Path p){
		usedP = p;
		idResult = new String(g.getNom() + " " + p.toString());
		this.threshold = threshold;
		
		for (Integer i = 0; i < resultHete.getNCols(); ++i){
			for (Integer j = 0; j < resultHete.getNRows(); ++j){
				if (resultHete.getValue(i,j) != 0.f){
					Node n1 = g.getNode(i, p.getContingut().get(0)); //Get first node
					Node n2 = g.getNode(j, p.getContingut().get(p.getLength()-1)); //Get second node
					Float hetesimVal = resultHete.getValue(i,j); //Get hetesim value
					
					resultList.add(new NodePair(n1,n2,hetesimVal)); //Add NodePair to result list
				}
			}
		}
		
		sortResult(); //Sort result list
	}
	
	
	public Result(final Graf g, final Float threshold, final ArrayList<Pair<Integer,Float>> resultHete, final Node n1, final Path p) {
		if (p.getContingut().get(0) != n1.getTipus()) {/*Throw exception*/}
		firstN = n1;
		usedP = p;
		this.threshold = threshold;
		
		
		for(Integer i = 0; i < resultHete.size(); ++i){
			Node n2 = g.getNode(resultHete.get(i).first, p.getContingut().get(p.getLength()-1)); //Get second node (we already have the first)
			float hetesimVal = resultHete.get(i).second; //Get hetesim value
			resultList.add(new NodePair(n1,n2,hetesimVal)); //Add NodePair to result list
		}
		
		sortResult(); //Sort result list
	}
	
	
	public Result(final Graf g, final float threshold, final Float resultHete, final Node n1, final Node n2, final Path p){
		if (p.getContingut().get(0) != n1.getTipus() || p.getContingut().get(p.getLength()-1) != n2.getTipus()) {/*Throw exception*/}
		firstN = n1;
		lastN = n2;
		usedP = p;
		this.threshold = threshold;
		
		resultList.add(new NodePair(n1,n2,resultHete)); //Create NodePair and add to list. We only need to get the float value from Hetesim
	}
	
	
	
	//TODO: Add listaResultados
	public String toString(){
		return ("Resultado " + idResult + "\n -Path: " + usedP.toString() + "\n - N1: " + firstN.toString() + "\n - N2:" + lastN.toString()); 
	}
	
	
	
	
	public ArrayList<NodePair> getResult(){ //Get the whole result list, or with a default threshold
		ArrayList<NodePair> retResult = resultList;
		//while (listaResultado.get(i).hetesimVal>myThreshold)
		//    retResult.add(listaResultado.get(i));
		//    ++i;
		//}
		return retResult;
	}
	
	public ArrayList<NodePair> getResult(float threshold){
		ArrayList<NodePair> retResult = new ArrayList<NodePair>();
		Integer i = 0;
		while (resultList.get(i).hetesimVal>threshold){
			retResult.add(resultList.get(i));
			++i;
		}
		return retResult;
	}
	
	
	
	public void modifLine(Integer i, Float hetesimVal){
		resultList.get(i).hetesimVal = hetesimVal;
		sortResult();
	}
	
	public void deleteLine(Integer i){
		resultList.remove(i);
	}
	
	
	
	
	
	private void sortResult(){ //Sort the result list by hetesim value
		Collections.sort(resultList,new NodePairComparator());
	}
}


/* * * * * * * * * * * * * * * * * * *
 * 									 *
 * Clases propias de Resultado.java  *
 * 								     *
 * * * * * * * * * * * * * * * * * * */
class NodePair{
	public Pair<Node,Node> pairN;
	public float hetesimVal;
	
	NodePair(Node n1, Node n2, float hetesimVal) {
		pairN = new Pair<Node, Node>(n1,n2);
		if (hetesimVal < 0 || hetesimVal > 1) { /*Throw exception*/ }
		this.hetesimVal = hetesimVal;
	}
	
	public float compareTo(NodePair np){
		return (this.hetesimVal - np.hetesimVal);
	}
	
}
//Magic! Para poder sortear listas a partir del valor del hetesim en un NodePair
class NodePairComparator implements Comparator<NodePair>{
	public int compare(NodePair n1, NodePair n2){
		return ((int)n1.hetesimVal - (int)n2.hetesimVal);
	}
}



