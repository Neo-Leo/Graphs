/*
 * Nilanshu Sharma
 * https://leetcode.com/problems/minimum-genetic-mutation/
 */ 

public class Solution {
    public int minMutation(String start, String end, String[] bank) {
        HashSet<String> visited = new HashSet<String>(); 
        HashSet<String> dict = new HashSet<String>(); 
        for(String s : bank){
            dict.add(s); 
        }
        if(start.equals(end)) return 0; 
        char[] charSet = {'A','C','G','T'}; 
        Queue<String> queue = new LinkedList<String>(); 
        queue.offer(start);
        visited.add(start); 
        queue.offer(null);
        int distance = 0;
        while(!queue.isEmpty()) {
            String current = queue.poll(); 
            if(current == null) {
                if(queue.isEmpty()) break; 
                queue.offer(null);
                distance++; 
            } else {
                for(int i=0; i<=7; i++){
                    for(int j=0; j<=3; j++){
                        char[] currentArray = current.toCharArray();
                        currentArray[i] = charSet[j]; 
                        String neighbor = new String(currentArray);
                        if(dict.contains(neighbor) && !visited.contains(neighbor)) {
                            if(neighbor.equals(end)) return distance+1; 
                            visited.add(neighbor); 
                            queue.offer(neighbor);
                        }
                    }
                }
            }
        }
        return -1; 
    }
}