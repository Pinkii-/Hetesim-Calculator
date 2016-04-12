package Dominio;

import java.util.ArrayList;

public class Matrix {
	private ArrayList<ArrayList<Float>>  m;
	private final Float valDefault = 0.f;
	
	//Pre: Cert.
	//Post: s'inicialitza m.
	public Matrix() {
		m = new ArrayList<ArrayList<Float>>();
	}
	//Pre: id1 i id2 identifican a nodes que han estat afegits anteriorment
	//Post: En la posició identificada per id1 i id2, s'assignarà un 1.
	public void afegirArc(Integer id1, Integer id2) {
		
	}

	//Pre: Existeixen els nodes "id1" i "id2".
	//Post: Retorna un booleà que indica si existeix la relació entre "id1" i "id2".
	public Boolean existeixArc(Integer id1, Integer id2) {
		return null;
	}

	//Pre: Cert.
	//Post:S'ha assignat un 0 al la relacio.  
	public void esborrarArc(Integer id1, Integer id2) {
		
	}

	//Pre: Cert.
	//Post: Retorna una matriu que seria la matriu m trasposada, a més intercanvia pos1 i pos2.
	public Matrix trasposarMatriu() {
		return null;
	}

	//Pre: Cert.
	//Post: Retorna el número de columnes de la matriu.
	public Integer getNCols() {
		return null;
	}

	//Pre: Cert
	//Post: Retorna el número de files de la matriu.
	public Integer getNRows() {
		return null;
	}

	//Pre: El nodes n1 ha estat afegit a la matriu amb afegirNodeP1.
//		El node n2 ha estat afegit a la matriu amb afegirNodeP2.
	//Post: Retorna el float de la posició indicada.
	public Float getValue(Node n1, Node n2) {
		return null;
	}

	//Pre: La posició [id1][id2] de la matriu existeix.
	//Post: Retorna el float de la posició indicada.
	public Float getValue(Integer id1, Integer id2) {
		return null;
	}

	//Pre: La posició corresponent al index existeix
	//Post: Retorna la fila en la què es troba l'element
	public ArrayList<Float> getRow(Integer i) {
		return null;
	}

	//Pre: La posició corresponent al index existeix
	//Post: Retorna la columna número j
	public ArrayList<Float> getCol(Integer j) {
		return null;
	}



	//Pre: El node ha estat afegit a la matriu amb afegirNodeP2.
	//Post: Retorna la columna del map a la que està associada el node a.
	public ArrayList<Float> getCol(Node a) {
		return null;
	}

	//Pre: El node ha estat afegit a la matriu amb afegirNodeP1.
	//Post: Retorna la fila del map a la que està associada el node a.
	public ArrayList<Float> getRow(Node a) {
		return null;
	}
	//Pre:	Cert.
	//Post:  Especifica el tamany d'una matriu num files = f, num columnes = c, inicialitzant els valors a valDefault.
	public void setTamany(Integer f,Integer c) {
		
	}
	//Pre:	Cert.
	//Post:  Copia el tamany de la matriu m, a més copia pos1 i pos2.
	public void copiaTamany(Matrix m) {
		
	}

}

