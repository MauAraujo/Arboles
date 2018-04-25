package arboles;

import java.util.Scanner;
import java.util.HashSet;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class Kruskall{

    public double[][] graph; //Matriz de transicion del grafo
    public ArrayList<String> nodes; //Lista de nodos
    public int[] rank, parent; //Arreglos para simular conjuntos disjuntos
    public int n; //Numero de nodos
    public ArrayList<Edge> edges = new ArrayList<Edge>(); //Lista de aristas
    
    public Kruskall(double[][] graph, ArrayList<String> nodes){
        //Recibe la matriz de transicion y lista de nodos
        this.graph = graph;
        this.nodes = nodes;
        this.n = nodes.size();
        this.parent = new int[n];
        this.rank = new int[n];
        
        //Lee matriz en rejilla
        leerMatriz(n);
        
        //Agrega las aristas a la lista, recorriendo la matriz de transicion
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(graph[i][j] != Double.POSITIVE_INFINITY && graph[i][j] > 0){
                    this.edges.add(new Edge(nodes.get(i), nodes.get(j), graph[i][j]));
                }
            }
        }
    }

    public void makeSet(){
        //Funcion que pone a cada elemento de un conjunto en su propio conjunto {{a}, {b}, {c}, ...} 
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }
    }

    public int find(int x){
        //Funcion que encuentra el nodo padre de un conjunto dado
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }

        return  parent[x];
    }

    public void union(String a, String b){
        //Funcion que une los dos conjuntos que no tengan el mismo padre y actualiza el arreglo de padres
        int x, y;

        x = nodes.indexOf(a);
        y = nodes.indexOf(b);

        int xRoot = find(x), yRoot = find(y);

        if (xRoot == yRoot)
            return;

        if (rank[xRoot] < rank[yRoot])
            parent[xRoot] = yRoot;

        else if (rank[yRoot] < rank[xRoot])
            parent[yRoot] = xRoot;

        else
            {
                parent[yRoot] = xRoot;
                rank[xRoot] = rank[xRoot] + 1;
            }
    }
    
    public void leerMatriz(int n){
    Scanner key = new Scanner(System.in);
    System.out.println("Ingrese la matriz: \n");
    for(int i = 0; i < n; i++)
      for(int j= 0; j < n; j++){
        graph[i][j] = key.nextDouble();
        if(graph[i][j] == -1)
          graph[i][j] = Double.POSITIVE_INFINITY;
      }
    }

    public HashSet getMST(){
        HashSet<Edge> Q = new HashSet<Edge>(); //Conjunto de aristas del arbol resultante
        int x, y; //{x, y} en R
        Edge minim; //arista con peso minimo 
        makeSet(); //Crea el conjunto VS donde cada nodo esta en su propio conjunto 
        Collections.sort(edges, new comp()); //Ordena la lista de aristas de manera ascendente de acuerdo a su peso
        ListIterator<Edge> it = edges.listIterator(); //Iterador para recorrer la lista de aristas
        
        while(parent.length > 1 && it.hasNext()){
            minim = it.next();
            x = nodes.indexOf(minim.src); //Sacar el nodo origen de la arista
            y = nodes.indexOf(minim.dest); //Sacar nodo destino de la arista 

            if(find(x) != find(y)){ //Si x y y no pertenecen al mismo conjunto en VS
                Q.add(minim); 
                union(minim.src, minim.dest); //Unir el conjunto en el que estan x y y en VS
            }
        }
        
        
        for(Edge edg : Q) //Imprime las aristas del arbol resultante
            System.out.println(edg.src + "->" + edg.dest + " peso " + edg.weight);
       
        return Q;
    }
    
    class comp implements Comparator<Edge>{
        //Comparador que sirve para ordenar la lista de aristas de acuerdo a su peso
        public int compare(Edge edge1, Edge edge2) {
            if((edge1.weight) > (edge2.weight)){
                return 1;
            }   
        
            else if((edge1.weight) < (edge2.weight)){
                return -1;
            }   
        
            else
                return 0;
        }
    } 
    
     public static void main(String[] args){
      Scanner key = new Scanner(System.in);
      String node = "abcdefghij";
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
      main.getMST();
  }
}

