package graphprac;

public class Pair {
    int node;
    int weight;

    public Pair(int node,int weight){
        this.node=node;
        this.weight=weight;
    }

    @Override
    public String toString() {
        return "{" +
                "node=" + node +
                ", weight=" + weight +
                '}';
    }
}
