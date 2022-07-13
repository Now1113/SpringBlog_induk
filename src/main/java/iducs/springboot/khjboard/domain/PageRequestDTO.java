package iducs.springboot.khjboard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
    private int page;   //요청하는 페이지
    private int size;   //한 페이지에 나타나는 수

    private String sort;    //asc, desc
    //private Sort sort;
    private String type;    //검색 조건, setType(), getType()
    private String keyword; // 검색어, set Keyword(), getKeyword()

    public PageRequestDTO() {
        this.page = 1;
        this.size = 5;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }

}
