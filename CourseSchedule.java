/*
 * Nilanshu Sharma
 * https://leetcode.com/problems/course-schedule/
 */ 

/* O(|V| + |E|) - Same as DFS */

public class CourseSchedule {
     boolean hasCycle(int s, List<ArrayList<Integer>> graph, boolean[] visited, boolean[] recStack){
        if(visited[s] == true)
            return false;
            
        visited[s] = true;
        recStack[s] = true;
        for(int neighbor : graph.get(s)) {
            if(recStack[neighbor] || hasCycle(neighbor, graph, visited, recStack)){
                return true;
            }
        }
        recStack[s]=false;
        return false;
    }
    
    
    public boolean canFinish(int n, int[][] order) {
        if(n == 0) // There are no courses to take
            return true;
        
        if(order == null || order.length == 0 || order[0].length == 0){ // There are no ordering of the courses
             return true;
        }
        
        List<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        
        for(int i=0;i<n;i++) {
            graph.add(new ArrayList<Integer>());
        }
        for(int i=0;i<=order.length-1;i++) {
            graph.get(order[i][1]).add(order[i][0]);
        }
        
        //Graph is done. Do a topological sort.
        List<Integer> list = new ArrayList<Integer>(); 
        boolean[] visited = new boolean[n];
        boolean[] recStack = new boolean[n];
        
        for(int i=0;i<n;i++){
            if(hasCycle(i, graph, visited, recStack)){
                return false; 
            }
        }
        return true;
    }
}