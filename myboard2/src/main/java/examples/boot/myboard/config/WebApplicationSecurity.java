package examples.boot.myboard.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebApplicationSecurity
        extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources()
                        .atCommonLocations())
                .requestMatchers(
                        new AntPathRequestMatcher("/**.html"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .logout() // logout을 할 경우 /boards 로 이동한다.
                    .logoutRequestMatcher(
                            new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/boards").and()

                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/members/joinform").permitAll()
                    .antMatchers(HttpMethod.POST,
                            "/members/join").permitAll()
                    .antMatchers("/members/welcome").permitAll()
                    .antMatchers("/members/login").permitAll()
                    .antMatchers("/members/**").hasRole("USER")
                    .antMatchers(HttpMethod.GET,"/boards").permitAll()
                    .antMatchers(HttpMethod.POST,"/boards").hasRole("USER")
                    .antMatchers("/boards/**").hasRole("USER")
                    .antMatchers("/api/**").hasRole("USER")
                    .antMatchers("/h2-console/**").permitAll()
                    .anyRequest().fullyAuthenticated()
                .and().headers().frameOptions().disable() // h2-console
                .and()
                    .csrf().ignoringAntMatchers("/**") // post방식으로 값을 전달할 때 csrf를 무시
                .and()
                    .formLogin() // 사용자 정의 로그인 화면.
                        .loginPage("/members/login") // 사용자가 만드는 경로(로그인폼)
                        .loginProcessingUrl("/members/login") // 사용자가 만드는 경로가 아니다.
                        .usernameParameter("id") // 로그인 폼에서 사용하는 name필드 이름
                        .passwordParameter("password"); // 로그인 폼에서 사용하는 password필드이름

    }
}
