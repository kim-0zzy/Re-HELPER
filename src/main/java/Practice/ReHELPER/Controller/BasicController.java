package Practice.ReHELPER.Controller;

import Practice.ReHELPER.Config.LoggedMemberHolder;
import Practice.ReHELPER.Entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class BasicController {

    private final LoggedMemberHolder loggedMemberHolder;
    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @ResponseBody
    @GetMapping("/info")
    public String info(Authentication authentication) {
        Member member = loggedMemberHolder.getLoggedMember().get(authentication.getName());
        return member.getUsername() + " + " + authentication.getName();
    }

    @GetMapping("/testPage")
    public String testPage() {
        return "/testPage";
    }

    @GetMapping("/signUp")
    public String signUpPage() {
        return "/signUp";
    }
}
