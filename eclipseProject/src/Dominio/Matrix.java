package Dominio;
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
	ArrayList<Float> f = new ArrayList<Float>(cols);
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
	    if (i < m.size()) {
	        return m.get(i);
	    }
	    return null;
	}
	
	
	public ArrayList<Float> getCol(int j){
	    ArrayList<Float> resultado = new ArrayList<Float>();
	    for (int i = 0; i < m.size(); i++) {
	        resultado.add(m.get(i).get(j));
	    }
	    if (resultado.size() > 0)return resultado;
	    return null;
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

