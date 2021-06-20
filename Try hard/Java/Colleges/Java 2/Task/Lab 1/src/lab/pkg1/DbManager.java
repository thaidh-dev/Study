package lab.pkg1;

interface DbAction {
    void insert();
    void update();
    void delete();
    void select();
}

class Product implements DbAction {
    
    @Override
    public void insert() {
        System.out.println("insert product");
    }
    
    @Override
    public void update() {
        System.out.println("update product");
    }
    
    @Override
    public void delete() {
        System.out.println("delete product");
    }
    
    @Override
    public void select() {
        System.out.println("select product");
    }
}

class Order implements DbAction {
    
    public void insert() {
        System.out.println("insert order");
    }
    
    public void update() {
        System.out.println("update order");
    }
    
    public void delete() {
        System.out.println("delete order");
    }
    
    public void select() {
        System.out.println("select order");
    }
}

public class DbManager {
    
    public static void main(String[] args) {
        Product a = new Product();
        a.insert();
        a.update();
        a.delete();
        a.select();
        Order b = new Order();
        b.insert();
        b.update();
        b.delete();
        b.select();
    }
}
