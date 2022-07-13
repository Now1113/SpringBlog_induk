package iducs.springboot.khjboard.entity;

import lombok.*;

import javax.persistence.*;


@Entity //Spring Data JPA의 엔티티(Entity, 개체)임을 나타냄.
@Table(name = "tbl_memo")
@ToString   //toString() 생성
@Getter
@Setter
@Builder
@AllArgsConstructor //모든 매개 변수를 갖는 생성자
@NoArgsConstructor  //Default 생성자(매개 변수X)
public class MemoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(length = 50, nullable = false)
    private String memoText;

}
