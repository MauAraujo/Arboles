
package arboles;

import java.util.*;

public class SubList {
    Node head;
    int num;
    ArrayList<String> recorrido = new ArrayList();
    ArrayList<String> busqueda = new ArrayList();
    
    
    public SubList(String valor){
        head = new Node(valor);
    }
    
    public void insertAt(Node origin,String valor){
        Node nuevo = new Node(new Node(valor), null);
        if (origin.referencia_der != null){
            insertAt(origin.referencia_der,valor);
        }
        else{
           origin.referencia_der = nuevo;
           num++;
        }
    }
    
    
    
    public void recorrer(Node x){
        if (x == null){
            return;
        }
        
        else if(x == head){
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
    
    public String toString(){
        String result = "";
        
        this.recorrido.clear();
        recorrer(head);
        
        for(int i = 0; i < recorrido.size(); i++){
            result += recorrido.get(i);
        }
        
        return result;
    }
    
    public static void main(String args[]){
        SubList test = new SubList("a");
        test.insertAt(test.head,"b");
        test.insertAt(new Node("b"), "c");
        test.insertAt(test.head, "d");
        System.out.println(test);
    }
    
}
