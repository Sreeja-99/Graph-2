//Tarjan's algorithm
//Here the edges which are not part of loop or cycle are critical edges
//Nodes which are not part of cycle can be identified in a single pass with Tarjan algorithm
//This algorithm have discover array -  which shows whether node is discovered or not
//lowest array - shows the time at which the node is discovered
//start from any node - here starting form 0
//To prevent visiting same current node again pass parent node
//For every iteration, if node is not discovered, add time to it and add time to lowest node. 
//If node is already discovered, disover of current node is less than lowest of neighbour -> means node is not part of cycle. Add to result and update lowest of curr node to min of lowest of curr and lowest of neighbor
//TC: O(V+E)
//SC: O(V+E)
class Solution {
    List<List<Integer>> result;
    int[] discover;
    int[] lowest;
    Map<Integer,List<Integer>> map;
    int time;


    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.map=new HashMap<>();
        this.time=0;

        for(List<Integer> curr:connections){
            int first=curr.get(0);
            int second=curr.get(1);

            if(!(map.containsKey(first))){
                map.put(first, new ArrayList<>());
            }

            map.get(first).add(second);

            if(!(map.containsKey(second))){
                map.put(second, new ArrayList<>());
            }

            map.get(second).add(first);
        }

        this.result=new ArrayList<>();
        this.discover=new int[n];
        this.lowest=new int[n];

        Arrays.fill(discover,-1);

        dfs(0,0);

        return result;
    }

    private void dfs(Integer v, Integer u){
        //base case
        if(discover[v]!=-1) return;

        //logic
        discover[v]=time;
        lowest[v]=time;
        time+=1;

        for(int n:map.get(v)){
            if(u==n) continue;
            dfs(n,v);

            if(discover[v]<lowest[n]){
                result.add(Arrays.asList(v,n));
            }

            lowest[v]=Math.min(lowest[n],lowest[v]);
        }
    }
}
