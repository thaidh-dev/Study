/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NhapCopyEntity;

/**
 *
 * @author Dương Hồng Thái
 */
public class B {
    public static void main(String[] args) {
        A a = new A();
        a.setId(1);
        a.setTenA("Thái");
        
        A a2 = new A();
        a2.setId(2);
        a2.setTenA("Nam");
        
        a = a2;
        
        System.out.println(a.getTenA());
        System.out.println(a.getId());
    }
}
