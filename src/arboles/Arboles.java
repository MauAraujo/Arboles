/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author mau
 */
public class Arboles {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner key = new Scanner(System.in);
        String node = "abcdefghij";
        String raiz;
        HashSet<Edge> mst_edges; //Conjunto de aristas del mst resultante
        Edge[] aristas;
        int n_aristas;
        Node tempNode;
        
        int n;
        double[][] grafo;
        ArrayList<String> nodos = new ArrayList<String>();
      
        System.out.println("Programa que modifica el algoritmo de Kruskal para encontrar el arbol costo minimo \n\n");
        System.out.println("Ingrese el numero de nodos: ");
        n = key.nextInt();
      
        grafo = new double[n][n];
      
        for(int i = 1; i <= n;i++){
            nodos.add(node.substring(i-1,i));
        }
      
        Kruskall main = new Kruskall(grafo, nodos);
        mst_edges = main.getMST();
        n_aristas = mst_edges.size();
        aristas = mst_edges.toArray(new Edge[n_aristas]);
        
        System.out.println("Elija el nodo raiz del arbol m-ario: ");
        raiz = key.next();
        SubList arbolM = new SubList(raiz);
        
        //Node head = new Node(null);
        //head.setId(raiz);
        
        for(Edge each : aristas){
            if(each.src.equals(raiz)){
                arbolM.insertAt(raiz, each.dest);
            }
            
            else if(each.dest.equals(raiz)){
                arbolM.insertAt(raiz, each.src);
            }
        }                
    }
    
}
