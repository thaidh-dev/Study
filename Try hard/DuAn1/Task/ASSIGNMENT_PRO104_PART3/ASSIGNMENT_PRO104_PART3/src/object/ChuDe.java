/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.io.Serializable;

/**
 *
 * @author Thanh
 */
public class ChuDe implements Serializable{
    
   private int maCD;
   private String tenCD;

    public ChuDe() {
    }

    public ChuDe(int maCD, String tenCD) {
        this.maCD = maCD;
        this.tenCD = tenCD;
    }

    @Override
    public String toString() {
        return tenCD;
    }

    public int getMaCD() {
        return maCD;
    }

    public void setMaCD(int maCD) {
        this.maCD = maCD;
    }

    public String getTenCD() {
        return tenCD;
    }

    public void setTenCD(String tenCD) {
        this.tenCD = tenCD;
    }
    
}
