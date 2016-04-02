package Dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HeteSim {
	
	class Node {
		public String id;
		Node(String id) {
			this.id = id;
		}
	}
	
	class NodePaper extends Node {
		NodePaper(String id){
			super(id);
		}
	}
	
	class NodeAuthor extends Node {
		NodeAuthor(String id){
			super(id);
		}
	}
	
	private class Matrix<T> {
		HashMap<String,Integer> pos;
		ArrayList<ArrayList<T>> m;
		
		Matrix() {
			pos = new HashMap<String,Integer>();
			m = new ArrayList<ArrayList<T>>();
		}
		
		void copySizes(Matrix m) {
			this.pos = m.getPositions();
			setSizes(m.getColSize(), m.getRowSize());
		}
		
		private HashMap<String, Integer> getPositions() {
			return pos;
		}

		void setSizes(Integer numberOfRows, Integer numberOfCols) {
			pos = new HashMap<String,Integer>();
			this.m = new ArrayList<ArrayList<T>>(numberOfRows);
			for (int i = 0; i < numberOfRows; ++i) {
				this.m.add(new ArrayList<T>(numberOfCols));
				for (int j = 0; j < numberOfCols; ++j) {
					this.m.get(i).add(null);
				}
			}
		}
		
		void set(Node n1, Node n2, T value, T d) {
			Integer i;
			if (!pos.containsKey(n1.id)) {
				i = m.size();
				pos.put(n1.id, i);
				m.add(new ArrayList<T>() );
				for (int c = 0; c < m.get(0).size(); ++c) {
					m.get(i).add(d);
				}
			}
			else i = pos.get(n1.id);
			
			
			if (!pos.containsKey(n2.id)) {
				pos.put(n2.id, m.get(0).size());
				for(List<T> row : m) {
					row.add(d);
				}
			}
			
			Integer j = pos.get(n2.id);
			m.get(i).set(j, value);
			
		}
		
		int getColSize() {
			return m.size();
		}
		
		int getRowSize() {
			if (getColSize() == 0) return -1; // Throw Exception blablablu
			return m.get(0).size();
		}
		
		ArrayList<T> getCol(Integer j) {
			if (m.size() < 1 || j >= m.get(0).size())  {/* Throw Exception*/}
			ArrayList<T> ret = new ArrayList<T>();
			for (ArrayList<T> row : m) {
				ret.add(row.get(j));
			}
			return ret;
		}
		
		ArrayList<T> getCol(Node n) {
			if (!pos.containsKey(n.id)) { /* Throw Exception */ }
			return getCol(pos.get(n.id));
		}
		
		ArrayList<T> getRow(Integer i) {
			if (i >= m.size())  {/* Throw Exception*/}
			return m.get(i);
		}
		
		ArrayList<T> getRow(Node n) {
			if (!pos.containsKey(n.id)) { /* Throw Exception */ }
			return getRow(pos.get(n.id));
		}
		
		Matrix<T> transpose() {
			Matrix<T> ret = new Matrix<T>();
			ret.setPositions(pos);
			ret.setSizes(getRowSize(), getColSize());
			
			for (Integer i = 0; i < getColSize(); ++i) {
				for (Integer j = 0; j < getRowSize(); ++j) {
					ret.getRow(j).set(i, m.get(i).get(j));
				}
			}
			return ret;
		}
		
		void setPositions(HashMap<String, Integer> pos2) {
			this.pos = pos2;
		}
		
		

		void print() {
			for (int i = 0; i < m.size(); ++i) {
				System.out.println(m.get(i).toString());
			}
		}
	}
	
	
	Matrix<Boolean> adyacent;
	Matrix<Float> hetesims;
	
	Matrix<Boolean> adyacentLeft;
	Matrix<Float> hetesimsLeft;
	Matrix<Boolean> adyacentRight;
	Matrix<Float> hetesimsRight;
	
	public HeteSim() {
		// Inicializar para probar el programa
		adyacent = new Matrix<Boolean>();
		NodePaper a1 = new NodePaper("a1");
		NodePaper a2 = new NodePaper("a2");
		NodePaper a3 = new NodePaper("a3");
		NodeAuthor b1 = new NodeAuthor("b1");
		NodeAuthor b2 = new NodeAuthor("b2");
		NodeAuthor b3 = new NodeAuthor("b3");
		NodeAuthor b4 = new NodeAuthor("b4");
		Node e1 = new Node("e1");
		Node e2 = new Node("e2");
		Node e3 = new Node("e3");
		Node e4 = new Node("e4");
		Node e5 = new Node("e5");
		Node e6 = new Node("e6");
		adyacent.set(a1,b1,true,false);
		adyacent.set(a1,b2,true,false);
		adyacent.set(a2,b2,true,false);
		adyacent.set(a2,b3,true,false);
		adyacent.set(a2,b4,true,false);
		adyacent.set(a3,b4,true,false);
		System.out.println("A-B");
		adyacent.print();
		
		adyacentLeft = new Matrix<Boolean>();
		
		adyacentLeft.set(a1,e1,true,false);
		adyacentLeft.set(a1,e2,true,false);
		adyacentLeft.set(a2,e3,true,false);
		adyacentLeft.set(a2,e4,true,false);
		adyacentLeft.set(a2,e5,true,false);
		adyacentLeft.set(a3,e6,true,false);
		System.out.println("A-E");
		adyacentLeft.print();
		
		adyacentRight = new Matrix<Boolean>();
		
		adyacentRight.set(e1,b1,true,false);
		adyacentRight.set(e2,b2,true,false);
		adyacentRight.set(e3,b2,true,false);
		adyacentRight.set(e4,b3,true,false);
		adyacentRight.set(e5,b4,true,false);
		adyacentRight.set(e6,b4,true,false);
		System.out.println("E-B");
		adyacentRight.print();
		
	}
	
	public void initHeteSims() {
		hetesims = generateHeteSim(adyacent);
		hetesimsLeft = generateHeteSim(adyacentLeft);
		hetesimsRight = generateHeteSim(adyacentRight);
		System.out.println("Hetesim:");
		hetesimsLeft.print();
		System.out.println("Hetesim:");
		hetesimsRight.print();
		System.out.println("Hetesim:");
		hetesims.print();
		
//		System.out.println("transpose");
//		hetesims.transpose().print();
		
		System.out.println("mult");
		multiply(normaliceRows(hetesims),normaliceRows(hetesims).transpose()).print();
		
		System.out.println("Rand");
		normaliceRows(multiply(hetesims,hetesims.transpose())).print();
		System.out.println("Rand");
		normaliceRows(hetesimsRight).print();
	}
	
	Matrix<Float> generateHeteSim(Matrix<Boolean> b) {
		Matrix<Float> ret = new Matrix<Float>();
		ret.copySizes(b);
		ArrayList<Float> rowsPrct = new ArrayList<Float>();
		for (int i = 0; i < b.getColSize(); ++i) {
			rowsPrct.add(1.0f/countTrues(b.getRow(i)));
		}
		
		System.out.println(rowsPrct.toString());

		ArrayList<Float> colsPrct = new ArrayList<Float>();
		for (int i = 0; i < b.getRowSize(); ++i) {
			colsPrct.add(1.0f/countTrues(b.getCol(i)));
		}
		
		System.out.println(colsPrct.toString());
		
		for (Integer i = 0; i < ret.getColSize(); ++i) {
			for (Integer j = 0; j < ret.getRowSize(); ++j) {
				ret.getRow(i).set(j, rowsPrct.get(i)*colsPrct.get(j)*(b.getRow(i).get(j) ? 1 : 0));
			}
		}
		
		return ret;
	}
	
	private int countTrues(final List<Boolean> a) {
		long ret = a.stream().filter(b -> b == true).count();
		return (int) ret;
	}
	
	private Matrix<Float> multiply(Matrix<Float> leftSide, Matrix<Float> rightSide) {
		Matrix<Float> result = new Matrix<Float>();
		result.setSizes(leftSide.getColSize(), rightSide.getRowSize());
		for (Integer i = 0; i < leftSide.getColSize(); ++i) {
			for (Integer j = 0; j < rightSide.getRowSize(); ++j) {
				result.getRow(i).set(j,multiplyVectors(leftSide.getRow(i),rightSide.getCol(j)));
			}
		}
		return result;
	}
	
	private Float multiplyVectors(ArrayList<Float> v1, ArrayList<Float> v2) {
		if (v1.size() != v2.size()) { return -1000.f; /*Throw exception*/}
		Float total = 0.f;
		for (Integer i = 0; i < v1.size();++i) {
			total += v1.get(i) * v2.get(i);
		}
		return total;
	}
	
	private Matrix<Float> normaliceRows(Matrix<Float> m) {
		Matrix<Float> result = new Matrix<Float>();
		result.copySizes(m);
		
		for (Integer i = 0; i < m.getColSize(); ++i) {
			Float total = 0.f;
			for (Integer j = 0; j < m.getRowSize(); ++j) {
				total += m.getRow(i).get(j);
			}
			for (Integer j = 0; j < m.getRowSize(); ++j) {
				result.getRow(i).set(j,m.getRow(i).get(j)/total);
			}
		}
		return result;
	}
	
//	private Matrix<Float> normaliceHeteSim(Matrix<Float> m) {
//		Matrix<Float> result = new Matrix<Float>();
//		result.copySizes(m);
//		
//		for (Integer i = 0; i < m.getColSize(); ++i) {
//			Float total = 0.f;
//			for (Integer j = 0; j < m.getRowSize(); ++j) {
//				total += m.getRow(i).get(j);
//			}
//		}
//		
//		return result;
//	}
}
