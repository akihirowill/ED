public class ExempoQuickSort {
    int partition(int arr[], int low, int high)
    {
        int pivot = arr[high];
        int i = (low-1); // index do menor elemento
        for (int j=low; j<high; j++)
        {
            // se o elemento atual e menor ou igual ao pivot
            if (arr[j] <= pivot)
            {
                i++;

                // troca arr[i] e arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // troca arr [i + 1] e arr [high] (ou pivot)
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }

    void sort(int arr[], int low, int high)
    {
        if (low < high)
        {
            /*pi é o índice de particionamento, arr [pi] está agora no lugar certo */
            int pi = partition(arr, low, high);

            /*Classifica recursivamente os elementos antes da partição e depois da partição*/
            sort(arr, low, pi-1);
            sort(arr, pi+1, high);
        }
    }


    /* funcao que printa o array */
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }

    public static void main(String[] args) {
        int arr[] = {10, 7, 8, 9, 1, 5};
        int n = arr.length;

        ExempoQuickSort ob = new ExempoQuickSort();
        ob.sort(arr, 0, n-1);

        System.out.println("Array Ordenado: ");
        printArray(arr);
    }
}
