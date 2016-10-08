/*
 * Nilanshu Sharma
 * https://leetcode.com/problems/alien-dictionary/
 */ 

public class AlienDictionary {
   public String alienOrder(String[] words) {
        if(words == null || words.length == 0) { return "";} 
        if(words.length == 1)  return words[0]; 
        HashMap<Character, HashSet<Character>> graph = new HashMap<Character, HashSet<Character>>();
        HashSet<Character> hs = new HashSet<Character>();
        buildCharSet(words, hs);
        buildGraph(graph,hs,words);
        StringBuilder sb = topSort(graph, hs);
        return new String(sb);
    }
    
    public void buildCharSet(String[] words, HashSet<Character> hs){
        for(String s : words){
            for(char c : s.toCharArray()){
                hs.add(c);
            }
        }
    }
    
    public boolean hasCycle(HashMap<Character, HashSet<Character>> graph, StringBuilder sb, HashSet<Character> hs, char c, boolean[] visited, boolean[] recStack){
        if(visited[c-'a']) 
            return false; 
        visited[c-'a']=true;
        recStack[c-'a']=true;
        HashSet<Character> adjList = graph.get(c);
        if(adjList != null){
            for(Character neighbor : adjList){
                if(recStack[neighbor-'a'] || hasCycle(graph, sb, hs, neighbor, visited, recStack)){
                    return true;    
                }
            }
        }
        recStack[c-'a']=false;
        sb.append(c);
        return false;
    }
    
    public StringBuilder topSort(HashMap<Character, HashSet<Character>> graph, HashSet<Character> hs){
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[26];
        boolean[] recStack = new boolean[26];
        for(Character c : graph.keySet()) {
            if(hasCycle(graph, sb, hs, c, visited, recStack)) {
                return new StringBuilder("");
            }
        }
        sb.reverse();
        for(char c : hs)
            sb.append(c);
        return sb;
    }
    
    public void buildGraph(HashMap<Character, HashSet<Character>> graph, HashSet<Character> hs, String[] words){
        for(int i=0; i<=words.length-2; i++) { // Pick a string
            for(int j=i+1; j<=words.length-1;j++){ // Iterate for all other subsequent Strings
                //int j = i+1; Comparing only adjacent strings will also work. 
                for(int k=0; k<Math.min(words[i].length(), words[j].length()); k++){
                    if(words[i].charAt(k) != words[j].charAt(k)){ //Found a match 
                        hs.remove(words[i].charAt(k));
                        hs.remove(words[j].charAt(k));
                        if(graph.containsKey(words[i].charAt(k))){ // Node already exists
                            HashSet<Character> adjList = graph.get(words[i].charAt(k)); //Update the Adj List
                            adjList.add(words[j].charAt(k));
                            graph.put(words[i].charAt(k), adjList);
                        } else {
                            HashSet<Character> adjList = new HashSet<Character>(); //Create a new Adj List
                            adjList.add(words[j].charAt(k));
                            graph.put(words[i].charAt(k), adjList);
                        }
                        break; //found the first non-matching character, rest of the portion of the string doesn't make sense, so break. 
                    }
                }
            }
        }    
    }
}