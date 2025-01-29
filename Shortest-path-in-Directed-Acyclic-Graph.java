Given a Directed Acyclic Graph of V vertices from 0 to n-1 and a 2D Integer array(or vector) edges[ ][ ] of length E, where there is a directed edge from edge[i][0] to edge[i][1] with a distance of edge[i][2] for all i.

Find the shortest path from src(0) vertex to all the vertices and if it is impossible to reach any vertex, then return -1 for that vertex.

Examples :

Input: V = 4, E = 2, edges = [[0,1,2], [0,2,1]]
Output: [0, 2, 1, -1]
Explanation: Shortest path from 0 to 1 is 0->1 with edge weight 2. Shortest path from 0 to 2 is 0->2 with edge weight 1. There is no way we can reach 3, so it's -1 for 3.
Input: V = 6, E = 7, edges = [[0,1,2], [0,4,1], [4,5,4], [4,2,2], [1,2,3], [2,3,6], [5,3,1]]
Output: [0, 2, 3, 6, 1, 5]
Explanation: Shortest path from 0 to 1 is 0->1 with edge weight 2. Shortest path from 0 to 2 is 0->4->2 with edge weight 1+2=3. Shortest path from 0 to 3 is 0->4->5->3 with edge weight 1+4+1=6. Shortest path from 0 to 4 is 0->4 with edge weight 1.Shortest path from 0 to 5 is 0->4->5 with edge weight 1+4=5.
Constraint:
1 <= V <= 100
1 <= E <= min((N*(N-1))/2,4000)
0 <= edgesi,0, edgesi,1 < n
0 <= edgei,2 <=105
  
CODE:
class Pair{
    int v;
    int w;
    Pair(int v,int w){
        this.v = v;
        this.w = w;
    }
}
class Solution {

    public int[] shortestPath(int V, int E, int[][] edges) {
        // Code here
        
        
        ArrayList<ArrayList<Integer>>[] graph = new ArrayList[V];
        
        int[] ans = new int[V];
        Arrays.fill(ans,Integer.MAX_VALUE);
        ans[0]=0;
        for(int i=0;i<V;i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0;i<edges.length;i++){
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];
            ArrayList<Integer> v_weight = new ArrayList<>();
            v_weight.add(v);
            v_weight.add(w);
            graph[u].add(v_weight);
        }
        boolean[] visited = new boolean[V];
        
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> a.w-b.w);
        
        Pair p = new Pair(0,0);
        pq.add(p);
        
        while(!pq.isEmpty()){
            
            Pair rem = pq.poll();
            
            
            int curr_v = rem.v;
            int curr_w = rem.w;
            
            if(visited[rem.v] == true) continue;
            visited[rem.v] = true;
            
            for(ArrayList<Integer> nbr : graph[rem.v]){
                int nbr_v = nbr.get(0);
                int nbr_w = nbr.get(1);
                
                if(visited[nbr_v] == false && curr_w+nbr_w < ans[nbr_v]){
                    
                    Pair np = new Pair(nbr_v,curr_w+nbr_w);
                    pq.add(np);
                    ans[nbr_v] = curr_w+nbr_w;
                }
            }
        }
        
        for(int i=0;i<V;i++){
            if(ans[i] == Integer.MAX_VALUE)
            ans[i] = -1;
        }
        return ans;
    }
}
