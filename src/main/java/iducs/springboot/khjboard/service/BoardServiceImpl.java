package iducs.springboot.khjboard.service;

import iducs.springboot.khjboard.domain.Board;
import iducs.springboot.khjboard.domain.PageRequestDTO;
import iducs.springboot.khjboard.domain.PageResultDTO;
import iducs.springboot.khjboard.entity.BoardEntity;
import iducs.springboot.khjboard.entity.MemberEntity;
import iducs.springboot.khjboard.repository.BoardRepository;
import iducs.springboot.khjboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.ObjectError;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public Long register(Board dto) {   //Controller -> DTO 객체 -> Service
        log.info(dto);
        BoardEntity boardEntity = dtoToEntity(dto);
        boardRepository.save(boardEntity);
        return boardEntity.getBno();    //게시물 번호
    }

    @Override
    public void delete(Board board) {
        BoardEntity entity = dtoToEntity(board);
        boardRepository.deleteById(entity.getBno());
    }

    @Transactional
    @Override
    public int updateView(Long bno) {
        return boardRepository.updateView(bno);
    }

    @Override
    public void write(Board dto, MultipartFile file) throws IOException {
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        dto.setFilename(fileName);
        dto.setFilepath("/files/" + fileName);

        BoardEntity boardEntity = dtoToEntity(dto);
        boardRepository.save(boardEntity);
    }

    @Override
    public PageResultDTO<Board, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info(">>>>" + pageRequestDTO);

        Function<Object[], Board> fn = (entity -> entityToDto((BoardEntity) entity[0],
                (MemberEntity) entity[1],(Long) entity[2]));
        String type = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = null;
        String page = pageRequestDTO.getSort();
        String asc = "asc";
        pageable = pageRequestDTO.getPageable(Sort.by("views").descending());
        if(page != null) {
            if(page.equals(asc)) {
                pageable = pageRequestDTO.getPageable(Sort.by("views").ascending());
            }
        }

        Page<Object[]> result = boardRepository.searchPage(type, keyword, pageable);

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public Board getById(Long bno) {
        Object result = boardRepository.getBoardByBno(bno); //기본 CRUD는 JpaRepository 가 제공
        Object[] en = (Object[]) result;
        return entityToDto((BoardEntity) en[0], (MemberEntity) en[1], (Long) en[2]);
    }

    @Override
    public Long modify(Board dto) {
        return null;
    }


    @Override
    public void update(Board board) {
        BoardEntity entity = dtoToEntity(board);
        boardRepository.save(entity);
    }

}
