package Practice.ReHELPER.Service.Impl;

import Practice.ReHELPER.DTO.MemberDTO;
import Practice.ReHELPER.Entity.Member;
import Practice.ReHELPER.Exception.NotFoundResultException;
import Practice.ReHELPER.Exception.PasswordException;
import Practice.ReHELPER.Repository.MemberRepository;
import Practice.ReHELPER.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<MemberDTO> findAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(this::buildMemberDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberDTO> findAllByNickName(String nickName) {
        return memberRepository.findByNickName(nickName)
                .stream()
                .map(this::buildMemberDTO)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "memberDTO", key = "#id")
    @Override
    public MemberDTO findOneById(Long id) throws NotFoundResultException {
        Optional<Member> optionalMember = memberRepository.findById(id);
        return optionalMember.map(this::buildMemberDTO).orElseThrow(() -> new NotFoundResultException("Member is not Founded"));
    }

    @Override
    public MemberDTO findByUsername(String username) throws NotFoundResultException {
        Optional<Member> optionalMember = memberRepository.findByUsername(username);
        return optionalMember.map(this::buildMemberDTO).orElseThrow(() -> new NotFoundResultException("Member is not Founded"));
    }

    @Override
    public MemberDTO buildMemberDTO(Member member) {
        return MemberDTO.builder()
                .id(member.getId())
                .username(member.getUsername())
                .nickName(member.getNickName())
                .role(member.getRole())
                .address(member.getAddress())
                .build();
    }

    @Override
    public Boolean validToNotDuplicatedMember(String username) {
        return memberRepository.findByUsername(username).isPresent();
    }

    @Override
    public Member loggedMember(String username) {
        System.out.println("username = " + username);
        return memberRepository.findByUsername(username).get();
    }

}
