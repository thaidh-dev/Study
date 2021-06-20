package demodao;

import java.util.Scanner;
/**
 *
 * @author giasutinhoc.vn
 */
public class JDBCSingletonExample {

    public static void main(String[] args) throws Exception {

        //JDBCSingleton object = new JDBCSingleton();
        JDBCSingleton jdbc = JDBCSingleton.getInstance();
        Scanner s = new Scanner(System.in);
        int count = 1;
        int choice;
        do {
            System.out.println(" 1. Create account");
            System.out.println(" 2. Display account");
            System.out.println(" 3. Exit");

            System.out.print("Please enter the choice: ");
            choice = s.nextInt();

            //clear buffer
            s.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhap ten : ");
                    String username = s.nextLine();
                    System.out.print("Nhap mat khau : ");
                    String password = s.nextLine();

                    try {
                        int i = jdbc.create(username, password);

                        if (i > 0) {
                            System.out.println("Them thanh cong");
                        } 
                        else {
                            System.out.println("Them that bai ");
                        }

                    } 
                    catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
               case 2:
                   System.out.print("Nhap ten : ");
                    username = s.nextLine();

                    try {
                        jdbc.display(username);
                    } 
                    catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
            }
        } 
        while (choice != 3);
    }
}