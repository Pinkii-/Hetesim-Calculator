//package Dominio;
package Dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Resultado {
	private Node origen; //Search origin node
	private Node destino; //Search destination node
	private Path pathUsado; //Search Path
	private String idResultado;
	
	private ArrayList<NodePair> listaResultado; //Hetesim results, structured in NodePairs and (hopefully) sorted by Hetesim values
	
	
	
	public Resultado(final Graf g, final Matrix resultHete, final Path p){
		pathUsado = p;
		idResultado = new String(g.getNom() + " " + p.toString());
		
		for (Integer i = 0; i < resultHete.getNCols(); ++i){
			for (Integer j = 0; j < resultHete.getNRows(); ++j){
				if (resultHete.getValue(i,j) != 0.f){
					Node n1 = g.getNode(i, p.getContingut().get(0)); //Get first node
					Node n2 = g.getNode(j, p.getContingut().get(p.getLength()-1)); //Get second node
					Float hetesimVal = resultHete.getValue(i,j); //Get hetesim value
					
					listaResultado.add(new NodePair(n1,n2,hetesimVal)); //Add NodePair to result list
				}
			}
		}
		
		sortResultado(); //Sort result list
	}
	
	
	public Resultado(final Graf g, final ArrayList<Pair<Integer,Float>> resultHete, final Node n1, final Path p) {
		if (p.getContingut().get(0) != n1.getTipus()) {/*Throw exception*/}
		origen = n1;
		pathUsado = p;
		
		
		for(Integer i = 0; i < resultHete.size(); ++i){
			Node n2 = g.getNode(resultHete.get(i).first, p.getContingut().get(p.getLength()-1)); //Get second node (we already have the first)
			float hetesimVal = resultHete.get(i).second; //Get hetesim value
			listaResultado.add(new NodePair(n1,n2,hetesimVal)); //Add NodePair to result list
		}
		
		sortResultado(); //Sort result list
	}
	
	
	public Resultado(final Graf g, final Float resultHete, final Node n1, final Node n2, final Path p){
		if (p.getContingut().get(0) != n1.getTipus() || p.getContingut().get(p.getLength()-1) != n2.getTipus()) {/*Throw exception*/}
		origen = n1;
		destino = n2;
		pathUsado = p;
		
		listaResultado.add(new NodePair(n1,n2,resultHete)); //Create NodePair and add to list. We only need to get the float value from Hetesim
	}
	
	
	
	//TODO: Add listaResultados
	public String toString(){
		return ("Resultado " + idResultado + "\n -Path: " + pathUsado.toString() + "\n - N1: " + origen.toString() + "\n - N2:" + destino.toString()); 
	}
	
	
	
	
	public ArrayList<NodePair> getResultado(){ //Get the whole result list, or with a default threshold
		ArrayList<NodePair> retResult = listaResultado;
		//while (listaResultado.get(i).hetesimVal>myThreshold)
		//    retResult.add(listaResultado.get(i));
		//    ++i;
		//}
		return retResult;
	}
	
	// (Quitar este comentario) Estas haciendo adds a un ArrayList que es un null(no está inicializado) Te va a saltar un nullpointerexception
	public ArrayList<NodePair> getResultado(float threshold){
		ArrayList<NodePair> retResult = new ArrayList<NodePair>();
		Integer i = 0;
		while (listaResultado.get(i).hetesimVal>threshold){
			retResult.add(listaResultado.get(i));
			++i;
		}
		return retResult;
	}
	
	
	
	
	
	
	
	
	
	private void sortResultado(){ //Sort the result list by hetesim value
		Collections.sort(listaResultado,new NodePairComparator());
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



