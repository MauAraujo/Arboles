
package arboles;

import java.util.*;

public class SubList {
    Node head;
    int num;
    ArrayList<String> recorrido = new ArrayList();
    ArrayList<Node> busqueda = new ArrayList();
    
    
    
    public SubList(String valor){
        head = new Node(valor);
    }
    
    public void insertAt(String originValor,String valor){
        //Si vas a llamar a la funcion, se debe usar como insertAt(this.head.valor, "valor_buscado")
        //donde "valor_buscado es el nombre del nodo que quieres buscar
        
        //este es el nodo al que se le va a insertar el nuevo nodo
        Node origin = buscar(head,originValor);
        //este es el nuevo nodo
        Node nuevo = new Node(valor);
        //si el nodo tiene ya uno o mas hijos, entonces se le va a hacer otro hijo
        if (origin.referencia_der != null){
            insertAt(origin.referencia_der,nuevo);
        }
        //si no tiene hijos, se le va a hacer el primer hijo
        else{
           origin.referencia_der = new Node(nuevo,null);
           num++;
        }
    }
    
    public void insertAt(Node origin, Node nuevo){
         if (origin.referencia_der != null){
            insertAt(origin.referencia_der,nuevo);
        }
        //si no tiene hijos, se le va a hacer el primer hijo
        else{
           origin.referencia_der = new Node(nuevo,null);
           num++;
        }
    }
    
    public Node buscar(Node x, String buscado){
        
        //Dado un nodo de partida, va a ir recorriendo toda la sublista hasta 
        //dar con el nodo en cuestion
        Node resp = null;
        
        if((x == null))
            return null;
        
        
        else if(x == head){
            busqueda.clear();
            busqueda.add(head);
            if(x.valor == buscado)
                return head;
            resp = buscar(head.referencia_der, buscado);
            
        }
        
        else if(!x.hasValor){
           resp = buscar(x.referencia_izq, buscado);
           if(resp == null)
                resp = buscar(x.referencia_der, buscado);
        }
        
        else if((x.hasValor) && (!busqueda.contains(x.valor))){
            busqueda.add(x);
            if(x.valor == buscado)
                resp = x;
            else
                resp = buscar(x.referencia_der, buscado);
        }
        
        return resp;
    
    }
    
    
    
    public void recorrer(Node x){
        // recorre toda la sublista y va almacenando los nodos recorridos en un arreglo
        if (x == null){
            return;
        }
        
        else if(x == head){
            recorrido.clear();
            recorrido.add(head.valor);
            recorrer(head.referencia_der);
        }
        
        else if(!x.hasValor){
            recorrer(x.referencia_izq);
            recorrer(x.referencia_der);
        }
        
        else if((x.hasValor) && (!recorrido.contains(x.valor))){
            recorrido.add(x.valor);
            recorrer(x.referencia_der);
        }
        
        return;
    }
    
    public boolean contains(String nodo){
        recorrer(head);
        if(recorrido.contains(nodo))
            return true;
        return false;
    }
    
    
    public String toString(){
        String result = "";
        
        //this.recorrido.clear();
        recorrer(head);
        
        for(int i = 0; i < recorrido.size(); i++){
            result += recorrido.get(i);
        }
        
        return result;
    }
    
    public static void main(String args[]){
        SubList test = new SubList("a");
        test.insertAt(test.head.valor,"b");
        test.insertAt("b", "c");
        test.insertAt(test.head.valor, "d");
        System.out.println(test);
        System.out.println((test.buscar(test.head,"a")).valor);
    }
    
}
