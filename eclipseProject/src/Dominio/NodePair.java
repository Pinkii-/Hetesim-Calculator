//Autor: Xavier Peñalosa
package Dominio;


//Cada una de las lineas de un Result es un NodePair, compuesto por dos nodos (origen-destino) y su valor de Hetesim.
public class NodePair{
	public Pair<Node,Node> pairN;
	private float hetesimVal;
	
	NodePair(Node n1, Node n2, float hetesimVal) {
		pairN = new Pair<Node, Node>(n1,n2);
		if (hetesimVal < 0 || hetesimVal > 1) { /*Throw exception*/ }
		this.hetesimVal = hetesimVal;
	}
	
	/*public float compareTo(NodePair np){
		return (this.hetesimVal - np.hetesimVal);
	}*/
	public void setHetesim(float hetesimVal){
		this.hetesimVal = hetesimVal;
	}
	public float getHetesim(){
		return hetesimVal;
	}
	
	public String toString(){
		return ("First Node: " + pairN.first.toString() + " Second Node: " + pairN.second.toString() + " Hetesim: " + hetesimVal);
	}
	
}
