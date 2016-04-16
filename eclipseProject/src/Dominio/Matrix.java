package Dominio;

//import java.util.ArrayList;
//
//public class Matrix {
//	private ArrayList<ArrayList<Float>>  m;
//	private final Float valDefault = 0.f;
//	
//	//Pre: Cert.
//	//Post: s'inicialitza m.
//	public Matrix() {
//		m = new ArrayList<ArrayList<Float>>();
//	}
//	//Pre: id1 i id2 identifican a nodes que han estat afegits anteriorment
//	//Post: En la posició identificada per id1 i id2, s'assignarà un 1.
//	public void afegirArc(Integer id1, Integer id2) {
//		
//	}
//
//	//Pre: Existeixen els nodes "id1" i "id2".
//	//Post: Retorna un booleà que indica si existeix la relació entre "id1" i "id2".
//	public Boolean existeixArc(Integer id1, Integer id2) {
//		return null;
//	}
//
//	//Pre: Cert.
//	//Post:S'ha assignat un 0 al la relacio.  
//	public void esborrarArc(Integer id1, Integer id2) {
//		
//	}
//
//	//Pre: Cert.
//	//Post: Retorna una matriu que seria la matriu m trasposada, a més intercanvia pos1 i pos2.
//	public Matrix trasposarMatriu() {
//		return null;
//	}
//
//	//Pre: Cert.
//	//Post: Retorna el número de columnes de la matriu.
//	public Integer getNCols() {
//		return null;
//	}
//
//	//Pre: Cert
//	//Post: Retorna el número de files de la matriu.
//	public Integer getNRows() {
//		return null;
//	}
//
//	//Pre: El nodes n1 ha estat afegit a la matriu amb afegirNodeP1.
////		El node n2 ha estat afegit a la matriu amb afegirNodeP2.
//	//Post: Retorna el float de la posició indicada.
//	public Float getValue(Node n1, Node n2) {
//		return null;
//	}
//
//	//Pre: La posició [id1][id2] de la matriu existeix.
//	//Post: Retorna el float de la posició indicada.
//	public Float getValue(Integer id1, Integer id2) {
//		return null;
//	}
//
//	//Pre: La posició corresponent al index existeix
//	//Post: Retorna la fila en la què es troba l'element
//	public ArrayList<Float> getRow(Integer i) {
//		return null;
//	}
//
//	//Pre: La posició corresponent al index existeix
//	//Post: Retorna la columna número j
//	public ArrayList<Float> getCol(Integer j) {
//		return null;
//	}
//
//
//
//	//Pre: El node ha estat afegit a la matriu amb afegirNodeP2.
//	//Post: Retorna la columna del map a la que està associada el node a.
//	public ArrayList<Float> getCol(Node a) {
//		return null;
//	}
//
//	//Pre: El node ha estat afegit a la matriu amb afegirNodeP1.
//	//Post: Retorna la fila del map a la que està associada el node a.
//	public ArrayList<Float> getRow(Node a) {
//		return null;
//	}
//	//Pre:	Cert.
//	//Post:  Especifica el tamany d'una matriu num files = f, num columnes = c, inicialitzant els valors a valDefault.
//	public void setTamany(Integer f,Integer c) {
//		
//	}
//	//Pre:	Cert.
//	//Post:  Copia el tamany de la matriu m, a més copia pos1 i pos2.
//	public void copiaTamany(Matrix m) {
//		
//	}
//
//}
//


import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alejandro Ibáñez
 */
public class Matrix {
    
	private ArrayList<ArrayList<Float>>  m;
	private final float valDefault;
	int rows,cols;
	
	 
	public Matrix() {
	    m = new ArrayList<ArrayList<Float>>();
	    valDefault = 0.0f;
	}
	
	public void afegirArc(int id1, int id2) {
	     m.get(id1).set(id2,1.0f);
	}
	    
	    
	public Boolean existeixArc(int id1, int id2){
	    return(1.0f == m.get(id1).get(id2));
	}
	
	public void esborrarArc(int id1, int id2){
	   
	     m.get(id1).set(id2, valDefault);
	}
	
	public void addNodeCol() {
	cols += 1;
	for (int i = 0; i < rows; ++i) {
	    int size = m.get(i).size();
	        for (int n = size-1; n < cols; ++n) {
	            m.get(i).add(valDefault);
	        }
	    }
	}
	
	public void addNodeRow() {
	rows += 1;
	ArrayList f = new ArrayList<Float>(cols);
	    for (int i = 0; i < cols; i++) f.add(valDefault);
	    m.add(f);
	}
	
	public Matrix trasposarMatriu(){
	    Matrix t = new Matrix();
	    t.setTamany(getNCols(),getNRows());
	    for (int i = 0; i < getNCols(); i++) {
	        for (int j = 0; j < getNRows(); j++) {
	            t.getRow(j).set(i, m.get(i).get(j));
	        }
	    }
	    return t;
	}
	
	public Integer getNCols(){
	    return m.get(0).size();
	}
	
	
	public Integer getNRows(){
	    return m.size();
	}
	
	public Float getValue(int id1, int id2){
	    return m.get(id1).get(id2);
	}
	
	
	public ArrayList<Float> getRow(int i){
	    if (i <= m.size()) {
	        return m.get(i);
	    }
	    ArrayList<Float> n = new ArrayList<Float>();
	    return n;
	}
	
	
	public ArrayList<Float> getCol(int j){
	    ArrayList resultado = new ArrayList<Float>();
	    for (int i = 0; i < m.size(); i++) {
	        resultado.add(m.get(j));
	    }
	    return resultado;
	}
	
	public void setTamany(int f,int c){
	    m = new ArrayList< ArrayList<Float> >(f);
	    for (int i = 0; i <  f; i++) {
	        m.add(new ArrayList<Float>(c));
	        for (int j = 0; j < c; j++) {
	            m.get(i).add(valDefault);
	        }
	    }
	}
	
	public void copiaTamany(Matrix c) {
	    while (c.rows >= rows) {
	        addNodeRow();
	        ++rows;
	    }
	    while(c.cols >= cols) {
	        addNodeCol();
	        ++cols;
	    }
	}



}

