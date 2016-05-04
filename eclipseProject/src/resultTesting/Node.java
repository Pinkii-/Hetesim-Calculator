package resultTesting;

public class Node {
	Type tipus;
	
	Integer id;
	
	String nom;
	
	public enum Type { 
	    Autor, Conferencia, Paper, Terme, MidElement
	}
	
	public enum Label {
	Database, DataMining, AI, InformationRetrieval
	}
	
	public Node() {
	}
	
	public void initialize(Type tipus, Integer id, String nom) {
	    this.tipus = tipus;
	    this.id = id;
	    this.nom = nom;
	}
	
	public Type getTipus() {
	    return this.tipus;
	}
	
	public Integer getId() {
	    return this.id;
	}
	
	public String getNom() {
	    return this.nom;
	}
	
	public void setId(Integer id) {
	    this.id = id;
	} 
	
	public void setNom(String nom) {
	    this.nom = nom;
	}

}