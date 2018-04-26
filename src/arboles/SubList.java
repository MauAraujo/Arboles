
package arboles;

public class SubList {
    Node head;
    int num;
    
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
    
}
