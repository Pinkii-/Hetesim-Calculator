package Dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import Dominio.Node.Type;

public class HeteSim {	
	class Partite { // Esta me la quedo
		Matrix leftToMid;
		Matrix midToRight;
		Partite(Matrix f,Matrix s){
			leftToMid = f;
			midToRight = s;
		}
	}
	
	class WhatMatrix {
		Boolean trasposarMatriud;
		HeteSim.PathTypes pathType;
		WhatMatrix(Boolean trans, HeteSim.PathTypes t) {
			this.trasposarMatriud = trans;
			this.pathType = t;
		}
	}
	
	
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
	
	private Graf graph;
	
	private enum PathTypes {
		Author2Paper, Conf2Paper, Term2Paper, 
		Author2Mid, Paper2MidAut,
		Conf2Mid, Paper2MidConf,
		Term2Mid, Paper2MidTerm
	}
	
	
	Matrix adyacent;
	Matrix heteAtoB;
	Matrix heteBtoA;
	
	Matrix adyacentLeft;
	Matrix hetesimsLeft;
	Matrix adyacentRight;
	Matrix hetesimsRight;
	
	public HeteSim() {
		graph = null;
		// Testhings
		adyacent = new Matrix();
		adyacentLeft = new Matrix();
		adyacentRight = new Matrix();
	}
	
//	public void initHeteSims() {
		// Inicializar para probar el programa
//		NodePaper a1 = new NodePaper("a1");
//		NodePaper a2 = new NodePaper("a2");
//		NodePaper a3 = new NodePaper("a3");
//		NodeAuthor b1 = new NodeAuthor("b1");
//		NodeAuthor b2 = new NodeAuthor("b2");
//		NodeAuthor b3 = new NodeAuthor("b3");
//		NodeAuthor b4 = new NodeAuthor("b4");
//		Node e1 = new Node("e1");
//		Node e2 = new Node("e2");
//		Node e3 = new Node("e3");
//		Node e4 = new Node("e4");
//		Node e5 = new Node("e5");
//		Node e6 = new Node("e6");
//		Node e7 = new Node("e7");
//		adyacent.set(a1,b1,1,false);
//		adyacent.set(a1,b2,1,false);
//		adyacent.set(a2,b2,true,false);
//		adyacent.set(a2,b3,true,false);
//		adyacent.set(a2,b4,true,false);
//		adyacent.set(a3,b4,true,false);
////		adyacent.set(a3,b2,true,false);
//		
//		
//		adyacentLeft.set(a1,e1,true,false);
//		adyacentLeft.set(a1,e2,true,false);
//		adyacentLeft.set(a2,e3,true,false);
//		adyacentLeft.set(a2,e4,true,false);
//		adyacentLeft.set(a2,e5,true,false);
//		adyacentLeft.set(a3,e6,true,false);
////		adyacentLeft.set(a3,e7,true,false);
//		
//		adyacentRight = new Matrix();
//		
//		adyacentRight.set(e1,b1,true,false);
//		adyacentRight.set(e2,b2,true,false);
//		adyacentRight.set(e3,b2,true,false);
//		adyacentRight.set(e4,b3,true,false);
//		adyacentRight.set(e5,b4,true,false);
//		adyacentRight.set(e6,b4,true,false);
////		adyacentRight.set(e7,b2,true,false);
//		
//		
//		heteAtoB = adyacent;
//		hetesimsLeft = adyacentLeft;
//		hetesimsRight = adyacentRight;
//		heteBtoA = heteAtoB.trasposarMatriu();
//		System.out.println("A-E");
//		hetesimsLeft.print();
//		System.out.println("E-B");
//		hetesimsRight.print();
//		System.out.println("A-B");
//		heteAtoB.print();
//		System.out.println("B-A:");
//		heteBtoA.print();
//		
//		
//		Partite p = partiteMatrix(heteAtoB);
//		System.out.println("A-E?:");
//		p.leftToMid.print();
//		System.out.println("E-B?:");
//		p.midToRight.print();
		
//		System.out.println("a1 b1 " +heteAtoB.getValue(a1, b2));
//		System.out.println("b1 a1 " +heteBtoA.getValue(b2, a1));
//		
////		System.out.println("trasposarMatriu");
////		heteAtoB.trasposarMatriu().print();
//		
//		System.out.println("mult");
//		multiply(normaliceRows(heteAtoB),normaliceCols(heteBtoA)).print();
////		
////		System.out.println("Rand");
////		normaliceRows(multiply(heteAtoB,heteAtoB.trasposarMatriu())).print();
////		System.out.println("Rand");
////		normaliceRows(hetesimsRight).print();
//		System.out.println("not Equal to");
//		multiply(normaliceRows(hetesimsLeft), normaliceCols(hetesimsRight)).print();
//		
//		Matrix<Float> hLnorm = normaliceRows(heteAtoB);
//		Matrix<Float> hRnorm = normaliceCols(heteBtoA);
//		
//		System.out.println("a1 b1 normaliced " +hLnorm.getValue(a1, b2));
//		System.out.println("b1 a1 normaliced" +hRnorm.getValue(b2, a1));
//
//		System.out.println("");
//		hLnorm.print();
//		System.out.println("");
//		hRnorm.print();
//		
//		System.out.println("");
//		
//		
//		Matrix<Float> PathLeftToMid = normaliceRows(hetesimsLeft);
//////		System.out.println("b1 b1 multiplied " +PathLeftToMid.getValue(b2, b2));
//		Matrix<Float> PathRightToMidtrasposarMatriud =  normaliceRows(hetesimsRight.trasposarMatriu()); // La formula estaba maaaal.
//		normaliceHeteSim(PathLeftToMid,PathRightToMidtrasposarMatriud).print();
//		System.out.println("a1 b1 " +normaliceHeteSim(PathLeftToMid,PathRightToMidtrasposarMatriud).getValue(a1, b1));
//	}
	
	
	
//	Matrix generateHeteSim(Matrix<Boolean> b) {
//		Matrix<Float> ret = new Matrix<Float>();
//		ret.copySizes(b);
////		ArrayList<Float> rowsPrct = new ArrayList<Float>();
////		for (int i = 0; i < b.getNRows(); ++i) {
////			rowsPrct.add(1.0f/countTrues(b.getRow(i)));
////		}
////		
//////		System.out.println(rowsPrct.toString());
////
////		ArrayList<Float> colsPrct = new ArrayList<Float>();
////		for (int i = 0; i < b.getNCols(); ++i) {
////			colsPrct.add(1.0f/countTrues(b.getCol(i)));
////		}
////		
//////		System.out.println(colsPrct.toString());
//		
////		for (Integer i = 0; i < ret.getNRows(); ++i) {
////			for (Integer j = 0; j < ret.getNCols(); ++j) {
////				ret.getRow(i).set(j, rowsPrct.get(i)*colsPrct.get(j)*(b.getRow(i).get(j) ? 1 : 0));
////			}
////		}
//		
////		ArrayList<Integer> rowsCount = new ArrayList<Integer>(); // out S
////		for (int i = 0; i < b.getNRows(); ++i) {
////			rowsCount.add(countTrues(b.getRow(i)));
////		}
////		ArrayList<Integer> colsCount = new ArrayList<Integer>(); // in T
////		for (int i = 0; i < b.getNCols(); ++i) {
////			colsCount.add(countTrues(b.getCol(i)));
////		}
//		
//		for (Integer i = 0; i < ret.getNRows(); ++i) {
//			for (Integer j = 0; j < ret.getNCols(); ++j) {
//				ret.getRow(i).set(j, ((b.getRow(i).get(j) ? 1.0f : 0.0f)));
//			}
//		}
//		
//		return ret;
//	}
	
//	private int countTrues(final List<Boolean> a) {
//		long ret = a.stream().filter(b -> b == true).count();
//		return (int) ret;
//	}

	
	private Matrix multiply(Matrix leftSide, Matrix rightSide) {
		Matrix result = new Matrix();
		result.setTamany(leftSide.getNRows(), rightSide.getNCols());
		for (Integer i = 0; i < leftSide.getNRows(); ++i) {
			for (Integer j = 0; j < rightSide.getNCols(); ++j) {
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
	
	private Matrix normaliceRows(Matrix m) {
		Matrix result = new Matrix();
		result.copiaTamany(m);
		
		for (Integer i = 0; i < m.getNRows(); ++i) {
			Double total = 0.0;
			for (Integer j = 0; j < m.getNCols(); ++j) {
				total += Math.pow(m.getRow(i).get(j),2);
			}
			total = Math.sqrt(total);
			for (Integer j = 0; j < m.getNCols(); ++j) {
				result.getRow(i).set(j,(float) (m.getRow(i).get(j)/total));
			}
		}
		return result;
	}
	
//	private Matrix<Float> normaliceCols(Matrix<Float> m) {
//		return normaliceRows(m.trasposarMatriu()).trasposarMatriu();
//	}
//	
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
	 * @param right is the trasposarMatriu of the multiplications of the U matrix from T to M (PMpr-1') (actually, is not trasposed, because row*row instead row*col)
	 * @return Matrix of Hetesims
	 */
	private Matrix normaliceHeteSim(Matrix left, Matrix right) {
		Matrix result = new Matrix();
		result.setTamany(left.getNRows(),right.getNRows());
		
		for (Integer i = 0; i < result.getNRows(); ++i) {
			for (Integer j = 0; j < result.getNCols(); ++j) {
				double top = multiplyVectors(left.getRow(i),right.getRow(j));
				double bot = Math.sqrt(norm(left.getRow(i))*norm(right.getRow(j)));
				result.getRow(i).set(j,(float) (top/bot));
				
			}
		}
		
		return result;
	}
	
	// PRE ESPECIALIZATION
	
	public void setGraph(Graf g) {
		graph = g;
		paperAuthor = paperConf = paperTerm = authorMid = confMid = termMid = false;
	}
	
	public Matrix getHeteSim(Path p) {
		ArrayList<Node.Type> left = null;
		ArrayList<Node.Type> right = null;
		Pair<ArrayList<Node.Type>, ArrayList<Node.Type>> aux = p.getPath();
		left = aux.first;
		right = aux.second;
		Collections.reverse(right);
		return normaliceHeteSim(mutiplyMatrixes(getMatrixesToMultiply(left,right)),mutiplyMatrixes(getMatrixesToMultiply(right,left)));
	}
	
	public ArrayList<Pair<Integer,Float>> getHeteSim(Path p, Node n) {
		ArrayList<Node.Type> left = null;
		ArrayList<Node.Type> right = null;
		Pair<ArrayList<Node.Type>, ArrayList<Node.Type>> aux = p.getPath();
		left = aux.first;
		right = aux.second;
		Collections.reverse(right);
		Matrix hete = normaliceHeteSim(multiplyVectorMatrix(n,getMatrixesToMultiply(left,right)),mutiplyMatrixes(getMatrixesToMultiply(right,left)));
		ArrayList<Pair<Integer,Float>> ret = new ArrayList<Pair<Integer,Float>>();
		if (hete.getNRows() != 1) {
			//throwEception Petó. Lern to Code Faget
			System.out.println("getHeteSim(Path p, Node n), el resultado no tiene un solo arraylist. Baia");
		}
		for (Integer i = 0; i < hete.getNCols(); ++i) {
			if (hete.getRow(0).get(i).equals(0.0f)) {
				ret.add(new Pair(i, hete.getRow(0).get(i)));
			}
		}
		return ret;
	}
	
	public Float getHeteSim(Path p, Node n1, Node n2) {
		ArrayList<Node.Type> left = null;
		ArrayList<Node.Type> right = null;
		Pair<ArrayList<Node.Type>, ArrayList<Node.Type>> aux = p.getPath();
		left = aux.first;
		right = aux.second;
		Collections.reverse(right);
		return normaliceHeteSim(multiplyVectorMatrix(n1, getMatrixesToMultiply(left,right)),multiplyVectorMatrix(n2, getMatrixesToMultiply(right,left))).getValue(0,0);
	}
	
	// Private Metods
	
	private Matrix arrayListToMatrix(ArrayList<Float> a) {
		Matrix ret = new Matrix();
		ret.setTamany(1, a.size());
		for (Integer i = 0; i < a.size(); ++i) {
			ret.getRow(0).set(i, a.get(i));
		}
		return ret;
	}
	
	private Matrix multiplyVectorMatrix(Node n, ArrayList<Matrix> matrixesToMultiply) {
		if (matrixesToMultiply.size() < 1) {
			// Throw Exception ("The path cant be this short dude, or maybe this whole shit is bugged. Dunno")
		}
		Matrix ret = arrayListToMatrix(matrixesToMultiply.get(0).getRow(n)); // n.id
		for (Integer i = 1; i < matrixesToMultiply.size(); ++i) {
			ret = multiply(ret,matrixesToMultiply.get(i));
		}
		return ret;
	}
	
	private Matrix mutiplyMatrixes(ArrayList<Matrix> matrixesToMultiply) {
		if (matrixesToMultiply.size() < 1) {
			// Throw Exception ("The path cant be this short dude, or maybe this whole shit is bugged. Dunno")
		}
		Matrix ret = matrixesToMultiply.get(0);
		for (Integer i = 1; i < matrixesToMultiply.size(); ++i) {
			ret = multiply(ret,matrixesToMultiply.get(i));
		}
		return ret;
	}
	

	
	/**
	 * 
	 * @param path
	 * @param aux If path finish with E and the Node.Type of before is Paper, we dont know what matrix chose. We need to know the full path
	 * @return
	 */
	
	private ArrayList<Matrix> getMatrixesToMultiply(ArrayList<Node.Type> path,ArrayList<Node.Type> aux) {
		ArrayList<Matrix> matrixesToMultiply = new ArrayList<Matrix>();
		ArrayList<WhatMatrix> whatMatrixes = getPairs(path, aux);
		for (Integer i = 0; i < whatMatrixes.size(); ++i) {
			WhatMatrix w = whatMatrixes.get(i);
			switch (w.pathType) {
			case Author2Paper:
				if (!paperAuthor) { // init paper2Author
					this.author2paper = normaliceRows(graph.getMatrixAuthor());
					this.paper2author = normaliceRows(graph.getMatrixAuthor().trasposarMatriu());
					this.paperAuthor = true;
				}
				if (w.trasposarMatriud) matrixesToMultiply.add(paper2author);
				else matrixesToMultiply.add(author2paper);
				break;
			case Conf2Paper:
				if (!paperConf) { // init paper2Author
					this.conf2paper = normaliceRows(graph.getMatrixConf());
					this.paper2conf = normaliceRows(graph.getMatrixConf().trasposarMatriu());
					this.paperConf = true;
				}
				if (w.trasposarMatriud) matrixesToMultiply.add(paper2conf);
				else matrixesToMultiply.add(conf2paper);
				break;
			case Term2Paper:
				if (!paperTerm) { // init paper2Author
					this.term2paper = normaliceRows(graph.getMatrixTerm());
					this.paper2term = normaliceRows(graph.getMatrixTerm().trasposarMatriu());
					this.paperTerm = true;
				}
				if (w.trasposarMatriud) matrixesToMultiply.add(paper2term);
				else matrixesToMultiply.add(term2paper);
				break;
			case Author2Mid:
			case Paper2MidAut:
				if (!authorMid) {
					Partite p = partiteMatrix(graph.getMatrixAuthor());
					this.author2mid = normaliceRows(p.leftToMid);
					this.paper2authorMid = normaliceRows(p.midToRight.trasposarMatriu());
					authorMid = true;
				}
				if (w.pathType == PathTypes.Author2Mid) matrixesToMultiply.add(author2mid);
				else matrixesToMultiply.add(paper2authorMid);
				break;
			case Conf2Mid:
			case Paper2MidConf:
				if (!confMid) {
					Partite p = partiteMatrix(graph.getMatrixConf());
					this.conf2mid = normaliceRows(p.leftToMid);
					this.paper2confMid = normaliceRows(p.midToRight.trasposarMatriu());
					confMid = true;
				}
				if (w.pathType == PathTypes.Conf2Mid) matrixesToMultiply.add(conf2mid);
				else matrixesToMultiply.add(paper2confMid);
				break;
			case Term2Mid:
			case Paper2MidTerm:
				if (!termMid) {
					Partite p = partiteMatrix(graph.getMatrixTerm());
					this.term2mid = normaliceRows(p.leftToMid);
					this.paper2termMid = normaliceRows(p.midToRight.trasposarMatriu());
					termMid = true;
				}
				if (w.pathType == PathTypes.Term2Mid) matrixesToMultiply.add(term2mid);
				else matrixesToMultiply.add(paper2termMid);
				break;
			}
		}
		return matrixesToMultiply;
	}

	private Partite partiteMatrix(Matrix matrix) {
		Matrix thingA2Mid = new Matrix();
		Matrix mid2ThingB = new Matrix();
		Integer total = 0;
		for (Integer i = 0; i < matrix.getNRows(); ++i) {
			for (Integer j = 0; j < matrix.getNCols(); ++j) {
				total += Math.round((Float) matrix.getValue(i, j));	// Useless cast			
			}
		}
		thingA2Mid.setTamany(matrix.getNRows(), total);
		mid2ThingB.setTamany(total, matrix.getNCols());
		Integer index = 0;
		for (Integer i = 0; i < matrix.getNRows(); ++i) {
			for (Integer j = 0; j < matrix.getNCols(); ++j) {
				if ((float) matrix.getValue(i, j) == 1.f) { // Useless cast
					thingA2Mid.getRow(i).set(index,1.f);
					mid2ThingB.getRow(index).set(j, 1.f);
					index += 1;
				}
			}
		}
		return new Partite(thingA2Mid,mid2ThingB);
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
							ret.add(new WhatMatrix(false,PathTypes.Author2Mid));
							break;
						case Conferencia:
							ret.add(new WhatMatrix(false,PathTypes.Conf2Mid));
							break;
						case Terme:
							ret.add(new WhatMatrix(false,PathTypes.Term2Mid));
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
						ret.add(new WhatMatrix(trans,PathTypes.Author2Paper));
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

class Pair<F,S> {
	F first;
	S second;
	Pair(F f, S s) {
		first = f;
		second = s;
	}
}

class SparseMatrix {
	
}
