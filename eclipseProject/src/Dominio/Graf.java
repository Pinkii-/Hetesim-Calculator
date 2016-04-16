package Dominio;

//import java.util.ArrayList;
//
//public class Graf {
//	String nom;
//	Integer id;
//	private Matrix autorPaper;
//	private Matrix temaPaper;
//	private Matrix confPaper;
//	private ArrayList<Node> autors;
//	private ArrayList<Node> conferencies;
//	private ArrayList<Node> papers;
//	private ArrayList<Node> terms;
//
//	//Pre: Cert.
//	//Post: Es crea un graf buit amb nom "nom".
//	public Graf(String nom, Integer id){
//		
//	}
//
//	//Pre: Cert.
//	//Post:  Es crea un graf buit sense nom
//	public Graf() {
//		
//	}
//
//	//Pre: El graf ha de tenir nom.
//	//Post: Retorna el nom del graf.
//	public String getNom() {
//		return null;
//	}
//
//	//Pre: Cert.
//	//Post: El nom del graf passa a ser s.		
//	public void setNom(String s) {
//		
//	}
//
//	//Pre: Existeix el graf i el node a afegir no existeix.
//	//Post: Es crea un node amb id "id" i nom "nom" i tipus "tipus". S'afegeix el node a la Matrix i al ArrayList pertanyent. Es retorna la posició del node al ArrayList.
//	public Integer addNode(Node.Type tipus, Integer id, String nom) {
//		return null;
//	}
//	//Pre: L'index està comprés entre els valors 0 i ArrayList.size() - 1 del arraylist corresponent.
//	//Post: S'afegeix el label al node que es troba a la posició "index" del ArrayList corresponent al tipus "tipus"
//	public void addLabel(Integer index, Node.Label label, Node.Type tipus) {
//		
//	}
//	//Pre:Cert.
//	//Post:Retorna un booleà que indica si existeix el node "n".
//	private Boolean existsNode(Node n) {
//		return null;
//	}
//
//	//Pre:Cert.
//	//Post:Retorna un booleà que indica si existeix el node amb idNode == id i type == type.
//	private Boolean existsNode(Integer id, Node.Type type) {
//		return null;
//	}
//
//	//Pre:la id del node existeix es menor que el size de el arrayList corresponent a type.
//	//Post:Retorna el node amb idNode == id i type == type.
//	public Node getNode(Integer id, Node.Type type) {
//		return null;
//	}
//
//	//Pre: Existeixen els nodes "a" i "b".
//	//Post: Retorna un booleà que indica si existeix la relació entre "a" i "b".
//	private Boolean existsArc(Node a, Node b) {
//		return null;
//	}
//
//	//Pre:El node ha d'existir.
//	//Post:El node ha estat esborrat. Es mantindrà la consistència de les dades.
//	public void deleteNode(Node a) {
//		
//	}
//
//	//Pre:L'arc ha d'existir.
//	//Post:L'arc ha estat esborrat. Es mantindrà la consistència de les dades.
//	public void deleteArc(Node a, Node b) {
//		
//	}
//
//
//	//Pre: La id del node “a” existeix al ArrayList corresponent al tipus a.tipus.
//	//Post: Si existeix un node amb el mateix identificador el sobreescriu.
//	public void setNode(Node a) {
//		
//	}
//	//Pre: El Node a y el Node b existeixen en una de les Matrix.
//	//Post: Es crea un arc entre el paper "idpaper" i el node tipus "t" amb id "id2" si no existeix, si ja existeix el sobreescriu .
//	public void setArc(Integer idpaper, Integer id2, Node.Type t){
//		
//	}
//	//Pre: Res.
//	//Post: Retorna autorPaper
//	public Matrix getMatrixAuthor(){
//		return null;
//	}
//	//Pre: Res.
//	//Post: Retorna temaPaper
//	public Matrix getMatrixTerm(){
//		return null;
//	}
//	//Pre: Res.
//	//Post: Retorna confPaper
//	public Matrix getMatrixConf() {
//		return null;
//	}
//
//}
//

///////// Clase compartida

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chus
 */
public class Graf {
    
    //Atributos
    
    String nom;   
    int id;
    private Matrix autorPaper;
    private Matrix temaPaper;
    private Matrix confPaper;
    public ArrayList<Node> autors;
    public ArrayList<Node> conferencies;
    public ArrayList<Node> papers;
    public ArrayList<Node> termes;
    
    //Metodos
    
    public Graf(String nom, int id) {
        this.nom = nom;
        this.id = id;
        autorPaper = new Matrix();
        temaPaper = new Matrix();
        confPaper = new Matrix();
        autors = new ArrayList<Node>(); 
        conferencies = new ArrayList<Node>();
        papers = new ArrayList<Node>();
        termes = new ArrayList<Node>();
    }   
    
    public Graf() {
        autorPaper = new Matrix();
        temaPaper = new Matrix();
        confPaper = new Matrix();
        autors = new ArrayList<Node>(); 
        conferencies = new ArrayList<Node>();
        papers = new ArrayList<Node>();
        termes = new ArrayList<Node>();
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String s) {
        this.nom = s;
    }
    
    public int addNode(Node.Type tipus, int id, String nom) {
        Node aux = new Node();
        int res;
        aux.initialize(tipus,id,nom);
        switch(tipus) {
            case Autor: 
                autors.add(aux);
                res = autors.size()-1;
                autorPaper.addNodeRow();
                break;
            case Conferencia:
                conferencies.add(aux);
                res = conferencies.size()-1;
                confPaper.addNodeRow();
                break;
            case Paper:
                papers.add(aux);
                res = papers.size()-1;
                autorPaper.addNodeCol();
                confPaper.addNodeCol();
                temaPaper.addNodeCol();
                break;
            case Terme:
                termes.add(aux);
                res = termes.size()-1;
                temaPaper.addNodeRow();
                break;
            default: res = -1;
                break;
        }
        return res;
    }
    
    public void addLabel(int index, Node.Label label, Node.Type tipus) {
        switch(tipus) {
            case Autor: autors.get(index).setLabel(label);
                break;
            case Conferencia: conferencies.get(index).setLabel(label);
                break;
            case Paper: papers.get(index).setLabel(label);
                break;
            default: break;
        }
    }
    
    private boolean existsNode(Node n) {
        switch(n.getTipus()) {
            case Autor: if(autors.contains(n)) return true;
            case Conferencia: if(conferencies.contains(n)) return true;
            case Paper: if(papers.contains(n)) return true;
            case Terme: if (termes.contains(n)) return true;
        }
        return false;
    }
    
    Node getNode(int id, Node.Type tipus) {
        switch(tipus) {
            case Autor: return autors.get(id);
            case Conferencia: return conferencies.get(id);
            case Paper: return papers.get(id);
            case Terme: return termes.get(id);
            default: break;
        }
        return null;
    }
    
    private boolean existsArc(Node a, Node b) {
        int i, j;
        j = papers.indexOf(b);
        switch(a.getTipus()) {
            case Autor: i = autors.indexOf(a);
                return autorPaper.existeixArc(i,j);
            case Conferencia: i = conferencies.indexOf(a);
                return confPaper.existeixArc(i,j);
            case Terme: i = termes.indexOf(a);
                return temaPaper.existeixArc(i,j);
            default: break;
        }
        return false;
    }
    
    public void deleteNode(Node a) {
        switch(a.getTipus()) {
            case Autor: if(autors.contains(a)) autors.remove(a);
                break;
            case Conferencia: if(conferencies.contains(a)) conferencies.remove(a); 
                break;
            case Paper: if(papers.contains(a)) papers.remove(a);
                break;
            case Terme: if (termes.contains(a)) termes.remove(a);
                break; 
        }
    }
    
    public void deleteArc(Node a, Node b) {
        int i, j;
        j = papers.indexOf(b);
        switch(a.getTipus()) {
            case Autor: 
                i = autors.indexOf(a);
                autorPaper.esborrarArc(i,j);
                break;
            case Conferencia: 
                i = conferencies.indexOf(a);
                confPaper.esborrarArc(i,j);
                break;
            case Terme: 
                i = termes.indexOf(a);
                temaPaper.esborrarArc(i,j);
                break;
            default: break;
        }
    }
    
    public void setNode(Node a, String s) {
        switch(a.getTipus()) {
            case Autor: 
                autors.get(autors.indexOf(a)).setNom(s);
                break;
            case Conferencia: 
                conferencies.get(conferencies.indexOf(a)).setNom(s);
                break;
            case Paper:
                papers.get(papers.indexOf(a)).setNom(s);
                break;
            case Terme: 
                termes.get(termes.indexOf(a)).setNom(s);
                break;
            default: break;
        }
    }
    
    public void setArc(Integer idpaper, Integer id2, Node.Type t) {
        switch(t) {
            case Autor: autorPaper.afegirArc(id2, idpaper);
                break;
            case Conferencia: confPaper.afegirArc(id2, idpaper); 
                break;
            case Terme: temaPaper.afegirArc(id2, idpaper);
                break;
            default: break;
        }
    }
    
    public Matrix getMatrixAuthor() {
        return autorPaper;
    }
    
    public Matrix getMatrixTerm() {
        return temaPaper;
    }
    
    public Matrix getMatrixConf() {
        return confPaper;
    }
}
 




