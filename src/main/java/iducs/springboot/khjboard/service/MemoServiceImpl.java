package iducs.springboot.khjboard.service;

import iducs.springboot.khjboard.domain.Memo;
import iducs.springboot.khjboard.entity.MemoEntity;
import iducs.springboot.khjboard.repository.MemoRepository;
import iducs.springboot.khjboard.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {

    //MemoServiceImpl 객체가 생성될 때 MemoRepository 객체가 자동 주입
    private final MemoRepository memoRepository;

    @Override
    public void create(Memo memo) {
        // DTO(Controller -> Service) -> Entity (Service -> Repository 에서 동작)
        // ????????????????????
        MemoEntity entity = MemoEntity.builder()
                .mno(memo.getMno())
                .memoText(memo.getMemoText())
                .build();

        memoRepository.save(entity);
    }

    @Override
    public Memo readById(Long mno) {
        Memo memo = null;
        Optional<MemoEntity> result = memoRepository.findById(mno);

        if (result.isPresent()) {
            memo = Memo.builder()
                    .mno(result.get().getMno())
                    .memoText(result.get().getMemoText())
                    .build();
        }

        return memo;
    }

    @Override
    public List<Memo> readAll() {
        List<MemoEntity> listOfMemoEntity = memoRepository.findAll();
        List<Memo> listOfMemo = new ArrayList<>();
        for (MemoEntity entity : listOfMemoEntity) {
            Memo memo = Memo.builder()
                    .mno(entity.getMno())
                    .memoText(entity.getMemoText())
                    .build();

            listOfMemo.add(memo);
        }

        return listOfMemo;
    }

    @Override
    public void update(Memo memo) {
        MemoEntity entity = MemoEntity.builder()
                .mno(memo.getMno())
                .memoText(memo.getMemoText())
                .build();

        memoRepository.save(entity);
    }

    @Override
    public void delete(Memo memo) {

    }

    @Override
    public Optional<MemoEntity> findById(Long mno) {
        return memoRepository.findById(mno);
    }

    @Override
    public List<MemoEntity> findAll() {
        return memoRepository.findAll();
    }
}
