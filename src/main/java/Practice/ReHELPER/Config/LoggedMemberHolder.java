package Practice.ReHELPER.Config;

import Practice.ReHELPER.Entity.Member;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class LoggedMemberHolder {
    private final ConcurrentHashMap<String, Member> loggedMember = new ConcurrentHashMap<>();
    public ConcurrentHashMap<String, Member> getLoggedMember() {
        return loggedMember;
    }
}
