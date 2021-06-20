package lab.pkg8;

import java.io.Serializable;

public class Product implements Serializable{
    public String name;
    public Double price;
    
    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
}

// sử dụng kiểu dữ liệu như 1 tham số
// khi implement mới check kiểu dữ liệu
// không cần ép kiểu
// tái sử dụng mã
