package lab.pkg8;

import java.util.ArrayList;
import java.util.List;

public class Bai2 {
    public static void main(String[] args) {
        ArrayList <Integer> myArr = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            myArr.add(i);
        }
        for (Integer i : myArr) {
            System.out.print(i + " ");
        }

    }
}
