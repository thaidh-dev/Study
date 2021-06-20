package dft.domain.service;

import dft.domain.model.DmQuanHuyenCriteria;
import dft.domain.model.DmThonXom;
import dft.domain.repository.DmThonXomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class DmThonXomServiceImpl implements DmThonXomService {
    @Inject
    private DmThonXomRepository dmThonXomRepository;

    @Override
    public List<DmThonXom> findAll() {
        return dmThonXomRepository.findAll();
    }

    @Override
    public List<DmThonXom> findThonXomByMaXaPhuong(int idXaPhuong) {
        return dmThonXomRepository.findThonXomByMaXaPhuong(idXaPhuong);
    }

    @Override
    public DmThonXom findById(int id) {
        return dmThonXomRepository.findById(id);
    }

    @Override
    public void create(DmThonXom dmThonXom) {
        dmThonXomRepository.create(dmThonXom);
    }

    @Override
    public void update(DmThonXom dmThonXom) {
        dmThonXomRepository.update(dmThonXom);
    }

    @Override
    public void deleteById(int id) {
        dmThonXomRepository.deleteById(id);
    }

    @Override
    public Page<DmThonXom> searchDmThonXom(DmQuanHuyenCriteria criteria, Pageable pageable) {
        long total = countByCriteria(criteria);
        List<DmThonXom> dmThonXoms;
        if (total > 0) {
            dmThonXoms = dmThonXomRepository.findPageByCriteria(criteria, pageable);
        } else {
            dmThonXoms = Collections.emptyList();
        }

        return new PageImpl<>(dmThonXoms,pageable,total);
    }

    @Override
    public long countByCriteria(DmQuanHuyenCriteria criteria) {
        return dmThonXomRepository.countByCriteria(criteria);
    }

}
