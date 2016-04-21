package Dominio;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class MatrixChainMultiplication {
	static private List<List<Long>> m;
	static private List<List<Integer>> s;
	
	static SparseMatrix compute(ArrayList<SparseMatrix> ms) {
		m = Arrays.asList(Arrays.asList(new Long[ms.size()]));
		SparseMatrix ret;
		
		
		
		return ret;
	}
	
	static void findShortestPath(ArrayList<SparseMatrix> ms, int i, int j) {
		if (i == j) {
			m.get(i).set(j, 0L);
		}
	}
}
