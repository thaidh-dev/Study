package nhap;

import java.util.UUID;

public class NhapUUID {

    public static void main(String[] args) throws InterruptedException {
        String ref = UUID.randomUUID().toString();
        if (true) {
            System.out.println(ref);
            return;
        }
        System.out.println("xong");
        
    }

}
