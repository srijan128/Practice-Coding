package Practice2026;

import graphprac.Pair;

import java.util.ArrayList;
import java.util.List;

public class Graph2026 {

     int [][] adjMatrix;

     List<List<Integer>> adjList;

    List<List<Pair>> adjListWithWeight;

    public Graph2026(int nodes){
        adjMatrix=new int[nodes][nodes];
        adjList=new ArrayList<>();
        adjListWithWeight=new ArrayList<>();
        for(int i=0;i<nodes;i++){
            adjList.add(new ArrayList<>());
            adjListWithWeight.add(new ArrayList<>());
        }
    }

    public void addElementsInAdjMatrix(int [][] edges,boolean isDirected){
        for(int [] edge:edges){
            int u=edge[0];
            int v = edge[1];
            if(isDirected)
               adjMatrix[u][v]=1;
            else{
                adjMatrix[u][v]=1;
                adjMatrix[v][u]=1;
            }
        }
    }

    public void addElementsInAdjMatrixWithWeight(int [][] edges,boolean isDirected){
        for(int [] edge:edges){
            int u=edge[0];
            int v = edge[1];
            int w=edge[2];
            if(isDirected)
                adjMatrix[u][v]=w;
            else{
                adjMatrix[u][v]=w;
                adjMatrix[v][u]=w;
            }
        }
    }

    public void addElementsInAdjList(int [][] edges,boolean isDirected){
        for(int [] edge:edges){
            int u=edge[0];
            int v = edge[1];
            if(isDirected)
                adjList.get(u).add(v);
            else{
                adjList.get(u).add(v);
                adjList.get(v).add(u);
            }
        }
    }

    public void addElementsInAdjListWithWeight(int [][] edges,boolean isDirected){
        for(int [] edge:edges){
            int u=edge[0];
            int v = edge[1];
            int w=edge[2];
            if(isDirected) {
                Pair pair = new Pair(v, w);
                adjListWithWeight.get(u).add(pair);
            }
            else{
                Pair pair1 = new Pair(v, w);
                adjListWithWeight.get(u).add(pair1);
                Pair pair2 = new Pair(u, w);
                adjListWithWeight.get(v).add(pair2);
            }
        }
    }

    public void printMatrix(){
        for(int i=0;i<adjMatrix.length;i++){
            System.out.print("row " + i + "->");
            for(int j=0;j<adjMatrix[i].length;j++){
                System.out.print(adjMatrix[i][j]);
                if(j != adjMatrix[i].length-1)
                    System.out.print(", ");
            }
            System.out.println();
        }
    }

    public void printList(){
        for(int i=0;i<adjListWithWeight.size();i++){
            System.out.print("row " + i + "->");
            System.out.print("[");
            for(int j=0;j<adjListWithWeight.get(i).size();j++){
                System.out.print(adjListWithWeight.get(i).get(j));
                if(j != adjListWithWeight.get(j).size()-1)
                    System.out.print(", ");
            }
            System.out.print("]");
            System.out.println();
        }
    }


    public void findDegreeFromAdjList(List<List<Integer>> adjList,boolean isDirected){
        int nodes=adjList.size();
        int [] indegree=new int[nodes];
        int [] outdegree=new int[nodes];
        int [] degree=new int[nodes];
        for(int i=0;i<adjList.size();i++){
            if(!isDirected)
                degree[i]=adjList.get(i).size();
            else {
                outdegree[i]=adjList.get(i).size();
                for (int j = 0; j < adjList.get(i).size(); j++) {
                    indegree[adjList.get(i).get(j)]++;
                }
            }
        }

        System.out.println("Undirected Graph");
        for(int k=0;k<nodes;k++){
            System.out.println("node - > " + k + "degree " + degree[k]);

        }
        System.out.println("Directed Graph");
        for(int k=0;k<nodes;k++){
            System.out.print("node " + k +" - > " + "in-degree " + indegree[k] + " - ");
            System.out.print( "out-degree " + outdegree[k]);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int nodes=3;
        int [][] edges={{0,1},{1,2},{0,2}};
//        Graph2026 graph=new Graph2026(3);
//        System.out.println("Undirected Graph");
//        graph.addElementsInAdjMatrix(edges,false);
//        graph.printMatrix();
//        System.out.println("Directed Graph");
//        Graph2026 directedGraph=new Graph2026(3);
//        directedGraph.addElementsInAdjMatrix(edges,true);
//        directedGraph.printMatrix();

//        int [][] edgesWithEdges={{0,1,4},{1,2,3},{0,2,6}};
//        Graph2026 graph=new Graph2026(3);
//        System.out.println("Undirected Graph");
//        graph.addElementsInAdjMatrixWithWeight(edgesWithEdges,false);
//        graph.printMatrix();
//        System.out.println("Directed Graph");
//        Graph2026 directedGraph=new Graph2026(3);
//        directedGraph.addElementsInAdjMatrixWithWeight(edgesWithEdges,true);
//        directedGraph.printMatrix();

//        int [][] edgesWithEdges={{0,1,4},{1,2,3},{0,2,6},{2,1,10}};
//        Graph2026 graph=new Graph2026(3);
//        System.out.println("Undirected Graph");
//        graph.addElementsInAdjListWithWeight(edgesWithEdges,false);
//        graph.printList();
//        System.out.println("Directed Graph");
//        Graph2026 directedGraph=new Graph2026(3);
//        directedGraph.addElementsInAdjListWithWeight(edgesWithEdges,true);
//        directedGraph.printList();
       // int [][] edgesWithEdges={{0,1,4},{1,2,3},{0,2,6},{2,1,10}};
        Graph2026 graph=new Graph2026(3);
        System.out.println("Undirected Graph");
        graph.addElementsInAdjList(edges,false);
        graph.printList();
        graph.findDegreeFromAdjList(graph.adjList,false);
        System.out.println("Directed Graph");
        Graph2026 directedGraph=new Graph2026(3);
        directedGraph.addElementsInAdjList(edges,true);
        directedGraph.printList();
        graph.findDegreeFromAdjList(directedGraph.adjList,true);
    }
}
