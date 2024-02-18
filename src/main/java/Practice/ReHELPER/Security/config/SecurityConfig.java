package Practice.ReHELPER.Security.config;

import Practice.ReHELPER.Config.LoggedMemberHolder;
import Practice.ReHELPER.Security.handler.CustomAuthenticationFailureHandler;
import Practice.ReHELPER.Security.handler.CustomAuthenticationSuccessHandler;
import Practice.ReHELPER.Security.provider.CustomAuthenticationProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final LoggedMemberHolder loggedMemberHolder;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }
    @Bean
    public AuthenticationSuccessHandler successHandler(){
        return new CustomAuthenticationSuccessHandler();
    }
    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());

    }
    @Bean
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }


//    https://velog.io/@woosim34/Spring-Security-6.1.0%EC%97%90%EC%84%9C-is-deprecated-and-marked-for-removal-%EC%98%A4%EB%A5%98
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/**").permitAll()
//                    .requestMatchers("/member/**").hasRole("MANAGER")
                    .anyRequest().authenticated());

        httpSecurity.formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .defaultSuccessUrl("/main")
                        .successHandler(successHandler())
                        .failureHandler(failureHandler())
                        .permitAll()
                ).logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        loggedMemberHolder.getLoggedMember().remove(authentication.getName());
                    }
                })
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"));

//        httpSecurity.sessionManagement(management -> management
//                                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//                        );

        return httpSecurity.build();
    }
}
