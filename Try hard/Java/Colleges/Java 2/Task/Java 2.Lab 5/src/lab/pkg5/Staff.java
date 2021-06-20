package lab.pkg5;

import java.io.Serializable;

public class Staff implements Serializable{
    public String ten;
    public double luong;
}

/* file là đọc nhị phân
read(); write();

data là đọc kiểu dữ liệu
readInt(); wwriteInt();

Object là đọc ghi đối tượng
readObject(); writeObject();

đọc trong input thường có vòng lặp (read)
còn ghi trong output bằng mảng (write)
*/

/*
kiểu đọc ghi kí tự gây ra bất tiện, mất thời gian vì nó đọc 1 kí tự rồi ghi rồi lại quay về đọc 1 kí tự rồi ghi ra
nên là nó nghĩ cái trò dùng luồng đệm befered
*/
