package Dominio;

import java.util.ArrayList;

public class Graf {
	String nom;
	Integer id;
	private Matrix autorPaper;
	private Matrix temaPaper;
	private Matrix confPaper;
	private ArrayList<Node> autors;
	private ArrayList<Node> conferencies;
	private ArrayList<Node> papers;
	private ArrayList<Node> terms;

	//Pre: Cert.
	//Post: Es crea un graf buit amb nom "nom".
	public Graf(String nom, Integer id){
		
	}

	//Pre: Cert.
	//Post:  Es crea un graf buit sense nom
	public Graf() {
		
	}

	//Pre: El graf ha de tenir nom.
	//Post: Retorna el nom del graf.
	public String getNom() {
		return null;
	}

	//Pre: Cert.
	//Post: El nom del graf passa a ser s.		
	public void setNom(String s) {
		
	}

	//Pre: Existeix el graf i el node a afegir no existeix.
	//Post: Es crea un node amb id "id" i nom "nom" i tipus "tipus". S'afegeix el node a la Matrix i al ArrayList pertanyent. Es retorna la posició del node al ArrayList.
	public Integer addNode(Node.Type tipus, Integer id, String nom) {
		return null;
	}
	//Pre: L'index està comprés entre els valors 0 i ArrayList.size() - 1 del arraylist corresponent.
	//Post: S'afegeix el label al node que es troba a la posició "index" del ArrayList corresponent al tipus "tipus"
	public void addLabel(Integer index, Node.Label label, Node.Type tipus) {
		
	}
	//Pre:Cert.
	//Post:Retorna un booleà que indica si existeix el node "n".
	private Boolean existsNode(Node n) {
		return null;
	}

	//Pre:Cert.
	//Post:Retorna un booleà que indica si existeix el node amb idNode == id i type == type.
	private Boolean existsNode(Integer id, Node.Type type) {
		return null;
	}

	//Pre:la id del node existeix es menor que el size de el arrayList corresponent a type.
	//Post:Retorna el node amb idNode == id i type == type.
	private Node getNode(Integer id, Node.Type type) {
		return null;
	}

	//Pre: Existeixen els nodes "a" i "b".
	//Post: Retorna un booleà que indica si existeix la relació entre "a" i "b".
	private Boolean existsArc(Node a, Node b) {
		return null;
	}

	//Pre:El node ha d'existir.
	//Post:El node ha estat esborrat. Es mantindrà la consistència de les dades.
	public void deleteNode(Node a) {
		
	}

	//Pre:L'arc ha d'existir.
	//Post:L'arc ha estat esborrat. Es mantindrà la consistència de les dades.
	public void deleteArc(Node a, Node b) {
		
	}


	//Pre: La id del node “a” existeix al ArrayList corresponent al tipus a.tipus.
	//Post: Si existeix un node amb el mateix identificador el sobreescriu.
	public void setNode(Node a) {
		
	}
	//Pre: El Node a y el Node b existeixen en una de les Matrix.
	//Post: Es crea un arc entre el paper "idpaper" i el node tipus "t" amb id "id2" si no existeix, si ja existeix el sobreescriu .
	public void setArc(Integer idpaper, Integer id2, Node.Type t){
		
	}
	//Pre: Res.
	//Post: Retorna autorPaper
	public Matrix getMatrixAuthor(){
		return null;
	}
	//Pre: Res.
	//Post: Retorna temaPaper
	public Matrix getMatrixTerm(){
		return null;
	}
	//Pre: Res.
	//Post: Retorna confPaper
	public Matrix getMatrixConf() {
		return null;
	}

}








