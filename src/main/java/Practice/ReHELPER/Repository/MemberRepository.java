package Practice.ReHELPER.Repository;

import Practice.ReHELPER.Entity.Member;

import java.util.List;

public interface MemberRepository {

    void save(Member member);
    List<Member> findAll();
    List findByRealName(String realName);
    Member findByUsername(String username);
}
