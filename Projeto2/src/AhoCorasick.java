import java.util.*;

public class AhoCorasick {

    static final int TAMANHO_ALFABETO = 26;

    No[] arvoreDePadroes;
    int proxNo;

    public static class No {
        int pai;
        char caractereDoPai;
        int linkDeFalha = -1;
        int[] filhos = new int[TAMANHO_ALFABETO];
        int[] transicoesDaArvore = new int[TAMANHO_ALFABETO];
        boolean folha;

        {
            Arrays.fill(filhos, -1);
            Arrays.fill(transicoesDaArvore, -1);
        }
    }

    public AhoCorasick(int maxNoPadrao) {
        arvoreDePadroes = new No[maxNoPadrao];
        // cria a raiz
        arvoreDePadroes[0] = new No();
        arvoreDePadroes[0].linkDeFalha = 0;
        arvoreDePadroes[0].pai = -1;
        proxNo = 1;
    }

    public void addPadrao(String padrao) {
        int atual = 0;
        for (char caractere : padrao.toCharArray()) {
            int c = caractere - 'a';
            if (arvoreDePadroes[atual].filhos[c] == -1) {
                arvoreDePadroes[proxNo] = new No();
                arvoreDePadroes[proxNo].pai = atual;
                arvoreDePadroes[proxNo].caractereDoPai = caractere;
                arvoreDePadroes[atual].filhos[c] = proxNo++;
            }
            atual = arvoreDePadroes[atual].filhos[c];
        }
        arvoreDePadroes[atual].folha = true;
    }

    public int suffLink(int nodeIndex) {
        No no = arvoreDePadroes[nodeIndex];

        if (no.linkDeFalha == -1){

            if(no.pai == 0){
                no.linkDeFalha = 0; //cria link com o no raiz
            } else {
                no.linkDeFalha = busca(suffLink(no.pai), no.caractereDoPai);//verifica a partir do no pai
            }

        }
        return no.linkDeFalha;
    }

    public int busca(int noAtual, char caracterDaEntrada) {
        int posicaoCaracterAlfabeto = caracterDaEntrada - 'a';

        No no = arvoreDePadroes[noAtual];

        if (no.transicoesDaArvore[posicaoCaracterAlfabeto] == -1) {// se o no não tem transicao nessa letra do alfabeto entra pra testar

            if(no.filhos[posicaoCaracterAlfabeto] != -1) { // se o no tem o filho com o caracter de entrada entra pra atualizar a transicao

                no.transicoesDaArvore[posicaoCaracterAlfabeto] = no.filhos[posicaoCaracterAlfabeto];  //atualiza a transicao com o proximo no a ser testado

            } else { // o no atual não tem filho com o caracter da entrada

                if(noAtual == 0) { // é o no inicial?

                    no.transicoesDaArvore[posicaoCaracterAlfabeto] = 0; // como não tem filho e é o no inicial então a trasição é pro no zero

                } else {

                    no.transicoesDaArvore[posicaoCaracterAlfabeto] = busca(suffLink(noAtual), caracterDaEntrada); //continua a busca recursiva

                }

            }
        }
        return no.transicoesDaArvore[posicaoCaracterAlfabeto];
    }

    public static void main(String[] args) {
        AhoCorasick ahoCorasick = new AhoCorasick(1000);
        ahoCorasick.addPadrao("he");
        ahoCorasick.addPadrao("she");
        ahoCorasick.addPadrao("his");
        ahoCorasick.addPadrao("hers");

        String textoEntrada = "ahishers";
        int noAtual = 0;

        for (int caracterDaVez = 0; caracterDaVez < textoEntrada.length(); caracterDaVez++) {

            noAtual = ahoCorasick.busca(noAtual, textoEntrada.charAt(caracterDaVez));

            if (ahoCorasick.arvoreDePadroes[noAtual].folha){

                System.out.println("Padrao encontrado no indice: " + caracterDaVez);

            }

        }
    }
}