/*
 * Nilanshu Sharma
 * https://leetcode.com/problems/evaluate-division/
 */ 

public class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        if(equations == null || values == null || equations.length == 0 || values.length == 0) return new double[0]; 
        HashMap<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>(); 
        HashMap<String, ArrayList<Double>> edgeWeight = new HashMap<String, ArrayList<Double>>();
        for(int i=0; i<=equations.length-1; i++){
            String source = equations[i][0]; 
            String destination = equations[i][1];
            if(!graph.containsKey(source)) graph.put(source, new ArrayList<String>());
            if(!graph.containsKey(destination)) graph.put(destination, new ArrayList<String>());
            if(!edgeWeight.containsKey(source)) edgeWeight.put(source, new ArrayList<Double>());
            if(!edgeWeight.containsKey(destination)) edgeWeight.put(destination, new ArrayList<Double>());
            graph.get(source).add(destination);  
            graph.get(destination).add(source);
            edgeWeight.get(source).add(values[i]);
            edgeWeight.get(destination).add(1/values[i]);
        }
        
        double[] result = new double[queries.length]; 
        for(int i=0; i<=queries.length-1; i++){
            double val = dfs(graph,edgeWeight,queries[i][0],queries[i][1],queries[i][0],new HashSet<String>()); 
            if(val == 0.0) result[i] = -1.0; 
            else result[i] = val; 
        }
        return result; 
    }
    
    public double dfs(HashMap<String, ArrayList<String>> graph, HashMap<String, ArrayList<Double>> edgeWeight, String source, String destination, String current, HashSet<String> visited) {
        if(visited.contains(current)) return 0.0;
        if(!graph.containsKey(current)) return 0.0;
        visited.add(current);
        if(current.equals(destination)) return 1.0; 
        List<String> neighbors = graph.get(current); 
        List<Double> weights = edgeWeight.get(current);
        double val = 0.0; 
        for(int i=0; i<=neighbors.size()-1; i++) {
            val = weights.get(i)*dfs(graph, edgeWeight, source, destination, neighbors.get(i), visited);   
            if(val != 0.0) break; 
        }
        return val; 
    }
}