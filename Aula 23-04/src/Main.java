import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        int t = 0;
        try {
            t = Integer.parseInt(buf.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            buf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder resposta = new StringBuilder("");
        while (t-- > 0) {

            ArrayList<String> lista = new ArrayList<String>();
            while (true) {
                try {
                    lista.add(buf.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (lista.get(lista.size() - 1).equals("*")) {
                    break;
                }
            }
            while (true) {
                String s = null;
                try {
                    s = buf.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (s == null || s.length() == 0)
                    break;
                String[] line = s.split(" ");
                int min = BFS(lista, line[0], line[1]);
                resposta.append(line[0] + " " + line[1] + " " + min + "\n");

            }
            if (t != 0)
                resposta.append("\n");
        }
        System.out.print(resposta);
    }

    public static int troca(String primeiro, String segundo) {
        int count = 0;
        if (primeiro.length() != segundo.length())
            return 2;
        for (int i = 0; i < primeiro.length(); i++)
            if (primeiro.charAt(i) != segundo.charAt(i))
                count++;
        return count;
    }

    public static int BFS(ArrayList<String> lista, String primeiro, String ultimo) {
        boolean[] visitado = new boolean[lista.size() - 1];
        Queue<String> fila1 = new LinkedList<String>();
        Queue<Integer> fila2 = new LinkedList<Integer>();
        fila1.add(primeiro);
        fila2.add(0);
        while (!fila1.isEmpty()) {
            String atual = fila1.poll();
            int count = fila2.poll();
            if (atual.equals(ultimo))
                return count;
            for (int i = 0; i < lista.size() - 1; i++)
                if (!visitado[i] && troca(lista.get(i), atual) == 1) {
                    fila1.add(lista.get(i));
                    fila2.add(count + 1);
                    visitado[i] = true;
                }
        }
        return 0;
    }


}