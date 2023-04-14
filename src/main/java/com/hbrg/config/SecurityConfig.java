package com.hbrg.config;

import com.hbrg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.csrf.LazyCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.formLogin()
                .loginPage("/user/login")
                .defaultSuccessUrl("/")
                .usernameParameter("id")
                .failureUrl("/user/login/error")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/")
                ;

        http.authorizeRequests()
                .mvcMatchers("/", "/user/**", "images/**").permitAll()
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                ;

        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                ;

        //Spring Security의 CSRF(Cross-Site Request Forgery) 토큰 저장소 구현에서 이 문제가 발생할 가능성이 있습니다.
        // 이 경우에는 웹 요청에서 CSRF 토큰을 저장하기 위해 Spring Security가 세션을 생성하는데,
        // 이미 요청이 끝났다는 메시지를 받아 에러가 발생합니다.
        //이 문제를 해결하기 위해 Spring Security의 CSRF 토큰 저장소 구현에서 LazyCsrfTokenRepository를 사용할 수 있습니다.
        // LazyCsrfTokenRepository는 요청이 완료될 때까지 세션을 생성하지 않습니다.
        // 이를 위해, SecurityConfig 클래스에서 다음과 같이 설정해볼 수 있습니다.
        http.csrf().csrfTokenRepository(new LazyCsrfTokenRepository(new HttpSessionCsrfTokenRepository()));
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/css/**", "js/**", "img/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }
}
