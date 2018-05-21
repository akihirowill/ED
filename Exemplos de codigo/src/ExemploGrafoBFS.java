import java.util.Iterator;
import java.util.LinkedList;

public class ExemploGrafoBFS {
    private int V;   // No. de vertices
    private LinkedList<Integer> adj[]; // Listas de adjacencia

    // Construtor
    ExemploGrafoBFS(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    // funcao para add uma aresta no grafo
    void addEdge(int v,int w)
    {
        adj[v].add(w);
    }

    // imprime BFS traversal dado um s
    void BFS(int s)
    {
        //marca todas as verticies como nao visitadas (por padrao setada como false)
        boolean visited[] = new boolean[V];

        // cria uma  queue para BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // marca o no atual como visitado e tira da fila
        visited[s]=true;
        queue.add(s);

        while (queue.size() != 0)
        {
            // retira o verticie da fila e imprime
            s = queue.poll();
            System.out.print(s+" ");

            // Obter todos os vértices adjacentes do vértice demarcado s
            // Se um adjacente não tiver sido visitado, marque-o como visitado e enfileire-o
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    public static void main(String[] args) {
        ExemploGrafoBFS g = new ExemploGrafoBFS(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("BFS "+
                "(comecando do verticie 2)");

        g.BFS(2);
    }
}
