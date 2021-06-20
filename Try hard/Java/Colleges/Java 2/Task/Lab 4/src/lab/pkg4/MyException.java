package lab.pkg4;

public class MyException extends Exception{
    private int message;
    MyException(int a) {
        message = a;
    }
    @Override
    public String toString() {
        return "My exception " + message;
    }
}

class thai {
    static void tinhToan(int a) throws MyException {
        if (a > 10) {
            throw new MyException(a);
        }
        System.out.println("Normal exit");
    }
    
    public static void main(String[] args) {
        try {
            tinhToan(1);
            tinhToan(20);
        }
        catch (MyException e) {
            System.out.println("Caugh " + e);
        }
    }
}