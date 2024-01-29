package Practice.ReHELPER.Repository;

import Practice.ReHELPER.Entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    void save(Member member);
    Optional<Member> findById(Long id);
    List<Member> findAll();
    List<Member> findByNickName(String nickName);
    Optional<Member> findByUsername(String username);
}
