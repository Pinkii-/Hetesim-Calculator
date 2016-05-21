package Tests;

import java.util.Scanner;
import Dominio.Graph;
import Dominio.Node;
import Dominio.Utils;
import Dominio.Node.Label;
import Dominio.Node.Type;

/**
 *
 * @author chus
 */
public class GrafTest {
    
    public static void main(String[] args) throws Exception {
        System.out.println ("Probamos la clase Graf");
        System.out.println ("1. set nom ");
        System.out.println ("2. get nom");
        System.out.println ("3. add node");
        System.out.println ("4. add label");
        System.out.println ("5. exists node");
        System.out.println ("6. get node");
        System.out.println ("7. exists arc");
        System.out.println ("8. delete node");
        System.out.println ("9. delete arc");
        System.out.println ("10. set node");
        System.out.println ("11. set arc");
        System.out.println ("12. get matrix author");
        System.out.println ("13. get matrix conf"); 
        System.out.println ("14. get matrix term");
        System.out.println ("15. get arraylist de entidad");
        System.out.println ("-1. Para salir");
        System.out.println ("Por favor introduce una opcion:");
        
        String aux, aux2;
        Node.Type type = null;
        Node.Label etiq = null;
        int ind, ind2;
        Node n = new Node();
        Graph g = new Graph();     //creadora
        Scanner in = new Scanner (System.in); //Creación de un objeto Scanner
        int op = Integer.parseInt(in.nextLine()); //Invocamos un método sobre un objeto Scanner
        
        while (op != -1) {
            OUTER:
            switch (op) {
                case 1:     //ok
                    System.out.println ("Introduce un nom al grafo: ");
                    aux = in.nextLine();
                    g.setNom(aux);
                    break;
                case 2:     //ok
                    System.out.println ("Devuelve el nom del grafo: " + g.getNom() + "");
                    break;
                case 3:     //ok
                    System.out.println ("Introduce tipo entidad: ");
                    aux2 = in.nextLine();
                    System.out.println ("Introduce nombre entidad: ");
                    aux = in.nextLine();
                    if (null != aux2) {
                        switch (aux2) {
                            case "Autor":
                                System.out.println("Devuelve la pos: " + g.addNode(Node.Type.Autor, aux) + "");
                                break;
                            case "Conferencia":
                                System.out.println("Devuelve la pos: " + g.addNode(Node.Type.Conferencia, aux) + "");
                                break;
                            case "Paper":
                                System.out.println("Devuelve la pos: " + g.addNode(Node.Type.Paper, aux) + "");
                                break;
                            case "Terme":
                                System.out.println("Devuelve la pos: " + g.addNode(Node.Type.Terme, aux) + "");
                                break;
                            default:
                                System.out.println("Tipo incorrecto: ");
                                break OUTER;
                        }
                    }
                    break;
                case 4:
                    System.out.println ("Introduce tipo entidad: ");
                    aux2 = in.nextLine();
                    if (null != aux2) {
                        switch (aux2) {
                            case "Autor":
                                type = Node.Type.Autor;
                                break;
                            case "Conferencia":
                                type = Node.Type.Conferencia;
                                break;
                            case "Paper":
                                type = Node.Type.Paper;
                                break;
                            case "Terme":
                                type = Node.Type.Terme;
                                break;
                            default:
                                System.out.println ("Tipo incorrecto");
                                break OUTER;
                        }
                    }
                    System.out.println ("Introduce indice del nodo: ");
                    ind = Integer.parseInt(in.nextLine());
                    System.out.println ("Introduce label");
                    aux2 = in.nextLine();
                    if (null != aux2) {
                        switch (aux2) {
                            case "AI":
                                etiq = Node.Label.AI;
                                break;
                            case "Database":
                                etiq = Node.Label.Database;
                                break;
                            case "DataMining":
                                etiq = Node.Label.DataMining;
                                break;
                            case "InformationRetrieval":
                                etiq = Node.Label.InformationRetrieval;
                                break;
                            default:
                                System.out.println ("Tipo incorrecto");
                                break OUTER;
                        }
                    }
                    g.addLabel(ind, etiq, type);
                    break;
                case 5:     //ok
                    System.out.println ("Introduce tipo entidad: ");
                    aux2 = in.nextLine();
                    if (null != aux2) {
                        switch (aux2) {
                            case "Autor":
                                type = Node.Type.Autor;
                                break;
                            case "Conferencia":
                                type = Node.Type.Conferencia;
                                break;
                            case "Paper":
                                type = Node.Type.Paper;
                                break;
                            case "Terme":
                                type = Node.Type.Terme;
                                break;
                            default:
                                System.out.println ("Tipo incorrecto");
                                break OUTER;
                        }
                    }
                    System.out.println ("Introduce nom entidad: ");
                    aux = in.nextLine();
                    System.out.println ("Introduce indice: ");
                    ind = Integer.parseInt(in.nextLine());
                    n.initialize(type, ind, aux);
                    if(g.existsNode(n) != -1) System.out.println ("El nodo existe: "+g.existsNode(n)+"");
                    else System.out.println ("El nodo no existe");
                    break;
                case 6:     //ok
                    System.out.println ("Introduce tipo entidad: ");
                    aux2 = in.nextLine();
                    if (null != aux2) {
                        switch (aux2) {
                            case "Autor":
                                type = Node.Type.Autor;
                                break;
                            case "Conferencia":
                                type = Node.Type.Conferencia;
                                break;
                            case "Paper":
                                type = Node.Type.Paper;
                                break;
                            case "Terme":
                                type = Node.Type.Terme;
                                break;
                            default:
                                System.out.println ("Tipo incorrecto");
                                break OUTER;
                        }
                    }
                    System.out.println ("Introduce indice: ");
                    ind = Integer.parseInt(in.nextLine());
                    n = g.getNode(ind, type);
                    Utils.printNode(n);
                    break;
                case 7:     //ok
                    System.out.println ("Introduce tipo entidad: ");
                    aux2 = in.nextLine();
                    if (null != aux2) {
                        switch (aux2) {
                            case "Autor":
                                type = Node.Type.Autor;
                                break;
                            case "Conferencia":
                                type = Node.Type.Conferencia;
                                break;
                            case "Paper":
                                type = Node.Type.Paper;
                                break;
                            case "Terme":
                                type = Node.Type.Terme;
                                break;
                            default:
                                System.out.println ("Tipo incorrecto");
                                break OUTER;
                        }
                    }
                    System.out.println ("Introduce indice nodo: ");
                    ind = Integer.parseInt(in.nextLine());
                    Node n3 = g.getNode(ind, type);
                    System.out.println ("Introduce indice paper: ");
                    ind = Integer.parseInt(in.nextLine());
                    Node n4 = g.getNode(ind, Node.Type.Paper);
                    if(g.existsArc(n3,n4)) System.out.println ("La relacion existe");
                    else System.out.println ("La relacion no existe");
                    break;
                case 8:     //ok
                    System.out.println ("Introduce tipo entidad: ");
                    aux2 = in.nextLine();
                    if (null != aux2) {
                        switch (aux2) {
                            case "Autor":
                                type = Node.Type.Autor;
                                break;
                            case "Conferencia":
                                type = Node.Type.Conferencia;
                                break;
                            case "Paper":
                                type = Node.Type.Paper;
                                break;
                            case "Terme":
                                type = Node.Type.Terme;
                                break;
                            default:
                                System.out.println ("Tipo incorrecto");
                                break OUTER;
                        }
                    }
                    System.out.println ("Introduce indice nodo: ");
                    ind = Integer.parseInt(in.nextLine());
                    Node n5 = g.getNode(ind, type);
                    g.deleteNode(n5);
                    break;
                case 9:     //ok
                    System.out.println ("Introduce tipo entidad: ");
                    aux2 = in.nextLine();
                    if (null != aux2) {
                        switch (aux2) {
                            case "Autor":
                                type = Node.Type.Autor;
                                break;
                            case "Conferencia":
                                type = Node.Type.Conferencia;
                                break;
                            case "Paper":
                                type = Node.Type.Paper;
                                break;
                            case "Terme":
                                type = Node.Type.Terme;
                                break;
                            default:
                                System.out.println ("Tipo incorrecto");
                                break OUTER;
                        }
                    }
                    System.out.println ("Introduce indice nodo: ");
                    ind = Integer.parseInt(in.nextLine());
                    Node n6 = g.getNode(ind, type);
                    System.out.println ("Introduce indice paper: ");
                    ind = Integer.parseInt(in.nextLine());
                    Node n7 = g.getNode(ind, Node.Type.Paper);
                    g.deleteArc(n6, n7);
                    break;
                case 10:    //ok
                    System.out.println ("Introduce tipo entidad: ");
                    aux2 = in.nextLine();
                    if (null != aux2) {
                        switch (aux2) {
                            case "Autor":
                                type = Node.Type.Autor;
                                break;
                            case "Conferencia":
                                type = Node.Type.Conferencia;
                                break;
                            case "Paper":
                                type = Node.Type.Paper;
                                break;
                            case "Terme":
                                type = Node.Type.Terme;
                                break;
                            default:
                                System.out.println ("Tipo incorrecto");
                                break OUTER;
                        }
                    }
                    System.out.println ("Introduce indice nodo: ");
                    ind = Integer.parseInt(in.nextLine());
                    Node n8 = g.getNode(ind, type);
                    System.out.println ("Introduce nuevo nombre: ");
                    aux = in.nextLine();
                    g.setNode(n8, aux);
                    break;
                case 11:
                    System.out.println ("Introduce tipo entidad: ");
                    aux2 = in.nextLine();
                    if (null != aux2) {
                        switch (aux2) {
                            case "Autor":
                                type = Node.Type.Autor;
                                break;
                            case "Conferencia":
                                type = Node.Type.Conferencia;
                                break;
                            case "Paper":
                                type = Node.Type.Paper;
                                break;
                            case "Terme":
                                type = Node.Type.Terme;
                                break;
                            default:
                                System.out.println ("Tipo incorrecto");
                                break OUTER;
                        }
                    }
                    System.out.println ("Introduce indice nodo: ");
                    ind = Integer.parseInt(in.nextLine());
                    System.out.println ("Introduce indice paper: ");
                    ind2 = Integer.parseInt(in.nextLine());
                    g.setArc(ind, ind2, type);
                    break;
                case 12:
                	Utils.printMatrix(g.getMatrixAuthor());                    
                    break;
                case 13:
                	Utils.printMatrix(g.getMatrixConf());
                    break;
                case 14:
                	Utils.printMatrix(g.getMatrixTerm());
                    break;
                case 15:
                    System.out.println ("Escriu el tipus d'entitats a imprimir: ");
                    aux2 = in.nextLine();
                    if (null != aux2) {
                        switch (aux2) {
                            case "Autor":
                                type = Node.Type.Autor;
                                break;
                            case "Conferencia":
                                type = Node.Type.Conferencia;
                                break;
                            case "Paper":
                                type = Node.Type.Paper;
                                break;
                            case "Terme":
                                type = Node.Type.Terme;
                                break;
                            default:
                                System.out.println ("Tipo incorrecto");
                                break OUTER;
                        }
                    }
                    g.escriuArray(type);
                    break;
                default: 
                    System.out.println("Opcion no valida.");
                    break;
            }
            System.out.println ("Por favor introduce una opcion: ");
            op = Integer.parseInt(in.nextLine());
        }
    }
}
