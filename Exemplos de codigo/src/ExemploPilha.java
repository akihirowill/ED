import java.util.Iterator;
import java.util.Stack;

public class ExemploPilha {
    public static void main(String[] args){
        Stack pilha = new Stack();

        pilha.push(9);
        pilha.push(8);
        pilha.push(7);

        Iterator it = pilha.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }

        while (pilha.iterator().hasNext()){
            System.out.println("Valor: " + pilha.peek() +" Retirado da pilha!");
            pilha.pop();
        }

        if(pilha.isEmpty()){
                System.out.println("Pilha Vazia!!");
        }

    }
}
