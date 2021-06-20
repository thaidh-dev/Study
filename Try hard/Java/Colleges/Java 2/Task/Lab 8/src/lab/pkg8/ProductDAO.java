package lab.pkg8;

import java.io.Serializable;

public class ProductDAO extends DAO<Product> {

    @Override
    public void update(Product entity) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).name.equalsIgnoreCase(entity.name)) {
                list.set(i, entity);
            }
        }
    }

    @Override
    public void find(Serializable id) {
        for (Product p : list) {
            if (p.name.equals(id)) {
                System.out.println(p.name);
                System.out.println(p.price);
                break;
            }
        }
    }
}

// không sử dụng mảng
// không static vì lúc nó chạy nó mới biết đc kiểu dữ liệu
// không bắt lỗi đc vì không viết nó lỗi j, lúc chạy mới biết được kiểu dữ liệu