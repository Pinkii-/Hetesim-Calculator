//package Dominio;
package Dominio2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Resultado {
	public Node origen;	//Search origin node
	public Node destino; //Search destination node
	public Path pathUsado; //Search Path
	
	private Graph g; //Need the graph to find the nodes
	private ArrayList<NodePair> listaResultado; //Hetesim results, structured in NodePairs and (hopefully) sorted by Hetesim values
	
	
	
	public Resultado(final Path p){
		pathUsado = p;
		
		HeteSim hete = new HeteSim();
		hete.initHeteSims();
		Matrix<Float> resultMatrix = hete.getHeteSim(p);
		for (Integer i = 0; i < resultMatrix.m.size(); ++i){
			for (Integer j = 0; j < resultMatrix.m.get(i).size(); ++j){
				Node n1 = g.getNode(i, p.getContingut().get(0));                //Get first node
				Node n2 = g.getNode(j, p.getContingut().get(p.getLength()-1));  //Get second node
				float hetesimVal = resultMatrix.getValue(i,j);                  //Get hetesim value
				
				listaResultado.add(new NodePair(n1,n2,hetesimVal));                             //Add NodePair to result list
			}
		}
		
		
		
		sortResultado(); //Sort result list
	}
	
	public Resultado(final Node n1, final Path p) {
		if (p.getContingut().get(0) != n1.getTipus()) {/*Throw exception*/}
		origen = n1;
		pathUsado = p;
		
		HeteSim hete = new HeteSim();
		hete.initHeteSims();
		ArrayList<Pair<Integer,Float>> m = hete.getHeteSim(p, n1);
		for(Integer i = 0; i < m.size(); ++i){
			Node n2 = g.getNode(m.get(i).first,p.getContingut().get(p.getLength()-1));	//Get second node (we already have the first)
			float hetesimVal = m.get(i).second;											//Get hetesim value
			listaResultado.add(new NodePair(n1,n2,hetesimVal));							//Add NodePair to result list
		}
		
		sortResultado(); //Sort result list
	}
	
	public Resultado(Node n1, Node n2, Path p){
		if (p.getContingut().get(0) != n1.getTipus() || p.getContingut().get(p.getLength()-1) != n2.getTipus()) {/*Throw exception*/}
		origen = n1;
		destino = n2;
		pathUsado = p;
			
		HeteSim hete = new HeteSim();
		listaResultado.add(new NodePair(n1,n2,hete.getHeteSim(p, n1, n2))); //Create NodePair and add to list. We only need to get the float value from Hetesim
	}
	
	private void sortResultado(){ //Sort the result list by hetesim value
		Collections.sort(listaResultado,new NodePairComparator());
	}
	
	
	public ArrayList<NodePair> getResultado(){ //Get the whole result list, or with a default threshold
		ArrayList<NodePair> retResult = listaResultado;
		//while (listaResultado.get(i).hetesimVal>myThreshold)
		//    retResult.add(listaResultado.get(i));
		//    ++i;
		//}
		return retResult;
	}
	
	@SuppressWarnings("null")  //No deberia ser necesario, vamos a tener al menos un resultado cuando se llama a esto. 
	public ArrayList<NodePair> getResultado(float threshold){
		ArrayList<NodePair> retResult = null;
		Integer i = 0;
		while (listaResultado.get(i).hetesimVal>threshold){
			retResult.add(listaResultado.get(i));
			++i;
		}
		return retResult;
	}
	
}












class HeteSim{ /*TEMPORAL para que no me salten 278364579263428 errores*/
	
	public Matrix<Float> getHeteSim(Path p){
		return null;
	}
	public ArrayList<Pair<Integer,Float>> getHeteSim(Path p, Node n1){
		return null;
	}
	public float getHeteSim(Path p, Node n1, Node n2){
		return 0.f;
	}
	
	public void initHeteSims() {}
	
}

class Graph{

	
	public Node getNode(int Id, Node.Type tipus){
		return null;
	}
}

class Matrix<T>{
	HashMap<String,Integer> pos1;
	HashMap<String,Integer> pos2;
	ArrayList<ArrayList<T>> m;
	
	Matrix() {
		pos1 = new HashMap<String,Integer>();
		pos2 = new HashMap<String,Integer>();
		m = new ArrayList<ArrayList<T>>();
	}
	
	T getValue(Integer i, Integer j) {
		return m.get(i).get(j);
	}
	
}
	
class Path {
	private ArrayList<Node.Type> contingut;
	public void getPath(ArrayList<Node.Type> begin, ArrayList<Node.Type> end) {}
	public int getLength(){ //HardCoded
		return 2;
	}
	public ArrayList<Node.Type> getContingut(){
		return contingut;
	}

}


class Node{
	Type tipus;
	Integer id;
	String nom;
	Label label;
	
	public enum Type {
		Autor, Conferencia, Paper, Terme, MidElement
	}
	public enum Label {
		Database, DataMining, AI, InformationRetrieval
	}

	Node(Type tipus, Integer id, String nom){
		this.tipus = tipus;
		this.id = id;
		this.nom = nom;
	}
	
	public Type getTipus(){
		return tipus;
	}
	public void setTipus(Type tipus){
		this.tipus = tipus;
	}
	
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	
	public String getNom(){
		return nom;
	}
	public void setNom(String nom){
		this.nom = nom;
	}
	
	public Label getLabel(){
		return label;
	}
	public void setLabel(Label label){
		
	}
	
}

class Pair<F,S>{
	public F first;
	public S second;
	Pair(F f, S s) {
		first = f;
		second = s;
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
		pairN = Pair(n1,n2); //??? wut
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



