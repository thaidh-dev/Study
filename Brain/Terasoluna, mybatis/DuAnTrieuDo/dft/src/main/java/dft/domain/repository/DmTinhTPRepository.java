package dft.domain.repository;

import dft.domain.model.DmTinhTP;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DmTinhTPRepository {

    List<DmTinhTP> findAll();

    DmTinhTP findOne(int id);

    void create(DmTinhTP dmTinhTP);

    void update(DmTinhTP dmTinhTP);

    void deleteById(int id);
}
