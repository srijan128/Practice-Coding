package graphprac;

import java.util.ArrayList;
import java.util.List;

public class Graph1 {

    int [][] adjMatrix;

    List<List<Integer>> adjList;

    List<List<Pair>> adjListWithWeight;


    public Graph1(int nodes){
        adjMatrix=new int[nodes][nodes];
        adjList=new ArrayList<>();
        adjListWithWeight=new ArrayList<>();
        for(int i=0;i<nodes;i++){
            adjList.add(new ArrayList<>());
            adjListWithWeight.add(new ArrayList<>());
        }
    }

   // [[0,1],[0,2]]

    public void addEdgesInAdjMatrix(int [][] edges, boolean isDirected){
        for(int i=0;i<edges.length;i++){
            int u=edges[i][0];
            int v=edges[i][1];

            if(!isDirected){
                adjMatrix[u][v]=1;
                adjMatrix[v][u]=1;
            }else{
                adjMatrix[u][v]=1;
            }
        }
    }

    public void printMatrix(){
        for(int i=0;i<adjMatrix.length;i++){
            System.out.print("row " + i + " -> ");
            for(int j=0;j<adjMatrix[i].length;j++){
                System.out.print(adjMatrix[i][j] + ",");
            }
            System.out.println();
        }
    }


    public void addEdgesInAdjList(int [][] edges, boolean isDirected){
        for(int [] edge:edges){
            int u=edge[0];
            int v=edge[1];

            if(!isDirected){
                adjList.get(u).add(v);
                adjList.get(v).add(u);
            }else{
                adjList.get(u).add(v);
            }
        }
    }

    public void printList(){
        for(int i=0;i<adjList.size();i++){
            System.out.print(i + " -> ");
            System.out.print("[");
            for(int j=0;j<adjList.get(i).size();j++) {
                System.out.print(adjList.get(i).get(j));
                if (j != adjList.get(i).size() - 1) {
                    System.out.print(",");
                }
            }
            System.out.print("]");
        }
        System.out.println();
    }

    public void addEdgesWithWeightInList(int [][] edges, boolean isDirected){
        for(int [] edge:edges){
            int u=edge[0];
            int v=edge[1];
            int w=edge[2];
                if(isDirected){
                    adjListWithWeight.get(u).add(new Pair(v,w));
                }else{
                    adjListWithWeight.get(u).add(new Pair(v,w));
                    adjListWithWeight.get(v).add(new Pair(u,w));
                }
        }
    }

}
