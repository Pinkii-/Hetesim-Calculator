package Dominio;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Eduard Maestro
 */
public class Path implements Serializable{
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
        contingut = new ArrayList<Node.Type>();
    }
    
    //Pre: Cert.
    //Post: Especifica el contingut del path.
    public void setContingut(String contingut) {
        this.contingut.clear(); // Vacío/limpio el arraylist;
       for (int i = 0; i < contingut.length(); ++i) {
           // Autor A, paper P, term T, conference C; Paper al medio;
           switch (contingut.charAt(i)) {
               case 'A':
                   this.contingut.add(Node.Type.Autor);
                   break;
               case 'C':
                   this.contingut.add(Node.Type.Conferencia);
                   break;
               case 'P':
                   this.contingut.add(Node.Type.Paper);
                   break;
               case 'T':
                   this.contingut.add(Node.Type.Terme);
                   break;
               default:
                   break;
           }
       } 
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
        ArrayList<Node.Type> reversed = new ArrayList<Node.Type>();
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
        Pair<ArrayList<Node.Type>, ArrayList<Node.Type>> doblepath;
        doblepath = new Pair<ArrayList<Node.Type>, ArrayList<Node.Type>>
        (new ArrayList<Node.Type>(), new ArrayList<Node.Type>());
        
        int n = this.contingut.size();
        // Comprobamos la paridad de this.contingut;
        
        if (this.contingut.size()%2 == 0) {
            // Si path tiene elementos pares, debemos añadir el elemento  neutro
            // tanto al final del primer minipath, como al principio del primero.
            for (int i = 0; i < n/2; ++i) {
                doblepath.first.add(this.contingut.get(i));
                // Llenamos el primer minipath;
            }
            doblepath.first.add(Node.Type.MidElement);
            doblepath.second.add(Node.Type.MidElement);
            // Añadimos el midelement al final del primer minipath
            // y al principio del segundo. Llenamos el segundo.
            for (int i = n/2; i < n; ++i) {
                doblepath.second.add(this.contingut.get(i));
            }
        }
        else {
            //El path tiene elementos impares, duplico el del medio;
            for (int i = 0; i <= n/2; ++i) {
                doblepath.first.add(this.contingut.get(i));
                // Llenamos el primer minipath;
            }
            // Llenamos el segundo.
            for (int i = n/2; i < n; ++i) {
                doblepath.second.add(this.contingut.get(i));
            }
            
        }
        return doblepath;
    }
        
}