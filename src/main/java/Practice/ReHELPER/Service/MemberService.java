package Practice.ReHELPER.Service;

import Practice.ReHELPER.DTO.MemberDTO;
import Practice.ReHELPER.Entity.Member;
import Practice.ReHELPER.Exception.PasswordException;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    void signIn(Member member);
    String updatePassword(Member member, String tryPassword, String changePassword) throws PasswordException;
    List<Member> findAllMembers();
    List<Member> findAllByNickName(String nickName);
    Optional<Member> findOneById(Long id);
    Optional<Member> findByUsername(String username);
    MemberDTO buildMemberDTO(Member member);
    Boolean validToNotDuplicatedMember(String username);

}
