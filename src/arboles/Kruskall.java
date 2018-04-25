package arboles;

import java.util.Scanner;
import java.util.HashSet;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class Kruskall{

    public double[][] graph;
    public ArrayList<String> nodes;
    public int[] rank, parent;
    public int n;
    public ArrayList<Edge> edges = new ArrayList<Edge>();
    
    public Kruskall(double[][] graph, ArrayList<String> nodes){
        this.graph = graph;
        this.nodes = nodes;
        this.n = nodes.size();
        this.parent = new int[n];
        this.rank = new int[n];
        
        leerMatriz(n);
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(graph[i][j] != Double.POSITIVE_INFINITY && graph[i][j] > 0){
                    this.edges.add(new Edge(nodes.get(i), nodes.get(j), graph[i][j]));
                }
            }
        }
    }

    public void makeSet(){
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }
    }

    public int find(int x){

        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }

        return  parent[x];
    }

    public void union(String a, String b){
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
        HashSet<Edge> Q = new HashSet<Edge>();
        int x, y;
        Edge minim;
        makeSet();
        Collections.sort(edges, new comp());
        ListIterator<Edge> it = edges.listIterator();
        
        while(parent.length > 1 && it.hasNext()){
            minim = it.next();
            x = nodes.indexOf(minim.src);
            y = nodes.indexOf(minim.dest);

            if(find(x) != find(y)){
                Q.add(minim);
                union(minim.src, minim.dest);
            }
        }
        
        
        for(Edge edg : Q)
            System.out.println(edg.src + "->" + edg.dest + " peso " + edg.weight);
       
        return Q;
    }
    
    class comp implements Comparator<Edge>{
 
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

