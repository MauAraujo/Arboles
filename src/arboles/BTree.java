
package arboles;

import java.util.*;

public class BTree {
    
    Tree arbol;
    String raiz;
    SubList nodos;
    ArrayList<String> recorrido = new ArrayList();
    ArrayList<Node> impresion = new ArrayList();
    
    public BTree(Tree arbol){
        this.arbol = arbol;
        this.raiz = arbol.raiz;
        this.nodos = new SubList(raiz);
        transform();
        
    }
    
    public void transform(){
        inserta(arbol.nodos.head,arbol.nodos.head.valor);
    }
    
    //Node x es el nodo del arbol original, 
    //mientras que resp va a ser un nodo del arbol binario
    public Node inserta(Node x, String valor){
        Node resp = new Node (valor);
        
        if (x == null){
            return null;
        }
        
        else if(x == arbol.nodos.head){
            recorrido.clear();
            recorrido.add(arbol.nodos.head.valor);
            resp = inserta(arbol.nodos.head.referencia_der, valor);
            nodos.head.referencia_izq = resp;
        }
        
        else if(!x.hasValor){
            resp = inserta(x.referencia_izq,x.referencia_izq.valor);
            resp.referencia_der = inserta(x.referencia_der,valor);
            
        }
        
        else if((x.hasValor) && (!recorrido.contains(x.valor))){
            recorrido.add(x.valor);
           resp.referencia_izq = inserta(x.referencia_der,valor);
            
        }
        
        return resp;
        
    }
    
    public void recorrer(Node x){
        if(x == null)
            return;
        else if(x == nodos.head){
            impresion.clear();
            impresion.add(x);
            recorrer(nodos.head.referencia_izq);
        }
        else{
            impresion.add(x);
            recorrer(x.referencia_izq);
            recorrer(x.referencia_der);
        }
        return;
    }
    
    public String toString(){
        String result = "";
        result += "Arbol binario: \n\n";
        recorrer(nodos.head);
        for(int i = 0; i < impresion.size();i++)
            result += impresion.get(i).referencia_izq + " <- " + impresion.get(i).valor + " -> " + impresion.get(i).referencia_der +"\n";
        return result;
    }
    
}
