package Controller;

import java.io.Serializable;

public class CalcBean implements Serializable{
    private int value1;
    private int value2;

    public int getValue1() {
        return value1;
    }

    public void setValue1(int value1) {
        this.value1 = value1;
    }

    public int getValue2() {
        return value2;
    }

    public void setValue2(int value2) {
        this.value2 = value2;
    }

    public CalcBean() {
    }
    
    public int getSum() {
        return value1+value2;
    }
}
