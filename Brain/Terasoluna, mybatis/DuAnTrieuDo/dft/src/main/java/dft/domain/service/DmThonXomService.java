package dft.domain.service;

import dft.domain.model.DmQuanHuyenCriteria;
import dft.domain.model.DmThonXom;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DmThonXomService {
    /*Phương thức: Lấy toàn bộ danh sách thôn xóm*/
    List<DmThonXom> findAll();

    /*Phương thức: Lấy danh sách thôn xóm theo Mã của Xã, Phường*/
    List<DmThonXom> findThonXomByMaXaPhuong(int idXaPhuong);

    /*Phương thức: Tìm thôn xóm theo Mã của Xã, Phường*/
    DmThonXom findById(int id);

    /*Phương thức: Tạo một thôn, xóm mới*/
    void create(DmThonXom dmThonXom);

    /*Phương thức: Cập nhật thôn, xóm*/
    void update(DmThonXom dmThonXom);

    /*Phương thức: Xóa thôn, xóm theo ID*/
    void deleteById(int id);

    /*Phương thức: Tìm kiếm thôn, xóm theo tên*/
    Page<DmThonXom> searchDmThonXom(DmQuanHuyenCriteria criteria, Pageable pageable);

    /*Phương thức: Đếm số kết quả tìm kiếm trả về*/
    long countByCriteria(@Param("criteria") DmQuanHuyenCriteria criteria);
}
