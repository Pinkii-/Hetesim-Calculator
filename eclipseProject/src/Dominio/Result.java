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

	private Float threshold; //Lowest heteSim value which will be displayed on screen 
	
	private String idResult; //Unique result id
	private String idGraph;	//Id of the graph associated to this result
	private Boolean modified; //When true: The result or the graph have been manually edited and they will probably not be coherent
	
	
	private ArrayList<NodePair> resultList; //Hetesim results, structured in NodePairs and (hopefully) sorted by Hetesim values
	
	
	//Only one path
	public Result(final Graf g, final Float threshold, final Matrix resultHete, final Path p){
		usedP = p;
		idResult = new String(g.getNom() + " " + p.toString());
		idGraph = String.valueOf(g.id);
		modified = false;
		this.threshold = threshold;
		
		resultList = new ArrayList<NodePair>();
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
	//One node, one path
	public Result(final Graf g, final Float threshold, final ArrayList<Pair<Integer,Float>> resultHete, final Path p, final Node n1) {
		assert (p.getContingut().get(0) == n1.getTipus());
		
		firstN = n1;
		usedP = p;
		modified = false;
		this.threshold = threshold;
		
		resultList = new ArrayList<NodePair>();
		for(Integer i = 0; i < resultHete.size(); ++i){
			Node n2 = g.getNode(resultHete.get(i).first, p.getContingut().get(p.getLength()-1)); //Get second node (we already have the first)
			float hetesimVal = resultHete.get(i).second; //Get hetesim value
			resultList.add(new NodePair(n1,n2,hetesimVal)); //Add NodePair to result list
		}
		
		sortResult(); //Sort result list
	}
	//Two nodes, one path
	public Result(final Graf g, final float threshold, final Float resultHete, final Path p, final Node n1, final Node n2){
		//Assert that the path starts with the node type N1 and ends with the node type N2
		assert (p.getContingut().get(0) == n1.getTipus() && p.getContingut().get(p.getLength()-1) == n2.getTipus());
		
		firstN = n1;
		lastN = n2;
		usedP = p;
		modified = false;
		this.threshold = threshold;
		
		resultList = new ArrayList<NodePair>();
		resultList.add(new NodePair(n1,n2,resultHete)); //Create NodePair and add to list. We only need to get the float value from Hetesim
	}
	
	
	
	public String toString(){
		String retStr = new String();
		
		retStr = ("Resultado: " + idResult + "\n");                                            //Result: idresult
		retStr = retStr + ("    Path: " + usedP.toString() + "\n");                            //Path: path
		if (firstN != null) retStr = retStr + ("    N1: " + firstN.getNom() + "\n");           //N1: <Node to string>    <<<<Igual solo con el nombre basta?
		if (lastN != null) retStr = retStr + ("    N2: " + lastN.getNom() + "\n");             //N2: <Node to string>    <<<<Igual solo con el nombre basta?
		retStr = retStr + ("    Threshold: " + threshold + "\n");                              //Threshold: threshold
		retStr = retStr + ("\n");                                                              //
		int i = 0;
		System.out.println(resultList.size());
		for (i = 0; i < resultList.size(); ++i){
			retStr = retStr + "    " + resultList.get(i).toString() + "\n";                    //First node: <Node to string> Last node: <Node to string> Hetesim: valorHetesim
		}
		
		return retStr; 
	}

	/* 
	 * Checks consistency with the loaded graph. If the ids don't match,
	 * the return value of toString will have an extra line with "Not consistent"
	 * at the beggining.
	 * 
	*/
	public String toString(Graf g){
		String retStr = new String();
		if (String.valueOf(g.id) != idGraph || modified) retStr = retStr + "Not consistent!\n";
		retStr = retStr + toString();
		return retStr;
	}
	
	
	//Get the result list
	public ArrayList<NodePair> getResult(){ 
		ArrayList<NodePair> retResult = resultList;
		int i = 0;
		while (i < resultList.size() && resultList.get(i).getHetesim() > threshold){
		    retResult.add(resultList.get(i));
		    ++i;
		}
		return retResult;
	}
	
	
	//Clone all the information, not just the main object
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

	public String getIdGraf(){
		return idGraph;
	}
	
	public void setIdGraf(String idGraph){
		this.idGraph = idGraph;
	}
	
	//Modify the value of the heteSim for the line i
	public void modifLine(Integer i, Float hetesimVal){
		assert (i < resultList.size());
		
		resultList.get(i).setHetesim(hetesimVal);
		modified = true;
		sortResult();
	}
	
	//Delete the line i
	public void deleteLine(Integer i){
		assert (i < resultList.size());
		
		resultList.remove(i);
		modified = true;
	}
	
	//If either the result or the graph have been modified, the information is not consistent and toString() will issue a warning
	public void setModified(){ 
		modified = true;
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





