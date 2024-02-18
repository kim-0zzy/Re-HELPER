package Practice.ReHELPER.Controller.Member;

import Practice.ReHELPER.Config.LoggedMemberHolder;
import Practice.ReHELPER.Entity.Member;
import Practice.ReHELPER.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequiredArgsConstructor
public class TestMemberController {

    private final LoggedMemberHolder loggedMemberHolder;
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception, Model model){
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal() == "anonymousUser"){
            return "/login";
        }
        return "redirect:/";
    }

    @ResponseBody
    @GetMapping("/info")
    public String info(Authentication authentication) {
        Member member = loggedMemberHolder.getLoggedMember().get(authentication.getName());
        return member.getUsername() + " + " + authentication.getName();
    }

}
