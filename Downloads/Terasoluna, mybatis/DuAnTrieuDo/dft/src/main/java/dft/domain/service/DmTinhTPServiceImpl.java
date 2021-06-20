package dft.domain.service;

import dft.domain.model.DmTinhTP;
import dft.domain.repository.DmTinhTPRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Transactional
@Service
public class DmTinhTPServiceImpl implements DmTinhTPService {

    @Inject
    DmTinhTPRepository dmTinhTPRepository;

    // Lấy danh sách Tỉnh TP
    @Override
    public List<DmTinhTP> findAll() {
        try {
            return dmTinhTPRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Lấy thông tin Quận Huyên theo id
     * @param id <id>
     * @return DmTinhTP
     */
    @Override
    public DmTinhTP findOne(int id) {
        try {
            return dmTinhTPRepository.findOne(id);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Thêm mới Tỉnh TP
     * @param dmTinhTP
     * @return boolean <true: thêm mới thành công , false: thêm mới thất bại >
     */
    @Override
    public boolean create(DmTinhTP dmTinhTP) {
        try {
            dmTinhTPRepository.create(dmTinhTP);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Sửa thông tin Tỉnh TP
     * @param dmTinhTP
     * @return boolean <true: Sửa thành công , false: Sửa  thất bại >
     */
    @Override
    public boolean update(DmTinhTP dmTinhTP) {
        try {
            dmTinhTPRepository.update(dmTinhTP);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Xóa Tỉnh TP
     * @param id <id của Tỉnh TP>
     * @return boolean <true: Xóa thành công , false: Xóa thất bại >
     */
    @Override
    public boolean deleteById(int id) {
        try {
            dmTinhTPRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
