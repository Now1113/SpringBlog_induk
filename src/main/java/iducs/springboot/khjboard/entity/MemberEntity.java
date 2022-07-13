package iducs.springboot.khjboard.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//엔티티

//Spring Data 관련 Annotation
@Entity //Spring Data JPA의 엔티티(Entity, 개체)임을 나타냄.
@Table(name = "t_member")

//Lombok 관련 Annotation
@ToString   //toString() 생성
@Getter
@Setter
@Builder
@AllArgsConstructor //모든 매개 변수를 갖는 생성자
@NoArgsConstructor  //Default 생성자(매개 변수X)
public class MemberEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    @Column(length = 30, nullable = false)
    private String id;
    @Column(length = 30, nullable = false)
    private String pw;
    @Column(length = 30, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String email;
    @Column(length = 100)
    private String phone;
    @Column(length = 100)
    private String address;
    private String level;

    private String category;

    @OneToMany(mappedBy = "writer")
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    private String access;



}
