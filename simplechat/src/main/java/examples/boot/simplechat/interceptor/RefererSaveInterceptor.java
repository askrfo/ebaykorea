package examples.boot.simplechat.interceptor;

import examples.boot.simplechat.domain.User;
import examples.boot.simplechat.security.LoginUserInfo;
import examples.boot.simplechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RefererSaveInterceptor extends HandlerInterceptorAdapter {
    @Autowired(required = false)
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.getPrincipal() instanceof LoginUserInfo){
            LoginUserInfo loginUserInfo = (LoginUserInfo)authentication.getPrincipal();
            User user = new User();
            user.setId(loginUserInfo.getId());
            user.setName(loginUserInfo.getName());
            user.setEmail(loginUserInfo.getEmail());
            request.setAttribute("loginUser", user);
        }

        // referer 주소를 설정한다.
        String referer = request.getHeader("referer");
        String uri = request.getRequestURI();
        if("/users/login".equals(uri)){
            uri = referer;
        }
        request.setAttribute("loginRedirect", uri);
        return true;
    }
}
