package iducs.springboot.khjboard.service;

import iducs.springboot.khjboard.domain.Memo;
import iducs.springboot.khjboard.entity.MemoEntity;

import java.util.List;
import java.util.Optional;

public interface MemoService {

    void create(Memo memo);

    Memo readById(Long mno);

    List<Memo> readAll();

    void update(Memo memo);

    void delete(Memo memo);

    Optional<MemoEntity> findById(Long mno);

    List<MemoEntity> findAll();


}
