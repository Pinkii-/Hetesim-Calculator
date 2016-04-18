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

	private Float threshold;
	
	private String idResult;
	private String idGraph;
	private Boolean modified;
	
	
	private ArrayList<NodePair> resultList; //Hetesim results, structured in NodePairs and (hopefully) sorted by Hetesim values
	
	
	
	public Result(final Graf g, final Float threshold, final Matrix resultHete, final Path p){
		usedP = p;
		idResult = new String(g.getNom() + " " + p.toString());
		idGraph = String.valueOf(g.id);
		modified = false;
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
		modified = false;
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
		modified = false;
		this.threshold = threshold;
		
		resultList.add(new NodePair(n1,n2,resultHete)); //Create NodePair and add to list. We only need to get the float value from Hetesim
	}
	
	
	
	public String toString(){
		String retStr = new String();
		retStr = ("Resultado: " + idResult + "\n");                          //Resultado: idresult
		retStr = retStr + ("    Path: " + usedP.toString() + "\n");          //Path: path
		retStr = retStr + ("    N1: " + firstN.toString() + "\n");           //N1: <Node to string>    <<<<Igual solo con el nombre basta?
		retStr = retStr + ("    N2: " + lastN.toString() + "\n");            //N2: <Node to string>    <<<<Igual solo con el nombre basta?
		retStr = retStr + ("    Threshold: " + threshold + "\n");            //Threshold: threshold
		retStr = retStr + ("\n");                                            //
		int i = 0;
		for (i = 0; i < resultList.size(); ++i){
			retStr = retStr + "    " + resultList.get(i).toString() + "\n"; //First node: <Node to string> Last node: <Node to string> Hetesim: valorHetesim
		}
		return retStr; 
	}
	
	/* Comprueba coherencia con el grafo g cargado en dominio. Si no coinciden los IDs,
	 * se añade la linea "No coherente" al principio del toString.
	 * La vista se encargará de poner el triangulito rojo de warning.
	*/
	public String toString(Graf g){
		String retStr = new String();
		if (String.valueOf(g.id) != idGraph || modified) retStr = retStr + "No coherente\n";
		retStr = retStr + toString();
		return retStr;
	}
	
	public void setModified(){ //Si el 
		modified = true;
	}
	
	
	public ArrayList<NodePair> getResult(){ //Get the result list
		ArrayList<NodePair> retResult = resultList;
		int i = 0;
		while (resultList.get(i).getHetesim()>threshold){
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
		resultList.get(i).setHetesim(hetesimVal);
		modified = true;
		sortResult();
	}
	
	public void deleteLine(Integer i){
		resultList.remove(i);
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

//Magic! Para poder sortear listas a partir del valor del hetesim en un NodePair



