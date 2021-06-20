package nhap;

public interface NhapInterface {

    int x = 7; // mặc định luôn là static final
    String y = "Fuck my life";

    default void a() {
        System.out.println("Dương Hồng Thái");
    }

    static void b() {
        System.out.println("Đào Văn Thư");
    }

    void c();
}
