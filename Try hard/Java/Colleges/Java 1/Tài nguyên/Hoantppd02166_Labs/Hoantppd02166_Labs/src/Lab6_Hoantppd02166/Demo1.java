package Lab6_Hoantppd02166;

public class Demo1 {

    static double luythua(double coso, double somu) {
        double kq = 1;
        for (int i = 1; i < somu; i++) {
            kq = kq * coso;
        }
        return kq;
    }

    static int tonghaiso(int a, int b) {
        return a + b;
    }

    static int tongbaso(int x, int y, int z) {
        return x + y + z;
    }

    public static void main(String[] args) {
        double a = 5 + 7 * Math.random();
        System.out.println("A= " + a + ", Căn bậc hai = " + Math.sqrt(a));
        int c, b, d;
        c = 6;
        b = 8;
        d = 2;
        System.out.println("Lũy thừa: " + luythua(c, b));
        System.out.println("Tổng hai số " + tonghaiso(b, c));
        System.out.println("Tổng ba số " + tongbaso(c, b, d));
    }

}
