package demostatic;

public class HinhVuong {
    int canh; // lưu vào heap
    static int goc; // lưu vào data segment
    
    // static thuộc về class
    // non-static thuộc về đối tượng
    
    public void chuVi() {
        System.out.println("Chu vi");
    }
    
    public static void xinChao(HinhVuong hinhVuong) {
        String s = "Xin chào";
        System.out.println(s);
        hinhVuong.chuVi();
    }
    
    public static void main(String[] args) {
        int x = 10;
        System.out.println(x);
        HinhVuong hv = new HinhVuong();
        xinChao(hv);
    }
}
