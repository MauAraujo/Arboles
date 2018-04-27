/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

/**
 *
 * @author mau
 */
public class Node {
    String valor;
    Node referencia_izq;
    Node referencia_der;
    double weight;
    boolean hasValor;
    
    public Node(String valor, Node referencia, double weight){
        this.valor = valor;
        this.referencia_izq = null;
        this.referencia_der = referencia;
        this.weight = weight;
        this.hasValor = true;
    }
        
    public Node(Node referencia_izq, Node referencia_der){
        this.referencia_izq = referencia_izq;
        this.referencia_der = referencia_der;
        this.valor = null;
        this.hasValor = false;
    }
    
    public Node(String valor){
        this.valor=valor;
        this.referencia_izq = null;
        this.referencia_der = null;
        this.weight = 0;
        this.hasValor = true;
    }
    public String toString(){
        if(this.hasValor)
            return this.valor;
        else
            return "izq: " + this.referencia_izq + " der: " + this.referencia_der;
    }
}
