import java.util.Scanner;
import java.util.Stack;

class Main {

    public static void main(String args[]) {
        int testes = 1;
        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {

            System.out.println("Test Set #" + (testes++));
            int[] indice = new int[20];
            int[][] lista = new int[20][20];

            for (int i = 0; i < 19; i++) {
                int x = scan.nextInt();
                for (int j = 0; j < x; j++) {
                    int atual = scan.nextInt() - 1;
                    lista[i][indice[i]++] = atual;
                    lista[atual][indice[atual]++] = i;
                }
            }

            int q = scan.nextInt();

            for (int i = 0; i < q; i++) {
                int comeco = scan.nextInt() - 1;
                int fim = scan.nextInt() - 1;
                boolean[] checado = new boolean[20];

                checado[comeco] = true;
                Stack<Integer> atual = new Stack<Integer>();
                atual.push(comeco);

                boolean encontrou = false;
                int resp = 0;

                while (!encontrou) {
                    Stack<Integer> novo = new Stack<Integer>();
                    resp++;

                    while (!atual.isEmpty()) {

                        int temp = atual.pop();
                        for (int k = 0; k < indice[temp]; k++) {
                            if (lista[temp][k] == fim) {
                                encontrou = true;
                                break;
                            } else if (!checado[lista[temp][k]]) {
                                novo.push(lista[temp][k]);
                                checado[lista[temp][k]] = true;
                            }
                        }
                        if (encontrou)
                            break;
                    }
                    atual = novo;
                }
                System.out.format("%2d to %2d: %d\n", comeco + 1, fim + 1, resp);
            }
            System.out.println();
        }
        scan.close();
    }
}