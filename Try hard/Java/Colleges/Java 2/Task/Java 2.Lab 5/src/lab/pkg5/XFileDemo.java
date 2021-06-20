package lab.pkg5;

public class XFileDemo {
    public static void main(String[] args){
        XFile.write("duonghongthai.txt", "hello cac ban nha");
        for (byte a: XFile.read("duonghongthai.txt")) {
            System.out.print((char) a);
        }
    }
}
