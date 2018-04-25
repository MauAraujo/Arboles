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
    
    public static Node makeTree(String root, HashSet mst_edges){
        //Funcion para crear un arbol de acuerdo a la raiz dada. Hace falta arreglar la recursion
        
        Iterator<Edge> it = mst_edges.iterator(); //Iterador para recorrer el mst
        Node tnode = null;
        Edge temp;
        
        while(it.hasNext()){
            temp = it.next();
            if(temp.src.equals(root))
                tnode = new Node(root, makeTree(temp.dest,mst_edges), temp.weight);
            
            else if(temp.dest.equals(root))
                tnode = new Node(root, makeTree(temp.src,mst_edges), temp.weight);
        }
        return tnode;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner key = new Scanner(System.in);
        String node = "abcdefghij";
        String raiz;
        HashSet<Edge> mst_edges; //Conjunto de aristas del mst resultante
        Node head;
        
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
        
        System.out.println("Elija el nodo raiz del arbol m-ario: ");
        raiz = key.next();
        head = makeTree(raiz, mst_edges);
    }
    
}
