/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbstractClass;

/**
 *
 * @author Dương Hồng Thái
 */
public class Child extends Father {
    public String controls;
    
    public void x() {
        
//        ((Child) new Father()).y(); // sai vì không thể ép bố thành con được
        
        ((Father) new Child()).alo();
    }
    
    public void y() {
        System.out.println("yyyyyyy");
    }
    
    public static void main(String[] args) {
        Father f = new Child();
        f.alo();
        new Child().x();
    }
}

// tất cả nắm hết ở upcasting và downcasting in java