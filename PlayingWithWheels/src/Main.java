import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        StringBuilder out = new StringBuilder();
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        int [] comeco = new int[4];
        int [] fim = new int[4];
        int [] temp = new int[4];
        int n;

        for (int i = 0; i < t; i++) {
            v.clear();
            if(i != 0)
                in.nextLine();
            for (int j = 0; j < 4; j++) {
                comeco[j] = in.nextInt();
            }
            for (int j = 0; j < 4; j++) {
                fim[j] = in.nextInt();
            }
            n = in.nextInt();
            for (int j = 0; j < n; j++) {
                for (int j2 = 0; j2 < 4; j2++) {
                    temp[j2] = in.nextInt();
                }
                v.add(Arrays.toString(temp));
            }
            out.append(resolver(comeco, fim) + "\n");
        }
        System.out.print(out);
    }

    static HashSet<String> v = new HashSet<String>();


    static int resolver(int [] inicio, int [] finall){
        Queue<elemento> fila = new LinkedList<elemento>();
        String fim = Arrays.toString(finall);
        v.add(Arrays.toString(inicio));
        elemento comeco = new elemento();
        comeco.arr = new int [4];

        for (int i = 0; i < 4; i++) {
            comeco.arr [i] = inicio[i];
        }

        comeco.dist = 0;
        fila.add(comeco);

        while(!fila.isEmpty()){
            elemento atual = fila.poll();
            int [] arr = atual.arr;

            if(fim.equals(Arrays.toString(arr))) {
                return atual.dist;
            }

            for (int i = 0; i < 4; i++) {
                int [] a1 = new int[4];
                int [] a2 = new int[4];

                for (int j = 0; j < 4; j++) {
                    if(i == j){
                        a1[j] = (arr[j] + 1)%10;
                        a2[j] = (arr[j] + 9)%10;
                    }else{
                        a1[j] = a2[j] = arr[j];
                    }
                }

                String s1 = Arrays.toString(a1);
                String s2 = Arrays.toString(a2);

                if(!v.contains(s1)){
                    v.add(s1);
                    elemento e = new elemento();
                    e.arr = a1;
                    e.dist = atual.dist + 1;
                    fila.add(e);
                }
                if(!v.contains(s2)){
                    v.add(s2);
                    elemento e = new elemento();
                    e.arr = a2;
                    e.dist = atual.dist + 1;
                    fila.add(e);
                }
            }
        }
        return -1;
    }

    static class elemento {
        int [] arr;
        int dist;
    }
}