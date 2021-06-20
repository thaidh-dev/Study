package dft.domain.repository;

import dft.domain.model.DmQuanHuyen;
import dft.domain.model.DmQuanHuyenCriteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DmQuanHuyenRepository {

    long countByCriteria(@Param("criteria")DmQuanHuyenCriteria criteria);

    List<DmQuanHuyen> findPageByCriteria(@Param("criteria")DmQuanHuyenCriteria criteria, @Param("pageable") Pageable Pageable );

    List<DmQuanHuyen> findAll();

    DmQuanHuyen findOne(int id);

    void create(DmQuanHuyen dmQuanHuyen);

    void update(DmQuanHuyen dmQuanHuyen);

    void deleteById(int id);

    List<DmQuanHuyen> findByKeyWord (String keyWord);

    List<DmQuanHuyen> findByMaTinh (String maTinh);
}
