import java.util.Iterator;
import java.util.LinkedList;

public class ExemploLinkedList {
    public static void main(String args[]) {

        LinkedList ll = new LinkedList();
        ll.add(3);
        ll.add(2);
        ll.add(1);
        ll.add(4);
        ll.add(5);
        ll.add(6);
        ll.add(6);

        Iterator iter2 = ll.iterator();
        while (iter2.hasNext()) {
            System.out.println(iter2.next());
        }

    }
}
