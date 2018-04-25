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
    
    public Node(String valor, Node referencia, double weight){
        this.valor = valor;
        this.referencia_izq = referencia;
        this.referencia_der = null;
        this.weight = weight;
    }
    
    public Node(Node referencia_izq, Node referencia_der){
        this.referencia_izq = referencia_izq;
        this.referencia_der = referencia_der;
        this.valor = null;
    }
}
