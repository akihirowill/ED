import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class ExemploFIla {
    public static void main(String[] args){
        Queue fila = new LinkedList();

        fila.offer(3);
        fila.offer(1);
        fila.offer(2);

        Iterator it = fila.iterator();

        while(it.hasNext()){
            System.out.println(it.next());
        }

        while(fila.iterator().hasNext()){
            System.out.println("Valor: " + fila.iterator().next() + " Removido da fila!" );
            fila.poll();
        }


        if(fila.isEmpty()){
            System.out.println("Fila Vazia!!");
        }


    }
}
