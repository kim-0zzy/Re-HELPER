package Practice.ReHELPER.Service.Impl;

import Practice.ReHELPER.DTO.MemberDTO;
import Practice.ReHELPER.Entity.Member;
import Practice.ReHELPER.Exception.PasswordException;
import Practice.ReHELPER.Repository.MemberRepository;
import Practice.ReHELPER.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void signIn(Member member) {
        memberRepository.save(member);
    }

    @Override
    public String updatePassword(Member member, String tryPassword, String changePassword) throws PasswordException {
        if (!passwordEncoder.matches(tryPassword, member.getPassword())) {
            throw new PasswordException("Password is not Matched ! ");
        }
        member.setPassword(changePassword);
        return member.getUsername() + "'s Password Change Success";
    }

    @Override
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public List<Member> findAllByRealName(String realName) {
        return memberRepository.findByRealName(realName);
    }

    @Override
    public Optional<Member> findOneById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    @Override
    public MemberDTO buildMemberDTO(Member member) {
        return MemberDTO.builder()
                .username(member.getUsername())
                .realName(member.getRealName())
                .role(member.getRole())
                .build();
    }

}
