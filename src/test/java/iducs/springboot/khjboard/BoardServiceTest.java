package iducs.springboot.khjboard;

import iducs.springboot.khjboard.domain.Board;
import iducs.springboot.khjboard.domain.PageRequestDTO;
import iducs.springboot.khjboard.domain.PageResultDTO;
import iducs.springboot.khjboard.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class BoardServiceTest {

    @Autowired
    BoardService boardService;


    @Test
    public void 등록테스트() {
        IntStream.rangeClosed(1, 47).forEach(i -> {
            Board dto = Board.builder()
                    .title("Test")
                    .content("content")
                    .writerSeq((Long.valueOf("" + i)))
                    .block("unblock")
                    .views(0L)
                    .build();

            Long bno = boardService.register(dto);
        });
    }
}
