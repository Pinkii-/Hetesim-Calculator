package Dominio;
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
    private ArrayList<Node> autors;
    private ArrayList<Node> conferencies;
    private ArrayList<Node> papers;
    private ArrayList<Node> termes;
    
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
    
    public boolean existsNode(Node n) {
        switch(n.getTipus()) {
            case Autor: if(autors.contains(n)) return true;
            case Conferencia: if(conferencies.contains(n)) return true;
            case Paper: if(papers.contains(n)) return true;
            case Terme: if (termes.contains(n)) return true;
            default: return false;
        }
    }
    
    public Node getNode(int index, Node.Type tipus) {
        switch(tipus) {
            case Autor: return autors.get(index);
            case Conferencia: return conferencies.get(index);
            case Paper: return papers.get(index);
            case Terme: return termes.get(index);
            default: return null;
        }
    }
    
    public boolean existsArc(Node a, Node b) {
        int i, j;
        j = papers.indexOf(b);
        switch(a.getTipus()) {
            case Autor: i = autors.indexOf(a);
                return autorPaper.existeixArc(i,j);
            case Conferencia: i = conferencies.indexOf(a);
                return confPaper.existeixArc(i,j);
            case Terme: i = termes.indexOf(a);
                return temaPaper.existeixArc(i,j);
            default: return false;
        }
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
            default: break;
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
    
    public void setArc(int indexpaper, int index2, Node.Type t) {
        switch(t) {
            case Autor: autorPaper.afegirArc(index2, indexpaper);
                break;
            case Conferencia: confPaper.afegirArc(index2, indexpaper); 
                break;
            case Terme: temaPaper.afegirArc(index2, indexpaper);
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


