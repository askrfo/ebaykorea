package examples.boot.myboard.config;

import examples.boot.myboard.security.MemberLoginInfoArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

// WebMvcConfigurer 인터페이스를 구현하는 클래스는
// 웹과 관련된 기능을 확장할 수 있다.
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new MemberLoginInfoArgumentResolver());
    }
}
