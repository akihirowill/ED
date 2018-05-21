public class ExemploHeap {
    protected int[] data = new int[11];

    public ExemploHeap() {
        this.data[0] = 0;
    }

    public ExemploHeap insert(int value){
        ++this.data[0];
        this.data[this.data[0]] = value;
        this.percDown(this.data[0]);
        return this;
    }

    public void percUp(int index){
        int parentIndex =  index / 2;

        if (index == 1 || this.data[parentIndex] < this.data[index]){
            return;
        }

        int parentValue = this.data[parentIndex];
        this.data[parentIndex] = this.data[index];
        this.data[index]       = parentValue;

        percUp(parentIndex);
    }

    public void percDown(int index){
        if(this.isLeaf(index)) {
            return;
        }

        int leftChildIndex  = index *2;
        int rightChildIndex = (index * 2) +1;
        int minChildIndex;

        // Verifique se rightChildren não existe, nesse caso
        // o minChildIndex é a esquerda, caso contrário, encontre o minChild

        if(rightChildIndex > this.length()){
            minChildIndex = leftChildIndex;
        }else{
            minChildIndex = (this.data[leftChildIndex] < this.data[rightChildIndex])
                    ? leftChildIndex : rightChildIndex;
        }

        //parar no caso do pai ser menor do que os filhos
        if (this.data[index] < this.data[minChildIndex]){
            return;
        }

        int childValue = this.data[minChildIndex];
        this.data[minChildIndex]    = this.data[index];
        this.data[index]            = childValue;

        percDown(minChildIndex);

    }

    public int remove() {
        int minValue = this.data[1];
        int lastValue = this.data[this.data[0]--];
        this.data[1] = lastValue;
        this.percDown(1);
        return minValue;
    }


    private int length() {
        return this.data[0];
    }

    private boolean isLeaf(int index) {
        return (index * 2) > this.data[0];
    }

    public void printHeapList() {
        for(int i = 0; i < this.data[0]; i++) {
            System.out.printf("%d, ", this.data[i]);
        }
        System.out.printf("%d\n", this.data[this.data[0]]);
    }

    public static void main(String[] args) {
        ExemploHeap h = new ExemploHeap();
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
