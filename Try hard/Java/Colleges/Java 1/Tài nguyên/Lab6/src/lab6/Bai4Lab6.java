/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Dang Hung
 */
public class Bai4Lab6 {

    public static void main(String[] args) {
        int a[], n, i;
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so phan tu mang: ");
        n = sc.nextInt();
        a = new int[n];
        // Nhap mang
        for (i = 0; i < n; i++) {
            System.out.print("Nhap vao a[" + i + "]: ");
            a[i] = sc.nextInt();
        }
        // Xuat mang 
        for(i=0;i<a.length;i++){
	System.out.print("\t"+a[i]);
		}
        System.out.println("");
        // Trao doi vi tri trong mang
        System.out.print("Nhap vao vi tri can doi thu nhat: ");
        int vt1 = sc.nextInt();
        System.out.print("Nhap vao vi tri can doi thu hai: ");
        int vt2 = sc.nextInt();
        Bai4Lab6.swap(a, vt1, vt2);
        // Xuat mang sau khi trao
        System.out.println("Vi tri sau khi thay doi la: ");
        for (i = 0; i < n; i++) {
            System.out.printf("%6d", a[i]);
            
        }
        // Them phan tu vao mang
        System.out.println("");
        int b[]= new int[a.length+1];
        int x;
        System.out.print("Nhap vao phan tu muon them: ");
        x=sc.nextInt();
        System.arraycopy(a, 0, b, 0, a.length);
        b[a.length] = x;
        System.out.println("Mang sau khi them la:");
        for(i=0;i<a.length+1;i++){
            System.out.printf("%6d",b[i]);
        }
        // Xoa phan tu thu 3 trong mang
        System.out.println("");
        
        int c[] = new int[b.length - 1];
        System.arraycopy(b, 0, c, 0, i);
        System.arraycopy(b, i + 1, c, i, a.length - i - 1);
        
        System.out.println("Mang sau khi xoa phan tu thu 3 la: ");
        
     
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;

    }

    public static int min(int[] a) {
        int min = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i] < min) {
                min = a[i];
            }
        }
        return min;
    }

    public static int[] add(int[] a, int x) {
        int b[] = new int[a.length + 1];
        System.arraycopy(a, 0, b, 0, a.length);
        b[a.length] = x;
        return b;
        
       
    }

    public static int[] remove(int[] a, int i) {
        int b[] = new int[a.length - 1];
        System.arraycopy(a, 0, b, 0, i);
        System.arraycopy(a, i + 1, b, i, a.length - i - 1);
        return b;
    }

    public static int max(int[] a) {
        int max = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }
}
