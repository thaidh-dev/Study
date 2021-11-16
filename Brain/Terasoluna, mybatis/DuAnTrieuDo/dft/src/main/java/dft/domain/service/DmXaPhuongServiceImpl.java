package dft.domain.service;

import dft.domain.model.DmQuanHuyen;
import dft.domain.model.DmQuanHuyenCriteria;
import dft.domain.model.DmXaPhuong;
import dft.domain.repository.DmXaPhuongRepository;
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
public class DmXaPhuongServiceImpl implements DmXaPhuongService{
    @Inject
    DmXaPhuongRepository dmXaPhuongRepository;

    @Override
    public Page<DmXaPhuong> searchDmXaPhuong(DmQuanHuyenCriteria criteria, Pageable pageable) {
        long total = dmXaPhuongRepository.countByCriteria(criteria);
        List<DmXaPhuong> dmXaPhuongs;
        if (0 < total) {
            dmXaPhuongs = dmXaPhuongRepository.findPageByCriteria(criteria, pageable);
        } else {
            dmXaPhuongs = Collections.emptyList();
        }
        return new PageImpl<>(dmXaPhuongs, pageable, total);
    }

    @Override
    public List<DmXaPhuong> findAll() {
        return dmXaPhuongRepository.findAll();
    }

    @Override
    public List<DmXaPhuong> findByMaQuanHuyen(String maQuanHuyen){
        try {
            return dmXaPhuongRepository.findByMaQuanHuyen(maQuanHuyen);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    @Override
    public DmXaPhuong findById(long id){
        return dmXaPhuongRepository.findById(id);
    }

    @Override
    public void deleteById(int id){
        dmXaPhuongRepository.deleteById(id);
    }

    @Override
    public void create(DmXaPhuong dmXaPhuong) {
        dmXaPhuongRepository.create(dmXaPhuong);
    }

    @Override
    public void update(DmXaPhuong dmXaPhuong) {
        dmXaPhuongRepository.update(dmXaPhuong);
    }


}
