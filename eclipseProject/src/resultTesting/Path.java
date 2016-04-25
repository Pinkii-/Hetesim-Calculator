package resultTesting;

import java.util.ArrayList;

/**
 *
 * @author Eduard Maestro
 */
public class Path {
    private ArrayList<Node.Type> contingut;
    
    //Pre: Cert.
    //Post: Es crea un path buit.
    public Path() {
        contingut = new ArrayList<Node.Type>();
        contingut.add(Node.Type.Autor);
        contingut.add(Node.Type.Paper);
        contingut.add(Node.Type.Autor);
        contingut.add(Node.Type.Paper);
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
    
    public String getNom(){
    	return "TestPath";
    }
        
}