package dft.domain.service.TtCaNhanService;

import dft.domain.model.*;

import java.util.List;

public interface TtCaNhanService {
    List<TtCaNhan> findAllTtCaNhan(Long thonXomId, int trang);
    List<DmTinhTP> findAllTinh();
    List<DmQuanHuyen> findAllQuanHuyen(String maTinh);
    List<DmXaPhuong> findAllXaPhuong(String maQuanHuyen);
    List<DmThonXom> findAllThonXom(String idXaPhuong);

    TtCaNhan findOneById(Long id);
    int findOneByMaYTeCaNhan(String maYTeCaNhan);

    String findMaYTeCaNhanById(Long id);
    String findMaTinh(Long id);
    String findMaQuanHuyen(Long id);
    String findMaXaPhuong(Long id);

    void insert(TtCaNhan ttCaNhan);
    void update(TtCaNhan ttCaNhan);
    void delete(Long id);

    int tongSoDong(Long id);
}
