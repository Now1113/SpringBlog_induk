package iducs.springboot.khjboard.controller;

import iducs.springboot.khjboard.domain.MemberDTO;
import iducs.springboot.khjboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//데이터(처리결과)를 view 에게 전달
//@RestController   //자원의 상태(응답)를 Client 에게 저장
//Restful (Representational State Transfer), OpenAPIs
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {

    private final MemberService memberService;

    @GetMapping("")
    public String getIndex() {
        return "index";
    }

    @GetMapping("index2")
    public String getIndex2() {
        return "index2";
    }

    @GetMapping("index3")
    public String getIndex3() {
        return "index3";
    }

    @GetMapping("pages/tables/simple")
    public String getSimple() {
        return "/pages/tables/simple";
    }

    @GetMapping("pages/contacts/{idx}")
    public String getContacts(@PathVariable("idx") Long seq, Model model) {
        MemberDTO member = memberService.readById(seq);
        model.addAttribute("member", member);
        return "/members/contacts";
    }
}
