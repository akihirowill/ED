import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class ExemploDeque {
    public static void main(String[] args) {
        Deque deque = new LinkedList();

        //pode adicionar elementos no deque de varias maneiras
        deque.add("Elemento 1 (Tail)"); //adiciona no final
        deque.addFirst("Elemento 2 (Head)");//adiciona no comeco
        deque.addLast("Elemento 3 (Tail)");//adiciona no final
        deque.push("Elemento 4 (Head)");//adiciona no inicio
        deque.offer("Elemento 5 (Tail)");//adiciona no final e retorna um boolean para confirmar insercao
        deque.offerFirst("Elemento 6 (Head)");//adiciona no inicio e retorna um boolean para confirmar insercao
        deque.offerLast("Elemento 7 (Tail)");//adiciona no final e retorna um boolean para confirmar insercao

        System.out.println(deque + "\n");

        //iterando os elementos da fila
        System.out.println("Iterator Padrao:");
        Iterator it = deque.iterator();

        while(it.hasNext()){
            System.out.println("\t" + it.next());
        }


        //iterando em ordem reversa
        Iterator reverso = deque.descendingIterator();
        System.out.println("\n"  + "Iterador Reverso: ");

        while(reverso.hasNext()){
            System.out.println("\t" + reverso.next());
        }

        System.out.println("\n");

        //peek retorna a cabeca sem deleta-la do deque
        System.out.println("Peek: " + deque.peek());
        System.out.println("After peek: " + deque);

        System.out.println("\n");

        //pop retorna a cabeca e remove da deque
        System.out.println("Pop: " + deque.pop());
        System.out.println("After pop: " + deque);

        System.out.println("\n");

        //podemos verificar se existe o elemento no deque
        System.out.println("Contem o elemento 3: " +
                deque.contains("Elemento 3 (Tail)"));

        //podemos remover o primero e ultimo elemento
        deque.removeFirst();
        deque.removeLast();
        System.out.println("Deque depois de remover o " +
                "primeiro e ultimo: " + deque);

    }
}
