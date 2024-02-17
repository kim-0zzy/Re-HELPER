package Practice.ReHELPER.Security.provider;

import Practice.ReHELPER.Security.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    public UserDetailsService userDetailsService;
    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String memberName = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserDetails userDetails = userDetailsService.loadUserByUsername(memberName);
        MemberContext memberContext = new MemberContext(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

        if (!matchPassword(password, memberContext.getPassword())) {
            throw new BadCredentialsException(memberName + "'s password is not matched !");
        }
        if (!memberContext.isEnabled()) {
            throw new LockedException(memberName + "'s Account is Locked !");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(memberName, password, memberContext.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
        return new UsernamePasswordAuthenticationToken(memberName, password, memberContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public boolean matchPassword(String tryPassword, String savedPassword){
        return passwordEncoder.matches(tryPassword, savedPassword);
    }
}
