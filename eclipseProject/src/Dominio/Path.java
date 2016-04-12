package Dominio;

import java.util.ArrayList;

public class Path {
	private ArrayList<Node.Type> contingut;
	private String descripcio;
	private String nom;
	//Pre: Cert.
	//Post: Es crea un path buit.
	public Path() {
		
	}

	//Pre: Cert.
	//Post: Especifica el contingut del path.
	public void setContingut(String contingut) {
		
	}

	//Pre: Cert.
	//Post: Especifica la descripcio del path.
	public void setDescripcio(String contingut) {
		
	}


	//Pre: Cert.
	//Post: Especifica el nom del path.
	public void setNom(String nom) {
		
	}

	//Pre: El contingut no pot estar buit.
	//Post: Retorna el contingut.
	public ArrayList<Node.Type> getContingut() {
		return null;
	}


	//Pre: Cert.
	//Post: Retorna el tamany del path(length)
	public Integer getLength() {
		return null;
	}


	//Pre: La descripcio no pot estar buit.
	//Post: Retorna la descripcio del path.
	public String getDescripcio() {
		return null;
	}

	//Pre: El nom no pot estar buit.
	//Post: Retorna el nom del path.
	public String getNom() {
		return null;
	}


	//Pre: Contingut no es buit.
	//Post: Retorna el contigut del path a l'inreves.
	public ArrayList<Node.Type> reversePath() {
		return null;
	}
	//Pre: Contingut no és buit.
	//Post: retorn la primera i la segona meitat del contigut del path. Si lenght(contingut) impar, afegeix al final de begin y al principi de end un element = Node.Type.MidElemnt. En el cas de que sigui parell, l’ultim element de la primera meitat será el primer element de la segona meitat. En qualsevol cas, la primera meitat tindrà el mateix tamany que la segona meitat.
	public Pair<ArrayList<Node.Type>, ArrayList<Node.Type>> getPath() {
		return null;
	}

}
