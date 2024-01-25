package Practice.ReHELPER.Security.service;

import Practice.ReHELPER.Entity.Member;
import Practice.ReHELPER.Repository.MemberRepository;
import Practice.ReHELPER.Security.ManagerContext;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Manager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("UserName Is NotFound"));
        return ManagerContext.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .roles(member.getRole())
                .build();
    }
}
