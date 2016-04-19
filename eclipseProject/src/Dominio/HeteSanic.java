package Dominio;

import java.util.ArrayList;
import java.util.Collections;

public class HeteSanic {

	class WhatMatrix {
		boolean transposeMatrix;
		PathTypes pathType;
		WhatMatrix(boolean trans, PathTypes t) {
			this.transposeMatrix = trans;
			this.pathType = t;
		}
		public String toString() {
			return pathType + " " + (transposeMatrix ? "Transpuesta" : "");
		}
	}
	
	private boolean paperAuthor;
	private SparseMatrix paper2author;
	private SparseMatrix author2paper;
	private boolean paperConf;
	private SparseMatrix paper2conf;
	private SparseMatrix conf2paper;
	private boolean paperTerm;
	private SparseMatrix paper2term;
	private SparseMatrix term2paper;
	private boolean authorMid;
	private SparseMatrix author2mid;
	private SparseMatrix paper2authorMid;
	private boolean confMid;
	private SparseMatrix conf2mid;
	private SparseMatrix paper2confMid;
	private boolean termMid;
	private SparseMatrix term2mid;
	private SparseMatrix paper2termMid;
	
	private Graf graph;
	
	private enum PathTypes {
		Author2Paper, Conf2Paper, Term2Paper, 
		Author2Mid, Paper2MidAut,
		Conf2Mid, Paper2MidConf,
		Term2Mid, Paper2MidTerm
	}
	
	public HeteSanic() {
		graph = null;
		paperAuthor = paperConf = paperTerm = authorMid = confMid = termMid = false;
	}
	
	/**
	 *  
	 * @param left is the multiplications of the U matrix from S to M (PMpl)
	 * @param right is the trasposarMatriu of the multiplications of the U matrix from T to M (PMpr-1') (actually, is not trasposed, because row*row instead row*col)
	 * @return Matrix of Hetesims
	 */
	private SparseMatrix normaliceHeteSim(SparseMatrix left, SparseMatrix right) {
		SparseMatrix result = new SparseMatrix(left.getNRows(),right.getNRows());
		
		for (int i = 0; i < result.getNRows(); ++i) {
			for (int j = 0; j < result.getNCols(); ++j) {
				double top = SparseVector.multiply(left.getRow(i),right.getRow(j));
				double bot = Math.sqrt(left.getRow(i).norm()*right.getRow(j).norm());
				
//				System.out.println(top + " " + bot + " " + top/bot);
				
				result.set(i, j, (float) (top/bot));
				
//				System.out.println(result.getValue(i, j));
				
			}
		}
		
		return result;
	}
	
	// POST ESPECIALIZATION
	
	public void setGraph(Graf g) {
		graph = g;
		paperAuthor = paperConf = paperTerm = authorMid = confMid = termMid = false;
	}
	
	public Matrix getHeteSim(Path p) throws PathException {
		ArrayList<Node.Type> left = null;
		ArrayList<Node.Type> right = null;
		Pair<ArrayList<Node.Type>, ArrayList<Node.Type>> aux = p.getPath();
		left = aux.first;
		right = aux.second;
		if (left.size() < 2 || right.size() < 2) throw new PathException("The path is too short");
		Collections.reverse(right);
		return normaliceHeteSim(mutiplyMatrixes(getMatrixesToMultiply(left,right)),mutiplyMatrixes(getMatrixesToMultiply(right,left))).toMatrix();
	}
	
	public ArrayList<Pair<Integer,Float>> getHeteSim(Path p, Node n) throws PathException {
		ArrayList<Node.Type> left = null;
		ArrayList<Node.Type> right = null;
		Pair<ArrayList<Node.Type>, ArrayList<Node.Type>> aux = p.getPath();
		left = aux.first;
		right = aux.second;
		if (left.size() < 2 || right.size() < 2) throw new PathException("The path is too short");
		Collections.reverse(right);
		SparseMatrix hete = normaliceHeteSim(multiplyVectorMatrix(n,getMatrixesToMultiply(left,right)),mutiplyMatrixes(getMatrixesToMultiply(right,left)));
		ArrayList<Pair<Integer,Float>> ret = new ArrayList<Pair<Integer,Float>>();
		if (hete.getNRows() != 1) {
			throw new RuntimeException("getHeteSim(Path p, Node n), el resultado no tiene un solo arraylist. Baia");
		}
		for (int i : hete.getRow(0).keySet()) {
			ret.add(new Pair<Integer, Float>(i, hete.getRow(0).get(i)));
		}
		return ret;
	}
	
	public Float getHeteSim(Path p, Node n1, Node n2) throws PathException {
		ArrayList<Node.Type> left = null;
		ArrayList<Node.Type> right = null;
		Pair<ArrayList<Node.Type>, ArrayList<Node.Type>> aux = p.getPath();
		left = aux.first;
		right = aux.second;
		if (left.size() < 2 || right.size() < 2) throw new PathException("The path is too short");
		Collections.reverse(right);
		return normaliceHeteSim(multiplyVectorMatrix(n1, getMatrixesToMultiply(left,right)),multiplyVectorMatrix(n2, getMatrixesToMultiply(right,left))).getValue(0,0);
	}
	
	// Private Metods
	
	private SparseMatrix arrayListToMatrix(SparseVector sparseVector) {
		SparseMatrix ret = new SparseMatrix(1, sparseVector.size());
		for (int i : sparseVector.keySet()) {
			ret.set(0,i,sparseVector.get(i));
		}
		return ret;
	}
	
	private SparseMatrix multiplyVectorMatrix(Node n, ArrayList<SparseMatrix> matrixesToMultiply) {
		if (matrixesToMultiply.size() < 1) {
			// Throw Exception ("The path cant be this short dude, or maybe this whole shit is bugged. Dunno")
		}
		SparseMatrix ret = arrayListToMatrix(matrixesToMultiply.get(0).getRow(n.id));
		for (int i = 1; i < matrixesToMultiply.size(); ++i) {
			ret = SparseMatrix.multiply(ret,matrixesToMultiply.get(i));
		}
		ret.normaliceRows();
		return ret;
	}
	
	private SparseMatrix mutiplyMatrixes(ArrayList<SparseMatrix> matrixesToMultiply) {
		if (matrixesToMultiply.size() < 1) {
			System.out.println("BROKEN");// Throw Exception ("The path cant be this short dude, or maybe this whole shit is bugged. Dunno")
		}
		SparseMatrix ret = matrixesToMultiply.get(0);
		for (int i = 1; i < matrixesToMultiply.size(); ++i) {
			ret = SparseMatrix.multiply(ret,matrixesToMultiply.get(i));
		}
		ret.normaliceRows();
		return ret;
	}
	

	
	/**
	 * 
	 * @param path
	 * @param aux If path finish with E and the Node.Type of before is Paper, we dont know what matrix chose. We need to know the full path
	 * @return
	 * @throws PathException 
	 */
	
	private ArrayList<SparseMatrix> getMatrixesToMultiply(ArrayList<Node.Type> path,ArrayList<Node.Type> aux) throws PathException {
//		System.out.println("Getting matrixes to multiply");
		ArrayList<SparseMatrix> matrixesToMultiply = new ArrayList<SparseMatrix>();
		ArrayList<WhatMatrix> whatMatrixes = getPairs(path, aux);
//		System.out.println(whatMatrixes);
		for (int i = 0; i < whatMatrixes.size(); ++i) {
			WhatMatrix w = whatMatrixes.get(i);
			switch (w.pathType) {
			case Author2Paper:
				if (!paperAuthor) { // init paper2Author
					this.author2paper = new SparseMatrix(graph.getMatrixAuthor());
					this.paper2author = new SparseMatrix(this.author2paper);
					
//					this.author2paper.normaliceRows();
					this.paper2author.transpose();
//					this.paper2author.normaliceRows();
					
					this.paperAuthor = true;
				}
				if (w.transposeMatrix) matrixesToMultiply.add(paper2author);
				else matrixesToMultiply.add(author2paper);
				break;
			case Conf2Paper:
				if (!paperConf) { // init paper2Author
					this.conf2paper = new SparseMatrix(graph.getMatrixConf());
					this.paper2conf = new SparseMatrix(this.conf2paper);
					
//					this.conf2paper.normaliceRows();
					this.paper2conf.transpose();
//					this.paper2conf.normaliceRows();
					
					this.paperConf = true;
				}
				if (w.transposeMatrix) matrixesToMultiply.add(paper2conf);
				else matrixesToMultiply.add(conf2paper);
				break;
			case Term2Paper:
				if (!paperTerm) { // init paper2Author
					this.term2paper = new SparseMatrix(graph.getMatrixTerm());
					this.paper2term = new SparseMatrix(term2paper);
					
//					this.term2paper.normaliceRows();
					this.paper2term.transpose();
//					this.paper2term.normaliceRows();
					
					this.paperTerm = true;
				}
				if (w.transposeMatrix) matrixesToMultiply.add(paper2term);
				else matrixesToMultiply.add(term2paper);
				break;
			case Author2Mid:
			case Paper2MidAut:
				if (!authorMid) {
					Partite p = new Partite(new SparseMatrix(graph.getMatrixAuthor())); // new SparseMatrix hace una traduccion innecesaria(si el grafo tuviera sparse matrix) x3
					this.author2mid = p.leftToMid;
//					this.author2mid.normaliceRows();
					
					this.paper2authorMid = p.midToRight;
					this.paper2authorMid.transpose();
//					this.paper2authorMid.normaliceRows();
					
					authorMid = true;
				}
				if (w.pathType == PathTypes.Author2Mid) matrixesToMultiply.add(author2mid);
				else matrixesToMultiply.add(paper2authorMid);
				break;
			case Conf2Mid:
			case Paper2MidConf:
				if (!confMid) {
					Partite p =  new Partite(new SparseMatrix(graph.getMatrixConf()));
					this.conf2mid = p.leftToMid;
//					this.conf2mid.normaliceRows();
					
					this.paper2confMid = p.midToRight;
					this.paper2confMid.transpose();
//					this.paper2confMid.normaliceRows();
					
					confMid = true;
				}
				if (w.pathType == PathTypes.Conf2Mid) matrixesToMultiply.add(conf2mid);
				else matrixesToMultiply.add(paper2confMid);
				break;
			case Term2Mid:
			case Paper2MidTerm:
				if (!termMid) {
					Partite p = new Partite(new SparseMatrix(graph.getMatrixTerm()));
					this.term2mid = p.leftToMid;
//					this.term2mid.normaliceRows();
					
					this.paper2termMid = p.midToRight;
					this.paper2termMid.transpose();
//					this.paper2termMid.normaliceRows();
					termMid = true;
				}
				if (w.pathType == PathTypes.Term2Mid) matrixesToMultiply.add(term2mid);
				else matrixesToMultiply.add(paper2termMid);
				break;
			}
		}
//		System.out.println(matrixesToMultiply);
		return matrixesToMultiply;
	}

	private ArrayList<WhatMatrix> getPairs(ArrayList<Node.Type> path, ArrayList<Node.Type> aux) throws PathException {
		ArrayList<WhatMatrix> ret = new ArrayList<WhatMatrix>();
		for (int i = 1; i < path.size(); ++i) {
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
							throw new PathException("The path have a midElement linked to two papers or to another midElement");
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
							throw new PathException("The path have a midElement linked to another midElement");
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
						throw new PathException("The path have two papers together");
				}
			}
		}

		return ret;
	}
}
