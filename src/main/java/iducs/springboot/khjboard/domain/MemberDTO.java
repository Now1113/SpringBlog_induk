package iducs.springboot.khjboard.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDTO {   // DTO(Data Transfer Object) : Client <-> Controller <-> Service

    private Long seq;    //시퀀스 번호, 자동 증가하는 유일키
    private String id;
    private String pw;
    private String name;
    private String email;
    private String phone;
    private String address;

    private String level;

    private String access;

    private String category;

}
