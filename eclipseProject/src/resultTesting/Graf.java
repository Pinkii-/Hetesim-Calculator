package resultTesting;

public class Graf {
    
    //Atributos
    
    String nom;   
    int id;
    
    //Metodos
  
    
    public Graf() {
        id = 1;
    }
    
    public Graf(int a){
    	id = 2;
    }
    
    public String getNom() {
        return "GRAF";
    }
    
    public boolean existsNode(Node n) {
        return true;
    }
    
    public Node getNode(int index, Node.Type tipus) {
        Node asdf = new Node();
        asdf.initialize(Node.Type.Autor, 1, "NodeGraf"+index);
        return asdf;
    }
}


