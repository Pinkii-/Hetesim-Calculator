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
 * @author Eduard Maestro
 */
public class Path implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Node.Type> contingut;
    private String descripcioPath;
    private String nomPath;
    
    //Pre: Cert.
    //Post: Es crea un path buit.
    public Path() {
        nomPath = new String();
        descripcioPath = new String();
        contingut = new ArrayList<>();
    }
    
    //Pre: Cert.
    //Post: Es crea un path amb totes les dades d'entrada.
    public Path(String descripcio, String nom, String path) {
        nomPath = new String();
        descripcioPath = new String();
        contingut = new ArrayList<>();
        
        nomPath = path;
        descripcioPath = descripcio;
        contingut = stringapath(path);
    }
    
    
    
    //Pre: 
    //Post:
    private ArrayList<Node.Type> stringapath(String path) {
        ArrayList<Node.Type> noucontingut;
        noucontingut = new ArrayList<>();
        for (int i = 0; i < path.length(); ++i) {
           // Autor A, paper P, term T, conference C; Paper al medio;
           switch (path.charAt(i)) {
               case 'A':
                   noucontingut.add(Node.Type.Autor);
                   break;
               case 'C':
                   noucontingut.add(Node.Type.Conferencia);
                   break;
               case 'P':
                   noucontingut.add(Node.Type.Paper);
                   break;
               case 'T':
                   noucontingut.add(Node.Type.Terme);
                   break;
               default:
                   break;
           }
       }
        return noucontingut;
    }
    
    //Pre: Cert.
    //Post: Especifica el contingut del path.
    public void setContingut(String contingut) {
        this.contingut.clear(); // Vacio/limpio el arraylist;
        this.contingut = stringapath(contingut);
        
    }
    
    
    //Pre: Cert.
    //Post: Especifica la descripcio del path.
    public void setDescripcio(String contingut) {
        // Talvez deberia ser string descripció;
        this.descripcioPath = contingut;
    }
    
    //Pre: Cert.
    //Post: Especifica el nom del path.
    public void setNom(String nom){
        this.nomPath = nom;
    }
    
    //Pre: El contingut no pot estar buit.
    //Post: Retorna el contingut.
    public ArrayList<Node.Type> getContingut() {
        return this.contingut;
    }
    
    //Pre: Cert.
    //Post: Retorna el tamany del path(length).
    public Integer getLength() {
        return this.contingut.size();
    }
    
    //Pre: La descripcio no pot estar buit.
    //Post: Retorna la descripcio del path.
    public String getDescripcio() {
        return this.descripcioPath;
    }
    
    //Pre: El nom no pot estar buit.
    //Post: Retorna el nom del path.
    public String getNom() {
        return this.nomPath;
    }
    
    //Pre: Contingut no es buit.
    //Post: Retorna el contigut del path a l'inreves.
    public ArrayList<Node.Type> reversePath() {
        ArrayList<Node.Type> reversed = new ArrayList<>();
        int n;
        // Dudo de si hay que hacer new o no;
        n = this.contingut.size();
        for (int i = n-1; i >= 0 ; --i)
        {   // Si el vector tiene 4 valores, entonces n-1 =  3, que es la
            // cuarta posición del vector. cuando i = 0, entrará y hará
            // la cuarta iteración.
            reversed.add(this.contingut.get(i));
        }
        return reversed;
    }
    
    //Pre: Contingut no és buit.
    //Post: retorn la primera i la segona meitat del contigut del path.
    //Si lenght(contingut) par, afegeix al final de begin y al principi
    //de end un element = Node.Type.MidElemnt. En el cas de que sigui imparell,
    //l’ultim element de la primera meitat será el primer element de la segona meitat.
    //En qualsevol cas, la primera meitat tindrà el mateix tamany que la segona meitat.
    public Pair<ArrayList<Node.Type>, ArrayList<Node.Type>> getPath() {
        ArrayList<Node.Type> firstarray = new ArrayList<>();
        ArrayList<Node.Type> secondarray = new ArrayList<>();
        
        int n = this.contingut.size();
        // Comprobamos la paridad de this.contingut;
        
        if (this.contingut.size()%2 == 0) {
            // Si path tiene elementos pares, debemos añadir el elemento  neutro
            // tanto al final del primer minipath, como al principio del primero.
            for (int i = 0; i < n/2; ++i) {
                firstarray.add(this.contingut.get(i));
                // Llenamos el primer minipath;
            }
            firstarray.add(Node.Type.MidElement);
            secondarray.add(Node.Type.MidElement);
            // Añadimos el midelement al final del primer minipath
            // y al principio del segundo. Llenamos el segundo.
            for (int i = n/2; i < n; ++i) {
                secondarray.add(this.contingut.get(i));
            }
        }
        else {
            //El path tiene elementos impares, duplico el del medio;
            for (int i = 0; i <= n/2; ++i) {
                firstarray.add(this.contingut.get(i));
                // Llenamos el primer minipath;
            }
            // Llenamos el segundo.
            for (int i = n/2; i < n; ++i) {
                secondarray.add(this.contingut.get(i));
            }
            
        }
        Pair<ArrayList<Node.Type>, ArrayList<Node.Type>> doblepath;
        doblepath = new Pair<> (firstarray,secondarray);
        return doblepath;
    }
        
}
