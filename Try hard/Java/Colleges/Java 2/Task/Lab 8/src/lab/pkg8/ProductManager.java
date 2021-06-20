package lab.pkg8;

public class ProductManager {
    public static void main(String[] args) {
        Product p1 = new Product("iphone9", 1000.0);
        Product p2 = new Product("samsung", 3000.0);
        
        ProductDAO dao = new ProductDAO();
        dao.add(p1);
        dao.add(p2);
        dao.load("dht.dat");
        
        ProductDAO dao2 = new ProductDAO();
        dao2.store("dht.dat");
        dao2.find("iphone9");
    }
}

