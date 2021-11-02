package lavaspoon.hellospring.controller;

import lavaspoon.hellospring.domain.Member;
import lavaspoon.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private  final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
//1) URL 타고 들어 -> createForm() 매핑됨
    @GetMapping("members/new")
    public String createForm() {
        //2) templates 에서 createForm 파일을 찾
        return "members/createForm";
    }
    //4) post로 데이터를 받음
    @PostMapping("/members/new")
    public String create(MemberForm form){ //5) MemberForm 클래스의 setName에 post받은 name을 대입하여 private name에 값을 대입함.
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("GetName = " + member.getName());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public  String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
