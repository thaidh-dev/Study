
package Lab6_Hoantppd02166;

import java.util.Scanner;

public class Bai4 {
    public static void main(String[] args){
         int []arr={3,6,1,9,10,5,11};
        boolean swap = false;

     }
    public static void swap(int i){
        int[] a = null;
        int temp=a[i];
        int j = 0;
        a[i]=a[j];
        a[j]=temp;
    }
    public static void swap(String []a,int i,int j ){
        String temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
    public static void swap(double[] a, int i, int j){
        double temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
    public static int min(int []a){
        int kq=a[0];
        for (int i=1;i<a.length;i++){
        if(kq > a[i])
            kq=a[i];
    }
         return kq;
    }
    public static double min(double []a){
        double kq= a[0];
         for (int i=1;i<a.length;i++){
            if(kq > a[i])
            kq=a[i];
    } 
          return kq;
    }
    public static int max(int []a){
        int kq=a[0];
        for (int i=1;i>a.length;i++){
            if(kq < a[i])
            kq=a[i];
    }
        return kq;
    }
     public static double max(double []a){
         double kq= a[0];
         for (int i=1;i>a.length;i++){
            if(kq < a[i])
            kq=a[i];
    }
        return kq;
     }
      public static int[] add(int[] arr, int x){
          Scanner Sc = new Scanner(System.in);
        for (int i = 0; i < x; i++) {
            arr[i] = Sc.nextInt();
      }
        return arr;
      }
      
      public static int[] remove(double[] arr, int x){
          int[] array = {2,6};
          return array;
      }
      
}