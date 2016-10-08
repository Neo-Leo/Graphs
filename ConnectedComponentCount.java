/*
 * Nilanshu Sharma
 * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
 */ 

/*
public class ConnectedComponentCount {
    public void DFSUtil(ArrayList<ArrayList<Integer>> graph, boolean[] visited, int node){
        if(visited[node])
            return;
        visited[node] = true;    
        for(int neighbor : graph.get(node)){
            DFSUtil(graph, visited, neighbor);
        }
    }
    
    public int DFS(ArrayList<ArrayList<Integer>> graph, int n){
        int cc_count = 0;
        boolean[] visited = new boolean[n];
        for(int i=0;i<=n-1;i++){
            if(!visited[i]){
                cc_count++;
                DFSUtil(graph, visited, i);
            }
        }
        return cc_count;
    }
    
    public int countComponents(int n, int[][] edges) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        if(edges == null || edges.length == 0 || edges[0].length == 0) 
            return n;
        for(int i=0; i<=n-1; i++)
            graph.add(new ArrayList<Integer>());
        for(int i=0; i<=edges.length-1; i++){
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }    
        
        return DFS(graph,n);
    }
}
*/ 

// Revision 6th September, DFS pretty Slow

/*
public class ConnectedComponentCount {
    
    public void DFSUtil(LinkedList<LinkedList<Integer>> graph, boolean[] visited, int i){
        if(visited[i]) return; 
        visited[i] = true; 
        for(Integer neighbor : graph.get(i)) {
            DFSUtil(graph, visited, neighbor);
        }
    }
    
    public int DFS(LinkedList<LinkedList<Integer>> graph){
        int countCC = 0, n=graph.size(); 
        boolean[] visited = new boolean[graph.size()]; 
        for(int i=0; i<n; i++){
            if(!visited[i]){
                DFSUtil(graph,visited,i); 
                countCC++; 
            }
        }
        return countCC; 
    }
    
    public int countComponents(int n, int[][] edges) {
        if(n <= 0) return 0; 
        if(edges == null || edges.length == 0 || edges[0].length == 0) return n; 
        LinkedList<LinkedList<Integer>> graph = new LinkedList<LinkedList<Integer>>(); 
        for(int i=0; i<=n-1; i++){
            graph.add(new LinkedList<Integer>()); 
        }
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]); 
            graph.get(edge[1]).add(edge[0]); 
        }
        return DFS(graph);
    }
}
*/ 

// Revision 6th September, BFS pretty Slow

/*
public class ConnectedComponentCount {
    public void BFSUtil(LinkedList<LinkedList<Integer>> graph, boolean[] visited, int i){
        Queue<Integer> queue = new LinkedList<Integer>(); 
        queue.offer(i);
        while(!queue.isEmpty()){
            int curr = queue.poll(); 
            visited[curr] = true; // Process it
            for(Integer neighbor : graph.get(curr)) {
                if(!visited[neighbor]) { // If it not already been process, then only add to the queue. 
                    queue.offer(neighbor);
                }    
            }
        }
    }
    
    public int BFS(LinkedList<LinkedList<Integer>> graph){
        int countCC = 0, n=graph.size(); 
        boolean[] visited = new boolean[graph.size()]; 
        for(int i=0; i<n; i++){
            if(!visited[i]){
                BFSUtil(graph,visited,i); 
                countCC++; 
            }
        }
        return countCC; 
    }
    
    public int countComponents(int n, int[][] edges) {
        if(n <= 0) return 0; 
        if(edges == null || edges.length == 0 || edges[0].length == 0) return n; 
        LinkedList<LinkedList<Integer>> graph = new LinkedList<LinkedList<Integer>>(); 
        for(int i=0; i<=n-1; i++){
            graph.add(new LinkedList<Integer>()); 
        }
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]); 
            graph.get(edge[1]).add(edge[0]); 
        }
        return BFS(graph);
    }
}
*/ 

// Union Find Algorithm, a bit fast, every union operation is O(N) 

/*
public class ConnectedComponentCount {
    public void union(int v1, int v2, int n, int[] parent){
        int pV1 = parent[v1]; 
        for(int i=0; i<n; i++){
            if(parent[i] == pV1){
                parent[i] = parent[v2]; 
            }
        }
    }
    
    public int UnionFind(int[][] edges, int n){
        int[] parent = new int[n]; 
        for(int i=0; i<=n-1; i++){
            parent[i] = i;  
        }
        for(int[] edge : edges){
            int v1 = edge[0]; 
            int v2 = edge[1]; 
            union(v1,v2,n,parent); 
        }
        HashSet<Integer> hs = new HashSet<Integer>(); 
        for(int elem : parent){
            hs.add(elem); 
        }
        return hs.size(); 
    }
    
    public int countComponents(int n, int[][] edges) {
        if(n <= 0) return 0; 
        if(edges == null || edges.length == 0 || edges[0].length == 0) return n; 
        return UnionFind(edges, n); 
    }
}
*/ 

public class ConnectedComponentCount {
    public int root(int v, int[] parent){
        while(v != parent[v]){
            v = parent[v]; 
        }
        return v; 
    }
    
    public int UnionFind(int[][] edges, int n){
        int[] parent = new int[n]; 
        for(int i=0; i<=n-1; i++){
            parent[i] = i;  
        }
        for(int[] edge : edges){
            int root1 = root(edge[0]); 
            int root2 = root(edge[1]); 
            if(root1 != root2){
                parent[root1] = root2; 
            } 
        }
    }
    
    public int countComponents(int n, int[][] edges) {
        if(n <= 0) return 0; 
        if(edges == null || edges.length == 0 || edges[0].length == 0) return n; 
        return UnionFind(edges, n); 
    }
}