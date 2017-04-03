/*
 * Nilanshu Sharma
 * https://leetcode.com/problems/friend-circles/
 */ 

public class FriendCircles {
    
    public void DFSUtil(List<ArrayList<Integer>> graph, boolean[] visited, int start){
        if(visited[start]) return; 
        visited[start] = true;
        for(int neighbor : graph.get(start)) {
            DFSUtil(graph, visited, neighbor); 
        }
    }
    
    public int findCircleNum(int[][] M) {
        if(M == null) return 0; 
        List<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        int n = M.length, count=0; 
        for(int i=0; i<n; i++) { 
            graph.add(new ArrayList<Integer>());
        } 
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(M[i][j] == 1) {
                    graph.get(i).add(j);
                }
            }
        }
        
        boolean[] visited = new boolean[n]; 
        for(int i=0; i<n; i++) {
            if(!visited[i]) {
                DFSUtil(graph, visited, i);
                count++; 
            }
        }
        
        return count;
    }
}