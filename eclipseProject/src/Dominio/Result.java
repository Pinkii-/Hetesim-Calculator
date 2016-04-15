//Autor: Xavier Peñalosa
package Dominio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


@SuppressWarnings("serial")
/*
 * If you're actually using serialization, it only matters if you plan on storing and retrieving objects using serialization directly.
 * The serialVersionUID represents your class version, and you should increment it if the current version of your class is not backwards
 * compatible with its previous version.
 * 
 */
public class Result implements Cloneable, Serializable{
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
	
	public Result(final Graf g, final Float threshold, final ArrayList<Pair<Integer,Float>> resultHete, final Path p, final Node n1) {
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
	
	public Result(final Graf g, final float threshold, final Float resultHete, final Path p, final Node n1, final Node n2){
		if (p.getContingut().get(0) != n1.getTipus() || p.getContingut().get(p.getLength()-1) != n2.getTipus()) {/*Throw exception*/}
		firstN = n1;
		lastN = n2;
		usedP = p;
		this.threshold = threshold;
		
		resultList.add(new NodePair(n1,n2,resultHete)); //Create NodePair and add to list. We only need to get the float value from Hetesim
	}
	
	
	
	public String toString(){
		String retStr = new String();
		retStr = ("Resultado: " + idResult + "\n");
		retStr = retStr + ("    Path: " + usedP.toString() + "\n");
		retStr = retStr + ("    N1: " + firstN.toString() + "\n");
		retStr = retStr + ("    N2: " + lastN.toString() + "\n");
		retStr = retStr + ("\n");
		int i = 0;
		for (i = 0; i < resultList.size(); ++i){
			retStr = retStr + "    " + resultList.get(i).toString() + "\n";
		}
		return retStr; 
	}
	
	
	
	public ArrayList<NodePair> getResult(){ //Get the result list
		ArrayList<NodePair> retResult = resultList;
		int i = 0;
		while (resultList.get(i).hetesimVal>threshold){
		    retResult.add(resultList.get(i));
		    ++i;
		}
		return retResult;
	}
	
	
	
	public Result deepClone() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Result) ois.readObject();
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	
	
	
	public String getIdResult(){
		String retStr = new String(idResult);
		return retStr;
	}
	
	public void setIdResult(String idResult){
		this.idResult = idResult;
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
	
	public String toString(){
		return ("First Node: " + pairN.first.toString() + " Second Node: " + pairN.second.toString() + " Hetesim: " + hetesimVal);
	}
	
}
//Magic! Para poder sortear listas a partir del valor del hetesim en un NodePair
class NodePairComparator implements Comparator<NodePair>{
	public int compare(NodePair n1, NodePair n2){
		return ((int)n1.hetesimVal - (int)n2.hetesimVal);
	}
}



