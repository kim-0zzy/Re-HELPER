package Practice.ReHELPER.Service;

import Practice.ReHELPER.DTO.MemberDTO;
import Practice.ReHELPER.Entity.Member;
import Practice.ReHELPER.Exception.NotFoundResultException;
import Practice.ReHELPER.Exception.PasswordException;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    void signIn(Member member);
    String updatePassword(Member member, String tryPassword, String changePassword) throws PasswordException;
    List<MemberDTO> findAllMembers();
    List<MemberDTO> findAllByNickName(String nickName);
    MemberDTO findOneById(Long id) throws NotFoundResultException;
    MemberDTO findByUsername(String username) throws NotFoundResultException;
    MemberDTO buildMemberDTO(Member member);
    Boolean validToNotDuplicatedMember(String username);

}
