package Dominio;

import java.io.Serializable;

//public class Node {
//	Type tipus;
//	Integer id;
//	String nom;
//	Label label;
//
//	public enum Type { 
//		Autor, Conferencia, Paper, Terme, MidElement
//	}
//
//	public enum Label {
//	Database, DataMining, AI, InformationRetrieval
//	}
//
//	//Pre: tipus es es diferent de MidElement i no null
//
//	//Post: Es declara i s'inicialitza un node
//
//	Node(Type tipus, Integer id, String nom) {
//		
//	}
//		
//
//	//Pre: Cert
//
//	//Post: Retorna el tipus del node
//
//	public Type getTipus() {
//		return null;
//	}
//
//			
//	//Pre: Cert
//
//	//Post: Retorna el id del node
//
//	public Integer getId() {
//		return null;
//	}
//		
//
//	//Pre: Cert
//
//	//Post: Retorna el nom del node
//
//	public String getNom() {
//		return null;
//	}
//
//				
//	//Pre: Cert
//
//	//Post: El id del node pasa a ser el parametre "id"
//
//	public void setId(Integer id) {
//		
//	}
//
//
//	//Pre: Cert
//
//	//Post: El nom del node pasa a ser el parametre "nom"
//			
//	public void setNom(String nom) {
//
//	}
//
//	//Pre: Cert
//
//	//Post: Retorna la label del node
//
//	public Label getLabel() {
//		return null;
//	}
//
//	//Pre: Cert
//
//	//Post: This.label = label.
//
//	public void setLabel(Label label){
//		
//	}
//
//}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alejandro Ibáñez
 */
public class Node implements Serializable{
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
	
	public Label getLabel() {
	    return this.label;
	}
	
	public void setLabel(Label label) {
	    this.label = label;
	}

}