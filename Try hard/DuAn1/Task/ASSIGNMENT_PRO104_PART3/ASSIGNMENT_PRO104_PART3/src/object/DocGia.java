/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

/**
 *
 * @author Thanh
 */
public class DocGia {

    private int theDG;
    private String tenDG;

    public DocGia() {
    }

    public DocGia(int theDG, String tenDG) {
        this.theDG = theDG;
        this.tenDG = tenDG;
    }

    public int getTheDG() {
        return theDG;
    }

    public void setTheDG(int theDG) {
        this.theDG = theDG;
    }

    public String getTenDG() {
        return tenDG;
    }

    public void setTenDG(String tenDG) {
        this.tenDG = tenDG;
    }

}
