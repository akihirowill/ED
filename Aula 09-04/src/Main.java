import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        Grafo g = new Grafo(6);
         g.addAresta(2,0);
         g.addAresta(2,3);
         g.addAresta(2,4);
         g.addAresta(0,1);
         g.addAresta(0,4);
         g.addAresta(3,4);
         g.addAresta(3,5);
         g.addAresta(4,1);
         g.addAresta(4,5);
         g.addAresta(5,1);


        g.BFS(2);

        System.out.print("\n");

        g.DSF(2);
    }
}
