package Dominio;

import java.util.HashSet;
import java.util.Set;

public class Partite {
	SparseMatrix leftToMid;
	SparseMatrix midToRight;
	Partite(SparseMatrix f,SparseMatrix s){
		leftToMid = f;
		midToRight = s;
	}
	
	Partite(SparseMatrix matrix) {
		int total = matrix.numberOfNotZeros();
		SparseMatrix leftToMid = new SparseMatrix(matrix.getNRows(), total);
		SparseMatrix midToRight = new SparseMatrix(total, matrix.getNCols());

		int index = 0;
		for (int i = 0; i < matrix.getNRows(); ++i) {
			SparseVector sv = matrix.getRow(i);
			Set<Integer> aux = new HashSet<Integer>(sv.keySet());
			for (Integer j : aux) {
				leftToMid.set(i,index,1.f);
				midToRight.set(index, j, 1.f);
				index += 1;
			}
		}
		
	}
	
}
