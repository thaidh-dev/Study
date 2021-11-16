package dft.domain.repository.TtCaNhanRepository;

import dft.domain.model.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TtCaNhanRepository {
    //<editor-fold defaultstate="collapsed" desc="lấy ra danh mục tỉnh, huyện, xã, thôn, thông tin cá nhân">
    @Select("select * from ( \n" +
            "    select row_number() over (order by HO_TEN asc) STT,\n" +
            "           id,\n" +
            "           HO_TEN,\n" +
            "           MA_Y_TE_CA_NHAN,\n" +
            "           GIOI_TINH_ID,\n" +
            "           NGAY_SINH,\n" +
            "           TT_TINH_ID,\n" +
            "           TT_HUYEN_ID,\n" +
            "           TT_XA_ID,\n" +
            "           TT_THON_XOM_ID\n" +
            "    from TT_CA_NHAN\n" +
            "    where TT_THON_XOM_ID = #{param1}) T1\n" +
            "where\n" +
            "      T1.STT > #{param2} and\n" +
            "      ROWNUM <= 10")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "hoTen", column = "ho_ten"),
            @Result(property = "maYTeCaNhan", column = "ma_y_te_ca_nhan"),
            @Result(property = "gioiTinh", javaType = DMGioiTinh.class, column = "gioi_tinh_id", one = @One(select = "findGioiTinhById")),
            @Result(property = "ngaySinh", column = "ngay_sinh"),
            @Result(property = "ttTinh", javaType = DmTinhTP.class, column = "TT_TINH_ID", one = @One(select = "findTinhById")),
            @Result(property = "ttHuyen", javaType = DmQuanHuyen.class, column = "TT_HUYEN_ID", one = @One(select = "findQuanHuyenById")),
            @Result(property = "ttXa", javaType = DmXaPhuong.class, column = "TT_XA_ID", one = @One(select = "findXaPhuongById")),
            @Result(property = "ttThonXom", javaType = DmThonXom.class, column = "TT_THON_XOM_ID", one = @One(select = "findThonXomById"))
    })
    public List<TtCaNhan> findAllTtCaNhan(Long thonXomId, int trang);

    @Select("select * from DM_TINH_TP")
    public List<DmTinhTP> findAllTinh();

    @Select("select * from DM_QUAN_HUYEN where ma_tinh = #{maTinh}")
    public List<DmQuanHuyen> findAllQuanHuyen(String maTinh);

    @Select("select * from DM_XA_PHUONG where ma_huyen = #{maQuanHuyen}")
    public List<DmXaPhuong> findAllXaPhuong(String maQuanHuyen);

    @Select("select * from DM_THON_XOM where ma_xa = #{idXaPhuong}")
    public List<DmThonXom> findAllThonXom(String idXaPhuong);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="map">
    @Select("select id, ten from dm_gioi_tinh where id = #{id}")
    public DMGioiTinh findGioiTinhById();

    @Select("select id, ten from DM_TINH_TP where id = #{id}")
    public DmTinhTP findTinhById();

    @Select("select id, ten from DM_QUAN_HUYEN where id = #{id}")
    public DmQuanHuyen findQuanHuyenById();

    @Select("select id, ten from DM_XA_PHUONG where id = #{id}")
    public DmXaPhuong findXaPhuongById();

    @Select("select id, ten from DM_THON_XOM where id = #{id}")
    public DmThonXom findThonXomById();
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="thêm, sửa xóa">
    @Insert("insert into TT_CA_NHAN(" +
                "id, " +
                "HO_TEN, " +
                "MA_Y_TE_CA_NHAN, " +
                "NGAY_SINH, " +
                "GIOI_TINH_ID, " +
                "TT_TINH_ID, " +
                "tt_huyen_id, " +
                "tt_xa_id, " +
                "tt_thon_xom_id)\n" +
            "values (" +
                "HIBERNATE_SEQUENCE.nextval, " +
                "#{hoTen}, " +
                "#{maYTeCaNhan}, " +
                "#{ngaySinh}, " +
                "#{gioiTinh.id}, " +
                "#{ttTinh.id}, " +
                "#{ttHuyen.id}, " +
                "#{ttXa.id}, " +
                "#{ttThonXom.id})"
    )
    public void insert(TtCaNhan ttCaNhan);

    @Update("update TT_CA_NHAN set " +
                "HO_TEN = #{hoTen}, " +
                "MA_Y_TE_CA_NHAN = #{maYTeCaNhan}, " +
                "NGAY_SINH = #{ngaySinh}, " +
                "GIOI_TINH_ID = #{gioiTinh.id}, " +
                "TT_TINH_ID = #{ttTinh.id}, " +
                "tt_huyen_id = #{ttHuyen.id}, " +
                "tt_xa_id = #{ttXa.id}, " +
                "tt_thon_xom_id = #{ttThonXom.id}\n" +
            "where ID = #{id}")
    public void update(TtCaNhan ttCaNhan);

    @Delete("delete from tt_ca_nhan where id = #{id}")
    public void delete(Long id);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="find mã">
    @Select("select ma_y_te_ca_nhan from tt_ca_nhan where id = #{id}")
    public String findMaYTeCaNhanById(Long id);

    @Select("select ma from DM_TINH_TP where id = #{id}")
    public String findMaTinh(Long id);

    @Select("select ma from DM_QUAN_HUYEN where id = #{id}")
    public String findMaQuanHuyen(Long id);

    @Select("select ma from dm_xa_phuong where id = #{id}")
    public String findMaXaPhuong(Long id);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="find one">
    @Select("select count(*) from TT_CA_NHAN where MA_Y_TE_CA_NHAN = #{maYTeCaNhan}")
    public int findOneByMaYTeCaNhan(String maYTeCaNhan);

    @Select("select id, " +
                "ho_ten, " +
                "ma_y_te_ca_nhan, " +
                "gioi_tinh_id, " +
                "ngay_sinh, " +
                "TT_TINH_ID, " +
                "TT_HUYEN_ID, " +
                "TT_XA_ID, " +
                "TT_THON_XOM_ID " +
            "from tt_ca_nhan " +
            "where id = #{id}")
    @ResultMap("findAllTtCaNhan-Long-int")
    // lấy đoạn map @Results của findAllTtCaNhan(int trang, Long thonXomId)
    // int là kiểu tham số của phương thức findAllTtCaNhan
    // nếu không có tham số thì là void
    public TtCaNhan findOneById(Long id);
    //</editor-fold>

    // tổng số dòng của trang
    @Select("select count(*) from tt_ca_nhan where tt_thon_xom_id = #{id}")
    public int tongSoDong(Long id);

    // tên tham số #{} đặt thế nào cũng đc
    // nếu tham số truyền vào là 1 đối tương thì tham số #{} phải trùng với tên thuộc tính của đối tượng đc truyền vào
    // select thì mybatis sẽ tự map kết quả select đc vào
}
