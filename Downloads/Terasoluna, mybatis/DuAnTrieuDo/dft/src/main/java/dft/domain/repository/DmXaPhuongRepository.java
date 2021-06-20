package dft.domain.repository;

import dft.domain.model.DmQuanHuyen;
import dft.domain.model.DmQuanHuyenCriteria;
import dft.domain.model.DmXaPhuong;
import org.apache.ibatis.annotations.Param;
import org.apache.tiles.autotag.core.runtime.annotation.Parameter;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DmXaPhuongRepository {
    List<DmXaPhuong> findAll();

    List<DmXaPhuong> findByMaQuanHuyen (@Param("maQuanHuyen") String maQuanHuyen);

    DmXaPhuong findById(long id);

    void deleteById(int id);

    void create(DmXaPhuong dmXaPhuong);

    void update(DmXaPhuong dmXaPhuong);

    long countByCriteria(@Param("criteria") DmQuanHuyenCriteria criteria);

    List<DmXaPhuong> findPageByCriteria(@Param("criteria")DmQuanHuyenCriteria criteria, @Param("pageable") Pageable Pageable );
}
