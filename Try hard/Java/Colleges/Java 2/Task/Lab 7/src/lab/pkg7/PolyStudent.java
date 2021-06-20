package lab.pkg7;

// enum là lớp các hằng số, mặc định là static và final,
// không khác j class. class là lớp động, cần khai báo đối tượng còn enum là static

//boxing là chuyển thành object (đối tượng), còn unboxing là chuyển thành kiểu nguyên thủy
// Int i = 1; autoboxing

enum Career {
    UDPM, TKTW, LTDĐ, TKĐH
}

public class PolyStudent {
    public String fullname;
    public Career career;
    
    public void print() {
        System.out.println("Fullname: "+fullname);
        switch (career) {
            case UDPM:
                System.out.println("Career: Ứng dụng phần mềm");
                break;
            case TKTW:
                System.out.println("Career: Thiết kế trang web");
                break;
            case LTDĐ:
                System.out.println("Career: Lập trình di động");
                break;
            case TKĐH:
                System.out.println("Career: Thiết kế đồ họa");
                break;
        }
    }

    public static void main(String[] args) {
        PolyStudent sv = new PolyStudent();
        sv.fullname = "Dương Hồng Thái";
        // 2 cái dưới này ý nghĩa giống nhau
        sv.career = Career.UDPM;
        sv.career = Career.valueOf("UDPM");
        sv.print();
        int x=0;
int y=0;
while(true){
    if ((++x==10) && (++y==10))
        return;
}
    }
}
