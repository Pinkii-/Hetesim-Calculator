package Dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import Dominio.Node.Type;

public class HeteSim {
	
	
	
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
		HashMap<String,Integer> pos1;
		HashMap<String,Integer> pos2;
		ArrayList<ArrayList<T>> m;
		
		Matrix() {
			pos1 = new HashMap<String,Integer>();
			pos2 = new HashMap<String,Integer>();
			m = new ArrayList<ArrayList<T>>();
		}
		
		void copySizes(final Matrix m) {
			this.pos1 = (HashMap<String, Integer>) m.getPosition1().clone();
			this.pos2 = (HashMap<String, Integer>) m.getPosition2().clone();
			setSizes(m.getColSize(), m.getRowSize());
		}
		
		private HashMap<String, Integer> getPosition1() {
			return pos1;
		}
		
		private HashMap<String, Integer> getPosition2() {
			return pos2;
		}

		void setSizes(Integer numberOfRows, Integer numberOfCols) {
			this.m = new ArrayList<ArrayList<T>>(numberOfRows);
			for (int i = 0; i < numberOfRows; ++i) {
				this.m.add(new ArrayList<T>());
				for (int j = 0; j < numberOfCols; ++j) {
					this.m.get(i).add(null);
				}
			}
		}
		
		void set(final Node n1, final Node n2, T value, T d) {
			Integer i;
			if (!pos1.containsKey(n1.id)) {
				i = m.size();
				pos1.put(n1.id, i);
				m.add(new ArrayList<T>() );
				for (int c = 0; c < m.get(0).size(); ++c) {
					m.get(i).add(d);
				}
			}
			else i = pos1.get(n1.id);
			
			
			if (!pos2.containsKey(n2.id)) {
				pos2.put(n2.id, m.get(0).size());
				for(List<T> row : m) {
					row.add(d);
				}
			}
			
			Integer j = pos2.get(n2.id);
			m.get(i).set(j, value);
			
		}
		
		T getValue(Integer i, Integer j) {
			return m.get(i).get(j);
		}
		
		T getValue(Node n1,Node n2) {
			if (!pos1.containsKey(n1.id) || !pos2.containsKey(n2.id)) {
				/* Throw exception.*/
				System.out.println("Exception Paco");
				System.out.println(pos1.containsKey(n1.id) + " " + pos2.containsKey(n2.id));
				System.out.println(pos2);
				System.out.println("");
				
			}
			return getValue(pos1.get(n1.id),pos2.get(n2.id));
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
		
		ArrayList<T> getCol(final Node n) {
			if (!pos2.containsKey(n.id)) { /* Throw Exception */ }
			return getCol(pos2.get(n.id));
		}
		
		ArrayList<T> getRow(Integer i) {
			if (i >= m.size())  {/* Throw Exception*/}
			return m.get(i);
		}
		
		ArrayList<T> getRow(final Node n) {
			if (!pos1.containsKey(n.id)) { /* Throw Exception */ }
			return getRow(pos1.get(n.id));
		}
		
		Matrix<T> transpose() {
			Matrix<T> ret = new Matrix<T>();
			ret.setPosition1(pos2);
			ret.setPosition2(pos1);
//			ret.setPosition1(new HashMap<String, Integer>(pos2));
//			ret.setPosition2(new HashMap<String, Integer>(pos1));
			ret.setSizes(getRowSize(), getColSize());
			
			for (Integer i = 0; i < getColSize(); ++i) {
				for (Integer j = 0; j < getRowSize(); ++j) {
					ret.getRow(j).set(i, m.get(i).get(j));
				}
			}
			return ret;
		}
		
		void setPosition1(final HashMap<String, Integer> pos) {
			this.pos1 = pos;
		}
		
		void setPosition2(final HashMap<String, Integer> pos) {
			this.pos2 = pos;
		}
		
		void print() {
			for (int i = 0; i < m.size(); ++i) {
				System.out.println(m.get(i).toString());
			}
		}

		public Matrix<Float> trasposarMatriu() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	class Graph{

		public Matrix<Float> getMatrixAuthor() {
			// TODO Auto-generated method stub
			return null;
		}

		public Matrix<Float> getMatrixConf() {
			// TODO Auto-generated method stub
			return null;
		}

		public Matrix<Float> getMatrixTerm() {
			// TODO Auto-generated method stub
			return null;
		}}
	
	
	private Boolean paperAuthor;
	private Matrix paper2author;
	private Matrix author2paper;
	private Boolean paperConf;
	private Matrix paper2conf;
	private Matrix conf2paper;
	private Boolean paperTerm;
	private Matrix paper2term;
	private Matrix term2paper;
	private Boolean authorMid;
	private Matrix author2mid;
	private Matrix paper2authorMid;
	private Boolean confMid;
	private Matrix conf2mid;
	private Matrix paper2confMid;
	private Boolean termMid;
	private Matrix term2mid;
	private Matrix paper2termMid;
	
	private Graph graph;
	
	private enum PathTypes {
		author2Paper, conf2Paper, term2Paper, 
		author2Mid, Paper2MidAut,
		conf2Mid, Paper2MidConf,
		term2Mid, Paper2MidTerm
	}
	
	
	Matrix<Boolean> adyacent;
	Matrix<Float> heteAtoB;
	Matrix<Float> heteBtoA;
	
	Matrix<Boolean> adyacentLeft;
	Matrix<Float> hetesimsLeft;
	Matrix<Boolean> adyacentRight;
	Matrix<Float> hetesimsRight;
	
	public HeteSim() {
		graph = null;
		// Testhings
		adyacent = new Matrix<Boolean>();
		adyacentLeft = new Matrix<Boolean>();
		adyacentRight = new Matrix<Boolean>();
	}
	
	public void initHeteSims() {
		// Inicializar para probar el programa
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
		Node e7 = new Node("e7");
		adyacent.set(a1,b1,true,false);
		adyacent.set(a1,b2,true,false);
		adyacent.set(a2,b2,true,false);
		adyacent.set(a2,b3,true,false);
		adyacent.set(a2,b4,true,false);
		adyacent.set(a3,b4,true,false);
//		adyacent.set(a3,b2,true,false);
		System.out.println("A-B");
		adyacent.print();
		
		
		adyacentLeft.set(a1,e1,true,false);
		adyacentLeft.set(a1,e2,true,false);
		adyacentLeft.set(a2,e3,true,false);
		adyacentLeft.set(a2,e4,true,false);
		adyacentLeft.set(a2,e5,true,false);
		adyacentLeft.set(a3,e6,true,false);
//		adyacentLeft.set(a3,e7,true,false);
		System.out.println("A-E");
		adyacentLeft.print();
		
		adyacentRight = new Matrix<Boolean>();
		
		adyacentRight.set(e1,b1,true,false);
		adyacentRight.set(e2,b2,true,false);
		adyacentRight.set(e3,b2,true,false);
		adyacentRight.set(e4,b3,true,false);
		adyacentRight.set(e5,b4,true,false);
		adyacentRight.set(e6,b4,true,false);
//		adyacentRight.set(e7,b2,true,false);
		System.out.println("E-B");
		adyacentRight.print();
		
		
		heteAtoB = generateHeteSim(adyacent);
		hetesimsLeft = generateHeteSim(adyacentLeft);
		hetesimsRight = generateHeteSim(adyacentRight);
		heteBtoA = heteAtoB.transpose();
		System.out.println("Hetesim Left:");
		hetesimsLeft.print();
		System.out.println("Hetesim Right:");
		hetesimsRight.print();
		System.out.println("Hetesim:");
		heteAtoB.print();
		System.out.println("Hetesim B-A:");
		heteBtoA.print();
		
		System.out.println("a1 b1 " +heteAtoB.getValue(a1, b2));
		System.out.println("b1 a1 " +heteBtoA.getValue(b2, a1));
		
//		System.out.println("transpose");
//		heteAtoB.transpose().print();
		
		System.out.println("mult");
		multiply(normaliceRows(heteAtoB),normaliceCols(heteBtoA)).print();
//		
//		System.out.println("Rand");
//		normaliceRows(multiply(heteAtoB,heteAtoB.transpose())).print();
//		System.out.println("Rand");
//		normaliceRows(hetesimsRight).print();
		System.out.println("not Equal to");
		multiply(normaliceRows(hetesimsLeft), normaliceCols(hetesimsRight)).print();
		
		Matrix<Float> hLnorm = normaliceRows(heteAtoB);
		Matrix<Float> hRnorm = normaliceCols(heteBtoA);
		
		System.out.println("a1 b1 normaliced " +hLnorm.getValue(a1, b2));
		System.out.println("b1 a1 normaliced" +hRnorm.getValue(b2, a1));

		System.out.println("");
		hLnorm.print();
		System.out.println("");
		hRnorm.print();
		
		System.out.println("");
		
		
		Matrix<Float> PathLeftToMid = normaliceRows(hetesimsLeft);
//		System.out.println("b1 b1 multiplied " +PathLeftToMid.getValue(b2, b2));
		Matrix<Float> PathRightToMidTransposed =  normaliceRows(hetesimsRight.transpose()); // La formua estaba maaaal.
		normaliceHeteSim(PathLeftToMid,PathRightToMidTransposed).print();
		System.out.println("a1 b1 " +normaliceHeteSim(PathLeftToMid,PathRightToMidTransposed).getValue(a1, b1));
	}
	
	
	
	Matrix<Float> generateHeteSim(Matrix<Boolean> b) {
		Matrix<Float> ret = new Matrix<Float>();
		ret.copySizes(b);
//		ArrayList<Float> rowsPrct = new ArrayList<Float>();
//		for (int i = 0; i < b.getColSize(); ++i) {
//			rowsPrct.add(1.0f/countTrues(b.getRow(i)));
//		}
//		
////		System.out.println(rowsPrct.toString());
//
//		ArrayList<Float> colsPrct = new ArrayList<Float>();
//		for (int i = 0; i < b.getRowSize(); ++i) {
//			colsPrct.add(1.0f/countTrues(b.getCol(i)));
//		}
//		
////		System.out.println(colsPrct.toString());
		
//		for (Integer i = 0; i < ret.getColSize(); ++i) {
//			for (Integer j = 0; j < ret.getRowSize(); ++j) {
//				ret.getRow(i).set(j, rowsPrct.get(i)*colsPrct.get(j)*(b.getRow(i).get(j) ? 1 : 0));
//			}
//		}
		
//		ArrayList<Integer> rowsCount = new ArrayList<Integer>(); // out S
//		for (int i = 0; i < b.getColSize(); ++i) {
//			rowsCount.add(countTrues(b.getRow(i)));
//		}
//		ArrayList<Integer> colsCount = new ArrayList<Integer>(); // in T
//		for (int i = 0; i < b.getRowSize(); ++i) {
//			colsCount.add(countTrues(b.getCol(i)));
//		}
		
		for (Integer i = 0; i < ret.getColSize(); ++i) {
			for (Integer j = 0; j < ret.getRowSize(); ++j) {
				ret.getRow(i).set(j, ((b.getRow(i).get(j) ? 1.0f : 0.0f)));
			}
		}
		
		return ret;
	}
	
//	private int countTrues(final List<Boolean> a) {
//		long ret = a.stream().filter(b -> b == true).count();
//		return (int) ret;
//	}

	
	private Matrix<Float> multiply(Matrix<Float> leftSide, Matrix<Float> rightSide) {
		Matrix<Float> result = new Matrix<Float>();
		result.setSizes(leftSide.getColSize(), rightSide.getRowSize());
		result.setPosition1(leftSide.getPosition1());
		result.setPosition2(rightSide.getPosition2());
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
			Double total = 0.0;
			for (Integer j = 0; j < m.getRowSize(); ++j) {
				total += Math.pow(m.getRow(i).get(j),2);
			}
			total = Math.sqrt(total);
			for (Integer j = 0; j < m.getRowSize(); ++j) {
				result.getRow(i).set(j,(float) (m.getRow(i).get(j)/total));
			}
		}
		return result;
	}
	
	private Matrix<Float> normaliceCols(Matrix<Float> m) {
		return normaliceRows(m.transpose()).transpose();
	}
	
	private float norm(ArrayList<Float> v) {
		Float total = 0.f;
		for (Integer i = 0; i < v.size();++i) {
			total += (float) Math.pow(v.get(i), 2);
		}
		
		return (float) Math.sqrt(total);
	}
	
	/**
	 *  
	 * @param left is the multiplications of the U matrix from S to M (PMpl)
	 * @param right is the transpose of the multiplications of the U matrix from T to M (PMpr-1')
	 * @return Matrix of Hetesims
	 */
	private Matrix<Float> normaliceHeteSim(Matrix<Float> left, Matrix<Float> right) {
		Matrix<Float> result = new Matrix<Float>();
		result.setSizes(left.getColSize(),right.getColSize());
		result.setPosition1(left.getPosition1());
		result.setPosition2(right.getPosition1());
		
		for (Integer i = 0; i < result.getColSize(); ++i) {
			for (Integer j = 0; j < result.getRowSize(); ++j) {
				double top = multiplyVectors(left.getRow(i),right.getRow(j));
				double bot = Math.sqrt(norm(left.getRow(i))*norm(right.getRow(j)));
				result.getRow(i).set(j,(float) (top/bot));
				
			}
		}
		
		return result;
	}
	
	// PRE ESPECIALIZATION
	
	public void setGraph(Graph g) {
		graph = g;
		paperAuthor = paperConf = paperTerm = authorMid = confMid = termMid = false;
	}
	
	public Matrix getHeteSim(Path p) {
		ArrayList<Node.Type> left = null;
		ArrayList<Node.Type> right = null;
		p.getPath(left, right);
		Collections.reverse(right);
		return normaliceHeteSim(multiplyMatrixes(left,right),multiplyMatrixes(right,left));
	}
	
	public ArrayList<Float> getHeteSim(Path p, Node n) {
		ArrayList<Node.Type> left = null;
		ArrayList<Node.Type> right = null;
		p.getPath(left, right);
		Collections.reverse(right);
		return normaliceHeteSim(multiplyMatrixes(left,right),multiplyMatrixes(right,left)).getRow(n);
	}
	
	public Float getHeteSim(Path p, Node n1, Node n2) {
		ArrayList<Node.Type> left = null;
		ArrayList<Node.Type> right = null;
		p.getPath(left, right);
		Collections.reverse(right);
		return normaliceHeteSim(multiplyMatrixes(left,right),multiplyMatrixes(right,left)).getValue(n1,n2);
	}
	
	// Private Metods
	
	class WhatMatrix {
		public Boolean transposed;
		public HeteSim.PathTypes pathType;
		WhatMatrix(Boolean trans, HeteSim.PathTypes t) {
			this.transposed = trans;
			this.pathType = t;
		}
	}
	
	/**
	 * 
	 * @param path
	 * @param aux If path finish with E and the Node.Type of before is Paper, we dont know what matrix chose. We need to know the full path
	 * @return
	 */
	
	private Matrix multiplyMatrixes(ArrayList<Node.Type> path,ArrayList<Node.Type> aux) {
		ArrayList<Matrix> matrixesToMultiply = new ArrayList<Matrix>();
		ArrayList<WhatMatrix> whatMatrixes = getPairs(path, aux);
		for (Integer i = 0; i < whatMatrixes.size(); ++i) {
			WhatMatrix w = whatMatrixes.get(i);
			switch (w.pathType) {
			case author2Paper:
				if (!paperAuthor) { // init paper2Author
					this.author2paper = normaliceRows(graph.getMatrixAuthor());
					this.paper2author = normaliceRows(graph.getMatrixAuthor().trasposarMatriu());
					this.paperAuthor = true;
				}
				if (w.transposed) matrixesToMultiply.add(paper2author);
				else matrixesToMultiply.add(author2paper);
				break;
			case conf2Paper:
				if (!paperConf) { // init paper2Author
					this.conf2paper = normaliceRows(graph.getMatrixConf());
					this.paper2conf = normaliceRows(graph.getMatrixConf().trasposarMatriu());
					this.paperConf = true;
				}
				if (w.transposed) matrixesToMultiply.add(paper2conf);
				else matrixesToMultiply.add(conf2paper);
				break;
			case term2Paper:
				if (!paperTerm) { // init paper2Author
					this.term2paper = normaliceRows(graph.getMatrixTerm());
					this.paper2term = normaliceRows(graph.getMatrixTerm().trasposarMatriu());
					this.paperTerm = true;
				}
				if (w.transposed) matrixesToMultiply.add(paper2term);
				else matrixesToMultiply.add(term2paper);
				break;
			case author2Mid:
			case Paper2MidAut:
				if (!authorMid) {
					Matrix author2Mid = null, mid2Paper = null;
					partiteMatrix(graph.getMatrixAuthor(),author2Mid, mid2Paper);
					this.author2mid = normaliceRows(author2Mid);
					this.paper2authorMid = normaliceRows(mid2Paper.transpose());
				}
				
				break;
				/// MORE THINGS TO COME HERE
			}
		}
		return null;
	}

	private void partiteMatrix(Matrix matrix, Matrix thingA2Mid, Matrix mid2ThingB) {
		thingA2Mid = new Matrix<Float>();
		mid2ThingB = new Matrix<Float>();
		Integer total = 0;
		for (Integer i = 0; i < matrix.getColSize(); ++i) {
			for (Integer j = 0; j < matrix.getRowSize(); ++j) {
				total += Math.round((Float) matrix.getValue(i, j));	// Useless cast			
			}
		}
		thingA2Mid.setSizes(matrix.getColSize(), total);
		mid2ThingB.setSizes(total, matrix.getRowSize());
		Integer index = 0;
		for (Integer i = 0; i < matrix.getColSize(); ++i) {
			for (Integer j = 0; j < matrix.getRowSize(); ++j) {
				if ((float) matrix.getValue(i, j) == 1.f) { // Useless cast
					thingA2Mid.getRow(i).set(index,1.f);
					mid2ThingB.getRow(index).set(j, 1.f);
					index += 1;
				}
			}
		}
		
	}

	private ArrayList<WhatMatrix> getPairs(ArrayList<Node.Type> path, ArrayList<Node.Type> aux) {
		ArrayList<WhatMatrix> ret = new ArrayList<WhatMatrix>();
		for (Integer i = 1; i < path.size(); ++i) {
			Node.Type last = path.get(i-1);
			Node.Type current = path.get(i);
			if (current == Node.Type.MidElement) {
				if (last == Node.Type.Paper) {
					Node.Type nextToMid = aux.get(aux.size()-2);
					switch (nextToMid) {
						case Autor:
							ret.add(new WhatMatrix(false,PathTypes.Paper2MidAut));
							break;
						case Conferencia:
							ret.add(new WhatMatrix(false,PathTypes.Paper2MidConf));
							break;
						case Terme:
							ret.add(new WhatMatrix(false,PathTypes.Paper2MidTerm));
							break;
						default:
							/* Throw exception: The path is broken */
							System.out.println("The path is broken. The Mid element is linking Paper with Paper or another MidElement :(");
							System.out.println(path);
							System.out.println(aux);
							System.exit(-1);
					}
				}
				else {
					switch (last) {
						case Autor:
							ret.add(new WhatMatrix(false,PathTypes.author2Mid));
							break;
						case Conferencia:
							ret.add(new WhatMatrix(false,PathTypes.conf2Mid));
							break;
						case Terme:
							ret.add(new WhatMatrix(false,PathTypes.term2Mid));
							break;
						default:
							/* Throw exception: The function is broken or the path is broken */
							System.out.println("Maybe you dont know how to code, or maybe the path is broken. IoKze, no soi 100tifico :(");
							System.out.println(path);
							System.out.println(last);
							System.exit(-1);
					}
				}
			}
			else {
				boolean trans = false;
				if (last == Node.Type.Paper) { //Swap
					Node.Type ntAux = last;
					last = current;
					current = ntAux;
					trans = true;
				}
				switch (last) {
					case Autor:
						ret.add(new WhatMatrix(trans,PathTypes.author2Paper));
						break;
					case Conferencia:
						ret.add(new WhatMatrix(trans,PathTypes.Paper2MidConf));
						break;
					case Terme:
						ret.add(new WhatMatrix(trans,PathTypes.Paper2MidTerm));
						break;
					default:
						/* Throw exception: The function is broken or the path is broken */
						System.out.println("Two papers together :(");
						System.out.println(path);
						System.out.println(current);
						System.out.println(last);
						System.exit(-1);
				}
			}
		}

		return ret;
	}
	
}




class Node {
	public String id;
	public Type type;
	
	public enum Type { 
		Autor, Conferencia, Paper, Terme, MidElement
	};
	
	Node(String id) {
		this.id = id;
	}
}

class Path {
	private ArrayList<Node.Type> contingut;
	public void getPath(ArrayList<Node.Type> begin, ArrayList<Node.Type> end) {}

}
