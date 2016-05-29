package Dominio;

import java.io.Serializable;
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
public class Matrix implements Serializable{
    
private ArrayList<ArrayList<Float>>  m;
private final float valDefault = 0.0f;
private int rows,cols;

 
public Matrix() {
    m = new ArrayList<ArrayList<Float>>();
    rows = cols = 0;
}

public void afegirArc(int id1, int id2) {
     m.get(id1).set(id2,1.0f);
}
    
    
public Boolean existeixArc(int id1, int id2){
    return(m.get(id1).get(id2) == 1.0f);
}

public void esborrarArc(int id1, int id2){
     m.get(id1).set(id2, valDefault);
}

public void addNodeCol() {
cols += 1;
for (int i = 0; i < rows; ++i) {
    int size = m.get(i).size();
        for (int n = size; n < cols; ++n) {
            m.get(i).add(valDefault);
        }
    }
}

public void addNodeRow() {
rows += 1;
ArrayList<Float> f  = new ArrayList<Float>(cols);
    for (int i = 0; i < cols; i++) f.add(valDefault);
    m.add(f);
}

public Matrix trasposarMatriu(){
    Matrix t = new Matrix();
    t.setTamany(getNCols(),getNRows());
    for (int i = 0; i < getNCols(); i++) {
        for (int j = 0; j < getNRows(); j++) {
            t.getRow(i).set(j, m.get(j).get(i));
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

public float getValue(int id1, int id2){
    return (m.get(id1).get(id2));
}

public ArrayList<Float> getRow(int i){
    return m.get(i);
}


public ArrayList<Float> getCol(int j){
    ArrayList resultado = new ArrayList<Float>();
    for (int i = 0; i < m.size(); i++) {
        resultado.add(m.get(i).get(j));
    }
    return resultado;
}

public void setTamany(int f,int c){
    m = new ArrayList< ArrayList<Float> >(f);
    for (int i = 0; i <  f; i++) {
        m.add(new ArrayList<Float>(c));
        for (int j = 0; j < c; j++) {
            m.get(i).add(j,valDefault);
        }
    }
    rows = f;
    cols = c;
}

public void copiaTamany(Matrix c) {
    while (c.rows > rows) {
        addNodeRow();
    }
    while(c.cols > cols) {
        addNodeCol();
    }
}

public void setRelevance(int id1,int id2,float r) {
    m.get(id1).set(id2,r);
}


public void borraFila(int index) {
    for (int j = 0; j < cols; j++) {
        m.get(index).set(j, valDefault);
    }
}

public void borraCol(int index) {
    for (int i = 0; i < rows; i++) {
        m.get(i).set(index,valDefault);
    }
}

}

