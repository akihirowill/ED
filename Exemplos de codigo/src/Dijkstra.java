import java.util.Scanner;

public class Dijkstra
{
    static Scanner scan;

    public static void main(String[] args)
    {
        int[] preD = new int[5];
        int min = 999, nextNode = 0; // min detém o valor mínimo, nextNode mantém o valor para o próximo nó.
        scan = new Scanner(System.in);
        int[] distance = new int[5]; // matriz de distancia
        int[][] matrix = new int[5][5]; // matriz atual
        int[] visited = new int[5]; // array visitado

        System.out.println("Entre com o valor da matriz:");

        for (int i = 0; i < distance.length; i++)
        {
            visited[i] = 0; //inicializa os arrays visitados com 0
            preD[i] = 0;

            for (int j = 0; j < distance.length; j++)
            {
                matrix[i][j] = scan.nextInt(); //preenche a matriz
                if (matrix[i][j]==0)
                    matrix[i][j] = 999; // substitui os 0 com  999
            }
        }

        distance = matrix[0]; //inicializa o  array  de distancia
        visited[0] = 1; // seta o no como visitado
        distance[0] = 0; //define a distância  para zero, que é o ponto de partida

        for (int counter = 0; counter < 5; counter++)
        {
            min = 999;
            for (int i = 0; i < 5; i++)
            {
                if (min > distance[i] && visited[i]!=1)
                {
                    min = distance[i];
                    nextNode = i;
                }
            }

            visited[nextNode] = 1;
            for (int i = 0; i < 5; i++)
            {
                if (visited[i]!=1)
                {
                    if (min+matrix[nextNode][i] < distance[i])
                    {
                        distance[i] = min+matrix[nextNode][i];
                        preD[i] = nextNode;
                    }

                }

            }

        }

        for(int i = 0; i < 5; i++)
            System.out.print("|" + distance[i]);

        System.out.println("|");

        int j;
        for (int i = 0; i < 5; i++)
        {
            if (i!=0)
            {

                System.out.print("Caminho = " + i);
                j = i;
                do
                {
                    j = preD[j];
                    System.out.print(" <- " + j);
                }
                while(j != 0);
            }
            System.out.println();
        }
    }
}