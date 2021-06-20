package javaapplication73;

import java.util.Arrays;

public class JavaApplication73 {

    public static void main(String[] args) {
        int a[] = {1,2,3,4,5,6};
        JavaApplication73 s = new JavaApplication73();
        for (int x = 0; x < a.length; x++) {
            if (a[x] % 3 == 0) {
                a = s.xoa(a, x);
            }
        }
        System.out.println(Arrays.toString(a));
    }
    
    int[] xoa(int a[], int x) {
        int b[] = new int[a.length - 1];
        System.arraycopy(a, 0, b, 0, x);
        System.arraycopy(a, x+1, b, x, a.length - x - 1);
        return b;
    }
    
}
