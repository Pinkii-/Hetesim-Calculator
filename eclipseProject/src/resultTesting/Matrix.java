package resultTesting;
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
	private int rows, cols;


	public Matrix() {	
		m = new ArrayList<ArrayList<Float>>();
		valDefault = 0.0f; 
		cols = 5;
		rows = 1;
	}

	public Integer getNCols(){
		return cols;
	}

	public Integer getNRows(){
		return rows;
	}

	public Float getValue(int id1, int id2){
		return (5.f*id1*id2)/100.f;
	}

	public void afegirArc(int id1, int id2) {
		m.get(id1).set(id2,1.0f);
	}
	
	public ArrayList<Float> getRow(int i){
		return m.get(i);
	}


	public ArrayList<Float> getCol(int j){
		ArrayList<Float> resultado = new ArrayList<Float>();
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
		};
		cols = c;
		rows = f;
	}


}
