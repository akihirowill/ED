/*
*
Implementação do algoritmo de Rabin-Karp apresentada no livro Introducao a algoritmos 2010
*
* */

public class RabinKarp {
    // d é o numero de caracteres no alfabeto de entrada
    public static final int d = 256;
    public static final int q = 11; // numero primo.

    static void busca(String padrao,String texto){
        int tamanhoDoPadrao = padrao.length();
        int tamanhoDoTexto = texto.length();
        int i,j;
        int hashPadrao = 0;
        int hashTexto = 0;
        int h = 1;

        //calcula o valor do hash
        for (i = 0; i < tamanhoDoPadrao; i++){
            h = (h*d)%q;
        }

        // Calcula o valor do hash para o padrao e a primeira combinacao do texto
        for(i = 0; i < tamanhoDoPadrao-1; i++){
            hashPadrao = (d*hashPadrao + padrao.charAt(i))%q;
            hashTexto = (d*hashTexto + texto.charAt(i))%q;
        }

        // move o padrao sobre o texto um por um
        for (i = 0; i <= tamanhoDoTexto -tamanhoDoPadrao;i++){

            // Verifica se os valores dos hashs batem,
            // se baterem verificar caractere por caractere
            if (hashPadrao == hashTexto){
                //verificando 1 por 1
                for(j = 0; j < tamanhoDoPadrao; j++){
                    if(texto.charAt(i+j) != padrao.charAt(j)){
                        break;
                    }
                }

                // se p == t e padrao[0...M-1] = texto[i, i+1, ...i+M+1]
                if(j == tamanhoDoPadrao){
                    System.out.println("Padrao encontrado no indice: " +i);
                }
            }

            // calcula o valor para a proxima janela de texto:
            // remove dígito à esquerda, adiciona um dígito à direita
            if (i < tamanhoDoTexto-tamanhoDoPadrao) {
                hashTexto = (d * (hashTexto - texto.charAt(i) * h) + texto.charAt(i + tamanhoDoPadrao)) % q;

                // podemos pegar um valor negativo de hash, e converte-lo para positivo
                if (hashTexto < 0) {
                    hashTexto = (hashTexto + (100*q))%q;
                }
            }
        }


    }

    public static void main(String[] args) {
        String texto =  "PPGCCCPGAPGCCPPGCC";
        String padrao = "PPGCC";
        busca(padrao,texto);
    }

}
