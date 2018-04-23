public class ShortestPath {
    final static int INF = 99999, V=4;

    void floydWarshall(int graph[][]){
        int dist[][] = new int[V][V];

        for (int i = 0; i < V; i++){
            for (int j = 0; j < V; j++){
                dist[i][j] = graph[i][j];
            }
        }

        for (int k = 0; k < V; k++)
        {

            for (int i = 0; i < V; i++)
            {
                for (int j = 0; j < V; j++)
                {

                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
        imprime(dist);

    }
    void imprime(int dist[][])
    {
        System.out.println("Imprime Solução:");
        for (int i=0; i<V; ++i)
        {
            for (int j=0; j<V; ++j)
            {
                if (dist[i][j]==INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String args[]){
        int graph[][] = { {  0,  2,  5,INF},
                          {  1,  0,  4,INF},
                          {  2,  1,  0,INF},
                          {INF,INF,INF,  0},

        };

        ShortestPath a = new ShortestPath();


        a.floydWarshall(graph);
    }


}
