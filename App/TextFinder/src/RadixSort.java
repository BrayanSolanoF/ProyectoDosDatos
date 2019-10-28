import java.net.MalformedURLException;
import java.util.Arrays;

public class RadixSort {
    /**
     * Util funcion para obtener el mayor valor  en el array[]
     * */

    static int getMax(int arr[], int n) {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }
    /**
     * Una funciona para contar el arr[] de acuerdo a
     * el digito representado
     * */
    public static void countSort(int arr[], int n, int exp) {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count,0);

        // Guarda el numero de ocurrencias in count[]
        for (i = 0; i < n; i++)
            count[ (arr[i]/exp)%10 ]++;

        // Cambia count[i] asi que count[i] ahora contiene
        // la posicion actual de su digito de salida[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Construye la salida del array
        for (i = n - 1; i >= 0; i--) {
            output[count[ (arr[i]/exp)%10 ] - 1] = arr[i];
            count[ (arr[i]/exp)%10 ]--;
        }

        // Copia la salida array a arr[], asi que arr[] ahora
        // contiene numeros de acuerdo al numero actual
        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }
    public static int[] radixsort(int arr[], int n) {
        // Encuentra el numero maximo para conocer el numero de digitos
        int m = getMax(arr, n);
        // Hace el  counting sort para cada digito.
        for (int exp = 1; m/exp > 0; exp *= 10){
            countSort(arr, n, exp);
        }
        return arr;
    }

    public static int[] toIntArray(Mi_Lista dl){
        int[] result = new int[dl.getLength()-1];
        for(int i = 0; i < dl.getLength(); i++){
            result[i] = dl.get(i).getRealSize();
        }
        return result;
    }

    public static Mi_Lista backToDoublyList(int[] result, Mi_Lista dl) throws MalformedURLException {
        Mi_Lista tmpList = dl;
        dl.clearList();
        for(int i = 0; i < result.length; i++){
            for(int j = 0; j < tmpList.getLength(); j++){
                if (result[i] == tmpList.get(i).getRealSize()){
                    dl.addLast(tmpList.get(i));
                }
            }
        }
        return dl;
    }


}
