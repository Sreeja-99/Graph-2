//TC: O(v+e) ; SC: O(V)
class Solution {
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n=graph.length;
        int[] colors=new int[n];
        int cl=0;

        Arrays.fill(colors,-1);

        //Identifies number of connected components
        for(int i=0;i<n;i++){
            if(colors[i]==-1){
                buildGraph(graph, colors, cl, i);
                cl+=1;
            }  
        }

        //Number of nodes in each connected component
        int[] groups=new int[cl];
        for(int i=0;i<n;i++){
            groups[colors[i]]+=1;
        }
        

        //Number of malware nodes in each connected component
        int[] init=new int[cl];
        for(int i:initial){
            init[colors[i]]+=1;
        }

        int result=Integer.MAX_VALUE;
        for(int i:initial){
            //If number of malware nodes in each connected component is 1
            if(init[colors[i]] == 1){
                if(result==Integer.MAX_VALUE){
                    result=i;
                }else if(groups[colors[i]] > groups[colors[result]]){//If number of nodes can be saved by eliminating current malware node > number of nodes can be saved by prev malware node i.e., result
                    result=i;
                }else if(groups[colors[i]] == groups[colors[result]]){//If both are same, choose the one with lowest value
                    if(result>i){
                        result=i;
                    }
                }
            }
        }

        //If there is no node can be saved even if malware node is saved.
        if(result==Integer.MAX_VALUE){
            for(int i:initial){
                result=Math.min(i,result);
            }
        }

        return result;
    }

    private void buildGraph(int[][] graph,int[] groups, int cl, int i){
        if(groups[i]!=-1) return;

        groups[i]=cl;

        for(int j=0;j<graph.length;j++){
            if(graph[i][j]==1){
                buildGraph(graph,groups,cl,j);
            }
        }
    }
}
