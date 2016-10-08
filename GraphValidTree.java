/*
 * Nilanshu Sharma
 * https://leetcode.com/problems/graph-valid-tree/
 */ 

// There are three ways to do this, by union-find, DFS, BFS 
// DFS and BFS have time complexity of O(E+V)

/*
public class GraphValidTree {
    public boolean hasCycleDFS(List<ArrayList<Integer>> graph, int curr, int parent, boolean[] visited, boolean[] recStack){
        if(visited[curr]) return false; 
        visited[curr] = true; 
        recStack[curr] = true; 
        for(int neighbor : graph.get(curr)){
            if((parent != neighbor && recStack[neighbor]) || hasCycleDFS(graph, neighbor, curr, visited, recStack)){
                return true; 
            }
        }
        recStack[curr] = false; 
        return false; 
    }
    
    public boolean validTree(int n, int[][] edges) {
        if(n==0) return true;
        List<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(); 
        for(int i=0;i<=n-1;i++) graph.add(new ArrayList<Integer>());
        
        for(int[] edge : edges){
            int src = edge[0], dst=edge[1];
            graph.get(src).add(dst); 
            graph.get(dst).add(src);
        }
        
        boolean[] visited = new boolean[n];
        boolean[] recStack = new boolean[n];
        
        boolean hasCycle = hasCycleDFS(graph,0, -1, visited, recStack);
        
        if(hasCycle) return false;
        
        for(boolean elem : visited) {
            if(elem == false) return false; 
        }
        
        return true; 
    }
}
*/ 


public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        if(n==0) return true;
        List<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(); 
        for(int i=0;i<=n-1;i++) graph.add(new ArrayList<Integer>());
        
        for(int[] edge : edges){
            int src = edge[0], dst=edge[1];
            graph.get(src).add(dst); 
            graph.get(dst).add(src);
        }
        
        int[] visited = new int[n];
        
        Queue<Integer> q = new LinkedList<Integer>(); 
        q.offer(0);
        visited[0]=1; 
        
        while(!q.isEmpty()){
            int curr = q.poll(); 
            for(Integer neighbor : graph.get(curr)){
                if(visited[neighbor] == 1) {
                    return false; 
                } else if(visited[neighbor] == 0){
                    q.add(neighbor);
                    visited[neighbor] = 1;
                }
            }
            visited[curr] = 2; 
        }
        
        for(int elem : visited) {
            if(elem == 0) return false; 
        }
        
        return true; 
    }
}
