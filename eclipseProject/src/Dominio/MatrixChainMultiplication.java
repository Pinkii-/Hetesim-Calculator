package Dominio;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class MatrixChainMultiplication {
	static private List<List<Long>> m;
	static private List<List<Integer>> s;
	
	static SparseMatrix compute(ArrayList<SparseMatrix> ms) {
		m = new ArrayList<List<Long> >(ms.size());
		for (int i = 0; i < ms.size(); ++i) {
			m.add(new ArrayList<Long>(ms.size()));
			for (int j = 0; j < ms.size();++j) {
				m.get(i).add(0L);
			}
		}
		s = new ArrayList<List<Integer>>(ms.size());
		for (int i = 0; i < ms.size(); ++i) {
			s.add(new ArrayList<Integer>(ms.size()));
			for (int j = 0; j < ms.size();++j) {
				s.get(i).add(0);
			}
		}
		for (int l = 2; l < ms.size(); ++l) {
			for (int i = 0; i < ms.size() - l; ++i) {
				int j = i + l - 1;
				m.get(i).set(j, Long.MAX_VALUE);
				for (int k = i; k < j; ++k) {
					long q = m.get(i).get(k) + m.get(k+1).get(j) + ms.get(i).getNRows() * ms.get(k).getNCols() * ms.get(j).getNCols(); 
					if (q < m.get(i).get(j)) {
						m.get(i).set(j, q);
						s.get(i).set(j, k);
					}
				}
			}
		}		
		
		return mult(ms, 0, ms.size()-1);
	}

	private static SparseMatrix mult(ArrayList<SparseMatrix> ms, int i, int j) {
		if (i == j) return ms.get(i);
		int k = s.get(i).get(j);
		SparseMatrix left = mult(ms,i,k);
		SparseMatrix right = mult(ms,k+1, j);
		return SparseMatrix.multiply(left, right);
	}
	
}
