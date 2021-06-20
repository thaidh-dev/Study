package dft.domain.service;

import dft.domain.model.DmQuanHuyen;
import dft.domain.model.DmQuanHuyenCriteria;
import dft.domain.model.DmThonXom;
import dft.domain.model.DmXaPhuong;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DmXaPhuongService {
    Page<DmXaPhuong> searchDmXaPhuong(DmQuanHuyenCriteria criteria, Pageable pageable);
    List<DmXaPhuong> findAll();
    List<DmXaPhuong> findByMaQuanHuyen (String maQuanHuyen);
    DmXaPhuong findById(long id);
    void deleteById(int id);
    void create(DmXaPhuong dmXaPhuong);
    void update(DmXaPhuong dmXaPhuong);
}
