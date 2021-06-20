import java.util.Scanner;

public class Lab3Bai1 {

    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        System.out.print("Hệ số a: ");
        double a = thai.nextDouble();
        System.out.print("Hệ số b: ");
        double b = thai.nextDouble();
        if(a==0){
            if(b==0){
                System.out.println("Vô số nghiệm");
            }
                else{
                System.out.println("Vô nghiệm");
            }
        }
        else{
            System.out.println("Nghiệm: "+ -b/a);
        }
        }
    }
    
