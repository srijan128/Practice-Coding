package graphprac;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Graph {

    int [][] adjMatrix;

    List<List<Integer>> adjList;

    List<List<Pair>> adjListWithWeight;

    public Graph(int nodes){
        adjMatrix=new int[nodes][nodes];
        adjList=new ArrayList<>();
        adjListWithWeight=new ArrayList<>();
        for(int i=0;i<nodes;i++){
            adjList.add(new ArrayList<>());
        }
        for(int i=0;i<nodes;i++){
            adjListWithWeight.add(new ArrayList<>());
        }
    }

    // edges
    // [[2,0],[0,2],[1,0]]
    public void addEdgesInAdjMatrix(int [][] edges,boolean isDirected){
        for(int [] edge: edges){
            int u=edge[0];
            int v=edge[1];

            if(isDirected)
                adjMatrix[u][v]=1;
            else{
                adjMatrix[u][v]=1;
                adjMatrix[v][u]=1;
            }
        }
    }

    public void printMatrix(){
        for(int i=0;i<adjMatrix.length;i++){
            System.out.print("row "+i + "-> ");
            for(int j=0;j<adjMatrix[i].length;j++){
                System.out.print(adjMatrix[i][j] + ",");
            }
            System.out.println();
        }
    }

    public void addEdgesInAdjList(int [][] edges,boolean isDirected){
        for(int [] edge: edges){
            int u=edge[0];
            int v=edge[1];

            if(isDirected)
                adjList.get(u).add(v);
            else{
                adjList.get(u).add(v);
                adjList.get(v).add(u);
            }
        }
    }

    public void printList(){
        for(int i=0;i<adjList.size();i++){
            System.out.print(i + " -> ");
            System.out.print("[");
            for(int j=0;j<adjList.get(i).size();j++){
                System.out.print(adjList.get(i).get(j));
                if(j!=adjList.get(i).size()-1) {
                    System.out.print( ",");
                }
            }
            System.out.print("] ");
        }
        System.out.println();
    }


    public List<List<Integer>> getAdjList(){
        for(int i=0;i<adjList.size();i++){
            System.out.print(i + " -> ");
            System.out.print("[");
            for(int j=0;j<adjList.get(i).size();j++){
                System.out.print(adjList.get(i).get(j));
                if(j!=adjList.get(i).size()-1) {
                    System.out.print( ",");
                }
            }
            System.out.print("] ");
        }
        System.out.println();
        return adjList;
    }

    // edges with weight
    // [[2,0,10],[0,2,7],[1,0,8]]
    public void addEdgesWithWeightInAdjMatrix(int [][] edges,boolean isDirected){
        for(int [] edge: edges){
            int u=edge[0];
            int v=edge[1];
            int w=edge[2];
            if(isDirected)
                adjMatrix[u][v]=w;
            else{
                adjMatrix[u][v]=w;
                adjMatrix[v][u]=w;
            }
        }
    }

    public void addEdgesWithWeightInAdjList(int [][] edges,boolean isDirected){
        for(int [] edge: edges){
            int u=edge[0];
            int v=edge[1];
            int w=edge[2];
            if(isDirected)
                adjListWithWeight.get(u).add(new Pair(v,w));
            else{
                adjListWithWeight.get(u).add(new Pair(v,w));
                adjListWithWeight.get(v).add(new Pair(u,w));
            }
        }
    }

    public void findDegreeInUndirectedGraph(int [][] edges,int nodes){
        int [] degree=new int[nodes];
        for(int [] edge:edges){
            int u=edge[0];
            int v=edge[1];
            degree[u]++;
            degree[v]++;
        }
        for(int i=0;i<nodes;i++){
            System.out.println("node " + i + "->" + "degree " + degree[i]);
        }
    }


    public void findDegreeInDirectedGraph(int [][] edges,int nodes){
        int [] inDegree=new int[nodes];
        int [] outDegree=new int[nodes];
        for(int [] edge:edges){
            int from=edge[0];
            int to=edge[1];
            inDegree[to]++;
            outDegree[from]++;
        }
        for(int i=0;i<nodes;i++){
            System.out.println("node " + i + "->" + "Indegree " + inDegree[i] + " - " + "OutDegree " + outDegree[i]);
        }
    }


    public void findDegreeFromAdjList(List<List<Integer>> adjList,boolean isDirected){
        int nodes=adjList.size();
        int [] degree=new int[nodes];
        int [] inDegree=new int[nodes];
        int [] outDegree=new int[nodes];
        for(int i=0;i<adjList.size();i++){
            if(!isDirected){
                degree[i]=adjList.get(i).size();
            } else {
                outDegree[i]=adjList.get(i).size();
                for(int j=0;j<adjList.get(i).size();j++){
                    inDegree[adjList.get(i).get(j)]++;
                }
            }
        }
        System.out.println("Undirected Graph");
        for(int k=0;k<nodes;k++){
            System.out.println("node - > " + k + "degree " + degree[k]);

        }
        System.out.println("Directed Graph");
        for(int k=0;k<nodes;k++){
            System.out.print("node " + k +" - > " + "in-degree " + inDegree[k] + " - ");
            System.out.print( "out-degree " + outDegree[k]);
            System.out.println();
        }
    }


    public void findDegreeFromAdjMatrix(int [][] adjMatrix,boolean isDirected){
        int nodes=adjList.size();
        int [] degree=new int[nodes];
        int [] inDegree=new int[nodes];
        int [] outDegree=new int[nodes];
        for(int i=0;i<adjMatrix.length;i++){
            for(int j=0;j<adjMatrix[i].length;j++){
                if(adjMatrix[i][j]==1) {
                    if (!isDirected)
                        degree[i]++;
                    else {
                        inDegree[j]++;
                        outDegree[i]++;
                    }
                }
            }
        }
     /*   System.out.println("Undirected Graph");
        for(int k=0;k<nodes;k++){
            System.out.println("node - > " + k + "degree " + degree[k]);

        } */

        System.out.println("Directed Graph");
        for(int k=0;k<nodes;k++){
            System.out.print("node " + k +" - > " + "in-degree " + inDegree[k] + " - ");
            System.out.print( "out-degree " + outDegree[k]);
            System.out.println();
        }
    }


    public ArrayList<Integer> dfsOfGraph(ArrayList<ArrayList<Integer>> adj) {
        // Code here
        boolean [] visited =new boolean[adj.size()];
        ArrayList<Integer> ans=new ArrayList<>();
        for(int i=0;i<adj.size();i++){
            if(!visited[i])
                dfs(i,visited,ans,adj);
        }
        return ans;
    }

    public void dfs(int node, boolean [] visited,ArrayList<Integer> ans,
                    ArrayList<ArrayList<Integer>> adj){
        visited[node]=true;
        ans.add(node);
        for(int neighbour: adj.get(node)){
            if(!visited[neighbour])
                dfs(neighbour,visited,ans,adj);
        }
    }

    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // code here
        boolean [] visited=new boolean[V];

        ArrayList<Integer> ans=new ArrayList<>();
        for(int i=0;i<V;i++){
            if(!visited[i])
                bfs(i,visited,adj,ans);
        }
        return ans;
    }

    public void bfs(int node,boolean [] visited,ArrayList<ArrayList<Integer>> adj,
                    ArrayList<Integer> ans){
        Queue<Integer> q=new LinkedList<>();
        visited[node]=true;
        q.offer(node);
        while(!q.isEmpty()){
            int node1=q.poll();
            ans.add(node1);
            for(int neighbour:adj.get(node)){
                if(!visited[neighbour]){
                    visited[neighbour]=true;
                    q.offer(neighbour);
                }
            }
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int rows=image.length;
        int cols=image[0].length;
       // boolean [][] visited=new boolean[rows][cols];
        //if(!visited[sr][sc])
            floodFillDfs(image,sr,sc,image[sr][sc],color,rows,cols);
        return image;
    }

    private void floodFillDfs(int[][] image, int row, int col, int curColor, int newColor,
    int rows,int cols) {
        if(row<0 || row>=rows || col<0 || col>=cols || image[row][col] != curColor || image[row][col]==newColor)
            return;
       // visited[row][col]=true;
        image[row][col]=newColor;
        int [][] adjList={{row-1,col},{row+1,col},{row,col-1},{row,col+1}};
        for(int [] neighbour: adjList){
            floodFillDfs(image,neighbour[0],neighbour[1],curColor,newColor,rows,cols);
        }
    }


    public int[][] floodFillBfs(int[][] image, int sr, int sc, int color) {
        int rows=image.length;
        int cols=image[0].length;
        Queue<int[]> q=new LinkedList<>();
        q.offer(new int[]{sr,sc});
        int curColor=image[sr][sc];
        while(!q.isEmpty()){
            int [] node =q.poll();
            int row=node[0];
            int col=node[1];
            int [][] adjList={{row-1,col},{row+1,col},{row,col-1},{row,col+1}};
            for(int [] neighbour: adjList){
                int r=neighbour[0];
                int c=neighbour[1];
                if(r<0 || r>=rows || c<0 || c>=cols || image[r][c] != curColor || image[r][c]==color)
                    continue;
                q.offer(new int[]{r,c});
                image[r][c]=color;
            }
        }
        return image;
    }

    public int numIslands(char[][] grid) {
        int rows=grid.length;
        int cols=grid[0].length;
        int islands=0;
        boolean [][] visited=new boolean[rows][cols];
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(!visited[i][j] && grid[i][j]=='1'){
                    dfs(i,j,rows,cols,grid,visited);
                    islands++;
                }

            }
        }
        return islands;
    }
    public void dfs(int i, int j, int rows, int cols,char[][] grid, boolean [][] visited){
        if(i<0 || i>=rows || j<0 || j>=cols || grid[i][j]=='0' || visited[i][j])
            return;
        visited[i][j]=true;
        int [][] adjList={{i-1,j},{i+1,j},{i,j-1},{i,j+1}};
        for(int [] neighbour:adjList){
            dfs(neighbour[0],neighbour[1],rows,cols,grid,visited);
        }
    }


    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set=new HashSet<>(wordList);
        Queue<String> q=new LinkedList<>();
        int level=0;
        q.offer(beginWord);
        if(set.contains(beginWord))
            set.remove(beginWord);
        while(!q.isEmpty()){
            int curLevel=q.size();
            for(int i=0;i<curLevel;i++) {
                String node = q.poll();
                if (node.equals(endWord))
                    return level + 1;
                for (String neighbour : getNeighbours(node, set)) {
                    if (set.contains(neighbour)) {
                        q.offer(neighbour);
                        set.remove(neighbour);
                    }
                }
            }
            level++;
        }
        return 0;
    }

    private List<String> getNeighbours(String word, HashSet<String> set) {
        List<String> res=new ArrayList<>();
        for(int i=0;i<word.length();i++){
            for(char ch='a';ch<='z';ch++){
                if(word.charAt(i)==ch)
                    continue;
                String newWord=word.substring(0,i)+ch+word.substring(i+1,word.length());
                if(set.contains(newWord))
                    res.add(newWord);
            }
        }
        return res;
    }


    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set=new HashSet<>(wordList);
        Queue<List<String>> q=new LinkedList<>();
        List<List<String>> res =new ArrayList<>();
        List<String> dummy=new ArrayList<>();

        dummy.add(beginWord);
        int level=0;
        int resLevel =-1;
        q.offer(dummy);
        if(set.contains(beginWord))
            set.remove(beginWord);
        while(!q.isEmpty()){
            HashSet<String> usedWords=new HashSet<>();
            int curLevel=q.size();
            for(int i=0;i<curLevel;i++) {
                List<String> nodeList = q.poll();
                String node=nodeList.get(nodeList.size()-1);
                if (node.equals(endWord)) {
                    resLevel=level;
                    res.add(nodeList);
                }
                for (String neighbour : getNeighbours(node, set)) {
                    if (set.contains(neighbour)) {
                        nodeList.add(neighbour);
                        q.offer(new ArrayList<>(nodeList));
                        nodeList.remove(neighbour);
                        usedWords.add(neighbour);
                        //set.remove(neighbour);
                    }
                }
            }
            for(String visited: usedWords)
                set.remove(visited);
            if(level==resLevel)
                break;
            level++;

        }
        return res;
    }

    // Evaluate Division
    /*
    Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation:
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
note: x is undefined => -1.0
     */
    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String,HashMap<String,Double>> map=new HashMap<>();
        double [] res=new double[queries.size()];
        for(int i=0;i<equations.size();i++){
            String dividend=equations.get(i).get(0);
            String divisor=equations.get(i).get(1);
            double val=values[i];
            if(!map.containsKey(dividend)){
                map.put(dividend,new HashMap<String,Double>());
            }
            if(!map.containsKey(divisor)){
                map.put(divisor,new HashMap<String,Double>());
            }
            map.get(dividend).put(divisor,val);
            map.get(divisor).put(dividend,1/val);
        }

        for(int i=0;i<queries.size();i++){
            String dividend=queries.get(i).get(0);
            String divisor=queries.get(i).get(1);
            if(!map.containsKey(dividend) || !map.containsKey(divisor))
                res[i]=-1.0;
            else if(dividend.equals(divisor))
                res[i]=1.0;
            else{
                HashSet<String> visited=new HashSet<>();
                res[i]=dfsWithBacktracking(dividend,divisor,1,visited,map);
            }
        }
        return res;
    }

    private static double dfsWithBacktracking(String src, String target, double prod, HashSet<String> visited, HashMap<String, HashMap<String, Double>> map) {
        double ret = -1;
        visited.add(src);
        if(map.get(src).containsKey(target)){
            ret=prod*map.get(src).get(target);
        }else{
            for(String neighbour:map.get(src).keySet()){
                if(!visited.contains(neighbour)){
                    prod=prod*map.get(src).get(neighbour);
                    ret=dfsWithBacktracking(neighbour,target,prod,visited,map);
                    if(ret != -1)
                        break;
                    prod=prod/map.get(src).get(neighbour);
                }
            }
        }
        visited.remove(src);
        return ret;
    }

    public static List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        Queue<Integer> q=new LinkedList<>();
        HashSet<Integer> visited=new HashSet<>();
        int curLevel=0;
        q.offer(id);
        visited.add(id);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                int curId=q.poll();
                for(int friend:friends[curId]){
                    if(!visited.contains(friend)){
                        visited.add(friend);
                        q.offer(friend);
                    }
                }
            }
            curLevel++;
            if(curLevel==level)
                break;
        }
        List<VideoPair> videoPairs=new ArrayList<>();
        List<String> res=new ArrayList<>();
        HashMap<String,Integer> freqMap=new HashMap<>();
        while(!q.isEmpty()){
            int curId=q.poll();
            for(String video:watchedVideos.get(curId)){
                freqMap.put(video,freqMap.getOrDefault(video,0)+1);
            }
        }
        for(String video:freqMap.keySet())
            videoPairs.add(new VideoPair(video,freqMap.get(video)));
        Collections.sort(videoPairs);
        for(VideoPair pair:videoPairs)
            res.add(pair.id);
        return res;
    }

    public static boolean isCycle(ArrayList<ArrayList<Integer>> adj) {
        boolean [] visited=new boolean[adj.size()];
        for(int i=0;i<adj.size();i++){
            if(!visited[i] && checkIfCycle(i,adj,visited))
                return true;
        }
        return false;
    }

    private static boolean checkIfCycle(int src, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        Queue<int[]> q =new LinkedList<>();
        visited[src]=true;
        q.offer(new int[]{src,-1});
        while(!q.isEmpty()){
            int [] pair=q.poll();
            int node=pair[0];
            int parent=pair[1];
            for(int neighbour:adj.get(node)){
                if(neighbour==parent) continue;
                if(visited[neighbour]) return true;
                else{
                    q.offer(new int[]{neighbour,node});
                    visited[neighbour]=true;
                }
            }
        }
        return false;
     }

    // 994. Rotting Oranges
    // [[2,1,1],[1,1,0],[0,1,1]]
    public int orangesRotting(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int countOfFreshOranges=0;
        Queue<int[]> queue=new LinkedList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    countOfFreshOranges++;
                } else if(grid[i][j]==2){
                    queue.offer(new int[]{i,j});
                }
            }
        }
        if(countOfFreshOranges==0)
            return 0;

        int time=0;
        while(!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                int rottenLoc[]=queue.poll();
                int r=rottenLoc[0];
                int c=rottenLoc[1];
                int [][] neighbours ={{r-1,c},{r+1,c},{r,c-1},{r,c+1}};
                for(int neighbour[]:neighbours){
                    int nr = neighbour[0];
                    int nc = neighbour[1];
                    if(nr<0 || nr>=n || nc<0 || nc>=m || grid[nr][nc]==2 || grid[nr][nc]==0)
                        continue;

                    queue.offer(new int[]{nr,nc});
                    grid[r][c]=2;
                }
            }
            time++;
            countOfFreshOranges-=queue.size();
            if(countOfFreshOranges==0)
                break;
        }
        if(countOfFreshOranges==0)
            return time;
        return -1;
    }


    public int orangesRottingOptimized(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int freshCount=0;
        // o(n*m)
        Queue<int[]> queue = new LinkedList<>();
        // O(n*m)
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    freshCount++;
                }else if(grid[i][j]==2){
                    queue.offer(new int[]{i,j});
                }
            }
        }
        if(freshCount==0){
            return 0;
        }
        // O(n*m)
        int time=0;
        while(!queue.isEmpty()){
            int size = queue.size();
            //level by level
            for(int i=0;i<size;i++){
                int rottenLoc[] = queue.poll();
                int r = rottenLoc[0];
                int c = rottenLoc[1];
                int neighbours[][] = {{r-1,c},{r,c+1},{r+1,c},{r,c-1}};
                for(int neighbour[] : neighbours){
                    int nr = neighbour[0];
                    int nc = neighbour[1];
                    //out bound / rotten - visited
                    if(nr<0 || nr>=n || nc<0 || nc>=m || grid[nr][nc]==2 || grid[nr][nc] == 0){
                        continue;
                    }
                    queue.offer(new int[]{nr,nc});
                    grid[nr][nc]=2;
                    freshCount--;
                    if(freshCount==0){
                        return time+1;
                    }
                }
            }
            time++;
        }
        return -1;
    }

    public static void main(String[] args) {
       /* int nodes=4;
        int edges[][] = {{0,2},{0,1},{1,3}};
        System.out.println("UnDirected Graph->");
        Graph graph4 = new Graph(nodes);
        graph4.addEdgesInAdjList(edges,false);
        graph4.printList();
        System.out.println("Directed Graph->");
        Graph graph5 = new Graph(nodes);
        graph5.addEdgesInAdjList(edges,true);
        graph5.printList(); */

       // int edges[][] = {{0,2},{0,1},{1,3}};
        int edges[][] = {{0,2},{0,1}};
        int nodes = 3;
        System.out.println("UnDirected Graph->");
        Graph graph = new Graph(nodes);
        graph.addEdgesInAdjMatrix(edges,false);
        graph.printMatrix();
        System.out.println("Directed Graph->");
        Graph graph1 = new Graph(nodes);
        graph1.addEdgesInAdjMatrix(edges,true);
        graph1.printMatrix();

        Graph graph8 = new Graph(3);
        System.out.println("UnDirected Graph");
        graph8.findDegreeInUndirectedGraph(edges,3);
        // --- directed graph --- //
        //find indegree of nodes using edges
        //find outdegree of nodes using edges
        System.out.println("Directed Graph");
        graph8.findDegreeInDirectedGraph(edges,3);

        //System.out.println(graph4.adjList);
        System.out.println("Question");
      //  graph5.findDegreeFromAdjList(graph5.adjList,true);
        graph.findDegreeFromAdjMatrix(graph1.adjMatrix,true);

        List<List<String>> equations=new ArrayList<>();
        List<String> equation2=new ArrayList<>();
        equation2.add("a");
        equation2.add("b");
        List<String> equation=new ArrayList<>();
        equation.add("a");
        equation.add("e");
        List<String> equation1=new ArrayList<>();
        equation1.add("e");
        equation1.add("m");
        equations.add(equation2);
        equations.add(equation);
        equations.add(equation1);
        double [] values=new double[]{2.0,3.0,6.0};
        List<List<String>> queries=new ArrayList<>();
        List<String> query=new ArrayList<>();
        query.add("a");
        query.add("m");
        queries.add(query);
       // System.out.println(equations);
        double [] res=calcEquation(equations,values,queries);
        for(double d:res)
            System.out.println(d);

        List<List<String>> watchedVideos=new ArrayList<>();
        String [][] vid=new String[][]{{"A","B"},{"C"},{"B","C"},{"D"}};
        watchedVideos = Arrays.stream(vid).map(Arrays::asList).toList();
        int [][] friends = {{1,2},{0,3},{0,3},{1,2}};
        int id=0;
        System.out.println("test " + watchedVideosByFriends(watchedVideos,friends,0,2));

//        List<Integer> numbers= Arrays.asList(2,1,4,7,10);
//        Stream<Integer> numberStream= numbers.stream().filter((Integer val) -> val>=3).peek((Integer val) -> System.out.println(val));

        List<Integer> numbers= Arrays.asList(2,1,3,4,6);
//        Stream<Integer> numberStream=
//                numbers.stream().filter(val-> val>2).peek(val -> System.out.println(val))
//                       .map(val -> 2*val);

        System.out.println(numbers.stream().filter(val -> val>3).max((val1,val2)-> val1-val2).get());

       // List<Integer> numberList=numberStream.collect(Collectors.toList());
    }

}
