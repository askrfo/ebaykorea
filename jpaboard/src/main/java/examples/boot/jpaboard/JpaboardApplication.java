package examples.boot.jpaboard;

import examples.boot.jpaboard.utils.MyHeaderArgumentResolver;
import examples.boot.jpaboard.utils.MyInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/*
아규먼트 리졸버 작성 순서
1. MyHeaderArgumentResolver 클래스를 작성
2. WebMvcConfigurer를 구현한 java config클래스 작성
3. addArgumentResolvers 메소드 오버라이딩 후 1에서 작성한 클래스 등록
4. controller메소드에서 MyHeader를 파라미터로 적어주면 자동으로 객체가 주입된다.
 */
@SpringBootApplication
public class JpaboardApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(JpaboardApplication.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MyInterceptor());
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new MyHeaderArgumentResolver());
	}
}
