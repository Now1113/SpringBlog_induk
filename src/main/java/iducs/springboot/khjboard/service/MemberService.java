package iducs.springboot.khjboard.service;

import iducs.springboot.khjboard.domain.Board;
import iducs.springboot.khjboard.domain.MemberDTO;
import iducs.springboot.khjboard.domain.PageRequestDTO;
import iducs.springboot.khjboard.domain.PageResultDTO;
import iducs.springboot.khjboard.entity.MemberEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {

    void deleteWithBoards(Long seq);

    void create(MemberDTO member);

    MemberDTO readById(Long seq);

    List<MemberDTO> readAll();

    PageResultDTO<MemberDTO, MemberEntity> readListBy(PageRequestDTO pageRequestDTO);

    void update(MemberDTO member);

    void delete(MemberDTO member);

    MemberDTO readByName(MemberDTO member);

    MemberDTO readByEmail(String email);

    MemberDTO loginByEmail(MemberDTO dto);

}
