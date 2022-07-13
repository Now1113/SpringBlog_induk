package iducs.springboot.khjboard.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import iducs.springboot.khjboard.domain.Board;
import iducs.springboot.khjboard.domain.MemberDTO;
import iducs.springboot.khjboard.domain.PageRequestDTO;
import iducs.springboot.khjboard.domain.PageResultDTO;
import iducs.springboot.khjboard.entity.BoardEntity;
import iducs.springboot.khjboard.entity.MemberEntity;
import iducs.springboot.khjboard.entity.QMemberEntity;
import iducs.springboot.khjboard.repository.BoardRepository;
import iducs.springboot.khjboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

// @Component, @Configuration, @Beans
// @Service, @Repository
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Override
    public void create(MemberDTO member) {

        MemberEntity entity = dtoToEntity(member);
        //new MemberEntity(seq, id, pw, name, email, phone, address,);
        memberRepository.save(entity);
    }

    private MemberEntity dtoToEntity(MemberDTO member) {
        MemberEntity entity = MemberEntity.builder()
                .seq(member.getSeq())
                .id(member.getId())
                .pw(member.getPw())
                .name(member.getName())
                .email(member.getEmail())
                .phone(member.getPhone())
                .access(member.getAccess())
                .category(member.getCategory())
                .address(member.getAddress())
                .level(member.getLevel())
                .build();
        return entity;
    }

    // Service -> Controller : entity -> DTO로 변환 후 반환
    public MemberDTO entityToDto(MemberEntity entity) {

        MemberDTO member = MemberDTO.builder()
                .seq(entity.getSeq())
                .id(entity.getId())
                .pw(entity.getPw())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .access(entity.getAccess())
                .address(entity.getAddress())
                .level(entity.getLevel())
                .category(entity.getCategory())
                .build();
        return member;
    }

    @Override
    public MemberDTO readById(Long seq) {
        MemberDTO member = null;
        Optional<MemberEntity> result = memberRepository.findById(seq);
        if (result.isPresent()) {
            member = entityToDto(result.get());
        }
        return member;
    }



    @Override
    public List<MemberDTO> readAll() {

        List<MemberDTO> members = new ArrayList<>();   //반환 리스트 객체

        List<MemberEntity> entities = memberRepository.findAll();   //entity들
        for (MemberEntity entity : entities) {
            MemberDTO member = entityToDto(entity);
            members.add(member);
        }
        return members;
    }

    @Override
    public PageResultDTO<MemberDTO, MemberEntity> readListBy(PageRequestDTO pageRequestDTO) {

        // 정렬부분입니다
        Sort sort = Sort.by("seq").descending();
        if(pageRequestDTO.getSort() == null)
            Sort.by("seq").descending();
        else if(pageRequestDTO.getSort().equals("asc"))
            sort = Sort.by("seq").ascending();

        Pageable pageable = pageRequestDTO.getPageable(sort);
        BooleanBuilder booleanBuilder = findByCondition(pageRequestDTO);

        Page<MemberEntity> result = memberRepository.findAll(booleanBuilder, pageable);
        //Page<MemberEntity> result = memberRepository.findAll(pageable);
        Function<MemberEntity, MemberDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }

    private BooleanBuilder findByCondition(PageRequestDTO pageRequestDTO) {

        String type = pageRequestDTO.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QMemberEntity qMemberEntity = QMemberEntity.memberEntity;

        BooleanExpression expression = qMemberEntity.seq.gt(0L);    //where seq > 0 and title == "title"
        booleanBuilder.and(expression);

        if (type == null || type.trim().length() == 0) {
            return booleanBuilder;
        }

        String keyword = pageRequestDTO.getKeyword();

        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if(type.contains("e")){// email 로 검색
            conditionBuilder.or(qMemberEntity.email.contains(keyword));
        }
        if(type.contains("p")){// phone 로 검색
            conditionBuilder.or(qMemberEntity.phone.contains(keyword));
        }
        if(type.contains("a")){// address 로 검색
            conditionBuilder.or(qMemberEntity.address.contains(keyword));
        }
        if(type.contains("l")){// address 로 검색
            conditionBuilder.or(qMemberEntity.level.contains(keyword));
        }
        booleanBuilder.and(conditionBuilder);

        return booleanBuilder;  //완성된 조건 or 술어
    }

    @Override
    public void update(MemberDTO member) {
        MemberEntity entity = dtoToEntity(member);
        memberRepository.save(entity);
    }

    @Override
    public void delete(MemberDTO member) {
        MemberEntity entity = dtoToEntity(member);
        memberRepository.deleteById(entity.getSeq());
    }

    @Override
    public MemberDTO readByName(MemberDTO member) {
        return null;
    }

    @Override
    public MemberDTO readByEmail(String email) {
        return null;
    }

    @Override
    public MemberDTO loginByEmail(MemberDTO member) {
        MemberDTO memberDTO = null;
        Object result = memberRepository.getMemberByEmail(member.getEmail(), member.getPw());
        if (result != null) {
            memberDTO = entityToDto((MemberEntity) result);
        }
        return memberDTO;
    }

    @Override
    public void deleteWithBoards(Long seq) {
        MemberEntity byId = memberRepository.getById(seq);
        List<BoardEntity> boardEntities = byId.getBoardEntityList();
        Iterator b = boardEntities.iterator();
        while (b.hasNext()) {
            Object next = b.next();
            boardRepository.delete((BoardEntity) next);
        }

    }
}
