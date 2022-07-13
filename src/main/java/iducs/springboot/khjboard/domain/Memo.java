package iducs.springboot.khjboard.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

@Data
@Builder
public class Memo { //DTO : Controller -> Service

    private Long mno;

    private String memoText;


}
