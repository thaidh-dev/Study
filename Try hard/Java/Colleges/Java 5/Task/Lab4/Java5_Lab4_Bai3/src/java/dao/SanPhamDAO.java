package dao;

import dto.SanPhamDTO;
import helper.HibernateHelper;
import model.SanPham;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SanPhamDAO {
    HibernateHelper<SanPham> h = new HibernateHelper<>();

    // lấy toàn bộ sản phẩm và tính luôn giá mới = giá cũ * giảm giá / 100
    public List<SanPhamDTO> getALlSanPham() {
        List<SanPham> lstSanPham = h.query("from SanPham");
        List<SanPhamDTO> lstSanPhamDTO = new ArrayList<>();

        for (SanPham sp : lstSanPham) {
            SanPhamDTO spDTO = new SanPhamDTO();
            spDTO.setId(sp.getId());
            spDTO.setTenSanPham(sp.getTenSanPham());
            spDTO.setGiaCu(convert_So_Tien(sp.getGiaCu()));
            spDTO.setGiamGia(String.valueOf(sp.getGiamGia()) + "%");
            spDTO.setGiaMoi(tinhGiaMoi(sp.getGiaCu(), sp.getGiamGia()));

            lstSanPhamDTO.add(spDTO);
        }

        return lstSanPhamDTO;
    }

    public SanPham getById(int id) {
        return h.get("model.SanPham", id);
    }

    public void saveOrUpdate(SanPham sp) {
        h.saveOrUpdate(sp);
    }

    public void delete(int id) {
        h.delete("model.SanPham", id);
    }

    // chuyển từ dạng số sang dạng tiền
    private String convert_So_Tien(BigDecimal tien) {
        NumberFormat n = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return n.format(tien);
    }

    // tính giá mới = giá cũ * giảm giá / 100
    private String tinhGiaMoi(BigDecimal giaCu, int giamGia) {
        BigDecimal phanDuocGiam = giaCu.multiply(BigDecimal.valueOf(giamGia));
        phanDuocGiam = phanDuocGiam.divide(BigDecimal.valueOf(100), MathContext.DECIMAL32);

        BigDecimal giaMoi = giaCu.subtract(phanDuocGiam);

        return convert_So_Tien(giaMoi);
    }
}
