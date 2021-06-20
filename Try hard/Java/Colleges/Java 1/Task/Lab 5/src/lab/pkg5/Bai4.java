package lab.pkg5;

public class Bai4 {
        public static void main(String[] args) {
        String hoten[] = {"Tuấn", "Hạnh", "Cường"};
        double diem[][] = {{5,6,7}, {7,9,5}, {8,3,4}};
        double tbc;
        for (int a = 0; a < diem.length; a++) {
            double tong = 0;
            double dem = 0;
            for (int b = 0; b < diem[a].length; b++) {
                tong += diem[a][b];
                dem++;
            }
            tbc = tong/dem;
            System.out.printf("Điểm trung bình của %s là: %.1f\n", hoten[a], tbc);
    }
        }
}
