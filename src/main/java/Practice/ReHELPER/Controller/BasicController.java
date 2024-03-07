package Practice.ReHELPER.Controller;

import Practice.ReHELPER.Config.LoggedMemberHolder;
import Practice.ReHELPER.Entity.Member;
import Practice.ReHELPER.Exception.NotLoggedInException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class BasicController {

    private final LoggedMemberHolder loggedMemberHolder;

    public Member loadLoginMember() throws NotLoggedInException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            throw new NotLoggedInException("Not Logged in Yet");
        }
        return loggedMemberHolder.getLoggedMember().get(authentication.getName());
    }

    @GetMapping("/")
    public String home() {
        return "/login";
    }
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

    @GetMapping("/signUpSuccessPage")
    public String signUpSuccessPage() {
        return "/signUpSuccessPage";
    }

    @GetMapping("/inputSuccessPage")
    public String inputSuccessPage() {
        return "/inputSuccessPage";
    }

    @GetMapping("/createMemberSpec")
    public String createMS() throws NotLoggedInException {
        if (loadLoginMember().getMemberSpec() != null) {
            return "redirect:/testPage";
        }
        return "/createMSPage";
    }
}
