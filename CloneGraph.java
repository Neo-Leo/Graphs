/*
 * Nilanshu Sharma
 * https://leetcode.com/problems/clone-graph/
 */ 


public class CloneGraph {
    // This is the map which stores the nodes which are already created in the cloned graph 
    private Map<Integer, UndirectedGraphNode> hm = new  HashMap<Integer, UndirectedGraphNode>();    
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) return null; 

        if(hm.containsKey(node.label)) return hm.get(node.label); // If the node is aleady created, return it. 

        UndirectedGraphNode clonedNode = new UndirectedGraphNode(node.label); // Else create it. 
        hm.put(node.label, clonedNode); // And put it on the HashMap

        for(UndirectedGraphNode neighbor : node.neighbors){
            clonedNode.neighbors.add(cloneGraph(neighbor)); 
        }

        return clonedNode; 
    }
}