/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pack1;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author Nga
 */
public class textfile {
    public static void main(String[] args) {
        File filename = new File("first.txt");
        try {
            FileWriter out = new FileWriter(filename);
            out.write("Doc ghi du lieu trong Java!");
            out.write("\n");           //GHI VAO FILE
            out.write("Su dung Stream Character");
            out.close();
            //DOC TU FILE TEXT
            FileReader input = new FileReader(filename);
            System.out.println("Doc tu file first.txt:");
            int ch = input.read();
            while (ch != -1) {
                System.out.print((char) ch);
                ch = input.read();      //DOC TU FILE
            }
        } catch (Exception e) {
            System.out.println("Co loi: " + e);
        }
    }
}
