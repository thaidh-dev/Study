package dft.domain.service.TtCaNhanService;

import dft.domain.model.*;
import dft.domain.repository.TtCaNhanRepository.TtCaNhanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional
public class TtCaNhanServiceImplement implements TtCaNhanService {
    @Inject
    TtCaNhanRepository ttCaNhanRepository;

    //<editor-fold defaultstate="collapsed" desc="find all tất cả tt cá nhân, tỉnh, huyện, xã, thôn">
    @Override
    public List<TtCaNhan> findAllTtCaNhan(Long thonXomId, int trang) {
        return ttCaNhanRepository.findAllTtCaNhan(thonXomId, trang);
    }

    @Override
    public List<DmTinhTP> findAllTinh() {
        return ttCaNhanRepository.findAllTinh();
    }

    @Override
    public List<DmQuanHuyen> findAllQuanHuyen(String maTinh) {
        return ttCaNhanRepository.findAllQuanHuyen(maTinh);
    }

    @Override
    public List<DmXaPhuong> findAllXaPhuong(String maQuanHuyen) {
        return ttCaNhanRepository.findAllXaPhuong(maQuanHuyen);
    }

    @Override
    public List<DmThonXom> findAllThonXom(String idXaPhuong) {
        return ttCaNhanRepository.findAllThonXom(idXaPhuong);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="find mã">
    @Override
    public String findMaYTeCaNhanById(Long id) {
        return ttCaNhanRepository.findMaYTeCaNhanById(id);
    }

    @Override
    public String findMaTinh(Long id) {
        return ttCaNhanRepository.findMaTinh(id);
    }

    @Override
    public String findMaQuanHuyen(Long id) {
        return ttCaNhanRepository.findMaQuanHuyen(id);
    }

    @Override
    public String findMaXaPhuong(Long id) {
        return ttCaNhanRepository.findMaXaPhuong(id);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="find one">
    @Override
    public int findOneByMaYTeCaNhan(String maYTeCaNhan) {
        return ttCaNhanRepository.findOneByMaYTeCaNhan(maYTeCaNhan);
    }

    @Override
    public TtCaNhan findOneById(Long id) {
        return ttCaNhanRepository.findOneById(id);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="thêm sửa xóa">
    @Override
    public void insert(TtCaNhan ttCaNhan) {
        ttCaNhanRepository.insert(ttCaNhan);
    }

    @Override
    public void update(TtCaNhan ttCaNhan) {
        ttCaNhanRepository.update(ttCaNhan);
    }

    @Override
    public void delete(Long id) {
        ttCaNhanRepository.delete(id);
    }
    //</editor-fold>

    @Override
    public int tongSoDong(Long id) {
        return ttCaNhanRepository.tongSoDong(id);
    }
}
