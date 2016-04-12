package Dominio;

public class Node {
	Type tipus;
	Integer id;
	String nom;
	Label label;

	public enum Type { 
		Autor, Conferencia, Paper, Terme, MidElement
	}

	public enum Label {
	Database, DataMining, AI, InformationRetrieval
	}

	//Pre: tipus es es diferent de MidElement i no null

	//Post: Es declara i s'inicialitza un node

	Node(Type tipus, Integer id, String nom) {
		
	}
		

	//Pre: Cert

	//Post: Retorna el tipus del node

	public Type getTipus() {
		return null;
	}

			
	//Pre: Cert

	//Post: Retorna el id del node

	public Integer getId() {
		return null;
	}
		

	//Pre: Cert

	//Post: Retorna el nom del node

	public String getNom() {
		return null;
	}

				
	//Pre: Cert

	//Post: El id del node pasa a ser el parametre "id"

	public void setId(Integer id) {
		
	}


	//Pre: Cert

	//Post: El nom del node pasa a ser el parametre "nom"
			
	public void setNom(String nom) {

	}

	//Pre: Cert

	//Post: Retorna la label del node

	public Label getLabel() {
		return null;
	}

	//Pre: Cert

	//Post: This.label = label.

	public void setLabel(Label label){
		
	}

}
