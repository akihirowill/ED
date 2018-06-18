import java.util.Scanner;

class Main{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while(scan.hasNext()){
            int indice[] = new int[20];
            int lista[][] = new int[20][20];

            for(int i = 0;i < 19;i++){
                int x = scan.nextInt();
                for(int j = 0;j < x;j++){
                    int atual = scan.nextInt() - 1;
                    lista[i][indice[i]++] = atual;
                    lista[atual][indice[atual]++] = i;
                }
            }
        }

    }
}