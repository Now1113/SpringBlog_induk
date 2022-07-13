package iducs.springboot.khjboard.service;

import iducs.springboot.khjboard.domain.Board;
import iducs.springboot.khjboard.domain.PageRequestDTO;
import iducs.springboot.khjboard.domain.PageResultDTO;
import iducs.springboot.khjboard.entity.BoardEntity;
import iducs.springboot.khjboard.entity.MemberEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface BoardService {

    Long register(Board dto);   //Board : DTO or Domain
    PageResultDTO<Board, Object[]> getList(PageRequestDTO pageRequestDTO);

    Board getById(Long bno);    //Id는 primary Key 값 @ID
    Long modify(Board dto);

    void update(Board board);

    void delete(Board board);

    int updateView(Long bno);

    //이미지 업로드
    void write(Board dto, MultipartFile file) throws IOException;

    default BoardEntity dtoToEntity(Board dto) {

        MemberEntity member = MemberEntity.builder()
                .seq(dto.getWriterSeq())
                .build();

        BoardEntity board = BoardEntity.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .block(dto.getBlock())
                .content(dto.getContent())
                .views(dto.getViews())
                .filepath(dto.getFilepath())
                .filename(dto.getFilename())
                .writer(member)
                .build();

        return board;
    }

    default Board entityToDto(BoardEntity entity, MemberEntity member, Long replayCount) {
        Board dto = Board.builder()
                .bno(entity.getBno())
                .title(entity.getTitle())
                .views(entity.getViews())
                .block(entity.getBlock())
                .content(entity.getContent())
                .filepath(entity.getFilepath())
                .filename(entity.getFilename())
                .writerSeq(member.getSeq())
                .writerId(member.getId())
                .writerName(member.getName())
                .writerEmail(member.getEmail())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .replyCount(replayCount.intValue())
                .build();

        return dto;
    }


}
