//Autor: Xavier Peñalosa
package Dominio;

import java.util.Comparator;

/**
 * 
 * @author Xavier
 *
 */

//Used by Result.java to sort its NodePairs based on their Hetesim values
public class NodePairComparator implements Comparator<NodePair>{
	public int compare(NodePair n1, NodePair n2){
		return (n1.getHetesim() < n2.getHetesim() ? 1 : -1);
	}
}

