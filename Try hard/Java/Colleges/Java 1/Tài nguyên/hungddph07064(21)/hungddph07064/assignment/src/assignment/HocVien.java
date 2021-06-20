/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

/**
 *
 * @author Dang Hung
 */
public class HocVien {
    private String hoTen;
    private float diem;
    private String email;
    
    
    public HocVien(String hoTen, float diem, String email) {
        this.hoTen = hoTen;
        this.diem = diem;
        this.email = email;
    }
    
    public void setHoTen(String hoTen){
        this.hoTen = hoTen;
    }
    public String getHoTen(){
        return this.hoTen;
    }
    public void setDiem(float diem){
        this.diem = diem;
    }
    public float getDiem(){
        return this.diem;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    
    public String HocLuc() {
        if(this.diem<3){
            return "Kem";
        }
        else if(this.diem<5){
            return "Yeu";
        }
        else if(this.diem<6.5){
            return "Trung binh";
        }
        else if(this.diem<7.5){
            return "Kha";
        }
        else if(this.diem<9){
            return "Gioi";
        }
        else{
            return "Xuat sac";
        }
    }
 
    
    
}
