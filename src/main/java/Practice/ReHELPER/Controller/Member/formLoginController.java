package Practice.ReHELPER.Controller.Member;

import Practice.ReHELPER.Config.LoggedMemberHolder;
import Practice.ReHELPER.Entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
@RequiredArgsConstructor
public class formLoginController {

    private final LoggedMemberHolder loggedMemberHolder;
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception, Model model){
        return "/login";
    }

    @ResponseBody
    @GetMapping("/info")
    public String info(Authentication authentication) {
        Member member = loggedMemberHolder.getLoggedMember().get(authentication.getName());
        return member.getUsername() + " + " + authentication.getName();
    }
}
