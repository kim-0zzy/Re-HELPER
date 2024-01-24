package Practice.ReHELPER.Service;

import Practice.ReHELPER.Entity.Member;
import Practice.ReHELPER.Exception.NotFoundIdException;
import Practice.ReHELPER.Exception.PasswordException;

import java.util.List;

public interface MemberService {
    public Long join(Member member);
    public Long login(Member member, String passwd) throws PasswordException, NotFoundIdException;
    public List<Member> findMembers(Member member);
    public Member findOneById(Long id);
    public List<Member> findOneByRealName(String realName);
    public Member findByUsername(String username);

}
