package dft.domain.repository;

import dft.domain.model.DmQuanHuyenCriteria;
import dft.domain.model.DmThonXom;
import dft.domain.model.DmXaPhuong;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DmThonXomRepository {
    /*Phương thức: Lấy toàn bộ danh sách thôn xóm*/
    List<DmThonXom> findAll();

    /*Phương thức: Lấy danh sách thôn xóm theo Mã của Xã, Phường*/
    List<DmThonXom> findThonXomByMaXaPhuong(int idXaPhuong);

    /*Phương thức: Tìm thôn xóm theo ID*/
    DmThonXom findById(int id);

    /*Phương thức: Tạo một thôn, xóm mới*/
    void create(DmThonXom dmThonXom);

    /*Phương thức: Cập nhật thôn, xóm*/
    void update(DmThonXom dmThonXom);

    /*Phương thức: Xóa thôn, xóm theo ID*/
    void deleteById(int id);

    /*Phương thức: Tìm kiếm thôn, xóm theo tên*/
    List<DmThonXom> findPageByCriteria(@Param("criteria") DmQuanHuyenCriteria criteria, @Param("pageable") Pageable pageable);

    /*Phương thức: Đếm số thôn thỏa mãn điều kiện tìm kiếm*/
    long countByCriteria(@Param("criteria") DmQuanHuyenCriteria criteria);

}
