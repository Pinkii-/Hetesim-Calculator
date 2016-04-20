package Dominio;

import java.util.ArrayList;
import java.util.Set;

public class SparseMatrix {
	ArrayList<SparseVector> rows = new ArrayList<SparseVector>();
	ArrayList<SparseVector> cols = new ArrayList<SparseVector>();

	public SparseMatrix(Matrix matrix) {
		int nCols = matrix.getNCols();
		int nRows = matrix.getNRows();
		for (int i = 0; i < nRows; ++i) {
			rows.add(new SparseVector());
		}
		for (int i = 0; i < nCols; ++i) {
			cols.add(new SparseVector());
		}
		for (int i = 0; i < nRows; ++i) {
			for (int j = 0; j < nCols; ++j) {
				set(i,j,matrix.getValue(i, j));
			}
		}
	}
	
	SparseMatrix(int nRows, int nCols) {
		for (int i = 0; i < nRows; ++i) {
			rows.add(new SparseVector());
		}
		for (int i = 0; i < nCols; ++i) {
			cols.add(new SparseVector());
		}
	}
	
	SparseMatrix(SparseMatrix sm) {
		ArrayList<SparseVector> rows = sm.getRows();
		for (SparseVector sv : rows) {
			this.rows.add((SparseVector) sv.clone());
		}
		ArrayList<SparseVector> cols = sm.getCols();
		for (SparseVector sv : cols) {
			this.cols.add((SparseVector) sv.clone());
		}
	}
	
	private ArrayList<SparseVector> getCols() {
		return cols;
	}

	private ArrayList<SparseVector> getRows() {
		return rows;
	}
	
	void set(int row, int col, Float value) {
		if (value == 0.f) {
			try {
				if (rows.get(row).containsKey(col)) {
					rows.get(row).remove(col);
					cols.get(col).remove(row);
				}
			}
			catch (IndexOutOfBoundsException e) {
				System.out.println("Trying to set a position of the matrix that is outside the matrix");
				throw e;
			}
			return;
		}
		try {
			rows.get(row).put(col, value);
			cols.get(col).put(row, value);
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("Trying to set a position of the matrix that is outside the matrix");
			throw e;
		}
	}
	
	int getNRows() {
		return rows.size();
	}
	
	int getNCols() {
		return cols.size();
	}
	
	SparseVector getRow(int i) {
		return rows.get(i);
	}
	
	SparseVector getCol(int j) {
		return cols.get(j);
	}
	
	Float getValue(int i, int j) {
		if (i < rows.size() && rows.get(i).containsKey(j)) return rows.get(i).get(j);
		else return 0.f;
	}
	
	void transpose() {
		ArrayList<SparseVector> aux = rows;
		rows = cols;
		cols = aux;
	}
	
	static SparseMatrix multiply(SparseMatrix m1, SparseMatrix m2) {
		SparseMatrix ret = new SparseMatrix(m1.getNRows(), m2.getNCols());
		for (int i = 0; i < ret.getNRows(); ++i) {
			SparseVector v1 = m1.getRow(i);
			for (int j = 0; j < ret.getNCols(); ++j) {
				ret.set(i, j, SparseVector.multiply(v1, m2.getCol(j)));
//				System.out.println("m1.row: " + v1);
//				System.out.println("m2.col: " + m2.getRow(j));
//				System.out.println("M1\n" + m1);
//				System.out.println("M2\n" + m2);
//				System.out.println("ret\n" + ret);
			}
		}
		return ret;
	}
	
	static SparseMatrix multiply(Matrix m1, SparseMatrix m2) {
		if (m1.getNCols() != m2.getNRows()) throw new RuntimeException("Dimension 'm1' cols and 'm2' rows disagree");
		SparseMatrix ret = new SparseMatrix(m1.getNRows(), m2.getNCols());
		for (int i = 0; i < ret.getNRows(); ++i) {
			ArrayList<Float> v1 = m1.getRow(i);
			for (int j = 0; j < ret.getNCols(); ++j) {
				ret.set(i, j, SparseVector.multiply(v1, m2.getCol(j)));
			}
		}
		return ret;
	}
	
	static SparseMatrix multiply(SparseMatrix m1, Matrix m2) {
		SparseMatrix ret = new SparseMatrix(m1.getNRows(), m2.getNCols());
		for (int i = 0; i < ret.getNRows(); ++i) {
			SparseVector v1 = m1.getRow(i);
			for (int j = 0; j < ret.getNCols(); ++j) {
				ret.set(i, j, SparseVector.multiply(v1, m2.getCol(j)));
			}
		}
		return ret;
	}
	
	void normaliceRows() {
		for (int i = 0; i < getNRows(); ++i) {
			Double total = 0.0;
			for (Integer j : rows.get(i).keySet()) {
				total += Math.pow(getValue(i,j),2);
			}
			total = Math.sqrt(total);
			for (Integer j : rows.get(i).keySet()) {
				set(i,j,(float) (getValue(i,j)/total));
//				rows.get(i).put(j,(float) (rows.get(i).get(j)/total));
				
			}
		}
	}

	public int numberOfNotZeros() {
		int total = 0;
		
		for (SparseVector sv : rows) {
			total += sv.size();
		}
		
		return total;
	}

	public Matrix toMatrix() {
		System.out.println("Starting to parseTo matrix");
		Matrix ret = new Matrix();
		ret.setTamany(getNRows(), getNCols());
		for (int i = 0; i < getNRows(); ++i) {
			for (Integer k : rows.get(i).keySet()){
				ret.getRow(i).set(k, getValue(i,k)); // bypassing the things and modifiying directly the 'm'
			}
		}
		System.out.println("Done parseToMatrix");
		return ret;
	}
	
	public String toString() {
		String s = new String();
		for (int i = 0; i < getNRows(); ++i) {
			for (int j = 0; j < getNCols(); ++j) {
				s+=getValue(i, j) + " ";
			}
			s+= '\n';
		} 
		return s;
	}
}