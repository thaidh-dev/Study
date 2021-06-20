/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author moon
 */
public class Xarray {
     //min

    public static int xArrayMin(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    public static double xArrayMin(double[] arr) {
        double min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    public static int xArrayMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static double xArrayMax(double[] arr) {
        double max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
//swap

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void swap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //insert
    public static int[] insert(int[] a, int x) {
        int[] b = new int[a.length + 1];
        System.arraycopy(a, 0, b, 0, a.length);
        b[b.length - 1] = x;
        return b;
    }

    public static double[] insert(double[] a, double x) {
        double[] b = new double[a.length + 1];
        System.arraycopy(a, 0, b, 0, a.length);
        b[b.length - 1] = x;
        return b;
    }

    public static String[] insert(String[] a, String x) {
        String[] b = new String[a.length + 1];
        System.arraycopy(a, 0, b, 0, a.length);
        b[b.length - 1] = x;
        return b;
    }

    //delete
    public static int[] remove(int[] a, int i) {
        int[] b = new int[a.length - 1];
        System.arraycopy(a, 0, b, 0, i);
        System.arraycopy(a, i + 1, b, i, a.length - i - 1);
        return b;
    }

    public static double[] remove(double[] a, int i) {
        double[] b = new double[a.length - 1];
        System.arraycopy(a, 0, b, 0, i);
        System.arraycopy(a, i + 1, b, i, a.length - i - 1);
        return b;
    }

    public static String[] remove(String[] a, int i) {
        String[] b = new String[a.length - 1];
        System.arraycopy(a, 0, b, 0, i);
        System.arraycopy(a, i + 1, b, i, a.length - i - 1);
        return b;
    }

    public static void output(double a[]) {
        for (int i = 0; i < a.length; i++) {
            System.out.printf("%.1f ", a[i]);
        }
    }

    public static String xeploai(double diem) {
        String hocluc;
        if (diem >= 9) {
            hocluc = "Xuat sac";
        } else if (diem >= 7.5) {
            hocluc = "Gioi";
        } else if (diem >= 6.5) {
            hocluc = "Kha";
        } else if (diem >= 5) {
            hocluc = "Trung binh";
        } else {
            hocluc = "Yeu";
        }
        return hocluc;
    }
}
    

