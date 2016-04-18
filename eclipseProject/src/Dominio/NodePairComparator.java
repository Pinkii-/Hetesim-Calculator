//Autor: Xavier Peñalosa
package Dominio;

import java.util.Comparator;

//Used by Result.java to sort its NodePairs based on their Hetesim values
public class NodePairComparator implements Comparator<NodePair>{
	public int compare(NodePair n1, NodePair n2){
		return ((int)n1.getHetesim() - (int)n2.getHetesim());
	}
}

