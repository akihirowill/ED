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
        // marca o no atual como visitado
        visited[v] = true;
        System.out.print(v+" ");

        // recursao para tds verticies adjacentes
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
        // Marca todas as vertices (seta como falso por default)
        boolean visited[] = new boolean[V];

        // chama o helper recursivamanete para imprimir o DFS Transversal
        DFSUtil(v, visited);
    }

    public static void main(String[] args) {
        ExemploGrafoDFS g = new ExemploGrafoDFS(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("DFS Traversal "+
                "(comecando do verticie 2)");

        g.DFS(2);
    }
}
