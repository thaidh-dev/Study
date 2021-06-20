package ass;

public class xarrays {
    
    static void sapxeptang(double arr[], String ar[]) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++ ) {
                if (arr[i] > arr[j]) {
                    double temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    String templa = ar[i];
                    ar[i] = ar[j];
                    ar[j] = templa;
                }
            }
        }
    }
    
    static void sapxepgiam(double arr[], String ar[]) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++ ) {
                if (arr[i] < arr[j]) {
                    double temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    String templa = ar[i];
                    ar[i] = ar[j];
                    ar[j] = templa;
                }
            }
        }
    }

}



