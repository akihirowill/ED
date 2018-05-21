public class ExemploMergeSort {
    void merge(int arr[], int l, int m, int r)
    {
        // encontra o tamanho dos arrays para serem mergeados
        int n1 = m - l + 1;
        int n2 = r - m;

        /* arrays temporarios */
        int L[] = new int [n1];
        int R[] = new int [n2];

        /*Copia dados para os arrays temporarios*/
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];


        /* Mergeia os arrays temporarios */

        //index inicial do primeiro e segundo subarray
        int i = 0, j = 0;

        //index inicial do subarray mergeado
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copia os elementos restantes de L[] se tiver */
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copia os elementos restantes de R[] se tiver */
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    //funcao que organiza arrays[l..r] usado merge()
    void sort(int arr[], int l, int r)
    {
        if (l < r)
        {
            // encontra o meio
            int m = (l+r)/2;

            // Sort na primeira e segunda metade
            sort(arr, l, m);
            sort(arr , m+1, r);

            // Mergeia as partes organizadas
            merge(arr, l, m, r);
        }
    }

    /* funcao para printar o array*/
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int arr[] = {12, 11, 13, 5, 6, 7};

        System.out.println("Array inserido");
        printArray(arr);

        ExemploMergeSort ob = new ExemploMergeSort();
        ob.sort(arr, 0, arr.length-1);

        System.out.println("\nArray ordenado");
        printArray(arr);
    }
}
