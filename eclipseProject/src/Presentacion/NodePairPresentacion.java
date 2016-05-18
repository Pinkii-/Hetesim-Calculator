package Presentacion;

import Dominio.NodePair;

/**
 * String representation of a NodePair
 * 
 * @author Xavier Peñalosa
 */
public class NodePairPresentacion {
	private String n1, n2;
	private Float hetesimVal;
	
	public NodePairPresentacion(NodePair np){
		n1 = np.pairN.first.getNom();
		n2 = np.pairN.second.getNom();
		hetesimVal = np.getHetesim();
	}
	
	public NodePairPresentacion(String n1, String n2, Float hetesimVal){
		this.n1 = n1;
		this.n2 = n2;
		this.hetesimVal = hetesimVal;
	}
	
	public String getFirstName(){
		return n1;
	}
	
	public String getLastName(){
		return n2;
	}
	
	public void setHetesimVal(Float newVal){
		if (newVal >= 0 && newVal <= 1){
			this.hetesimVal = newVal;
		}
	}
	public Float getHeteSimVal(){
		return hetesimVal;
	}
	
}
