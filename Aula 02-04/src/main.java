public class main    {
    public static void main(String[] args) {
        Heap h = new Heap();
        h.insert(2);
        h.insert(4);
        h.insert(1);
        h.insert(3);
        h.insert(5);
        h.insert(8);
        h.insert(6);
        h.insert(9);
        h.insert(7);
        h.insert(0);
        h.printHeapList();
        h.remove();
        h.printHeapList();
    }
}
