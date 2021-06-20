package lab.pkg5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {
    public String name;
    public double marks;
    public String major;
    
    public Student(String name, double marks, String major) {
        this.name = name;
        this.marks = marks;
        this.major = major;
    }
    
    public String getGrade() {
        if (marks < 3) {
            return "Kém";
        }
        if (marks < 5) {
            return "Yếu";
        }
        if (marks < 6.5) {
            return "Trung bình";
        }
        if (marks < 7.5) {
            return "Khá";
        }
        if (marks < 9) {
            return "Giỏi";
        }
        return "Xuất sắc";
    }
    
    public boolean isBonus() {
        return this.marks >= 7.5;
    }
}

class Thai {
    public static void main(String[] args) {
        List <Student> list = new ArrayList<>();
        list.add(new Student("Tuấn", 5, "CNTT"));
        list.add(new Student("Cường", 7.5, "TKTW"));
        list.add(new Student("Hạnh", 8.5, "CNTT"));
        
        XFile.writeObject("lmao.txt", list);
        List <Student> list2 = (List <Student>) XFile.readObject("lmao.txt");
        for (Student sv : list) {
            System.out.println("Họ và tên: "+sv.name);
        }
    }
}
