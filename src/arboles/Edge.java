package arboles;

class Edge{
    String src, dest;
    double weight;

    public Edge(String src, String dest, double weight){
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
    
    public Edge getReverse(){
       return (new Edge(this.dest,this.src,this.weight));
        
    }
}
