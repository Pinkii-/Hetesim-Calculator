//Autor: Xavier Peñalosa
package Dominio;

import java.util.Comparator;

//Usado en el sort del Result.java para ordenar por valor de Hetesim
public class NodePairComparator implements Comparator<NodePair>{
	public int compare(NodePair n1, NodePair n2){
		return ((int)n1.getHetesim() - (int)n2.getHetesim());
	}
}

