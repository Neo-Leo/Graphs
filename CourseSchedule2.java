/*
 * Nilanshu Sharma
 * https://leetcode.com/problems/course-schedule-ii/
 */ 

public class CourseSchedule2 {
    
    boolean topSort(int s, List<ArrayList<Integer>> graph, boolean[] visited, boolean[] recStack, List<Integer> list){
        if(visited[s])
            return false;
            
        visited[s] = true;
        recStack[s] = true;
        
        for(int neighbor : graph.get(s)) {
            if(recStack[neighbor] || topSort(neighbor, graph, visited, recStack, list)){
                return true;
            }
        }
        
        recStack[s]=false;
        list.add(s);
        return false;
    }
    
    public int[] findOrder(int n, int[][] order) {
        if(n == 0)
            return new int[n]; 
        
        int[] result = new int[n];
        
        if(order == null || order.length == 0 || order[0].length == 0){
            for(int i=0;i<n;i++)
                result[i]=i;
            return result;
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
            if(topSort(i, graph, visited, recStack, list))
                return new int[0]; 
        }
        int k = 0;
        Collections.reverse(list);
        for(int i : list) {
            result[k++] = i;
        }
        return result;
    }
}