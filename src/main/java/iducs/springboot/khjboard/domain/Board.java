package iducs.springboot.khjboard.domain;

import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    private Long bno;
    private String title;
    private String content;

    @Column(nullable = false)
    private Long views;

    private String block = "unblock";

    //업로드 구현
    @Column(nullable = true)
    private String filename;
    @Column(nullable = true)
    private String filepath;

    private Long writerSeq;
    private String writerId;
    private String writerName;
    private String writerEmail;

    private LocalDateTime regDate;
    private LocalDateTime modDate;

    private int replyCount; //게시글 댓글 수



}
