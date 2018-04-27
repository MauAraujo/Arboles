
package arboles;

import java.util.*;

public class Tree {
    
    String raiz;
    SubList nodos;
    Edge[] aristas;
    ArrayList<String> recorrido = new ArrayList();
    ArrayList<Node> impresion = new ArrayList();
    
     
    public Tree(String raiz, Edge[] aristas){
        this.raiz = raiz;
        this.nodos = new SubList(raiz);
        this.aristas = aristas;
        
        create(raiz,getHojas(raiz));
        
    }
    
    public void create(String origen, ArrayList<String> hojitas){
        
        if(hojitas.isEmpty()){
            return;
        }
        for(int i = 0; i < hojitas.size();i++){
            String hoja = hojitas.get(i);
            if(!this.nodos.contains(hoja)){
                this.nodos.insertAt(origen, hoja);
                create(hoja, getHojas(hoja)); 
            }                        
        }
          
        
        return;
        
        
        
        
        
        /*ArrayList<String> hojas = getHojas(raiz);
        for(int i = 0; i < hojas.size(); i++){
            nodos.insertAt(raiz, hojas.get(i));
        }
        recorrido.add(raiz);
        
        for(int i = 0; i < aristas.length; i++){
            String origen = aristas[i].src;
            //busca el src que sea igual a raiz
            if(!recorrido.contains(origen)){
                hojas = getHojas(origen);
                for(int j = 0; j < hojas.size(); j++){
                    nodos.insertAt(origen, hojas.get(i));
                }
                recorrido.add(origen);
            }
        }*/
    }
    
    public ArrayList<String> getHojas(String padre){
        
        ArrayList<String> hojas = new ArrayList();
        
        for(int i = 0; i < aristas.length; i++){
            if(padre.equals(aristas[i].src))
                hojas.add(aristas[i].dest);
            else if(padre.equals(aristas[i].dest))
                hojas.add(aristas[i].src);
        }
        return hojas;  
    }
    
   public void recorrer(Node x){
        if(x == null)
            return;
        else if(x == nodos.head){
            impresion.clear();
            impresion.add(x);
            recorrer(nodos.head.referencia_der);
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
        result += "Arbol de Expresion Minima: \n\n";
        recorrer(nodos.head);
        for(int i = 0; i < impresion.size();i++){
            if(impresion.get(i).hasValor)
                result += impresion.get(i).valor + " -> " + impresion.get(i).referencia_der +"\n";
        }
        return result;
    }
    
    
    
}
