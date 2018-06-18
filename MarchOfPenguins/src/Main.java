import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static final int MAXIMO_DE_SALTOS = 200;

    public static int totalVerticesEntradaSaidaDoGelo;
    public static int verticeFromDaVez;

    public static int matrizAuxiliarDeMovimento[][];

    public static int matrizDePulosPossiveis[][];

    public static int movimentacaoDePinguin[];

    public static ArrayList<Integer>[] listaDeAdjacenciaGelos;

    static int maxFlow()
    {
        int flowMaximo = 0;
        while(true) {

            Queue<Integer> fila = new LinkedList<Integer>();

            movimentacaoDePinguin = new int[totalVerticesEntradaSaidaDoGelo];
            Arrays.fill(movimentacaoDePinguin, -1);

            fila.add(0);

            movimentacaoDePinguin[0] = 0;

            while(!fila.isEmpty()) {
                int elementoDaVez = fila.remove();

                //se for o vertice da vez para
                if(elementoDaVez == verticeFromDaVez) {
                    break;
                }

                for(int i = 0; i < listaDeAdjacenciaGelos[elementoDaVez].size(); ++i) {

                    int adjacenciaDaVez = listaDeAdjacenciaGelos[elementoDaVez].get(i);

                    if(matrizAuxiliarDeMovimento[elementoDaVez][adjacenciaDaVez] < matrizDePulosPossiveis[elementoDaVez][adjacenciaDaVez]
                            && movimentacaoDePinguin[adjacenciaDaVez] == -1) {

                        movimentacaoDePinguin[adjacenciaDaVez] = elementoDaVez;
                        fila.add(adjacenciaDaVez);
                    }
                }
            }

            if(movimentacaoDePinguin[verticeFromDaVez] == -1) {
                break;
            }

            flowMaximo += augment(verticeFromDaVez, MAXIMO_DE_SALTOS);
        }
        return flowMaximo;
    }

    static int augment(int verticeFrom, int flow) {

        if(verticeFrom == 0) {
            return flow;
        }

        int aux = matrizDePulosPossiveis[movimentacaoDePinguin[verticeFrom]][verticeFrom] -
                matrizAuxiliarDeMovimento[movimentacaoDePinguin[verticeFrom]][verticeFrom];

        flow = 	augment(movimentacaoDePinguin[verticeFrom], Math.min(flow, aux));

        matrizAuxiliarDeMovimento[movimentacaoDePinguin[verticeFrom]][verticeFrom] += flow;

        matrizAuxiliarDeMovimento[verticeFrom][movimentacaoDePinguin[verticeFrom]] -= flow;

        return flow;
    }

    static void adicionaPuloPossivelEAdjacencia(int verticeOrigem, int verticeDestino, int qtdPulos) {

        listaDeAdjacenciaGelos[verticeOrigem].add(verticeDestino);
        listaDeAdjacenciaGelos[verticeDestino].add(verticeOrigem);

        matrizDePulosPossiveis[verticeOrigem][verticeDestino] = qtdPulos;
    }

    static void adicionaPinguinNaAdjacenciaEMatrizDePulo(int verticeOrigem, int qtdPinguin) {

        listaDeAdjacenciaGelos[verticeOrigem].add(0);
        listaDeAdjacenciaGelos[0].add(verticeOrigem);

        matrizDePulosPossiveis[0][verticeOrigem] = qtdPinguin;
    }

    static void limpaListaAdjacenciaMatrizAuxiliar() {

        for(int i = 0; i < listaDeAdjacenciaGelos[0].size(); ++i) {

            int u = listaDeAdjacenciaGelos[0].get(i);
            matrizDePulosPossiveis[0][u] = 0;
            listaDeAdjacenciaGelos[u].remove(listaDeAdjacenciaGelos[u].size() - 1);
        }

        listaDeAdjacenciaGelos[0] = new ArrayList<Integer>();

        matrizAuxiliarDeMovimento = new int[totalVerticesEntradaSaidaDoGelo][totalVerticesEntradaSaidaDoGelo];

    }

    static int calculaVerticeFrom(int x) {
        return 1 + (x*2);
    }

    static int calculaVerticeTo(int x) {
        return 2 + (x*2);
    }

    public static void main(String[] args) throws Exception {

        //Le quantidade de casos de teste
        String casosDeTesteStr = in.readLine();
        int casosDeTeste = validateTestCase(casosDeTesteStr);

        while(casosDeTeste > 0) {

            int totalPinguins = 0;

            //le primeira linha com qtd de gelos e distancia maxima de pulo
            String geloDistanciaStr = in.readLine();
            double[] geloDistanciaVet = validateIceDist(geloDistanciaStr);
            int qtdGelos = (int) geloDistanciaVet[0];
            double distanciaPulo = geloDistanciaVet[1];

            //total de vertices possiveis pra quantidade de gelos informada, lembrando que
            //cada gelo é considerado duas listas (vetores) de vertices pois é o vertice de saida e entrada
            totalVerticesEntradaSaidaDoGelo = (qtdGelos * 2) + 1;

            matrizDePulosPossiveis = new int[totalVerticesEntradaSaidaDoGelo][totalVerticesEntradaSaidaDoGelo];

            listaDeAdjacenciaGelos = new ArrayList[totalVerticesEntradaSaidaDoGelo];

            //inicia a lista de adjacencia de gelos
            for(int i = 0; i < totalVerticesEntradaSaidaDoGelo; ++i)
                listaDeAdjacenciaGelos[i] = new ArrayList<Integer>();

            //vetor das posicoes x e y do gelo
            PosicaoGelo[] gelos = new PosicaoGelo[qtdGelos];

            //vetor que diz se o gelo tem pinguin
            int[] gelosComPinguins = new int[qtdGelos];

            //le os dados dos gelos
            for(int i = 0; i < qtdGelos; ++i) {

                String dadosGeloStr = in.readLine();

                int[] dadosGelo = validateIceData(dadosGeloStr);

                gelos[i] = new PosicaoGelo(dadosGelo[0], dadosGelo[1]);

                totalPinguins += dadosGelo[2];

                gelosComPinguins[i] = dadosGelo[2];

                adicionaPuloPossivelEAdjacencia(calculaVerticeFrom(i), calculaVerticeTo(i), dadosGelo[3]);
            }

            //preenche as demais adjacencias baseado na distancia de pulo e o maximo de saltos
            for(int geloInicial = 0; geloInicial < qtdGelos; ++geloInicial) {

                for (int outroGelo = geloInicial + 1; outroGelo < qtdGelos; ++outroGelo) {

                    double distanciaDePulo = distanciaPulo * distanciaPulo;

                    if (gelos[geloInicial].calculaDistancia(gelos[outroGelo]) <= distanciaDePulo) {

                        adicionaPuloPossivelEAdjacencia(calculaVerticeTo(geloInicial), calculaVerticeFrom(outroGelo), MAXIMO_DE_SALTOS);
                        adicionaPuloPossivelEAdjacencia(calculaVerticeTo(outroGelo), calculaVerticeFrom(geloInicial), MAXIMO_DE_SALTOS);

                    }
                }
            }


            int temResposta = 0;

            boolean printaSemEspaco = true;

            for(int geloDaVez = 0; geloDaVez < qtdGelos; ++geloDaVez) {

                limpaListaAdjacenciaMatrizAuxiliar();

                verticeFromDaVez = calculaVerticeFrom(geloDaVez);

                for(int outroGelo = 0; outroGelo < qtdGelos; ++outroGelo) {

                    if (geloDaVez != outroGelo) {
                        int verticeOutroGelo = calculaVerticeFrom(outroGelo);
                        adicionaPinguinNaAdjacenciaEMatrizDePulo(verticeOutroGelo, gelosComPinguins[outroGelo]);
                    }
                }

                if(maxFlow() == totalPinguins - gelosComPinguins[geloDaVez]) {
                    temResposta++;
                    if(printaSemEspaco) {
                        printaSemEspaco = false;
                        System.out.print(geloDaVez);
                    } else{
                        System.out.format(" %d", geloDaVez);
                    }
                }
            }

            if(temResposta == 0) {
                System.out.print(-1);
            }

            //linha extra pra submissão
            System.out.println();

            casosDeTeste--;
        }
    }

    private static int validateTestCase(String casosDeTesteStr) throws Exception {

        int n = Integer.valueOf(casosDeTesteStr);

        if(n < 1 || n > 100){
            throw new Exception("Teste case invalido");
        }

        return n;
    }

    private static double[] validateIceDist(String geloDistanciaStr) throws Exception {

        String[] split = geloDistanciaStr.split(" ");

        if(split.length != 2){
            throw new Exception("Input errado");
        }

        int n = Integer.valueOf(split[0]);

        if(n < 1 || n > 100){
            throw new Exception("Gelos fora do padrao");
        }

        double d = Double.valueOf(split[1]);

        if(d < 0 || d > 100000){
            throw new Exception("Distancia fora do padrao");
        }

        double[] dados = new double[2];
        dados[0] = n;
        dados[1] = d;

        return dados;

    }

    private static int[] validateIceData(String dadosGeloStr) throws Exception {

        String[] split = dadosGeloStr.split(" ");

        if(split.length != 4){
            throw new Exception("Input errado");
        }

        int x = Integer.valueOf(split[0]);

        if(x < -10000 || x > 10000){
            throw new Exception("posicao x fora do padrao");
        }

        int y = Integer.valueOf(split[1]);

        if(y < -10000 || y > 10000){
            throw new Exception("posicao y fora do padrao");
        }

        int n = Integer.valueOf(split[2]);

        if(n < 0 || n > 10){
            throw new Exception("mtos pinguins");
        }

        int m = Integer.valueOf(split[3]);

        if(m < 1 || m > 200){
            throw new Exception("mtos saltos");
        }

        int[] dados = new int[4];
        dados[0] = x;
        dados[1] = y;
        dados[2] = n;
        dados[3] = m;

        return dados;

    }


    static class PosicaoGelo {
        int x, y;

        public PosicaoGelo(int x, int y) {
            this.x = x;
            this.y = y;
        }

        double calculaDistancia(PosicaoGelo p) {
            double x = (double) (p.x - this.x) * (p.x - this.x);
            double y = (double) (p.y - this.y) * (p.y - this.y);
            return x + y;
        }
    }

}