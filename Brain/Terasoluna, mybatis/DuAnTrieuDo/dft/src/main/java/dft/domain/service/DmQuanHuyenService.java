package dft.domain.service;

import dft.domain.model.DmQuanHuyen;
import dft.domain.model.DmQuanHuyenCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DmQuanHuyenService {

    Page<DmQuanHuyen> searchDmQuanHuyen(DmQuanHuyenCriteria criteria, Pageable pageable);

    List<DmQuanHuyen> findAll();

    DmQuanHuyen findOne(int id);

    boolean create(DmQuanHuyen dmQuanHuyen);

    boolean update(DmQuanHuyen dmQuanHuyen);

    boolean deleteById(int id);

    List<DmQuanHuyen> findByKeyWord (String keyWord);

    List<DmQuanHuyen> findByMaTinh ( String maXaPhuong);
}
