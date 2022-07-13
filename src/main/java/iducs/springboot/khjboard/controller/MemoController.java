package iducs.springboot.khjboard.controller;

import iducs.springboot.khjboard.domain.Memo;
import iducs.springboot.khjboard.entity.MemoEntity;
import iducs.springboot.khjboard.service.MemoService;
import iducs.springboot.khjboard.service.MemoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/memos")
@RequiredArgsConstructor
public class MemoController {

    //MemoService 인터페이스로 부터 구현가능한 객체를 생성해서 주입.
    //Spring bean 객체로 구현하였기 때문에 Spring이 주입할 수 있음.
    private final MemoService memoService;

    //registration form 접근
    @GetMapping("/regform")
    public String getRegform(Model model) {
        model.addAttribute("memo",Memo.builder().build());
        return "/memos/regform";    
    }

    @PostMapping("")
    public String postMemo(@ModelAttribute("memo") Memo memo, Model model) {
        memoService.create(memo);
        model.addAttribute("memo", memo);
        return "/memos/memo";
    }

    @GetMapping("")
    public String getMemos(Model model) {
        List<Memo> memos = memoService.readAll();
        //List<MemoEntity> memos = memoService.findAll();
        model.addAttribute("list", memos);
        return "/memos/home";
    }

    @GetMapping("/{idx}")
    public String getMemo(@PathVariable("idx") Long mno, Model model) {
        Memo memo = memoService.readById(mno);
        model.addAttribute("memo",memo);
        return "/memos/memo";
    }

    // localhost:8888/memos/13/upform
    @GetMapping("/{idx}/upform")
    public String getUpform(@PathVariable("idx") Long mno, Model model) {
        Memo memo = memoService.readById(mno);  //entity -> domain
        model.addAttribute("memo",memo);
        return "/memos/regform";
    }

    @PutMapping("/{idx}")
    public String putMemo(@ModelAttribute("memo") Memo memo, Model model) {
        memoService.update(memo);
        model.addAttribute("memo",memo);
        return "/memos/memo";
    }

}
