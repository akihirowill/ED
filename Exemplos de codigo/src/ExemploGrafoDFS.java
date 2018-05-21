import java.util.Iterator;
import java.util.LinkedList;

//Exemplo de Deep First Search
public class ExemploGrafoDFS {
    private int V;   // No. de verticies

    // Array de lista de adjacencias
    private LinkedList<Integer> adj[];

    // Construtor
    ExemploGrafoDFS(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    //Funcao para  add uma aresta no grafo
    void addEdge(int v, int w)
    {
        adj[v].add(w);  // Add w to v's list.
    }

    // funcao utilizada pelo DFS
    void DFSUtil(int v,boolean visited[])
    {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v+" ");

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext())
        {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    // funcaco para fazer o DFS traversal. utiliza recursao do  DFSUtil()
    void DFS(int v)
    {
        // Mark all the vertices as not visited(set as
        // false by default in java)
        boolean visited[] = new boolean[V];

        // Call the recursive helper function to print DFS traversal
        DFSUtil(v, visited);
    }

}
