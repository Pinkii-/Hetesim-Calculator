//Autor: Xavier Peñalosa
package resultTesting;


/*
 * Each row of the Result is a NodePair, which has a pair of nodes (first/last node) and its Hetesim value;
 */
public class NodePair {
	public Pair<Node,Node> pairN;
	private float hetesimVal;
	
	NodePair(Node n1, Node n2, float hetesimVal) {
		pairN = new Pair<Node, Node>(n1,n2);
		setHetesim(hetesimVal);
	}
	
	//Modify the Hetesim value
	public void setHetesim(float hetesimVal){
		assert (hetesimVal>0 && hetesimVal<=1);
		this.hetesimVal = hetesimVal;
	}
	//Get the Hetesim value
	public float getHetesim(){
		return hetesimVal;
	}
	
	public String toString(){
		return ("First Node: " + pairN.first.getNom() + " - Second Node: " + pairN.second.getNom() + " - Hetesim: " + hetesimVal);
	}
}
