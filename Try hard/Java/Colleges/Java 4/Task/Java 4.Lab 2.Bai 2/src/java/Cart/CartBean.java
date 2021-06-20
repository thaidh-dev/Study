package Cart;

import java.util.HashMap;

public class CartBean extends HashMap {
    public void addSanPham(ProductDTO sp) {
        String key = sp.getSanpham().getCode();
        
        if (this.containsKey(key)) {
            int oldQuantity = ((ProductDTO)this.get(key)).getQuantity();
            ((ProductDTO)this.get(key)).setQuantity(oldQuantity+1);
        }
        else {
            this.put(sp.getSanpham().getCode(), sp);
        }
    }
    
    public boolean removeSanPham(String code) {
        if (this.containsKey(code)) {
            this.remove(code);
            return true;
        }
        return false;
    }
    
    public CartBean() {
        super();
    }
}
