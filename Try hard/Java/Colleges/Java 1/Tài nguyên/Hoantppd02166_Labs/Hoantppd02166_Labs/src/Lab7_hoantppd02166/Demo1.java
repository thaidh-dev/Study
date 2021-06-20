
package Lab7_hoantppd02166;
public class Demo1 {

    public static void main(String[] args) {
        String s;
        String s1 = new String();
        s = "Nguyễn Khánh Óc";
        s1 = "Hello";
        String s2 = new String("       Hellooo       ");
        System.out.println(s.toUpperCase());
        System.out.println(s1.toLowerCase());
        System.out.println(s2.trim());
        System.out.println(s2.length());
        System.out.println(s2.substring(7, 14));
        System.out.println(s.charAt(3));
        String [] arr = s.split(" ");
        System.out.println("Họ " + arr[0]);
        System.out.println("Tên " + arr[arr.length - 1]);
        
    }
    
}
