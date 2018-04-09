import java.util.Iterator;
import java.util.LinkedList;

public class Grafo {
    private int V; //para a qtd de vertices
    private LinkedList<Integer> adj[]; //lista adjacente

    Grafo(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList();
        }
    }

    void addAresta(int v, int w) {
        adj[v].add(w);
    }

    void BFS(int s) {
        //Marca todas as vertices como nao visitadas(false) por padrao
        boolean visitado[] = new boolean[V];

        //crio uma nova fila para O BFS
        LinkedList<Integer> fila = new LinkedList();

        //marca o nó atual como visitado(true) e coloca na fila
        visitado[s] = true;
        fila.add(s);
        System.out.print("BSF: ");
        while (fila.size() != 0) {
            //remove da fila e imprime
            s = fila.poll();
            System.out.print(s + " ");

            //pega todos os vertices adj na fila
            //se o adj nao tiver sido visitado seta true
            //depois que visitou coloca na fila novamente

            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visitado[n]) {
                    visitado[n] = true;
                    fila.add(n);
                }
            }


        }
    }

    void DFSUtil(int v,boolean visitado[]){
       visitado[v] = true;
        Iterator<Integer> i = adj[v].listIterator();


        while (i.hasNext())
        {
            int n = i.next();
            if (!visitado[n])
                DFSUtil(n, visitado);
        }
        System.out.print(v +" -> ");
    }

    void DSF(int v){
        boolean visitado[] = new boolean[V];
        System.out.print("DSF: ");
        DFSUtil(v,visitado);

    }

}
