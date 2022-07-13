package iducs.springboot.khjboard.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "t_board")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
public class BoardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;
    private String content;
    private Long views;

    private String filename;
    private String filepath;

    private String block = "unblock";

    @ManyToOne
    private MemberEntity writer;  //연관 관계 지정






}
